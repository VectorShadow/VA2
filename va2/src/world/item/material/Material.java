package world.item.material;

import combat.DamageType;
import main.extensible.Saveable;

/**
 * Specifies the substances items are made from, and determines the interactions between them.
 */
public class Material extends Saveable {
    private final int HARDNESS;
    private final int VOLATILITY;
    private final double[] DAMAGE_TYPE_SUSCEPTIBILITIES;

    public Material(int h, int v, double[] dts) {
        HARDNESS = h;
        VOLATILITY = v;
        DAMAGE_TYPE_SUSCEPTIBILITIES = dts;
    }

    /**
     * Hardness specifies the base interaction between item impacts(weapon vs weapon, or weapon vs armor).
     * In general, harder items will take less damage from impacts with softer items.
     *
     * @return the damage multiplier to the item whose material the check is called on - which is the inverse
     * of the damage multiplier to the item whose material is the argument.
     *
     * For example, an item with hardness 8 impacts an item with hardness 5:
     * item1.getMaterial().modifyByHardness(item2.getMaterial) returns 0.625,
     * so damage to item1 is multiplied by 0.625, while damage to item2 is multiplied by 1.6.
     */
    public double modifyByHardness(Material m) {
        return (double)(m.HARDNESS) / (double)HARDNESS ;
    }

    //todo - volatility interactions
    // this must be implemented so that it can go both ways, to any desired power. May need to be own class.

    /**
     * Return the multiplier to damage an item made from this material receives when struck by that damage type.
     * For armor, this may also affect damage transmission(though it should never increase damage dealt past armor).
     */
    public double getSusceptibility(DamageType dt) {
        return DAMAGE_TYPE_SUSCEPTIBILITIES[dt.ordinal()];
    }
}
