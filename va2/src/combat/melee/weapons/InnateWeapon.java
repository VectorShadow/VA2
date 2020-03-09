package combat.melee.weapons;

import combat.DamageType;
import main.Session;
import resources.continuum.Continuum;

import java.util.ArrayList;

public class InnateWeapon implements MeleeWeapon {
    private final int damageLimit;
    private final int damageVariance;
    private final double strengthInfluence;
    private final int[] statModifiers;
    private final MeleeStyle meleeStyle;
    private final MeleeWeaponClass meleeWeaponClass;
    private final Continuum<DamageType> damageTypeContinuum;

    public InnateWeapon(
            int damLim,
            int damVar,
            double strInf,
            int[] adjAccPreStr,
            MeleeStyle ms,
            MeleeWeaponClass mwc,
            DamageType dt
    ) {
        this(damLim, damVar, strInf, adjAccPreStr, ms, mwc, new Continuum<>(dt, new ArrayList<>()));
    }
    public InnateWeapon(
            int damLim,
            int damVar,
            double strInf,
            int[] adjAccPreStr,
            MeleeStyle ms,
            MeleeWeaponClass mwc,
            Continuum<DamageType> dtc
    ) {
        if (damLim < damVar || strInf < 0 || strInf > .5 || adjAccPreStr.length != 3)
            throw new IllegalStateException("Arguments out of bounds.");
        damageLimit = damLim;
        damageVariance = damVar;
        strengthInfluence = strInf;
        statModifiers = adjAccPreStr;
        meleeStyle = ms;
        meleeWeaponClass = mwc;
        damageTypeContinuum = dtc;
    }
    private InnateWeapon(InnateWeapon iw) {
        this(
                iw.damageLimit,
                iw.damageVariance,
                iw.strengthInfluence,
                iw.statModifiers,
                iw.meleeStyle,
                iw.meleeWeaponClass,
                iw.damageTypeContinuum
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
    public DamageType resolveDamageType() {
        return damageTypeContinuum.getValue(Session.getRNG());
    }

    @Override
    public MeleeWeapon clone() {
        return new InnateWeapon(this);
    }
}
