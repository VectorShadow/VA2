package engine.action;

import combat.melee.weapons.ResolvableMeleeWeapon;
import util.Direction;

public class AdjacentAttackAction extends DirectedAction implements MeleeAttackAction {

    private final ResolvableMeleeWeapon resolvableMeleeWeapon;

    public AdjacentAttackAction(Direction d, double energyMultiplier, ResolvableMeleeWeapon mw) {
        super(d, energyMultiplier);
        resolvableMeleeWeapon = mw;
    }

    @Override
    protected int getDefaultEnergyCost() {
        return ActionDefinitions.ADJACENT_ATTACK_ACTION_ENERGY_COST;
    }

    @Override
    public ResolvableMeleeWeapon getResolvableMeleeWeapon() {
        return resolvableMeleeWeapon;
    }
}
