package combat;

import combat.armor.ArmorDefinitions;
import combat.melee.forms.FormDefinitions;
import combat.melee.weapons.MeleeWeaponDefinitions;
import main.Session;
import util.InputSimplifier;

/**
 * The player's combatant. Uses the player level for all combat stats.
 */
public class PlayerCombatant extends Combatant {

    public PlayerCombatant() {
        super(
                0,
                0,
                0,
                new int[]{0,0,0,0,0},
                FormDefinitions.UNTRAINED,
                MeleeWeaponDefinitions.BARE_HANDED(),
                ArmorDefinitions.UNARMORED()
        );
    }

    @Override
    protected int getHealthCapacity() {
        return 128 + InputSimplifier.getValue(Session.getPlayer().getExperience().getLevel());
    }

    @Override
    protected int getSanityCapacity() {
        return 64 + InputSimplifier.getValue(Session.getPlayer().getExperience().getLevel()) / 2;
    }
    @Override
    protected int getSoulCapacity() {
        return 16 + InputSimplifier.getValue(Session.getPlayer().getExperience().getLevel()) / 8;
    }

    @Override
    public int getStatistic(int index) {
        return InputSimplifier.getValue(
                super.getStatistic(index) + //we can use the base stats for estate room directed level ups
                        Session.getPlayer().getExperience().getLevel() + //use the player level
                        meleeForm.adjustStatistic(index) //and still add the melee form bonus
        );
    }
    @Override
    public PlayerCombatant clone() {
        return this;
    }
}
