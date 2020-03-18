package combat.melee;

/**
 * Establishes all DefenseTactics used by melee combat.
 */
public enum DefenseTactic {
    EVADE(.1, 1.25, 0.0, 1.0, 1.0, "attempt$ to evade"),
    DEFLECT(.25, 0.0, 1.5, 1.0, 0.9, "attempt$ to deflect"),
    RIPOSTE(1.0,0.2, 0.4, 0.5,  1.0, "attempt$ to counter"),
    BRACE(0.0, 0.1, 0.1, 2.5, 0.6, "brace$ for"),
    IGNORE(0.0, 0.0, 0.0, 0.25,  1.1, "ignore$");

    double baseCounterChance;
    double baseEvasionMultiplier;
    double baseDeflectionMultiplier;
    double baseDefenseMultiplier;
    double baseDamageMultiplier;
    String message;

    DefenseTactic(double ctr, double eva, double dfl, double dfs, double dam, String msg){
        baseCounterChance = ctr;
        baseEvasionMultiplier = eva;
        baseDeflectionMultiplier = dfl;
        baseDefenseMultiplier = dfs;
        baseDamageMultiplier = dam;
        message = msg;
    }
}
