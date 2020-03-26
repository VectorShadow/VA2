package ai;

import engine.action.Action;
import main.Session;
import world.actor.Actor;

public class CloseCombatErraticAI extends CloseCombatAI  {

    private final double ECCENTRICITY;

    public CloseCombatErraticAI(double eccentricity) {
        ECCENTRICITY = eccentricity;
    }

    @Override
    public AI getSecondaryAI() {
        return AIDefinitions.RANDOM_AI;
    }

    @Override
    public Action decide(Actor a) {
        if (Session.getRNG().nextDouble() < ECCENTRICITY)
            return AIDefinitions.RANDOM_AI.decide(a);
        else
            return super.decide(a);
    }
}
