package combat;

import world.item.DegradeableItem;
import world.item.Material;

public interface ContactInteractive {
    double INTERACTION_DAMAGE = 32.0;
    Material getMaterial();
    double getSelfDamageMultiplier();

    static int[] interact(ContactInteractive ci1, DamageType t, ContactInteractive ci2) {
        Material m1 = ci1.getMaterial();
        Material m2 = ci2.getMaterial();
        double h = m1.modifyByHardness(m2);
        double mult1 = m1.getSusceptibility(DamageType.IMPACT) * h * ci1.getSelfDamageMultiplier(); //todo - volatility
        double mult2 = m1.getSusceptibility(t) * (1.0 / h) * ci2.getSelfDamageMultiplier(); //todo - volatility
        int dam1 = (int)(INTERACTION_DAMAGE * mult1);
        int dam2 = (int)(INTERACTION_DAMAGE * mult2);
        return new int[]{dam1, dam2};
    }

    static boolean damageSelf(ContactInteractive ci) {
        return ci instanceof DegradeableItem;
    }
}
