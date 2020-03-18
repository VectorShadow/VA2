package util;

/**
 * This class is designed to simplify input functions in constructors - for example, instead of determining a specific
 * double by which to multiply, we simply provide a plus or minus which is clearer to understand.
 */
public class InputSimplifier {
    /**
     * Take an integer value and return a double proportional to it.
     * In practice, we have the following:
     * 0 = 1.0
     * +1 => 0.875 // +2 => 0.75 // +3 => 0.625 // +4 => 0.5 // --- // +7 => 0.125 // +8 => 0.0
     * -1 => 1.125 // -2 => 1.25 // -3 => 1.375 // -4 => 1.5 // etc...
     * @param i
     * @return
     */
    public static double getMultiplier(int i) {
        return i > 8 ? 0.0 : (8.0 - (double)i) / 8.0;
    }
    public static double[] getMultipliers(int... I) {
        double[] D = new double[I.length];
        for (int i = 0; i < I.length; ++i)
            D[i] = getMultiplier(I[i]);
        return D;
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
