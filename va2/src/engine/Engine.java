package engine;

import combat.melee.MeleeResolver;
import engine.action.*;
import io.out.message.Message;
import io.out.message.MessageCenter;
import main.extensible.Saveable;
import status.StatusEffect;
import status.StatusType;
import world.dungeon.floor.Floor;
import world.dungeon.floor.FloorTile;
import main.Session;
import util.Coordinate;
import world.actor.Actor;
import world.terrain.TerrainTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import static status.StatusType.*;
import static io.out.message.MessageType.*;
import static io.out.message.MessageCenter.*;

/**
 * This class is responsible for updating the game world based on player input.
 */
public class Engine extends Saveable {
    private static final long STATUS_INTERVAL = 32;
    private static final long SAVE_INTERVAL = 8_192;

    private long gameTurn = 0;
    private LinkedList<Actor> actors;
    private ArrayList<Actor> deadActors;

    public Engine() {
        resetActors();
    }

    public void resetActors() {
        actors = new LinkedList<>();
        deadActors = new ArrayList<>();
    }
    public void addActor(Actor a) {
        addActor(a, false);
    }
    public void addActor(Actor a, boolean isPlayer) {
        if (isPlayer)
            actors.addLast(a);
        else
            actors.addFirst(a);
    }
    public LinkedList<Actor> listActors() {
        return actors;
    }
    public void removeActor(Actor a) {
        deadActors.add(a);
    }
    public void execute(Action playerAction) {
        Actor player = Session.getPlayer().getActor();
        if (player != actors.getLast())
            throw new IllegalStateException("Player out of order in engine actor list.");
        actors.getLast().queueAction(playerAction);
        Actor actor;
        Action action;
        int energyCost;
        while(true) {
            for (Actor a : deadActors) //cleanup dead actors
                actors.remove(a);
            deadActors = new ArrayList<>();
            for (Iterator<Actor> i = actors.iterator(); i.hasNext();) {
                if (deadActors.contains(Session.getPlayer().getActor()))
                    return; //immediately terminate engine handling if the player has died.
                actor = i.next();
                if (gameTurn % STATUS_INTERVAL == 0) //apply all relevant status checks before the death check, in case they kill the actor.
                    handleStatus(actor);
                if (deadActors.contains(actor)) //ignore dead actors
                    continue;
                //ensure player action messages are most recent at the end of each engine cycle
                if (!player.hasQueuedAction()) return;
                actor.gainEnergy(); //gain this turn's energy
                if (!actor.hasEnoughEnergy(ActionDefinitions.MAXIMUM_ACTION_ENERGY)) //wait till we can take any action
                    continue;
                if (!actor.hasQueuedAction()) {
                    if (actor == player) return; //if the player has no queued action, we are done
                    actor.plan(); //else use the plan method to invoke the AI and queue an action
                }
                action = actor.checkQueuedAction();
                while (!validate(actor, action)) {
                    //make sure a change in actors at our destination isn't the problem
                    if (action instanceof DirectedAction) {
                        DirectedAction da = transform(actor, (DirectedAction)action);
                        if (validate(actor, da)) {
                            action = da; //if the other type of directed action is valid, reassign and break
                            break;
                        }
                    }
                    //if this doesn't fix the problem, crash and attempt to debug:
                    //throw new IllegalStateException("Unable to validate action " + action + " for actor " + actor);
                    //otherwise, attempt to resolve as follows:
                    actor.clearQueuedActions();
                    if (actor == player) return; //if the player has no queued action, we are done
                    actor.plan(); //else use the plan method to invoke the AI and queue an action
                    action = actor.checkQueuedAction();
                }
                energyCost = action.getEnergyCost();
                if (actor.hasEnoughEnergy(energyCost)) {
                    actor.removeQueuedAction();
                    actor.consumeEnergy(energyCost);
                    handle(actor, action);
                }
            }
            //increment the game turn and automatically save every so often
            if (++gameTurn % SAVE_INTERVAL == 0) Session.getFileManager().saveGameState();
        }
    }
    public boolean validate(Actor actor, Action action) {
        Floor f = Session.getCurrentFloor();
        FloorTile ft;
        Coordinate origin= actor.getLocation();
        Coordinate destination;
        int origRow = origin.getRow();
        int origCol = origin.getColumn();
        int destRow, destCol;
        if (action instanceof PauseAction) {
          return true;
        } else if (action instanceof AdjacentMovementAction) {
            AdjacentMovementAction ama = (AdjacentMovementAction)action;
            destination = ama.getDirection().shift(actor.getLocation());
            destRow = destination.getRow();
            destCol = destination.getColumn();
            ft = f.tileAt(destRow, destCol);
            return f.inFloor(destRow, destCol) && ft.getActor() == null &&
                    ((TerrainTemplate)ft.getTerrain().getTemplate()).permitsMovement();
        } else if (action instanceof AdjacentAttackAction) {
            AdjacentAttackAction aaa = (AdjacentAttackAction)action;
            destination = aaa.getDirection().shift(actor.getLocation());
            destRow = destination.getRow();
            destCol = destination.getColumn();
            ft = f.tileAt(destRow, destCol);
            return f.inFloor(destRow, destCol) && ft.getActor() != null; //todo - ensure target actor is hostile
        }
        //todo - other cases
        return false;
    }

