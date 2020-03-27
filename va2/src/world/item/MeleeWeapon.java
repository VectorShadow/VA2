package world.item;

import combat.WeaponDamage;
import combat.melee.weapons.MeleeStyle;
import combat.melee.weapons.MeleeWeaponClass;
import main.Session;
import resources.continuum.Continuum;
import world.item.loadout.EquipmentSlot;

public class MeleeWeapon extends ContactInteractiveItem {

    private final int DAMAGE_LIMIT;
    private final int DAMAGE_VARIANCE;
    private final double STRENGTH_INFLUENCE;
    private final int[] STAT_MODIFIERS;
    private final MeleeStyle MELEE_STYLE;
    private final MeleeWeaponClass MELEE_WEAPON_CLASS;
    private final Continuum<WeaponDamage> WEAPON_DAMAGE_CONTINUUM;

    public MeleeWeapon(
            ItemTemplate it,
            boolean doesDegrade,
            boolean innate,
            int damLim,
            int damVar,
            double strInf,
            int[] adjAccPreStr,
            MeleeStyle ms,
            MeleeWeaponClass mwc,
            Continuum<WeaponDamage> wdc) {
        super(it, doesDegrade, EquipmentSlot.WIELDED, innate);
        if (damLim < damVar || strInf < 0 || strInf > .67 || adjAccPreStr.length != 3)
            throw new IllegalStateException("Arguments out of bounds.");
        DAMAGE_LIMIT = damLim;
        DAMAGE_VARIANCE = damVar;
        STRENGTH_INFLUENCE = strInf;
        STAT_MODIFIERS = adjAccPreStr;
        MELEE_STYLE = ms;
        MELEE_WEAPON_CLASS = mwc;
        WEAPON_DAMAGE_CONTINUUM = wdc;
    }
    public MeleeWeapon(
            ItemTemplate it,
            boolean doesDegrade,
            boolean innate,
            int damLim,
            int damVar,
            double strInf,
            int[] adjAccPreStr,
            MeleeStyle ms,
            MeleeWeaponClass mwc,
            WeaponDamage wd) {
        this(it, doesDegrade, innate, damLim, damVar, strInf, adjAccPreStr, ms, mwc, new Continuum<>(wd));
    }
    MeleeWeapon(MeleeWeapon mw) {
        this(
                ((ItemTemplate)mw.getTEMPLATE()),
                mw.DOES_DEGRADE,
                mw.INNATE,
                mw.DAMAGE_LIMIT,
                mw.DAMAGE_VARIANCE,
                mw.STRENGTH_INFLUENCE,
                mw.STAT_MODIFIERS,
                mw.MELEE_STYLE,
                mw.MELEE_WEAPON_CLASS,
                mw.WEAPON_DAMAGE_CONTINUUM
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

    @Override
    public MeleeWeapon clone() {
        return new MeleeWeapon(this);
    }
}
