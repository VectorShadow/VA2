package main.progression;

import main.Session;
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
        //the deeper the floor in a given dungeon, the better the experience todo - loot rewards should use this too
        double difference = playerLevel - (REWARD_LEVEL + Session.getCurrentFloor().DEPTH);
        double multiplier = 1.0 * Math.pow(0.92, difference);
        return (long)((double)REWARD_EXPERIENCE * multiplier);
    }

    public static void testScaling() {
        Reward r = new Reward(25, 128);
        for (int i = 0; i < 128; ++i) {
            System.out.println("Player level: " + i + " XP Awarded: " + r.evaluateExperience(i) + " / " + Experience.calculateXP(i));
        }
    }
}
