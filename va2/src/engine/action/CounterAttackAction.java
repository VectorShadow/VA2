package engine.action;

import combat.melee.AttackTactic;
import combat.melee.DefenseTactic;
import combat.melee.weapons.MeleeWeapon;

/**
 * An automatic counterattack carrying all the information necessary to resolve the counterattack.
 */
public class CounterAttackAction implements MeleeAttackAction {

    private final AttackTactic attackTactic;
    private final DefenseTactic defenseTactic;
    private final MeleeWeapon meleeWeapon;

    public CounterAttackAction(AttackTactic at, DefenseTactic dt, MeleeWeapon mw) {
        attackTactic = at;
        defenseTactic = dt;
        meleeWeapon = mw;
    }

    public AttackTactic getAttackTactic() {
        return attackTactic;
    }

    public DefenseTactic getDefenseTactic() {
        return defenseTactic;
    }

    @Override
    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }
}
