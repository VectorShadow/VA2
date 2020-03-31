package combat;

import resources.chroma.Chroma;
import resources.continuum.Continuum;
import resources.continuum.Pair;
import util.InputSimplifier;

import java.awt.*;
import java.util.ArrayList;

/**
 * Establishes all damage types.
 */
public enum DamageType {
    ACID("Acid", 1.0),
    ARCANE("Arcane", 0.85),
    COLD("Cold", 1.0),
    ELDRITCH("Eldritch", 0.0, 0.85),
    FIRE("Fire", 1.0),
    IMPACT("Impact", 1.0),
    LIGHTNING("Lightning", 1.0),
    PSYCHIC("Psychic", 0.0, 1.0),
    PUNCTURE("Puncture", 1.0),
    REND("Rend", 1.0),
    TRUE("TRUE", 0.0, 0.0);

    private final String NAME;
    private final double PERCENT_BODY;
    private final double PERCENT_MIND;
    private final double PERCENT_SOUL;

    DamageType(String name, double pctBody) {
        this(name, pctBody, 0.0);
    }
    DamageType(String name, double pctBody, double pctMind) {
        if (pctBody + pctMind > 1.0 || pctBody < 0.0 || pctMind < 0.0)
            throw new IllegalStateException();
        NAME = name;
        PERCENT_BODY = pctBody;
        PERCENT_MIND = pctMind;
        PERCENT_SOUL = 1.0 - (PERCENT_BODY + PERCENT_MIND);
    }

    public Color getColor() {
        switch (this) {
            case ACID: return Chroma.ELEMENTAL_ACID;
            case ARCANE: return Chroma.ULTRAMAGENTA;
            case COLD: return Chroma.ELEMENTAL_FROST;
            case ELDRITCH: return Chroma.ANTIAMBER;
            case FIRE: return Chroma.ELEMENTAL_FLAME;
            case IMPACT: return Chroma.BLUE;
            case LIGHTNING: return Chroma.ELEMENTAL_LIGHTNING;
            case PSYCHIC: return Chroma.ULTRACYAN;
            case PUNCTURE: return Chroma.GREEN;
            case REND: return Chroma.RED;
            case TRUE: return Chroma.WHITE;
            default:
                throw new IllegalStateException("Unimplemented DamageType");
        }
    }
    public String describe() {
        switch (this) {
            case ACID: return "dissolve$";
            case ARCANE: return "zap$";
            case COLD: return "freeze$";
            case ELDRITCH: return "warp$";
            case FIRE: return "burn$";
            case IMPACT: return "smash&";
            case LIGHTNING: return "shock$";
            case PSYCHIC: return "blast$";
            case PUNCTURE: return "puncture$";
            case REND: return "rend$";
            case TRUE: return "unmake$";
            default:
                throw new IllegalStateException("Unimplemented DamageType");
        }
    }

    public String getName() {
        return NAME;
    }

    public String abbreviate() {
        return NAME.substring(0, 2);
    }

    public double getPercentBody() {
        return PERCENT_BODY;
    }

    public double getPercentMind() {
        return PERCENT_MIND;
    }

    public double getPercentSoul() {
        return PERCENT_SOUL;
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
