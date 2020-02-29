package world.dungeon;

import io.out.message.MessageType;
import main.Session;
import world.dungeon.floor.Floor;
import world.dungeon.theme.ThemeDefinitions;

/**
 * Keeps track of a specific instance of a dungeon.
 */
public class Dungeon {

    private final int DEPTH;
    private final DungeonTemplate DUNGEON_TEMPLATE;

    public Dungeon(DungeonTemplate dt) {
        DEPTH = dt.rollDepth();
        DUNGEON_TEMPLATE = dt;
    }
    public void nextFloor() {
        int currentDepth = Session.getCurrentFloor().DEPTH;
        Floor f;
        if (currentDepth >= DEPTH) {
            //todo - add a completion bonus in loot and xp
            Session.getMessageCenter().sendMessage("You have cleared the dungeon.", MessageType.SUCCESS);
            exitDungeon(true);
            return;
        }
        Session.getMessageCenter().sendMessage("You make your way deeper into the dungeon.", MessageType.INFO);
        Session.setCurrentFloor(DUNGEON_TEMPLATE.DUNGEON_THEME.generateFloor(currentDepth + 1));
    }
    public void exitDungeon(boolean fullRewards) {
        //todo - award accumulated loot and xp, applying a penalty if not fullRewards, then zero them out
        Session.getMessageCenter().sendMessage(
                fullRewards ?
                        "You leave the dungeon and return to your estate." :
                        "You flee the dungeon and escape to your estate.",
                fullRewards ? MessageType.INFO : MessageType.WARNING
        );
        Session.setCurrentFloor(ThemeDefinitions.YSIAN_ESTATE.generateFloor(0));
    }
}
