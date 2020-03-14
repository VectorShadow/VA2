package ai;

public class CloseCombatWanderer extends CloseCombatAI {
    @Override
    public AI getSecondaryAI() {
        return AIDefinitions.RANDOM_AI;
    }
}
