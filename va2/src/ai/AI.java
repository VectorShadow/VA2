package ai;

import engine.action.Action;
import main.extensible.Saveable;
import world.actor.Actor;

/**
 * The base AI class. Given an actor, an AI should be able to queue an action or series of actions.
 */
public abstract class AI extends Saveable {
    public abstract Action decide(Actor a);
}
