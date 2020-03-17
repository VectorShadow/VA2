package combat;

import world.item.material.Material;

public interface ContactInteractive {
    double INTERACTION_DAMAGE = 32.0;
    double DIRECT_IMPACT_MULTIPLIER = 8.0;
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
        double mult1 = (directImpact //if contact is direct (weapon x armor), as opposed to glancing (weapon x weapon/shield)
                ? DIRECT_IMPACT_MULTIPLIER //multiply the damage to the weapon
                : 1.0)  //otherwise no adjustment
                * m1.getSusceptibility(DamageType.IMPACT) //attacking weapon receives impact damage from contact
                * h //attacking weapon gets base hardness modification
                * ci1.getSelfDamageMultiplier(); //damage to actors caused by innate weapon impacts is reduced here, but damage to degradeable items is not
        //todo - volatility
        double mult2 = (directImpact //as above
                ? ci1.doesDamageSelf() //if this interactive item takes its own damage, that is, degradable armor
                ? DIRECT_IMPACT_MULTIPLIER //multiply the damage to the armor
                : (1 / DIRECT_IMPACT_MULTIPLIER) //if not, divide it instead, so actors are not unduly harmed by damage to their innate armor
                : 1.0) //otherwise no adjustment
                * m1.getSusceptibility(t) //defending weapon receives damage of the type the attack possessed
                * (1.0 / h) //defending weapon gets inverse hardness modification
                * ci2.getSelfDamageMultiplier(); //as above
        //todo - volatility
        int dam1 = (int)(INTERACTION_DAMAGE * mult1);
        int dam2 = (int)(INTERACTION_DAMAGE * mult2);
        return new int[]{dam1, dam2};
    }
}
