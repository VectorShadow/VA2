package world.dungeon.theme;

import resources.continuum.Continuum;
import world.dungeon.generate.PredefinedMapGenerator;
import world.light.Light;

import java.util.ArrayList;

/**
 * Contains the definitions for each available dungeon theme.
 */
public class ThemeDefinitions {
    public static final DungeonTheme YSIAN_ESTATE =
            new DungeonTheme(
                    Light.DIM_LAMP,
                    21,
                    25,
                    1,
                    1,
                    new Continuum<>(
                            new PredefinedMapGenerator(),
                            new ArrayList<>()
                    )
            );
}
