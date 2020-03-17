package combat.melee.weapons;

import combat.CombatResolvable;
import combat.WeaponDamage;
import main.Session;
import resources.continuum.Continuum;
import resources.continuum.Pair;
import world.item.InteractiveItem;

import java.awt.*;
import java.util.ArrayList;

/**
 * This class specifies the properties of any type of melee weapon required by the MeleeResolver.
 */
public class ResolvableMeleeWeapon extends CombatResolvable {
    private final int DAMAGE_LIMIT;
    private final int DAMAGE_VARIANCE;
    private final double STRENGTH_INFLUENCE;
    private final int[] STAT_MODIFIERS;
    private final MeleeStyle MELEE_STYLE;
    private final MeleeWeaponClass MELEE_WEAPON_CLASS;
    private final Continuum<WeaponDamage> WEAPON_DAMAGE_CONTINUUM;

    private final Continuum<Color> COLOR_CONTINUUM;

    public ResolvableMeleeWeapon(
            InteractiveItem ii,
            int damLim,
            int damVar,
            double strInf,
            int[] adjAccPreStr,
            MeleeStyle ms,
            MeleeWeaponClass mwc,
            Continuum<WeaponDamage> wdc
    ){
        super(ii);
        if (damLim < damVar || strInf < 0 || strInf > .67 || adjAccPreStr.length != 3)
            throw new IllegalStateException("Arguments out of bounds.");
        DAMAGE_LIMIT = damLim;
        DAMAGE_VARIANCE = damVar;
        STRENGTH_INFLUENCE = strInf;
        STAT_MODIFIERS = adjAccPreStr;
        MELEE_STYLE = ms;
        MELEE_WEAPON_CLASS = mwc;
        WEAPON_DAMAGE_CONTINUUM = wdc;
        COLOR_CONTINUUM = buildColorContinuum();
    }
    public ResolvableMeleeWeapon(
            InteractiveItem ii,
            int damLim,
            int damVar,
            double strInf,
            int[] adjAccPreStr,
            MeleeStyle ms,
            MeleeWeaponClass mwc,
            WeaponDamage wd
    ) {
        this(ii, damLim, damVar, strInf, adjAccPreStr, ms, mwc, new Continuum<>(wd));
    }
    public ResolvableMeleeWeapon(ResolvableMeleeWeapon mwt) {
        this(
                mwt.getInteractiveItem(),
                mwt.DAMAGE_LIMIT,
                mwt.DAMAGE_VARIANCE,
                mwt.STRENGTH_INFLUENCE,
                mwt.STAT_MODIFIERS,
                mwt.MELEE_STYLE,
                mwt.MELEE_WEAPON_CLASS,
                mwt.WEAPON_DAMAGE_CONTINUUM
        );
    }

    public int rollRawDamage(int wielderStrength) {
        double rollPct = 1.0 - STRENGTH_INFLUENCE;
        int rollDamage = (int)(rollPct * (double)(DAMAGE_LIMIT - Session.getRNG().nextInt(DAMAGE_VARIANCE)));
        int strengthDamage = (int)(STRENGTH_INFLUENCE * (double)wielderStrength);
        return rollDamage + strengthDamage;
    }

    public int adjustAccuracy() {
        return STAT_MODIFIERS[0];
    }

    public int adjustPrecision() {
        return STAT_MODIFIERS[1];
    }

    public int adjustStrength() {
        return STAT_MODIFIERS[2];
    }

    public MeleeStyle getMeleeStyle() {
        return MELEE_STYLE;
    }

    public MeleeWeaponClass getMeleeWeaponClass() {
        return MELEE_WEAPON_CLASS;
    }

    public WeaponDamage resolveWeaponDamage(boolean isHeavyBlow) {
        return isHeavyBlow ? WEAPON_DAMAGE_CONTINUUM.getBase() : WEAPON_DAMAGE_CONTINUUM.getValue(Session.getRNG());
    }

    public Continuum<Color> getColorContinuum() {
        return COLOR_CONTINUUM;
    }

    private Continuum<Color> buildColorContinuum() {
        Color base = WEAPON_DAMAGE_CONTINUUM.getBase().type().getColor();
        ArrayList<Pair<Color>> list = new ArrayList<>();
        for (Pair<WeaponDamage> wdp : WEAPON_DAMAGE_CONTINUUM.getPairList()) {
            list.add(new Pair<>(wdp.probability, wdp.element.type().getColor()));
        }
        return new Continuum<>(base, list);
    }

    @Override
    public ResolvableMeleeWeapon clone() {
        return new ResolvableMeleeWeapon(this);
    }
}
