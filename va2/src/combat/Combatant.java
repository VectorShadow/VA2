package combat;

import combat.melee.forms.Form;
import combat.melee.weapons.MeleeWeapon;
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
    private int healthCapacity; //the maximum health of this combatant
    private int health; //the current health of this combatant
    private int accuracy; //the ability of this combatant to use weapons
    private int evasion; //the ability of this combatant to avoid incoming attacks
    private int precision; //the ability of this combatant to target vulnerable areas
    private int defense; //the ability of this combatant to protect vulnerable areas
    private int strength; //the physical power of this combatant

    private Form meleeForm; //the form this combatant uses in melee combat

    private Continuum<MeleeWeapon> meleeWeapons; //a continuum of melee weapons available to this combatant
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
            MeleeWeapon defaultMeleeWeapon
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
            Continuum<MeleeWeapon> defaultMeleeWeapons
    ) {
        health = healthCapacity = hea;
        accuracy = acc;
        evasion = eva;
        precision = pre;
        defense = def;
        strength = str;
        meleeForm = defaultForm;
        meleeWeapons = defaultMeleeWeapons;
    }
    private Combatant(Combatant c) {
        this(c.healthCapacity, c.accuracy, c.evasion, c.precision, c.defense, c.strength, c.meleeForm, c.meleeWeapons);
    }

    /**
     * Adjust the health of this combatant.
     * @param adjustment the amount to adjust the health value by. May be positive(healing) or negative(damage).
     * @return whether this combatant is still alive.
     */
    public boolean adjustHealth(int adjustment) {
        health += adjustment;
        if (health > healthCapacity) health = healthCapacity;
        return health > 0;
    }
    public double getHealthPercent() {
        return 100.0 * (double)health / (double)healthCapacity;
    }

    public int getAccuracy() {
        return accuracy + meleeForm.adjustAccuracy();
    }

    public int getEvasion() {
        return evasion + meleeForm.adjustEvasion();
    }

    public int getPrecision() {
        return precision + meleeForm.adjustPrecision();
    }
    public int getDefense() {
        return defense + meleeForm.adjustDefense();
    }

    public int getStrength() {
        return strength + meleeForm.adjustStrength();
    }

    public Form getMeleeForm() {
        return meleeForm;
    }

    public MeleeWeapon selectMeleeWeapon() {
        return meleeWeapons.getValue(Session.getRNG());
    }
    public void setMeleeWeapons(Continuum<MeleeWeapon> mwc) {
        meleeWeapons = mwc;
    }
    public void setMeleeWeapons(MeleeWeapon mw) {
        setMeleeWeapons(new Continuum<>(mw, new ArrayList<>()));
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

    @Override
    public Combatant clone() {
        return new Combatant(this);
    }
}
