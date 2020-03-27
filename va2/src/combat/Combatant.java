package combat;

import combat.melee.forms.Form;
import main.Session;
import resources.continuum.Continuum;
import resources.continuum.Pair;
import util.InputSimplifier;
import world.item.Armor;
import world.item.MeleeWeapon;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Contains the base combat stats for an actor.
 * Modifiable for the player via level up, permanent for npc.
 * CombatResolvers should function on Actors, which will derive values from these base stats
 * and further modify them as necessary(via permanent ability, form modification, temporary effect, etc.)
 */
public class Combatant implements Serializable {

    public static final int ACCURACY = 0;
    public static final int EVASION = 1;
    public static final int PRECISION = 2;
    public static final int DEFENSE = 3;
    public static final int STRENGTH = 4;

    public static final int COUNT_STATISTICS = 5; //todo - keep updated!

    private int healthCapacity;
    private int health;
    private int[] combatStatistics;

    protected Form meleeForm; //the form this combatant uses in melee combat

    private Continuum<MeleeWeapon> combatantMeleeWeapons; //a continuum of melee weapons available to this combatant
    //todo - ranged weapons
    private Armor combatantArmor;

    private boolean ignoreBonus = false; //whether this attacker has a bonus from ignoring the last incoming attack

    //todo - we probably want damage type modifications here as well.

    public Combatant(
            int healthCap,
            int[] combatStats,
            Form defaultForm,
            MeleeWeapon defaultMeleeWeapon,
            Armor defaultArmor
    ) {
        this(
                healthCap,
                combatStats,
                defaultForm,
                new Continuum<>(defaultMeleeWeapon, new ArrayList<>()),
                defaultArmor
        );
    }
    public Combatant(
            int healthCap,
            int[] combatStats,
            Form defaultForm,
            Continuum<MeleeWeapon> defaultMeleeWeapons,
            Armor defaultArmor
    ) {
        if (combatStats.length != COUNT_STATISTICS)
            throw new IllegalArgumentException("Invalid stat count: " + combatStats.length + ", expected: " +COUNT_STATISTICS);
        health = healthCapacity = healthCap;
        combatStatistics = combatStats;
        meleeForm = defaultForm;
        MeleeWeapon clonedBase = defaultMeleeWeapons.getBase().clone();
        ArrayList<Pair<MeleeWeapon>> pairs = defaultMeleeWeapons.getPairList();
        ArrayList<Pair<MeleeWeapon>> clonedPairs = new ArrayList<>();
        for (Pair<MeleeWeapon> mwp : pairs)
            clonedPairs.add(mwp.clone());
        combatantMeleeWeapons = new Continuum<>(clonedBase, clonedPairs);
        combatantArmor = defaultArmor.clone();
    }
    private Combatant(Combatant c) {
        this(
                c.healthCapacity,
                c.combatStatistics,
                c.meleeForm,
                c.combatantMeleeWeapons,
                c.combatantArmor
        );
    }

    /**
     * Adjust the health of this combatant.
     * @param adjustment the amount to adjust the health value by. May be positive(healing) or negative(damage).
     * @return whether this combatant is still alive.
     */
    public boolean adjustHealth(int adjustment) {
        health += adjustment;
        if (health > getHealthCapacity()) renewHealth();
        return health > 0;
    }
    public void renewHealth() {
        health = getHealthCapacity();
    }
    public double getHealthPercent() {
        return 100.0 * (double)health / (double)getHealthCapacity();
    }

    public int getHealthCapacity() {
        return healthCapacity;
    }

    public Form getMeleeForm() {
        return meleeForm;
    }

    public MeleeWeapon selectMeleeWeapon() {
        return combatantMeleeWeapons.getValue(Session.getRNG());
    }
    public void setMeleeWeapons(Continuum<MeleeWeapon> mwc) {
        combatantMeleeWeapons = mwc;
    }
    public void setMeleeWeapons(MeleeWeapon mw) {
        setMeleeWeapons(new Continuum<>(mw, new ArrayList<>()));
    }
    public Armor getArmor() {
        return combatantArmor;
    }

    public void setArmor(Armor armor) {
        combatantArmor = armor;
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
        return InputSimplifier.getValue(combatStatistics[index] + meleeForm.adjustStatistic(index));
    }
    private void setStatistic(int index, int value) {
        combatStatistics[index] = value;
    }

    @Override
    public Combatant clone() {
        return new Combatant(this);
    }
}
