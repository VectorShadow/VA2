package combat.melee;

import main.Session;
import resources.continuum.Continuum;

/**
 * A melee combat form for a specific Style and Weapon Class, specifying Tactics Continua and special abilities.
 */
public class Form {
    private final MeleeStyle meleeStyle;
    private final WeaponClass weaponClass;
    private final Continuum<AttackTactic> attackTactics;
    private final Continuum<DefenseTactic> defenseTactics;
    //todo - special abilities

    public Form(MeleeStyle ms, WeaponClass wc, Continuum<AttackTactic> at, Continuum<DefenseTactic> dt) {
        meleeStyle = ms;
        weaponClass = wc;
        attackTactics = at;
        defenseTactics = dt;
    }

    /**
     * Use this to browse a list of known Forms and return only those matching the provided style and class.
     */
    public boolean applicable(MeleeStyle ms, WeaponClass wc) {
        return meleeStyle == ms && weaponClass == wc;
    }

    /**
     * Use these to determine if this form permits a given tactic(else the player may not default to it)
     */
    public boolean contains(AttackTactic attackTactic) {
        return attackTactics.contains(attackTactic);
    }
    public boolean contains(DefenseTactic defenseTactic) {
        return defenseTactics.contains(defenseTactic);
    }

    public AttackTactic selectAttackTactic() {
        return attackTactics.getValue(Session.getRNG());
    }
    public DefenseTactic selectDefenseTactic() {
        return defenseTactics.getValue(Session.getRNG());
    }
}
