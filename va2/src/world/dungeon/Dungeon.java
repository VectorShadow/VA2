package world.dungeon;

import io.out.message.MessageType;
import main.Session;
import world.dungeon.floor.Floor;
import world.dungeon.theme.DungeonTheme;
import world.dungeon.theme.ThemeDefinitions;

import java.io.Serializable;

/**
 * Keeps track of a specific instance of a dungeon.
 */
public class Dungeon implements Serializable {

    private final DungeonTemplate DUNGEON_TEMPLATE;

    public Dungeon(DungeonTemplate dt) {
        DUNGEON_TEMPLATE = dt;
    }
    public void nextFloor() {
        int currentDepth = Session.getCurrentFloor().DEPTH;
        if (currentDepth == DUNGEON_TEMPLATE.getDepth()) {
            //todo - generate the final floor from the DUNGEON_TEMPLATE, using PredefinedFloorGenerator
            //todo - populate from the template
            //todo - send a special message
            throw new UnsupportedOperationException("Boss floors not implemented!");
        } else if (currentDepth > DUNGEON_TEMPLATE.getDepth()) {
            //todo - add a completion bonus in loot and xp
            Session.getMessageCenter().sendMessage("You have cleared the dungeon.", MessageType.SUCCESS);
            exitDungeon(true);
            return;
        } else {
            Session.getMessageCenter().sendMessage("You make your way deeper into the dungeon.", MessageType.INFO);
            Session.setCurrentFloor(new Floor(currentDepth + 1, DUNGEON_TEMPLATE.DUNGEON_THEME));
        }
    }
    public void exitDungeon(boolean fullRewards) {
        //todo - award accumulated loot and xp, applying a penalty if not fullRewards, then zero them out
        Session.getMessageCenter().sendMessage(
                fullRewards ?
                        "You leave the dungeon and return to your estate." :
                        "You flee the dungeon and escape to your estate.",
                fullRewards ? MessageType.INFO : MessageType.WARNING
        );
        Session.setCurrentFloor(new Floor(0, ThemeDefinitions.YSIAN_ESTATE));
    }
    public DungeonTheme getTheme() {
        return DUNGEON_TEMPLATE.DUNGEON_THEME;
    }
}
