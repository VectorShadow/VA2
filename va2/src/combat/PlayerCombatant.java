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
                FormDefinitions.UNTRAINED,
                MeleeWeaponDefinitions.BARE_HANDED,
                ArmorDefinitions.UNARMORED
        );
    }

    @Override
    public int getHealthCapacity() {
        return 128 + InputSimplifier.getValue(Session.getPlayer().getExperience().getLevel());
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