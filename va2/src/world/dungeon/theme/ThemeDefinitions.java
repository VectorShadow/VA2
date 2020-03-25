package world.dungeon.theme;

import resources.continuum.Continuum;
import resources.continuum.Pair;
import util.ArrayListBuilder;
import world.actor.Actor;
import world.actor.ActorDefinitions;
import world.actor.ActorTemplate;
import world.dungeon.generate.PredefinedMapGenerator;
import world.dungeon.generate.WarrenGenerator;
import world.light.Light;
import world.terrain.TerrainDefinitions;

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
                    0,
                    1,
                    1,
                    1,
                    0,
                    0,
                    new String[] {
                            "                           ",
                            "                           ",
                            "                           ",
                            "                           ",
                            "                           ",
                            "                           ",
                            "                           ",
                            "                           ",
                            "                           ",
                            "                           ",
                            "                           ",
                            "                           ",
                            "                           ",
                            "                           ",
                            "                           ",
                            "                           ",
                            "                           ",
                            "                           ",
                            "                           ",
                            "                           ",
                            "                           ",
                            "                           ",
                            "                           "
                    },
                    new String[] {
                            "!!!!!!!!!!!!!!!!!!!!!!!!!!!",
                            "!*************************!",
                            "!**;*;;;*;;;*>**;*;;;**;**!",
                            "!*;;;*;;;;*;;;*;;;;;;*;;;*!",
                            "!**;;;;;;;*;;;;;;;;*;;;;;*!",
                            "!*;;;;*;;;;;*;;;;;;;;;;***!",
                            "!##==##==#*;;;;;*#==##==##!",
                            "!##......#**;;;**#......##!",
                            "!##......#*;;;;;*#......##!",
                            "!#`......#*;;;;;*#......0#!",
                            "!##......#*;;;;;*#......##!",
                            "!##......#***;***#......##!",
                            "!##......####;####......##!",
                            "!#1.....................9#!",
                            "!##.....................##!",
                            "!##.....................##!",
                            "!##.....................##!",
                            "!#2.....................8#!",
                            "!##.....................##!",
                            "!##.....................##!",
                            "!####3###4###5###6###7####!",
                            "!#########################!",
                            "!!!!!!!!!!!!!!!!!!!!!!!!!!!"
                    },
                    new Continuum<>(
                            new PredefinedMapGenerator(),
                            new ArrayList<>()
                    ),
                    new ActorSet(
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            new ActorTemplate[]{}
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
                            TerrainDefinitions.FOREST_GATE,
                            null
                    )
            );
    public static final DungeonTheme DARK_GROVE =
            new DungeonTheme(
                    Light.MOONLIGHT,
                    87,
                    72,
                    21,
                    24,
                    33,
                    7,
                    4,
                    5,
                    new String[] {
                            "                                                      ",
                            "                                                      ",
                            "                                                      ",
                            "                                                      ",
                            "      1                                               ",
                            "                                                      ",
                            "                  1          1                   1    ",
                            "                         2 2                          ",
                            "                             2               1        ",
                            "                                                      ",
                            "                                                  3   ",
                            "    2 2                                               ",
                            "        2                                             ",
                            "       2                                              ",
                            "     2                                                ",
                            "                                                      ",
                            "                                                      ",
                            "                                 4                    ",
                            "                                                      ",
                            "                                                      ",
                            "                                                      ",
                            "                                                      ",
                            "                                                      ",
                            "           2       1        2                         ",
                            "                                                      ",
                            "         2                  2 2                       ",
                            "          2 2                    1                    ",
                            "                                   1                  ",
                            "                                                      ",
                            "                                                      ",
                            "                                                      ",
                            "                                                      ",
                            "                                                      ",
                            "                                                      ",
                            "       5 5                                    3  3    ",
                            "        0                                             ",
                            "                                                      ",
                            "                                                      "
                    },
                    new String[] {
                            "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!",
                            "!####################################################!",
                            "!##...###############################################!",
                            "!##.<.##############....#############################!",
                            "!##.....###########..###.############################!",
                            "!######..#########..#####....###############......###!",
                            "!########.#######...####.......############........##!",
                            "!########.#######..######.......#########......#....#!",
                            "!#######..######..#########....########..##...###...#!",
                            "!######.########..############...#####.#####....##..#!",
                            "!###...####..##.################......#######.......#!",
                            "!##.....##.##..###############################....###!",
                            "!##..#....###################################...#####!",
                            "!###......#############;;;#################...#######!",
                            "!####...#############;;;;;;;;#;##########...#########!",
                            "!##################;;;####;;;;;;;;;;#####.##########;!",
                            "!#################;;;######;;..;..;;;##..##########;;!",
                            "!################;;#########;;;....;;...##########;;#!",
                            "!###########;;;;;;#########..;;;...#;;###########;;;#!",
                            "!######;##;;###;;########..###;;;#...;############;##!",
                            "!###;;;#;;###############.######;;;;;;########;;;;###!",
                            "!;;;;##########..########.##########;;#######;;######!",
                            "!#;;#####............#####.##########;;;###;;;#######!",
                            "!#######..............#####..##########;;;;;;########!",
                            "!#####......###......######.....##########;;#########!",
                            "!######......####....#######......###################!",
                            "!########......######.########......######...########!",
                            "!############...######..#########.........###.#######!",
                            "!###############.#######..####################.######!",
                            "!################..#######.###################..#####!",
                            "!##########....####.######.####################.#####!",
                            "!##########.##..###.######..#######..##########.#####!",
                            "!#########..###..#.#######..#####.###..########.#####!",
                            "!######....######.##########..####.####.#####.....###!",
                            "!######.$.####################.###.#####...........##!",
                            "!######...#####################...##########.......##!",
                            "!####################################################!",
                            "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
                    },
                    new Continuum<>(
                            new WarrenGenerator(),
                            new ArrayList<>()
                    ),
                    new ActorSet(
                            new Continuum<>(
                                    ActorDefinitions.WOODLAND_SPIDER,
                                    ArrayListBuilder
                                            .initialize()
                                            .addElement(new Pair<>(0.15, ActorDefinitions.LARGE_HUNTING_SPIDER))
                                            .addElement(new Pair<>(0.025, ActorDefinitions.GIANT_HUNTING_SPIDER))
                                            .build()
                            ),
                            new Continuum<>(
                                    ActorDefinitions.FOREST_WOLF_TEMPLATE,
                                    ArrayListBuilder
                                            .initialize()
                                            .addElement(new Pair<>(0.25, ActorDefinitions.SILVER_WOLF_TEMPLATE))
                                            .build()
                            ),
                            new Continuum<>(
                                    ActorDefinitions.BLACK_BEAR_TEMPLATE,
                                    new ArrayList<>()
                            ),
                            new Continuum<>(
                                    ActorDefinitions.CAVE_BEAR_TEMPLATE,
                                    new ArrayList<>()
                            ),
                            new Continuum<>(
                                    ActorDefinitions.FOREST_WOLF_TEMPLATE,
                                    new ArrayList<>()
                            ),
                            new Continuum<>(
                                    null,
                                    ArrayListBuilder
                                            .initialize()
                                            .addElement(new Pair<>(0.15, ActorDefinitions.ANCIENT_WEB_CRAWLER_TEMPLATE))
                                            .build()
                            ),
                            new ActorTemplate[]{
                                    ActorDefinitions.GIANT_WATER_MOCCASIN, //todo - placeholder!
                                    ActorDefinitions.GIANT_HUNTING_SPIDER,
                                    ActorDefinitions.SILVER_WOLF_TEMPLATE,
                                    ActorDefinitions.CAVE_BEAR_TEMPLATE,
                                    ActorDefinitions.GIANT_WATER_MOCCASIN,
                                    ActorDefinitions.ANCIENT_WEB_CRAWLER_TEMPLATE
                            }
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
                            ),
                            TerrainDefinitions.TANGLED_PORTAL
                    )
            );
}
