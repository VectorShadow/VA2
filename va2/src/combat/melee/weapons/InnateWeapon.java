package combat.melee.weapons;

import combat.DamageType;
import combat.WeaponDamage;
import main.Session;
import main.extensible.TextDisplayable;
import resources.continuum.Continuum;

import java.util.ArrayList;

public class InnateWeapon extends TextDisplayable implements MeleeWeapon {
    private final int damageLimit;
    private final int damageVariance;
    private final double strengthInfluence;
    private final int[] statModifiers;
    private final MeleeStyle meleeStyle;
    private final MeleeWeaponClass meleeWeaponClass;
    private final Continuum<WeaponDamage> weaponDamageContinuum;

    public InnateWeapon(
            String d,
            String n,
            int damLim,
            int damVar,
            double strInf,
            int[] adjAccPreStr,
            MeleeStyle ms,
            MeleeWeaponClass mwc,
            Continuum<WeaponDamage> wdc
    ) {
        super(d, n, wdc.getBase().type().getColor());
        if (damLim < damVar || strInf < 0 || strInf > .5 || adjAccPreStr.length != 3)
            throw new IllegalStateException("Arguments out of bounds.");
        damageLimit = damLim;
        damageVariance = damVar;
        strengthInfluence = strInf;
        statModifiers = adjAccPreStr;
        meleeStyle = ms;
        meleeWeaponClass = mwc;
        weaponDamageContinuum = wdc;
    }

    public InnateWeapon(
            String d,
            String n,
            int damLim,
            int damVar,
            double strInf,
            int[] adjAccPreStr,
            MeleeStyle ms,
            MeleeWeaponClass mwc,
            WeaponDamage wd
    ) {
        this(d, n, damLim, damVar, strInf, adjAccPreStr, ms, mwc, new Continuum<>(wd, new ArrayList<>()));
    }
    private InnateWeapon(InnateWeapon iw) {
        this(
                iw.description,
                iw.name,
                iw.damageLimit,
                iw.damageVariance,
                iw.strengthInfluence,
                iw.statModifiers,
                iw.meleeStyle,
                iw.meleeWeaponClass,
                iw.weaponDamageContinuum
        );
    }

    @Override
    public int rollRawDamage(int wielderStrength) {
        double rollPct = 1.0 - strengthInfluence;
        int rollDamage = (int)(rollPct * (double)(damageLimit - Session.getRNG().nextInt(damageVariance)));
        int strengthDamage = (int)(strengthInfluence * (double)wielderStrength);
        return rollDamage + strengthDamage;
    }

    @Override
    public int adjustAccuracy() {
        return statModifiers[0];
    }

    @Override
    public int adjustPrecision() {
        return statModifiers[1];
    }

    @Override
    public int adjustStrength() {
        return statModifiers[2];
    }

    @Override
    public MeleeStyle getMeleeStyle() {
        return meleeStyle;
    }

    @Override
    public MeleeWeaponClass getMeleeWeaponClass() {
        return meleeWeaponClass;
    }

    @Override
    public WeaponDamage resolveWeaponDamage(boolean isHeavyBlow) {
        return isHeavyBlow ? weaponDamageContinuum.getBase() : weaponDamageContinuum.getValue(Session.getRNG());
    }

    @Override
    public MeleeWeapon clone() {
        return new InnateWeapon(this);
    }
}
