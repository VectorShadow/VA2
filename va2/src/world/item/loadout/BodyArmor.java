package world.item.loadout;

import combat.armor.ArmorDefinitions;
import main.Session;
import world.item.Armor;

public class BodyArmor extends LoadoutModule {
    private Armor armor;

    public Armor wear(Armor a) {
        Armor oldArmor = armor;
        armor = a;
        Session.getPlayer().getActor().getCombatant().setArmor(
                armor == null
                        ? ArmorDefinitions.UNARMORED //todo - change to an armor!
                        : armor
        );
        return oldArmor;
    }

    public Armor showArmor() {
        return armor;
    }
}
