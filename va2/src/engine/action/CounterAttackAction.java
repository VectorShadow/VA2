package engine.action;

import combat.melee.AttackTactic;
import combat.melee.DefenseTactic;
import combat.melee.weapons.ResolvableMeleeWeapon;

/**
 * An automatic counterattack carrying all the information necessary to resolve the counterattack.
 */
public class CounterAttackAction implements MeleeAttackAction {

    private final AttackTactic attackTactic;
    private final DefenseTactic defenseTactic;
    private final ResolvableMeleeWeapon resolvableMeleeWeapon;

    public CounterAttackAction(AttackTactic at, DefenseTactic dt, ResolvableMeleeWeapon mw) {
        attackTactic = at;
        defenseTactic = dt;
        resolvableMeleeWeapon = mw;
    }

    public AttackTactic getAttackTactic() {
        return attackTactic;
    }

    public DefenseTactic getDefenseTactic() {
        return defenseTactic;
    }

    @Override
    public ResolvableMeleeWeapon getResolvableMeleeWeapon() {
        return resolvableMeleeWeapon;
    }
}
