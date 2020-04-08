package main.progression.rewards;

import main.extensible.Saveable;

public class Experience extends Saveable {
    private static final long LEVEL_ONE = 64;
    private static final int MAX_LEVEL = 255;
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
        return 1.0 +
                (
                        (
                                (41.0 / 64.0) + //set a base rate
                                        ((double)(level / 0x0f) / 32.0) + //accelerate rate of increase every 16 levels
                                        ((double)(level / 0x50) / 16.0) + //and ramp up the acceleration after level 80
                                        ((double)(level / 0xa0) / 4.0) + //and again after level level 160
                                        ((double)(level / 0xf0) / 0.5) //and finally once more after level 240
                        ) /
                                (Math.sqrt(Math.E) * Math.log(level)) //apply a logarithmic scale of increase
                );
    }
    public static void fillXPTable() {
        XP_TABLE[0] = 0;
        XP_TABLE[1] = LEVEL_ONE;
        for (int i = 2; i < XP_TABLE.length; ++i) {
            XP_TABLE[i] = (long)((double)XP_TABLE[i-1] * slidingScale(i));
            System.out.println(i + ": " + XP_TABLE[i]);
        }
    }
    public static long calculateXP(int level) {
        return XP_TABLE[level];
    }
}
