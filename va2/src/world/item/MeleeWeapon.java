package world.item;

import combat.WeaponDamage;
import combat.melee.weapons.MeleeStyle;
import combat.melee.weapons.MeleeWeaponClass;
import main.Session;
import resources.continuum.Continuum;
import resources.continuum.Pair;
import util.Format;
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
                ((ItemTemplate)mw.getTemplate()),
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

    @Override
    public String informativeDisplay() {
        String id =  getTemplate().getName() + "(" + (DAMAGE_LIMIT - DAMAGE_VARIANCE) + "-" + DAMAGE_LIMIT +
                " @" + Format.percent(STRENGTH_INFLUENCE * 100, 1) + " Strength)";
        if (STAT_MODIFIERS[0] != 0 || STAT_MODIFIERS[1] != 0 || STAT_MODIFIERS[2] != 0) {
            id += "{";
            for (int i = 0; i < STAT_MODIFIERS.length; ++i) {
                int statMod = STAT_MODIFIERS[i];
                if (statMod != 0) {
                    id += (statMod < 0 ? "-" : "+") + statMod + " " +
                            (i == 0 ? "Accuracy" : i == 1 ? "Precision" : "Damage");
                    if (i < STAT_MODIFIERS.length - 1 && STAT_MODIFIERS[i + 1] != 0)
                        id += "/";
                }
            }
            id +="}";
        }
        id += "[" + MELEE_WEAPON_CLASS.shortDescribe() + "/" +  MELEE_STYLE.shortDescribe() + "]";
        id += "<" + WEAPON_DAMAGE_CONTINUUM.getBase().damageType().getName();
        if (WEAPON_DAMAGE_CONTINUUM.getPairList().size() > 0) {
            for (Pair<WeaponDamage> wdp : WEAPON_DAMAGE_CONTINUUM.getPairList())
                id += "/" + wdp.element.damageType().getName();
        }
        return id += ">";
    }
}
