package engine;

import world.actor.Actor;

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
        //todo - automatically add the player when we have the means to do so
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
                action = actor.getQueuedAction();
                //todo - ensure the queued action is still valid - if not, ask the AI for a new valid action
                //todo - if the player action is invalid, wipe the player's action queue and return
                //todo - use similar check as above:
                // if (!action.isValid()){
                //  //wipe queue
                //  if (i == 0) return;
                //  //ask AI to queue new action
                //  action = actor.getQueuedAction();
                // }
                energyCost = action.getEnergyCost();
                if (actor.hasEnoughEnergy(energyCost)) {
                    actor.consumeEnergy(energyCost);
                    handle(actor, action);
                }
            }
            ++gameTurn;
        }
    }
    private void handle(Actor actor, Action action) {
        //todo
    }
}
