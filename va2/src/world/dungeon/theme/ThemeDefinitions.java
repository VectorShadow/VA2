package world.dungeon.theme;

import resources.continuum.Continuum;
import world.dungeon.generate.PredefinedMapGenerator;
import world.dungeon.generate.WarrenGenerator;
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
    public static final DungeonTheme DARK_GROVE =
            new DungeonTheme(
                    Light.MOONLIGHT,
                    87,
                    72,
                    24,
                    33,
                    new Continuum<>(
                            new WarrenGenerator(),
                            new ArrayList<>()
                    )
            );
}
