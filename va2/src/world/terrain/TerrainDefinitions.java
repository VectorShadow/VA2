package world.terrain;

import resources.chroma.Chroma;
import world.ColorStandards;
import world.WorldObjectTemplateFactory;

import java.awt.*;

import static io.out.GUIManager.*;

/**
 * Contains the definitions for each available TerrainTemplate.
 */
public class TerrainDefinitions {
    public static TerrainTemplate EMPTY =
            WorldObjectTemplateFactory.initialize().manufactureTerrainTemplate(true, false, false);
    public static TerrainTemplate PERMANENT_WALL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("an impenetrable wall")
                    .setSymbols('#')
                    .setImage(GFX_ROW_TERRAIN,1)
                    .setBackgroundColors(Chroma.dark(Chroma.GREY))
                    .setForegroundColors(Chroma.BLACK)
                    .manufactureTerrainTemplate(false, false, false);
    public static TerrainTemplate SIMPLE_FLOOR =
            WorldObjectTemplateFactory
                    .initialize(true)
                    .setName("a simple floor")
                    .setSymbols('.')
                    .setImage(GFX_ROW_TERRAIN, 3)
                    .manufactureTerrainTemplate(true, true, false);
    public static TerrainTemplate SIMPLE_WALL =
            WorldObjectTemplateFactory
                    .initialize(true)
                    .setName("a simple wall")
                    .setSymbols('#')
                    .setImage(GFX_ROW_TERRAIN, 1)
                    .setForegroundColors(Color.LIGHT_GRAY.darker())
                    .manufactureTerrainTemplate(false, false, false);
    public static TerrainTemplate GRASSY_FLOOR =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("grass covered ground")
                    .setSymbols('.')
                    .setImage(GFX_ROW_TERRAIN, 5)
                    .setForegroundColors(ColorStandards.FOREST_UNDERGROWTH)
                    .manufactureTerrainTemplate(true, true, false);
    public static TerrainTemplate TOADSTOOLS =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("a cluster of toadstools")
                    .setSymbols(',')
                    .setForegroundColors(Chroma.BEIGE)
                    .setSecondaryColors(ColorStandards.FOREST_UNDERGROWTH)
                    .setImage(GFX_ROW_TERRAIN, 8)
                    .manufactureTerrainTemplate(true, true, false);
    public static TerrainTemplate ANCIENT_OAK =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("an ancient oak tree")
                    .setSymbols('#')
                    .setForegroundColors(ColorStandards.ANCIENT_OAK)
                    .setImage(GFX_ROW_TERRAIN, 6)
                    .manufactureTerrainTemplate(true, false, false);
    public static TerrainTemplate WEB_COVERED_OAK =
            WorldObjectTemplateFactory
                    .initialize(true)
                    .setName("an ancient oak tree covered in spider webs")
                    .setSymbols(
                            new char[]{
                                    '#',
                                    '*'
                            }
                    )
                    .setForegroundColors(
                            new Color[]{
                                    Chroma.WHITE,
                                    Chroma.WHITE,
                                    Chroma.WHITE,
                                    ColorStandards.ANCIENT_OAK,
                                    ColorStandards.ANCIENT_OAK
                            }
                    )

