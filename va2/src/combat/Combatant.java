package combat;

/**
 * Contains the base combat stats for an actor.
 * Modifiable for the player via level up, permanent for npc.
 * CombatResolvers should function on Actors, which will derive values from these base stats
 * and further modify them as necessary(via permanent ability, form modification, temporary effect, etc.)
 */
public class Combatant {
    private int healthCapacity; //the maximum health of this combatant
    private int health; //the current health of this combatant
    private int accuracy; //the ability of this combatant to use weapons
    private int evasion; //the ability of this combatant to avoid incoming attacks
    private int precision; //the ability of this combatant to target vulnerable areas
    private int defense; //the ability of this combatant to protect vulnerable areas
    //todo - we probably want damage type modifications here as well.

    public Combatant(int hea, int acc, int eva, int pre, int def) {
        health = healthCapacity = hea;
        accuracy = acc;
        evasion = eva;
        precision = pre;
        defense = def;
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
        return accuracy;
    }

    public int getDefense() {
        return defense;
    }

    public int getEvasion() {
        return evasion;
    }

    public int getPrecision() {
        return precision;
    }
}
