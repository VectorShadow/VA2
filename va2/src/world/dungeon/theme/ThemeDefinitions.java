package world.dungeon.theme;

import io.out.DisplayStandards;
import main.Session;
import resources.chroma.Chroma;
import resources.continuum.Continuum;
import resources.continuum.Pair;
import resources.glyph.GlyphString;
import util.ArrayListBuilder;
import world.ColorStandards;
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
    public static final int INNATE = 0x0;
    public static final int YSIAN_ESTATE = 0x1;
    public static final int DARK_GROVE = 0x2;
    //todo ...
    public static final int ANY = 0xf;

    public static int getIndex(DungeonTheme dt) {
        for (int i = 0; i < DUNGEON_THEMES.length; ++i) {
            if (DUNGEON_THEMES[i].getName().getText().equals(dt.getName().getText())) return i;
        }
        throw new IllegalArgumentException("Dungeon Theme " + dt.getName().getText() + " unknown!");
    }

    /**
     * For Reward XP calculations, etc.
     */
    public static int getDifficulty(DungeonTheme dt) {
        switch (getIndex(dt)) {
            case YSIAN_ESTATE: return 0;
            case DARK_GROVE: return 5;
            default: throw new IllegalArgumentException("Theme " + dt.getName().getText() + " has no rated difficulty.");
        }
    }
    public static final DungeonTheme[] DUNGEON_THEMES = new DungeonTheme[]{
            new DungeonTheme(
                    null,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    -1,
                    new GlyphString("INNATE", Session.getColorScheme().getBackground(), Chroma.WHITE),
                    new String[]{},
                    new String[]{},
                    new Continuum<>(
                            null,
                            new ArrayList<>()
                    ),
                    new ActorSet(new Continuum[]{null, null, null, null, null, null}, new ActorTemplate[]{}),
                    new TerrainSet(null, null, null, null, null)
            ),
            new DungeonTheme(
                    Light.DIM_LAMP,
                    23,
                    27,
                    0,
                    1,
                    1,
                    1,
                    0,
                    new GlyphString("Ysian Estate", Session.getColorScheme().getBackground(), Chroma.WHITE),
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
                            new Continuum[]{
                            null,
                            null,
                            null,
                            null,
                            null,
                            null},
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
            ),
            new DungeonTheme(
                    Light.UNLIGHTED,
                    87,
                    72,
                    21,
                    24,
                    33,
                    7,
                    4,
                    new GlyphString("The Dark Grove", Session.getColorScheme().getBackground(), DisplayStandards.THEME_THE_DARK_GROVE.getForeground()),
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
                            "       505                                    3  3    ",
                            "                                                      ",
                            "                                                      ",
                            "                                                      "
                    },
                    new String[] {
                            "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!",
                            "!####################################################!",
                            "!##...######**#*##########*#########*################!",
                            "!##.<.##############....###*#########***#############!",
                            "!##.....###*####*##..###.#######################*####!",
                            "!#*####..###**####..#####....###############......###!",
                            "!########.#######...####.......##*#########........##!",
                            "!#####*##.#######..##*###.......#########......#....#!",
                            "!#*###*#..######..#####*###....########..##...**#...#!",
                            "!####*#.########..############...##***.#####....*#..#!",
                            "!###...####..##.#######**#######......#######.......#!",
                            "!##.....*#.##..######################*####**##....###!",
                            "!##..*....#####**############**##############...*####!",
                            "!###......####***######;;;###############*#...#######!",
                            "!####...#**#####*####;;;;;;;;#;##**######...#########!",
                            "!#*################;;;####;;;;;;;;;;#####.##*#######;!",
                            "!####*######***###;;;###**#;;..;..;;;##..####*#*###;;!",
                            "!##############*#;;#*#######;;;....;;...######***#;;#!",
                            "!#######*##*;;;;;;###*#####..;;;...#;;#**#######*;;;#!",
                            "!######;##;;###;;########..###;;;#...;############;##!",
                            "!###;;;#;;#**#######*####.######;;;;;;##*#####;;;;###!",
                            "!;;;;#######*##..##**####.##########;;*###*##;;**####!",
                            "!#;;#####............#*###.###**#####;;;###;;;#######!",
                            "!#######..............*####..#####*####;;;;;;*#######!",
                            "!###*#......###......######.....##########;;#####*###!",
                            "!###**#......####....##*####......#**####*****#####*#!",
                            "!####*###......###**#.#*######......######...#*#***#*!",
                            "!####*#######...######..####**###.........###.######*!",
                            "!####*##########.#**####..######**#*####*#####.####*#!",
                            "!##**#######**###..*######.*#############**###..##*##!",
                            "!#######**#....####.#####*.#########***#####**#.#**##!",
                            "!#####**#*#.**..#**.###**#..###**#...##########.#####!",
                            "!####*****..#*#..#.######*..####*.*##..##*#####.*####!",
                            "!*##***....**####.###**#####..####.#***.#####.....###!",
                            "!#*##**.$.***####*############.#*#.#####...........*#!",
                            "!###***...#****#**##########**#...##*####*##.......*#!",
                            "!####*********##*###############***#**####*##########!",
                            "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
                    },
                    new Continuum<>(
                            new WarrenGenerator(),
                            new ArrayList<>()
                    ),
                    new ActorSet(
                            new Continuum[]{
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
                                            .addElement(new Pair<>(0.075, ActorDefinitions.RABID_WOLF_TEMPLATE))
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
                                    ActorDefinitions.GIANT_WATER_MOCCASIN,
                                    new ArrayList<>()
                                    ),
                                    new Continuum<>(
                                    null,
                                    ArrayListBuilder
                                            .initialize()
                                            .addElement(new Pair<>(0.15, ActorDefinitions.ANCIENT_WEB_CRAWLER_TEMPLATE))
                                            .build()
                                    )
                            },
                            new ActorTemplate[]{
                                    ActorDefinitions.SILK_MUMMY_TEMPLATE,
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
                                    ArrayListBuilder
                                            .initialize()
                                            .addElement(new Pair<>(0.03, TerrainDefinitions.WEB_COVERED_OAK))
                                            .build()
                            ),
                            new Continuum<>(
                                    TerrainDefinitions.WEB_COVERED_OAK,
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
            ),
    };
}