                    .setSecondaryColors(ColorStandards.ANCIENT_OAK)
                    .setTertiaryColors(ColorStandards.FOREST_UNDERGROWTH)
                    .setImage(GFX_ROW_TERRAIN, 7)
                    .manufactureTerrainTemplate(true, false, false);
    public static TerrainTemplate FOREST_GATE =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("a gateway leading into the forest")
                    .setSymbols('>')
                    .setForegroundColors(Color.GREEN.darker())
                    .setSecondaryColors(ColorStandards.ANCIENT_OAK)
                    .setImage(GFX_ROW_TERRAIN, 10)
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate LIBRARY_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("the door to your Library")
                    .setSymbols('<')
                    .setImage(GFX_ROW_TERRAIN, 2)
                    .setForegroundColors(Color.PINK)
                    .setSecondaryColors(Color.LIGHT_GRAY.darker())
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate HALL_OF_ARMS_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("the door to your Hall of Arms")
                    .setSymbols('<')
                    .setImage(GFX_ROW_TERRAIN, 2)
                    .setForegroundColors(Color.RED)
                    .setSecondaryColors(Color.LIGHT_GRAY.darker())
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate ARCHERY_RANGE_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("the door to your Archery Range")
                    .setSymbols('<')
                    .setImage(GFX_ROW_TERRAIN, 2)
                    .setForegroundColors(Color.GREEN)
                    .setSecondaryColors(Color.LIGHT_GRAY.darker())
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate LABORATORY_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("the door to your Laboratory")
                    .setSymbols('<')
                    .setImage(GFX_ROW_TERRAIN, 2)
                    .setForegroundColors(Color.BLUE)
                    .setSecondaryColors(Color.LIGHT_GRAY.darker())
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate TROPHY_HALL_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("the door to your Trophy Hall")
                    .setSymbols('<')
                    .setImage(GFX_ROW_TERRAIN, 2)
                    .setForegroundColors(Color.YELLOW)
                    .setSecondaryColors(Color.LIGHT_GRAY.darker())
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate MAUSOLEUM_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("the door to your Mausoleum")
                    .setSymbols('<')
                    .setImage(GFX_ROW_TERRAIN, 2)
                    .setForegroundColors(Color.DARK_GRAY)
                    .setSecondaryColors(Color.LIGHT_GRAY.darker())
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate FORGE_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("the door to your Forge")
                    .setSymbols('<')
                    .setImage(GFX_ROW_TERRAIN, 2)
                    .setForegroundColors(Color.ORANGE)
                    .setSecondaryColors(Color.LIGHT_GRAY.darker())
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate WORKSHOP_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("the door to your Workshop")
                    .setSymbols('<')
                    .setImage(GFX_ROW_TERRAIN, 2)
                    .setForegroundColors(Color.GRAY)
                    .setSecondaryColors(Color.LIGHT_GRAY.darker())
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate WAREHOUSE_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("the door to your Warehouse")
                    .setSymbols('<')
                    .setImage(GFX_ROW_TERRAIN, 2)
                    .setForegroundColors(Color.LIGHT_GRAY)
                    .setSecondaryColors(Color.LIGHT_GRAY.darker())
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate ARMORY_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("the door to your Armory")
                    .setSymbols('<')
                    .setImage(GFX_ROW_TERRAIN, 2)
                    .setForegroundColors(Color.CYAN)
                    .setSecondaryColors(Color.LIGHT_GRAY.darker())
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate RITUAL_CHAMBER_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("the door to your Ritual Chamber")
                    .setSymbols('<')
                    .setImage(GFX_ROW_TERRAIN, 2)
                    .setForegroundColors(Color.MAGENTA)
                    .setSecondaryColors(Color.LIGHT_GRAY.darker())
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate CLEAR_GLASS_WINDOW =
            WorldObjectTemplateFactory
                    .initialize(true)
                    .setName("a window of clear glass")
                    .setSymbols('=')
                    .setImage(GFX_ROW_TERRAIN, 4)
                    .setForegroundColors(Color.CYAN.brighter())
                    .manufactureTerrainTemplate(true, false, false);
    public static TerrainTemplate SHALLOW_WATER =
            WorldObjectTemplateFactory
                    .initialize(true)
                    .setName("a forest stream")
                    .setSymbols('~')
                    .setForegroundColors(ColorStandards.FOREST_STREAM)
                    .setImage(GFX_ROW_TERRAIN, 9)
                    .manufactureTerrainTemplate(true, true, false);
    public static TerrainTemplate FLIGHT_STAIR =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("a pathway offering a hasty escape")
                    .setSymbols('<')
                    .setForegroundColors(Chroma.YELLOW)
                    .setSecondaryColors(Chroma.GREY)
                    .setImage(GFX_ROW_TERRAIN, 13)
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate REWARD_STAIR =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("a pathway offering a deliberate exit")
                    .setSymbols('<')
                    .setForegroundColors(Chroma.BLUE)
                    .setSecondaryColors(Chroma.GREY)
                    .setImage(GFX_ROW_TERRAIN, 12)
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate NEXT_FLOOR_STAIR =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("a pathway leading deeper into the dungeon")
                    .setSymbols('>')
                    .setForegroundColors(Chroma.ORANGE)
                    .setSecondaryColors(Chroma.GREY)
                    .setImage(GFX_ROW_TERRAIN, 11)
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate TANGLED_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("a strange and tangled mass of ancient vines and webs")
                    .setSymbols('&')
                    .setForegroundColors(
                            new Color[]{
                                    ColorStandards.HUNTING_SPIDER_BODY,
                                    ColorStandards.ANCIENT_OAK,
                                    ColorStandards.FOREST_UNDERGROWTH,
                                    Chroma.BROWN,
                                    Chroma.GREEN
                            }
                            )
                    .setSecondaryColors(ColorStandards.ANCIENT_OAK)
                    .setTertiaryColors(
                            new Color[]{
                                    Chroma.BROWN,
                                    Chroma.ICHOR_BLUE,
                                    Chroma.ELEMENTAL_ACID,
                                    Chroma.RED,
                                    ColorStandards.HUNTING_SPIDER_HOURGLASS
                            }
                    )
                    .setImage(GFX_ROW_TERRAIN, 14)
                    .manufactureTerrainTemplate(true, true, true);
}
