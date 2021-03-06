package ai;

import engine.action.Action;
import engine.action.AdjacentMovementAction;
import engine.action.PauseAction;
import main.Session;
import util.Direction;
import world.actor.Actor;

/**
 * An AI which moves randomly.
 */
public class RandomAI extends AI {
    @Override
    public Action decide(Actor a) {
        Action action;
        do {
            Direction target = Direction.random(true);
            action = target == Direction.SELF ?
                    new PauseAction() :
                    new AdjacentMovementAction(target, a.getMoveEnergyMultiplier());
        } while(!Session.getEngine().validate(a, action));
        return action;
    }
}
