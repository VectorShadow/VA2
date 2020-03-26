package combat;

import resources.chroma.Chroma;
import resources.continuum.Continuum;
import resources.continuum.Pair;

import java.awt.*;
import java.util.ArrayList;

/**
 * Establishes all damage types.
 */
public enum DamageType {
    ACID,
    COLD,
    FIRE,
    IMPACT,
    PUNCTURE,
    REND;

    public Color getColor() {
        switch (this) {
            case ACID: return Chroma.ELEMENTAL_ACID;
            case COLD: return Chroma.ELEMENTAL_FROST;
            case FIRE: return Chroma.ELEMENTAL_FLAME;
            case IMPACT: return Chroma.BLUE;
            case PUNCTURE: return Chroma.GREEN;
            case REND: return Chroma.RED;
            default:
                throw new IllegalStateException("Unimplemented DamageType");
        }
    }
    public String describe() {
        switch (this) {
            case ACID: return "dissolve$";
            case COLD: return "freeze$";
            case FIRE: return "burn$";
            case IMPACT: return "smash&";
            case PUNCTURE: return "puncture$";
            case REND: return "rend$";
            default:
                throw new IllegalStateException("Unimplemented DamageType");
        }
    }

    /**
     * Pulled from ResolvableMeleeWeapon after deprecation for possible future use.
     */
    public static Continuum<Color> buildColorContinuum(Continuum<WeaponDamage> wdc) {
        Color base = wdc.getBase().damageType().getColor();
        ArrayList<Pair<Color>> list = new ArrayList<>();
        for (Pair<WeaponDamage> wdp : wdc.getPairList()) {
            list.add(new Pair<>(wdp.probability, wdp.element.damageType().getColor()));
        }
        return new Continuum<>(base, list);
    }
}
