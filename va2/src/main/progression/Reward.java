package main.progression;

import main.extensible.Saveable;

public class Reward extends Saveable {
    private final int REWARD_LEVEL;
    private final long REWARD_EXPERIENCE;
    //todo - items, etc.

    public Reward(int level, long xp) {
        REWARD_LEVEL = level;
        REWARD_EXPERIENCE = xp;
    }

    public long evaluateExperience(int playerLevel) {
        double difference = playerLevel - REWARD_LEVEL;
        double multiplier = 1.0 * (
                (difference < 0)
                        ? Math.min(Math.pow(1.04, (0 - difference)), 2.5)
                        : (difference > 0)
                        ? Math.max(Math.pow(0.96, difference), 0.0025)
                        : 1.0
        );
        return (long)(Math.max((double)REWARD_EXPERIENCE * multiplier, 1));
    }

    public static final void testScaling() {
        Reward r = new Reward(32, 1_024);
        for (int i = 0; i < 64; ++i) {
            System.out.println("Player level: " + i + " XP Awarded: " + r.evaluateExperience(i) + " / " + Experience.XP_TABLE[i]);
        }
    }
}
