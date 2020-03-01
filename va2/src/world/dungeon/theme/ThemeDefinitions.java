package world.dungeon.theme;

import resources.continuum.Continuum;
import resources.continuum.Pair;
import util.ArrayListBuilder;
import world.dungeon.generate.PredefinedMapGenerator;
import world.dungeon.generate.WarrenGenerator;
import world.light.Light;
import world.terrain.TerrainDefinitions;
import world.terrain.TerrainTemplate;

import java.util.ArrayList;

/**
 * Contains the definitions for each available dungeon theme.
 */
public class ThemeDefinitions {
    public static final DungeonTheme YSIAN_ESTATE =
            new DungeonTheme(
                    Light.DIM_LAMP,
                    23,
                    27,
                    1,
                    1,
                    new Continuum<>(
                            new PredefinedMapGenerator(),
                            new ArrayList<>()
                    ),
                    new TerrainSet(
                            new Continuum<>(
                                    TerrainDefinitions.SIMPLE_WALL,
                                    new ArrayList<>()
                            ),
                            new Continuum<>(
                                    TerrainDefinitions.ANCIENT_OAK,
                                    new ArrayList<>()
                            ),
                            new Continuum<>(
                                    TerrainDefinitions.SIMPLE_FLOOR,
                                    new ArrayList<>()
                            ),
                            new Continuum<>(
                                    TerrainDefinitions.GRASSY_FLOOR,
                                    new ArrayList<>()
                            ),
                            TerrainDefinitions.FOREST_GATE
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
                    ),
                    new TerrainSet(
                            new Continuum<>(
                                    TerrainDefinitions.ANCIENT_OAK,
                                    new ArrayList<>()
                            ),
                            new Continuum<>(
                                    TerrainDefinitions.ANCIENT_OAK,
                                    new ArrayList<>()
                            ),
                            new Continuum<>(
                                    TerrainDefinitions.GRASSY_FLOOR,
                                    ArrayListBuilder
                                            .initialize()
                                            .addElement(new Pair<>(0.0125, TerrainDefinitions.TOADSTOOLS))
                                    .build()
                            ),
                            new Continuum<>(
                                    TerrainDefinitions.SHALLOW_WATER,
                                    new ArrayList<>()
                            )
                    )
            );
}
