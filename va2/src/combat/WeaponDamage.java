package combat;

import main.extensible.Saveable;

public class WeaponDamage extends Saveable {
    private final String DAMAGE_DESCRIPTION;
    private final double DAMAGE_MODIFIER;
    private final DamageType DAMAGE_TYPE;

    public WeaponDamage(double dm, DamageType dt) {
        this(dt.describe(), dm, dt);
    }

    public WeaponDamage(String dd, double dm, DamageType dt) {
        DAMAGE_DESCRIPTION = dd;
        DAMAGE_MODIFIER = dm;
        DAMAGE_TYPE = dt;
    }
    public String describe() {
        return DAMAGE_DESCRIPTION;
    }
    public double modify() {
        return DAMAGE_MODIFIER;
    }
    public DamageType type() {
        return DAMAGE_TYPE;
    }
}
