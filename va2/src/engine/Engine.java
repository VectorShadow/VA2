package engine;

import combat.melee.MeleeResolver;
import engine.action.*;
import io.out.message.Message;
import world.dungeon.floor.Floor;
import world.dungeon.floor.FloorTile;
import main.Session;
import util.Coordinate;
import world.actor.Actor;
import world.terrain.TerrainTemplate;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is responsible for updating the game world based on player input.
 */
public class Engine implements Serializable {

    public static final int MAXIMUM_ENERGY = 1024;

    private long gameTurn = 0;
    private ArrayList<Actor> actors;

    public Engine() {
        resetActors();
    }

    public void resetActors() {
        actors = new ArrayList<>();
    }
    public void addActor(Actor a) {
        addActor(a, false);
    }
    public void addActor(Actor a, boolean isPlayer) {
        if (isPlayer)
            actors.add(0, a);
        else
            actors.add(a);
    }
    public void removeActor(Actor a) {
        actors.remove(a);
    }
    public void execute(Action playerAction) {
        actors.get(0).queueAction(playerAction);
        Actor actor;
        Action action;
        int energyCost;
        while(true) {
            for (int i = 0; i < actors.size(); ++i) {
                actor = actors.get(i);
                actor.gainEnergy(); //gain this turn's energy
                if (!actor.hasQueuedAction()) {
                    if (i == 0) return; //if the player has no queued action, we are done
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
                    if (i == 0) return; //if the player has no queued action, we are done
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
            if (++gameTurn % 1_000 == 0) Session.getFileManager().saveGameState();
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
                Session.getMessageCenter().sendMessage(playerMessage);
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
                    ft.getActor().getCombatant().selectMeleeWeapon()
            );
        if (da instanceof AdjacentAttackAction && ft.getActor() == null) //if we try to attack but there is no actor
            return new AdjacentMovementAction(da.getDirection(), actor.getMoveEnergyMultiplier());
        return da; //no problems were encountered
    }
}
