package world.item.loadout;

import world.item.define.ArmorDefinitions;
import main.Session;
import world.item.Armor;

public class BodyArmor extends LoadoutModule {
    private Armor armor;

    public Armor wear(Armor a) {
        Armor oldArmor = armor;
        armor = a;
        Session.getPlayer().getActor().getCombatant().setArmor(
                armor == null
                        ? ArmorDefinitions.UNARMORED()
                        : armor
        );
        return oldArmor;
    }

    public Armor showArmor() {
        return armor;
    }
}
