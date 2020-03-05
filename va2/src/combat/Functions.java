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
        int scale1 = first / 8;
        int scale2 = second / 8;
        int roll1 = Session.getRNG().nextInt(first) + 1;
        int roll2 = Session.getRNG().nextInt(second) + 1;
        return ((scale1 + roll1) - (scale2 + roll2)) >= 0;
    }
}
