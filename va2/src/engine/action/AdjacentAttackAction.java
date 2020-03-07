package engine.action;

import util.Direction;

public class AdjacentAttackAction extends DirectedAction implements DamageCarryingAction {

    private int attackDamage;

    public AdjacentAttackAction(Direction d, double energyMultiplier, int damageRoll) {
        super(d, energyMultiplier);
        setDamage(damageRoll);
    }

    @Override
    protected int getDefaultEnergyCost() {
        return ActionDefinitions.ADJACENT_ATTACK_ACTION_ENERGY_COST;
    }

    @Override
    public int getDamage() {
        return attackDamage;
    }

    @Override
    public void setDamage(int d) {
        attackDamage = d;
    }
}
