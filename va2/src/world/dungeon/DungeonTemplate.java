package world.dungeon;

import world.dungeon.theme.DungeonTheme;

import java.io.Serializable;

/**
 * Contains information required to build a specific dungeon.
 */
public class DungeonTemplate implements Serializable {
    private final int DEPTH;
    public final DungeonTheme DUNGEON_THEME;
    //todo - String[] to build last floor map from - probably also want one for monsters

    public DungeonTemplate(int depth, DungeonTheme dungeonTheme) {
        DEPTH = depth;
        DUNGEON_THEME = dungeonTheme;
    }

    int getDepth() {
        return DEPTH;
    }
}
