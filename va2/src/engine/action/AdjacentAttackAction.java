package engine.action;

import util.Direction;
import world.item.MeleeWeapon;

public class AdjacentAttackAction extends DirectedAction implements MeleeAttackAction {

    private final MeleeWeapon meleeWeapon;

    public AdjacentAttackAction(Direction d, double energyMultiplier, MeleeWeapon mw) {
        super(d, energyMultiplier);
        meleeWeapon = mw;
    }

    @Override
    protected int getDefaultEnergyCost() {
        return ActionDefinitions.ADJACENT_ATTACK_ACTION_ENERGY_COST;
    }

    @Override
    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }
}
