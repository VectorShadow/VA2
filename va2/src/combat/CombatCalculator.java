package combat;

import world.actor.Actor;

public class CombatCalculator {

    private static final int SUM_TABLE_CAPACITY = 1024;
    private static int[] sumTable = null;

    private static int sum(int ordinal) {
        if (ordinal >= SUM_TABLE_CAPACITY)
            throw new IllegalArgumentException("Argument (" + ordinal + ") exceeds limit of " + SUM_TABLE_CAPACITY);
        if (sumTable == null) {
            sumTable = new int[SUM_TABLE_CAPACITY];
            sumTable[0] = 0;
            for (int i = 1; i < SUM_TABLE_CAPACITY; ++i) {
                sumTable[i] = sumTable[i - 1] + i;
            }
        }
        return sumTable[ordinal];
    }

    public static double baseHitChance(Actor attacker, Actor target) {
        int range = (int)Math.ceil(attacker.getLocation().distanceTo(target.getLocation()));
        double missChance = sum(range);
        return Math.min(100.0 - missChance, 0.0);
    }
}