    /**
     * validate() is always called before handle() otherwise undefined behavior can occur
     */
    private void handle(Actor actor, Action action) {
        Floor f = Session.getCurrentFloor();
        Coordinate origin= actor.getLocation();
        Coordinate destination;
        int origRow = origin.getRow();
        int origCol = origin.getColumn();
        int destRow, destCol;
        Actor defender;
        if (action instanceof AdjacentMovementAction) {
            AdjacentMovementAction ama = (AdjacentMovementAction) action;
            destination = ama.getDirection().shift(actor.getLocation());
            f.placeActor(actor, destination);
        } else if (action instanceof AdjacentAttackAction) {
            AdjacentAttackAction aaa = (AdjacentAttackAction) action;
            destination = aaa.getDirection().shift(actor.getLocation());
            destRow = destination.getRow();
            destCol = destination.getColumn();
            defender = f.tileAt(destRow, destCol).getActor();
            Message playerMessage = MeleeResolver.resolve(actor, aaa, defender);
            if (playerMessage != null)
                Session.getMessageCenter().sendMessage(playerMessage, MessageCenter.PRIORITY_MAX);
        }
        //todo - else {} all other cases
    }
    /**
     * transform an adjacent attack to an adjacent move or vice versa
     */
    private DirectedAction transform(Actor actor, DirectedAction da) {
        Floor f = Session.getCurrentFloor();
        Coordinate destination = da.getDirection().shift(actor.getLocation());
        int destRow = destination.getRow();
        int destCol = destination.getColumn();
        FloorTile ft = f.tileAt(destRow, destCol);
        if (da instanceof AdjacentMovementAction && ft.getActor() != null) //if we try to move but there is an actor
            return new AdjacentAttackAction(
                    da.getDirection(),
                    actor.getAttackEnergyMultiplier(),
                    actor.getCombatant().selectMeleeWeapon()
            );
        if (da instanceof AdjacentAttackAction && ft.getActor() == null) //if we try to attack but there is no actor
            return new AdjacentMovementAction(da.getDirection(), actor.getMoveEnergyMultiplier());
        return da; //no problems were encountered
    }
    private void handleStatus(Actor a) {
        boolean isPlayer = a == Session.getPlayer().getActor();
        MessageCenter mc = Session.getMessageCenter();
        StatusType st;
        for (StatusEffect se : a.getStatusEffects()) {
            st = se.getType();
            if (st.affectsEngineInterval() && se.consume()) {
                if (isPlayer)
                    mc.sendMessage(st.CHECK_MESSAGE, st.isPositive() ? SUCCESS : WARNING, PRIORITY_LOW);
                if (!a.getCombatant().adjustHealth(st.AFFECTS[DAMAGE] ? -st.FLAT : st.FLAT))
                    Session.killActor(a);
                //todo - Sanity and Soul checks.
            }
        }
    }

    public long getGameTurn() {
        return gameTurn;
    }
}
