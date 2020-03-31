package combat;

import main.Session;
import main.extensible.Saveable;
import status.StatusType;

public class WeaponDamage extends Saveable {
    private final String DAMAGE_DESCRIPTION;
    private final double DAMAGE_MODIFIER;
    private final DamageType DAMAGE_TYPE;
    private final StatusType STATUS_TYPE;
    private final double STATUS_PERCENT;

    public WeaponDamage(double dm, DamageType dt) {
        this(dt.describe(), dm, dt);
    }
    public WeaponDamage(String dd, double dm, DamageType dt) {
        this(dd, dm, dt, null, 0.0);
    }
    public WeaponDamage(String dd, double dm, DamageType dt, StatusType st, double pct) {
        DAMAGE_DESCRIPTION = dd;
        DAMAGE_MODIFIER = dm;
        DAMAGE_TYPE = dt;
        STATUS_TYPE = st;
        STATUS_PERCENT = pct;
    }
    public String describe() {
        return DAMAGE_DESCRIPTION;
    }
    public double modify() {
        return DAMAGE_MODIFIER;
    }
    public DamageType damageType() {
        return DAMAGE_TYPE;
    }

    /**
     * Heavy Blows have an increased chance to apply status effects.
     */
    public StatusType getStatusType(boolean blow) {
        return Session.getRNG().nextDouble() / (blow ? 2 : 1) < STATUS_PERCENT ? STATUS_TYPE : null;
    }
}
