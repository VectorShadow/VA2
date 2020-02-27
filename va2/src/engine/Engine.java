package engine;

import engine.action.Action;
import engine.action.AdjacentMovementAction;
import floor.Floor;
import floor.FloorTile;
import main.Player;
import main.Session;
import util.Coordinate;
import world.actor.Actor;
import world.terrain.TerrainTemplate;

import java.util.ArrayList;

/**
 * This class is responsible for updating the game world based on player input.
 */
public class Engine {

    public static final int MAXIMUM_ENERGY = 1024;

    private long gameTurn = 0;
    private ArrayList<Actor> actors;

    public Engine() {
        resetActors();
    }

    public void resetActors() {
        actors = new ArrayList<>();
        Player player = Session.getPlayer();
        if (player != null)
            actors.add(Session.getPlayer().getActor());
    }

    /**
     * Use this when loading a saved game.
     */
    public void loadGameTurn(long turn) {
        gameTurn = turn;
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
                    //else use the AI to queue an action
                }
                action = actor.checkQueuedAction();
                if (!validate(actor, action)) {
                    actor.clearQueuedActions();
                    if (i == 0) return;
                    //todo - ask AI to queue a new action
                    action = actor.checkQueuedAction();
                }
                energyCost = action.getEnergyCost();
                if (actor.hasEnoughEnergy(energyCost)) {
                    actor.removeQueuedAction();
                    actor.consumeEnergy(energyCost);
                    handle(actor, action);
                }
            }
            ++gameTurn;
        }
    }
    private boolean validate(Actor actor, Action action) {
        Floor f = Session.getCurrentFloor();
        FloorTile ft;
        Coordinate origin= actor.getLocation();
        Coordinate destination;
        int origRow = origin.getRow();
        int origCol = origin.getColumn();
        int destRow, destCol;
        if (action instanceof AdjacentMovementAction) {
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
}
