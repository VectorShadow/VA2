package combat.melee;

/**
 * Establishes all AttackTactics used by melee combat.
 */
public enum AttackTactic {
    PROBE(0.75, 0.0, 0.4, "probe$"),
    FEINT(0.9, 0.5, 0.75, "feint$ toward"),
    STRIKE(1.0, 1.0, 1.0, "strike$ at"),
    BLOW(0.85, 1.25, 1.25, "aim$ a heavy blow at"),
    ANTICIPATE(1.25, 0.75, 1.0, "attempt$ to anticipate");

    double baseAccuracyMultiplier;
    double basePrecisionMultiplier;
    double baseDamageMultiplier;
    String message;

    AttackTactic(double acc, double pre, double dam, String msg) {
        baseAccuracyMultiplier = acc;
        basePrecisionMultiplier = pre;
        baseDamageMultiplier = dam;
        message = msg;
    }
}
