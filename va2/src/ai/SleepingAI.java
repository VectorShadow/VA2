package ai;

import engine.action.Action;
import engine.action.PauseAction;
import world.actor.Actor;

/**
 * SleepingAI simply waits.
 */
public class SleepingAI extends AI {
    @Override
    public Action decide(Actor a) {
        return new PauseAction();
    }
}
