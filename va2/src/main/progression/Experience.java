package main.progression;

import main.extensible.Saveable;

public class Experience extends Saveable {
    private static final long LEVEL_ONE = 64;
    private static final int MAX_LEVEL = 127;
    private static long[] XP_TABLE = new long[MAX_LEVEL + 1];

    private long exp = 0;
    private int level = 0;
    private int points = 0;

    public int getLevel() {
        return level;
    }

    public long getExp() {
        return exp;
    }

    public int getPoints() {
        return points;
    }

    public void gainXP(long xp) {
        if (level >= MAX_LEVEL) return; //stop XP gain after reaching maximum level
        exp += xp;
        int levelNow = calculateLevel();
        while (levelNow > level) levelUp();
    }
    private void levelUp(){
        ++level;
        ++points;
        if (level % 8 == 0) ++points;
        if (level % 32 == 0) ++points;
    }
    private boolean spendPoints(int pointCost) {
        if (points > pointCost) return false;
        points -= pointCost;
        return true;
    }
    private int calculateLevel() {
        for (int i = 0;;++i) {
            if (XP_TABLE[i] > exp) return i - 1;
        }
    }
    private static double slidingScale(int level){
        return 1.0 + (1.0 / (Math.sqrt(Math.E) * Math.log(level)));
    }
    public static void fillXPTable() {
        XP_TABLE[0] = 0;
        XP_TABLE[1] = LEVEL_ONE;
        for (int i = 2; i < XP_TABLE.length; ++i) {
            XP_TABLE[i] = (long)((double)XP_TABLE[i-1] * slidingScale(i));
        }
    }
    public static long calculateXP(int level) {
        return XP_TABLE[level];
    }
}
