package ai;

public class AIDefinitions {
    public static final PlayerAI PLAYER_AI = new PlayerAI();
    public static final RandomAI RANDOM_AI = new RandomAI();
    public static final SleepingAI SLEEPING_AI = new SleepingAI();
    public static final CloseCombatAI CCW_AI = new CloseCombatWanderer();
    public static final CloseCombatAI CCS_AI = new CloseCombatSentinel();
}
