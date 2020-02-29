package world.dungeon;

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
            exitDungeon(true);
            return;
        }
        Session.setCurrentFloor(DUNGEON_TEMPLATE.DUNGEON_THEME.generateFloor(currentDepth + 1));
    }
    public void exitDungeon(boolean fullRewards) {
        //todo - award accumulated loot and xp, applying a penalty if not fullRewards, then zero them out
        Session.setCurrentFloor(ThemeDefinitions.YSIAN_ESTATE.generateFloor(0));
    }
}
