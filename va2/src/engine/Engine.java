package engine;

import engine.action.*;
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
        actors.add(a);
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
        if (action instanceof AdjacentMovementAction) {
            AdjacentMovementAction ama = (AdjacentMovementAction) action;
            destination = ama.getDirection().shift(actor.getLocation());
            destRow = destination.getRow();
            destCol = destination.getColumn();
            f.placeActor(actor, destination);
        } //todo - else {} all other cases
    }
    /**
     * transform an adjacent attack to an adjacent move or vice versa
     */
    private DirectedAction transform(Actor actor, DirectedAction da) {
        //todo - get proper energy multipliers from actor, and roll damage if necessary
        if (da instanceof AdjacentMovementAction)
            return new AdjacentAttackAction(da.getDirection(), actor.getAttackEnergyMultiplier(), actor.rollDamage());
        if (da instanceof AdjacentAttackAction)
            return new AdjacentMovementAction(da.getDirection(), actor.getMoveEnergyMultiplier());
        throw new IllegalArgumentException("Unknown DirectedAction subclass: " + da.getClass());
    }
}
