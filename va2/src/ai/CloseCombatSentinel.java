package ai;

public class CloseCombatSentinel extends CloseCombatAI {
    @Override
    public AI getSecondaryAI() {
        return AIDefinitions.SLEEPING_AI;
    }
}
