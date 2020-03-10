package combat.melee;

/**
 * Establishes all DefenseTactics used by melee combat.
 */
public enum DefenseTactic {
    EVADE(.1, 1.0),
    DEFLECT(.25, 0.9),
    RIPOSTE(1.0, 1.0),
    BRACE(0.0, 0.6),
    IGNORE(0.0, 1.1);

    double counterChance;
    double damageAdjustment;

    DefenseTactic(double counter, double damageAdjust){
        counterChance = counter;
        damageAdjustment = damageAdjust;
    }
}
