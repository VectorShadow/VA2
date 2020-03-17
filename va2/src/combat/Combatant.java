package combat;

import combat.melee.forms.Form;
import combat.melee.weapons.ResolvableMeleeWeapon;
import main.Session;
import resources.continuum.Continuum;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Contains the base combat stats for an actor.
 * Modifiable for the player via level up, permanent for npc.
 * CombatResolvers should function on Actors, which will derive values from these base stats
 * and further modify them as necessary(via permanent ability, form modification, temporary effect, etc.)
 */
public class Combatant implements Serializable {

    public static final int HEALTH_CAPACITY = 0;
    public static final int HEALTH = 1;
    public static final int ACCURACY = 2;
    public static final int EVASION = 3;
    public static final int PRECISION = 4;
    public static final int DEFENSE = 5;
    public static final int STRENGTH = 6;

    private int[] combatStatistics;

    private Form meleeForm; //the form this combatant uses in melee combat

    private Continuum<ResolvableMeleeWeapon> combatantMeleeWeapons; //a continuum of melee weapons available to this combatant
    //todo - ranged weapons

    private boolean ignoreBonus = false; //whether this attacker has a bonus from ignoring the last incoming attack

    //todo - we probably want damage type modifications here as well.

    public Combatant(
            int hea,
            int acc,
            int eva,
            int pre,
            int def,
            int str,
            Form defaultForm,
            ResolvableMeleeWeapon defaultMeleeWeapon
    ) {
        this(hea, acc, eva, pre, def, str, defaultForm, new Continuum<>(defaultMeleeWeapon, new ArrayList<>()));
    }
    public Combatant(
            int hea,
            int acc,
            int eva,
            int pre,
            int def,
            int str,
            Form defaultForm,
            Continuum<ResolvableMeleeWeapon> defaultMeleeWeapons
    ) {
        combatStatistics = new int[] {hea, hea, acc, eva, pre, def, str};
        meleeForm = defaultForm;
        combatantMeleeWeapons = defaultMeleeWeapons;
    }
    private Combatant(Combatant c) {
        this(
                c.getStatistic(HEALTH_CAPACITY),
                c.getStatistic(ACCURACY),
                c.getStatistic(EVASION),
                c.getStatistic(PRECISION),
                c.getStatistic(DEFENSE),
                c.getStatistic(STRENGTH),
                c.meleeForm,
                c.combatantMeleeWeapons
        );
    }

    /**
     * Adjust the health of this combatant.
     * @param adjustment the amount to adjust the health value by. May be positive(healing) or negative(damage).
     * @return whether this combatant is still alive.
     */
    public boolean adjustHealth(int adjustment) {
        setStatistic(HEALTH, getStatistic(HEALTH) + adjustment);
        if (getStatistic(HEALTH) > getStatistic(HEALTH_CAPACITY)) renewHealth();
        return getStatistic(HEALTH) > 0;
    }
    public void renewHealth() {
        setStatistic(HEALTH, getStatistic(HEALTH_CAPACITY));
    }
    public double getHealthPercent() {
        return 100.0 * (double)getStatistic(HEALTH) / (double)getStatistic(HEALTH_CAPACITY);
    }

    public Form getMeleeForm() {
        return meleeForm;
    }

    public ResolvableMeleeWeapon selectMeleeWeapon() {
        return combatantMeleeWeapons.getValue(Session.getRNG());
    }
    public void setMeleeWeapons(Continuum<ResolvableMeleeWeapon> rmwc) {
        combatantMeleeWeapons = rmwc;
    }
    public void setMeleeWeapons(ResolvableMeleeWeapon rmw) {
        setMeleeWeapons(new Continuum<>(rmw, new ArrayList<>()));
    }

    public void setIgnoreBonus() {
        ignoreBonus = true;
    }
    public boolean hasIgnoreBonus(){
        if (ignoreBonus) {
            ignoreBonus = false;
            return true;
        }
        return false;
    }

    public int getStatistic(int index) {
        return combatStatistics[index] + ((index > HEALTH) ? meleeForm.adjustStatistic(index) : 0);
    }
    public void setStatistic(int index, int value) {
        combatStatistics[index] = value;
    }

    @Override
    public Combatant clone() {
        return new Combatant(this);
    }
}
