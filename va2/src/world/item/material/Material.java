package world.item.material;

import combat.DamageType;
import main.extensible.Saveable;

/**
 * Specifies the substances items are made from, and determines the interactions between them.
 */
public class Material extends Saveable {
    static final int OXIDIZEABLE = 0;
    static final int OXIDIZER = 1;
    static final int ORGANIC = 2;
    static final int CORROSIVE = 3;
    private static final int[] VOLATILITY_FACTORS = new int[]{2, 3, 5, 7};
    private static final int[] VOLATILITY_REACTIONS = new int[]{
            OXIDIZEABLE * OXIDIZER,
            ORGANIC * CORROSIVE,
    };

    private final String NAME;
    private final int HARDNESS;
    private final int[] VOLATILITIES;
    private final double[] DAMAGE_TYPE_MODIFICATIONS;

    public Material(String n, int h, int[] v, double[] dts) {
        NAME = n;
        HARDNESS = h;
        VOLATILITIES = v;
        DAMAGE_TYPE_MODIFICATIONS = dts;
    }

    public String getName() {
        return NAME;
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

    public double modifyByVolatility(Material m) {
        int power = 0;
        for (int v1 : VOLATILITIES) {
            for (int v2 : m.VOLATILITIES) {
                for (int r : VOLATILITY_REACTIONS) {
                    if (v1 * v2 == r)
                        ++power;
                }
            }
        }
        return Math.pow(2.0, (double)power);
    }

    /**
     * Return the multiplier to damage an item made from this material receives when struck by that damage type.
     * For armor, this may also affect damage transmission(though it should never increase damage dealt past armor).
     *
     * The higher the multiplier, the worse the material holds up against that damage type.
     */
    public double modifyByDamageType(DamageType dt) {
        return DAMAGE_TYPE_MODIFICATIONS[dt.ordinal()];
    }
}
