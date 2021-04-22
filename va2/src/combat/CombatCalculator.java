package combat;

public class CombatCalculator {

    private static final int SUM_TABLE_CAPACITY = 1024;
    private static int sumTableHighValue = -1;
    private static final int[] sumTable = new int[SUM_TABLE_CAPACITY];

    private static int sum(int ordinal) {
        if (ordinal < 1) //lower bound
            throw new IllegalArgumentException("Argument must be positive (was " + ordinal + ")");
        if (ordinal >= SUM_TABLE_CAPACITY) //upper bound
            throw new IllegalArgumentException("Argument (" + ordinal + ") exceeds limit of " + SUM_TABLE_CAPACITY);
        if (sumTableHighValue < 0) { //initialize
            sumTable[0] = 0;
            sumTableHighValue = 1;
        }
        if (sumTableHighValue < ordinal) { //fill to required value
            for (int i = sumTableHighValue; i < ordinal + 1; ++i) {
                sumTable[i] = sumTable[i - 1] + i;
            }
            sumTableHighValue = ordinal;
        }
        return sumTable[ordinal];
    }

    public static double baseHitChance(double atRange) {
        int range = (int)Math.ceil(atRange);
        double missChance = sum(range);
        return Math.min(100.0 - missChance, 0.0);
    }
}
