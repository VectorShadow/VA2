package world.dungeon.generate;

import world.dungeon.floor.Floor;
import world.dungeon.theme.DungeonTheme;

/**
 * The base class for Floor Generators.
 */
public abstract class AbstractGenerator {
    public abstract Floor generate(DungeonTheme dungeonTheme, int floorDepth);
}
