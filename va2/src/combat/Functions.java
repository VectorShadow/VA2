package combat;

import main.Session;

/**
 * Combat functions.
 */
public class Functions {
    /**
     * Opposition roll.
     * @param first adjusted value for the first actor.
     * @param second adjusted value for the second actor.
     * @return whether the first actor has won the roll.
     */
    public static boolean oppose(int first, int second) {
        if (first <= 0) //first actor automatically loses
            return false;
        else if (second <= 0) //second actor automatically loses
            return true;
        int scale1 = first / 8;
        int scale2 = second / 8;
        int roll1 = Session.getRNG().nextInt(first) + 1;
        int roll2 = Session.getRNG().nextInt(second) + 1;
        return ((scale1 + roll1) - (scale2 + roll2)) >= 0;
    }

    /**
     * Threshold roll.
     * @param first adjusted value for the first actor.
     * @param second adjusted value for the second actor.
     * @return the threshold the first actor must achieve for some outcome.
     */
    public static double setThreshold(int first, int second) {
        if (first <= 0) //first actor automatically loses - threshold is unattainable
            return 1.0;
        else if (second <= 0) //second actor automatically loses - threshold has its minimum value
            return 0.0;
        int scale1 = first / 8;
        int scale2 = second / 8;
        int roll1 = Session.getRNG().nextInt(first) + 1;
        int roll2 = Session.getRNG().nextInt(second) + 1;
        double result = ((double)(scale2 + roll2) / (double)(scale1 + roll1));
        return result > 1.0 ? 1.0 : result < 0.0 ? 0.0 : result;
    }
}
