package combat;

import combat.melee.forms.Form;
import main.Session;
import resources.continuum.Continuum;
import resources.continuum.Pair;
import resources.glyph.GlyphString;
import util.Format;
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
        if (health > getHealthCapacity()) health = getHealthCapacity();
        return health > 0;
    }
    public void renewHealth() {
        renewHealth(1.0);
    }
    public void renewHealth(double pct) {
        adjustHealth((int)((double)getHealthCapacity() * pct));
    }
    public boolean adjustSanity(int adjustment) {
        if (sanityCapacity < 0) return true; //this Combatant has no Mind and cannot die from a loss of sanity
        sanity += adjustment;
        if (sanity > getSanityCapacity()) sanity = getSanityCapacity();
        return sanity > 0;
    }
    public void renewSanity() {
        renewSanity(1.0);
    }
    public void renewSanity(double pct) {
        adjustSanity((int)((double)getSanityCapacity() * pct));
    }
    public boolean adjustSoul(int adjustment) {
        if (soulCapacity < 0) return true; //this Combatant has no Soul and cannot die by sundering body from mind
        soul += adjustment;
        if (soul > getSoulCapacity()) soul = getSoulCapacity();
        return soul > 0;
    }
    public void renewSoul() {
        renewSoul(1.0);
    }
    public void renewSoul(double pct) {
        adjustSoul((int)((double)getSoulCapacity() * pct));
    }
    protected int getHealthCapacity() {
        return healthCapacity;
    }
    protected int getSanityCapacity() {
        return sanityCapacity;
    }
    protected int getSoulCapacity() {
        return soulCapacity;
    }
    public GlyphString printHealth() {
        return Format.colorCode(
                health + "/" + getHealthCapacity(),
                (double)health / (double)getHealthCapacity());
    }
    public GlyphString printSanity() {
        if (getSanityCapacity() < 0)
            return new GlyphString("<n/a>", Session.getColorScheme().getBackground(), Session.getColorScheme().getForeground());
        double pct = (double)sanity / (double)getSanityCapacity();
        return Format.colorCode(
                Format.percent(100 * pct, 0),
                pct
        );
    }
    public GlyphString printSoul() {
        if (getSoulCapacity() < 0)
            return new GlyphString("<n/a>", Session.getColorScheme().getBackground(), Session.getColorScheme().getForeground());
        double pct = (double)soul / (double)getSoulCapacity();
        return Format.colorCode(
                pct > 1.0 ? "Uplifted" : pct > .9 ? "Whole" : pct > .667 ? "Intact" : pct > 0.333 ? "Weak" : "Fading",
                pct
        );
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

    @Override
    public Combatant clone() {
        return new Combatant(this);
    }

    @Override
    public String toString() {
        return "H:" + health + "/" + healthCapacity + "[" + getHealthCapacity() + "] M:" + sanity + "/" + sanityCapacity + "[" + getSanityCapacity() + "] S:" + soul + "/" + soulCapacity + "[" + getSoulCapacity() + "]";
    }
}
