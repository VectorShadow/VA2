package combat.melee.forms;

import combat.melee.*;
import combat.melee.weapons.MeleeStyle;
import combat.melee.weapons.ResolvableMeleeWeapon;
import combat.melee.weapons.MeleeWeaponClass;
import main.Session;
import main.extensible.TextDisplayable;
import resources.continuum.Continuum;

import java.awt.*;

/**
 * A melee combat form for a specific Style and ResolvableMeleeWeapon Class, specifying Tactics Continua and special abilities.
 */
public class Form extends TextDisplayable {
    private final MeleeStyle meleeStyle;
    private final MeleeWeaponClass meleeWeaponClass;
    private final Continuum<AttackTactic> attackTactics;
    private final Continuum<DefenseTactic> defenseTactics;
    private final int[] statModifiers;

    //todo - special abilities

    public Form(
            String d,
            String n,
            Color nc,
            MeleeStyle ms,
            MeleeWeaponClass wc,
            Continuum<AttackTactic> at,
            Continuum<DefenseTactic> dt,
            int[] adjAccEvaPreDefStr
    ) {
        super(d, n, nc);
        meleeStyle = ms;
        meleeWeaponClass = wc;
        attackTactics = at;
        defenseTactics = dt;
        if (adjAccEvaPreDefStr.length != 5)
            throw new IllegalStateException("Malformed statModification array");
        statModifiers = adjAccEvaPreDefStr;
    }

    /**
     * Use this to browse a list of known Forms and return only those matching the provided style and class.
     * Note that if a form does not specify a style or class, it can be used with any style or class.
     */
    public boolean applicable(ResolvableMeleeWeapon mw) {
        return (meleeStyle == null || meleeStyle == mw.getMeleeStyle()) &&
                (meleeWeaponClass == null || meleeWeaponClass == mw.getMeleeWeaponClass());
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

    public int adjustStatistic(int combatantStatisticIndex) {
        return statModifiers[combatantStatisticIndex - 2];
    }
}
