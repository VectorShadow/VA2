package util;

/**
 * This class is designed to simplify input functions in constructors - for example, instead of determining a specific
 * double by which to multiply, we simply provide a plus or minus which is clearer to understand.
 */
public class InputSimplifier {
    /**
     * Take an integer value and return a double which corresponds to it.
     * In general, each unit above or below zero takes the double 1/8th of its current value
     * farther from 1.0 in the appropriate direction, so:
     * +1 => 0.875, +2 => 0.765, +3 => 0.67, +4 => 0.586, etc.
     * -1 => 1.125, -2 => 1.265, -3 => 1.42, -4 => 1.603, etc.
     * with 0 => 1.0
     */
    public static double getMultiplier(int i) {
        int absoluteI = Math.abs(i);
        int unit = i == 0 ? 0 : i / absoluteI;
        return Math.pow((8.0 - unit) / 8.0, absoluteI);
    }
    public static double[] getMultipliers(int... I) {
        double[] D = new double[I.length];
        for (int i = 0; i < I.length; ++i)
            D[i] = getMultiplier(I[i]);
        return D;
    }
    public static void testGetMultiplier() {
        for (int i = -15; i < 16; ++i)
            System.out.println("i: " + i + " = " + getMultiplier(i));
    }

    /**
     * Take an integer specifying a level, and return a statistic value suitable for opposed roles.
     * Start at level 0 corresponding to 16, level 10 to 32, level 20 to 64, and so on, and interpolate between
     * via logarithms.
     * @param level
     * @return
     */
    public static int getValue(int level) {
        double power = 4.0 + (double)level / 10.0;
        return (int)(Math.pow(2.0, power));
    }

    public static int[] getValues(int... L) {
        int[] V = new int[L.length];
        for (int i = 0; i < L.length; ++i)
            V[i] = getValue(L[i]);
        return V;
    }
}
