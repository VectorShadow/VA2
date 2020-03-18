package combat;

import world.item.material.Material;

public interface ContactInteractive {
    double INTERACTION_DAMAGE = 32.0;
    double DIRECT_IMPACT_MULTIPLIER = 8.0;
    double MAXIMUM_HARDNESS_MULTIPLIER = 4.0;
    Material getMaterial();
    double getSelfDamageMultiplier();
    boolean doesDamageSelf();

    /**
     * @return whether the ContactInteractive has not been destroyed
     */
    boolean damageSelf(int amount);

    static int[] interact(ContactInteractive ci1, DamageType t, ContactInteractive ci2, boolean directImpact) {
        Material m1 = ci1.getMaterial();
        Material m2 = ci2.getMaterial();
        double h = m1.modifyByHardness(m2);
        double v = m1.modifyByVolatility(m2);
        double mult1 = (directImpact //if contact is direct (weapon x armor), as opposed to glancing (weapon x weapon/shield)
                ? DIRECT_IMPACT_MULTIPLIER //multiply the damage to the weapon
                : 1.0)  //otherwise no adjustment
                * m1.modifyByDamageType(DamageType.IMPACT) //attacking weapon receives impact damage from contact
                * Math.min(h, MAXIMUM_HARDNESS_MULTIPLIER) //attacking weapon gets base hardness modification
                * ci1.getSelfDamageMultiplier() //damage to actors caused by innate weapon impacts is reduced here, but damage to degradable items is not
                * v; //increase damage if there is a chemical or other volatile reaction between the materials
        double mult2 = (directImpact && ci1.doesDamageSelf() //contact is direct and item does not damage its combatant
                ? DIRECT_IMPACT_MULTIPLIER //multiply the damage to the armor
                : 1.0) //otherwise no adjustment - a non-direct hit or an actor damaging direct hit is not increased here
                * m1.modifyByDamageType(t) //defending weapon receives damage of the type the attack possessed
                * Math.min((1.0 / h), MAXIMUM_HARDNESS_MULTIPLIER) //defending weapon gets inverse hardness modification
                * ci2.getSelfDamageMultiplier() //as above
                * v; //as above
        //todo - volatility
        int dam1 = (int)(INTERACTION_DAMAGE * mult1);
        int dam2 = (int)(INTERACTION_DAMAGE * mult2);
        return new int[]{dam1, dam2};
    }
}
