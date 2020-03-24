package world.item;

import combat.DamageType;
import combat.Functions;
import world.item.loadout.EquipmentSlot;
import world.item.material.Material;

public class Armor extends ContactInteractiveItem {

    private final double COVERAGE;
    private final int THICKNESS;

    public Armor(ItemTemplate it, boolean doesDegrade, Material m, EquipmentSlot es, boolean innate, double c, int t) {
        super(it, doesDegrade, m, es, innate);
        if (c < 0.0 || c > 1.0)
            throw new IllegalArgumentException("Coverage out of bounds: " + c);
        if (t < 0)
            throw new IllegalArgumentException("Thickness out of bounds: " + t);
        COVERAGE = c;
        THICKNESS = t;
    }

    private int coverageValue(int oppositionValue) {
        return (int)(COVERAGE * ((double)oppositionValue));
    }

    /**
     * Roll to determine whether an attack strikes an armored area or not.
     * If we strike an unarmored region, the armor will not reduce the damage, and no material interaction will take place.
     * However, we assume that armor covers all vital areas(even with a low coverage value), so critical hits
     * will not be assessed. The exception to this is the special case where coverage is exactly 0.0, which we use to
     * indicate a completely unarmored status. This remains susceptible to critical hits, so we treat it as successfully
     * applied.
     *
     * We multiply by 8 to guarantee armor coverage at 100%. Note that while this would seem to inflate armor coverage
     * values below 100%, armor is designed specifically to protect the likeliest strike zones and critical areas, so
     * the odds of striking an armored area of a target will always be higher than the percent of area the armor covers.
     *
     * @param accuracyValue the attacker's final adjusted accuracy value
     * @return whether the attacker struck the defender's armor or not.
     */
    public boolean applyArmor(int accuracyValue) {
        return COVERAGE == 0.0 || Functions.oppose(coverageValue(8 * accuracyValue), accuracyValue);
    }

    /**
     * If an attack does strike armor, it has a chance to roll on the critical table.
     * First we oppose the attacker's precision against the defender's defense. If the defender wins,
     * no critical hit is assessed.
     * Then we use the armor coverage to set the critical threshold for the attacker. Note that, unlike the applyArmor
     * roll, we use the defender's value instead of the attacker's value as the base for the coverage roll. This is
     * designed to emphasize the interplay between the two stats, rather than create an even distribution.
     * @param precisionValue the attacker's final adjusted precision value
     * @param defenseValue the defender's final adjusted defense value
     * @return the threshold for the attacker's critical hit roll
     */
    public double criticalThreshold(int precisionValue, int defenseValue) {
        if (Functions.oppose(precisionValue, defenseValue))
            return Functions.setThreshold(precisionValue, 2 * coverageValue(defenseValue));
        return 1.0;
    }

    /**
     * Return a flat reduction to subtract from the damage being mitigated by this armor.
     * First calculate a hardness factor which is the ratio of the armor hardness to the weapon hardness, so that if the
     * armor is harder than the weapon, the reduction is increased, and vice versa.
     * We then multiply this with the armor's thickness, and finally divide by the armor material's susceptibility to
     * the attacker damage type. This is because the susceptibility is normally the damage multiplcation to the armor,
     * so that if the material is strong to that damage type, the susceptibility is < 1.0 and vice versa. So an armor
     * strong to a specific damage type should provide the inverse multiplier and increase its flat reduction against
     * that type.
     */
    public int flatReduction(Material attackerMaterial, DamageType attackerDamageType) {
        double hardnessFactor = attackerMaterial.modifyByHardness(getMaterial());
        return (int)(((double)THICKNESS * hardnessFactor) / getMaterial().modifyByDamageType(attackerDamageType));
    }

    /**
     * Return a percent of the incoming damage being mitigated by this armor.
     * First calculate the integrity of this armor. DegradeableItem has a method to return the percent of durability
     * remaining for this item. We take this value and divide by 75, instead of 100, to prevent penalizing loss of
     * durability prior to 75%, and reduce the penalty below that.
     * Second, find the type multiplier of this armor's material against the damage type.
     * Finally calculate a reduction factor such that the lower the type multiplier(that is, the stronger the
     * material is against the incoming damage type) and the higher the integrity(the better the condition of the
     * armor), the lower the reduction factor. This reduction factor is the percent of the remaining damage which will
     * still be dealt. Note that we cap integrity at 1.0, due to the penalty reduction(we don't want to instead offer a
     * bonus over 75%), and we also cap the returned value at 1.0 - since in the worst case, armor should not increase
     * incoming damage.
     */
    public double percentReduction(DamageType attackerDamageType) {
        double integrity = Math.max(1.0, getDurabilityPercent() / 75.0);
        double typeMultiplier = getMaterial().modifyByDamageType(attackerDamageType);
        double reductionFactor = typeMultiplier / integrity;
        return reductionFactor > 1.0 ? 1.0 : reductionFactor;
    }

    /**
     * Special case. If Coverage is 0.0, this is Unarmored, and we don't need to damage the combatant.
     * Otherwise, continue using the ContactInteractiveItem version of this method.
     */
    @Override
    public boolean doesDamageSelf() {
        return COVERAGE == 0.0 || super.doesDamageSelf();
    }
}
