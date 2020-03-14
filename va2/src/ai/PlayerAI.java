package ai;

import engine.action.Action;
import world.actor.Actor;

/**
 * The player's AI profile. This should take over for the player if the player's input is not valid for gameplay
 * reasons, such as fear, insanity, etc.
 */
public class PlayerAI extends AI {
    @Override
    public Action decide(Actor a) {
        throw new IllegalStateException("PlayerAI has not been implemented to an extent where it is permitted to call decide().");
    }
}
