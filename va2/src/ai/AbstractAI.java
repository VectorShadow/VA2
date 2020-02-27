package ai;

import engine.action.Action;
import world.actor.Actor;

/**
 * The base AI class. Given an actor, an AI should be able to queue an action or series of actions.
 */
public abstract class AbstractAI {
    public abstract Action decide(Actor a);
}
