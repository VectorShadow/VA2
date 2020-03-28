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
    private int sanityCapacity;
    private int sanity;
    private int soulCapacity;
    private int soul;
    private int[] combatStatistics;

    protected Form meleeForm; //the form this combatant uses in melee combat

    private Continuum<MeleeWeapon> combatantMeleeWeapons; //a continuum of melee weapons available to this combatant
    //todo - ranged weapons
    private Armor combatantArmor;

    private boolean ignoreBonus = false; //whether this attacker has a bonus from ignoring the last incoming attack

    //todo - we probably want damage type modifications here as well.

    public Combatant(
            int healthCap,
            int sanityCap,
            int soulCap,
            int[] combatStats,
            Form defaultForm,
            MeleeWeapon defaultMeleeWeapon,
            Armor defaultArmor
    ) {
        this(
                healthCap,
                sanityCap,
                soulCap,
                combatStats,
                defaultForm,
                new Continuum<>(defaultMeleeWeapon, new ArrayList<>()),
                defaultArmor
        );
    }
    public Combatant(
            int healthCap,
            int sanityCap,
            int soulCap,
            int[] combatStats,
            Form defaultForm,
            Continuum<MeleeWeapon> defaultMeleeWeapons,
            Armor defaultArmor
    ) {
        if (combatStats.length != COUNT_STATISTICS)
            throw new IllegalArgumentException("Invalid stat count: " + combatStats.length + ", expected: " +COUNT_STATISTICS);
        health = healthCapacity = healthCap;
        sanity = sanityCapacity = sanityCap;
        soul = soulCapacity = soulCap;
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
                c.sanityCapacity,
                c.soulCapacity,
                c.combatStatistics,
                c.meleeForm,
                c.combatantMeleeWeapons,
                c.combatantArmor
        );
    }

    /**
     * Adjust the health/sanity/soul of this combatant.
     * @param adjustment the amount to adjust the value by. May be positive(healing) or negative(damage).
     * @return whether this combatant is still alive.
     */
    public boolean adjustHealth(int adjustment) {
        if (healthCapacity < 0) return true; //this Combatant has no Body and cannot die from a loss of health
        health += adjustment;
        if (health > getHealthCapacity()) renewHealth();
        return health > 0;
    }
    public void renewHealth() {
        health = getHealthCapacity();
    }
    public boolean adjustSanity(int adjustment) {
        if (sanityCapacity < 0) return true; //this Combatant has no Mind and cannot die from a loss of sanity
        sanity += adjustment;
        if (sanity > getSanityCapacity()) renewSanity();
        return sanity > 0;
    }
    public void renewSanity() {
        sanity = getSanityCapacity();
    }
    public boolean adjustSoul(int adjustment) {
        if (soulCapacity < 0) return true; //this Combatant has no Soul and cannot die by sundering body from mind
        soul += adjustment;
        if (soul > getSoulCapacity()) renewSoul();
        return soul > 0;
    }
    public void renewSoul() {
        soul = getSoulCapacity();
    }
    protected int getHealthCapacity() {
        return healthCapacity;
    }
    protected int getSanityCapacity() {
        return healthCapacity;
    }
    protected int getSoulCapacity() {
        return healthCapacity;
    }
    public double getHealthPercent() {
        return 100.0 * (double)health / (double)getHealthCapacity();
    }
    public double getSanityPercent() {
        return 100.0 * (double)sanity / (double)getSanityCapacity();
    }
    public double getSoulPercent() {
        return 100.0 * (double)soul / (double)getSoulCapacity();
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
