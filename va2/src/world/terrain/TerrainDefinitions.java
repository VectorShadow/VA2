package world.terrain;

import resources.chroma.Chroma;
import world.ColorStandards;
import world.WorldObjectTemplateFactory;

import java.awt.*;
import java.lang.reflect.Array;

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
                    .setBackgroundColors(Chroma.dark(Chroma.GREY))
                    .setForegroundColors(Chroma.BLACK)
                    .manufactureTerrainTemplate(false, false, false);
    public static TerrainTemplate SIMPLE_FLOOR =
            WorldObjectTemplateFactory
                    .initialize(true)
                    .setName("a simple floor")
                    .setSymbols('.')
                    .manufactureTerrainTemplate(true, true, false);
    public static TerrainTemplate SIMPLE_WALL =
            WorldObjectTemplateFactory
                    .initialize(true)
                    .setName("a simple wall")
                    .setSymbols('#')
                    .setForegroundColors(Color.LIGHT_GRAY.darker())
                    .manufactureTerrainTemplate(false, false, false);
    public static TerrainTemplate GRASSY_FLOOR =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("grass covered ground")
                    .setSymbols('.')
                    .setForegroundColors(ColorStandards.FOREST_UNDERGROWTH)
                    .manufactureTerrainTemplate(true, true, false);
    public static TerrainTemplate TOADSTOOLS =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("a cluster of toadstools")
                    .setSymbols(',')
                    .setForegroundColors(Chroma.BEIGE)
                    .manufactureTerrainTemplate(true, true, false);
    public static TerrainTemplate ANCIENT_OAK =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("an ancient oak tree")
                    .setSymbols('#')
                    .setForegroundColors(ColorStandards.ANCIENT_OAK)
                    .manufactureTerrainTemplate(true, false, false);
    public static TerrainTemplate FOREST_GATE =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("a gateway leading into the forest")
                    .setSymbols('>')
                    .setForegroundColors(Color.GREEN.darker())
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate LIBRARY_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("the door to your Library")
                    .setSymbols('<')
                    .setForegroundColors(Color.PINK)
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate HALL_OF_ARMS_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("the door to your Hall of Arms")
                    .setSymbols('<')
                    .setForegroundColors(Color.RED)
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate ARCHERY_RANGE_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("the door to your Archery Range")
                    .setSymbols('<')
                    .setForegroundColors(Color.GREEN)
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate LABORATORY_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("the door to your Laboratory")
                    .setSymbols('<')
                    .setForegroundColors(Color.BLUE)
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate TROPHY_HALL_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("the door to your Trophy Hall")
                    .setSymbols('<')
                    .setForegroundColors(Color.YELLOW)
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate MAUSOLEUM_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("the door to your Mausoleum")
                    .setSymbols('<')
                    .setForegroundColors(Color.DARK_GRAY)
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate FORGE_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("the door to your Forge")
                    .setSymbols('<')
                    .setForegroundColors(Color.ORANGE)
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate WORKSHOP_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("the door to your Workshop")
                    .setSymbols('<')
                    .setForegroundColors(Color.GRAY)
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate WAREHOUSE_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("the door to your Warehouse")
                    .setSymbols('<')
                    .setForegroundColors(Color.LIGHT_GRAY)
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate ARMORY_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("the door to your Armory")
                    .setSymbols('<')
                    .setForegroundColors(Color.CYAN)
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate RITUAL_CHAMBER_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("the door to your Ritual Chamber")
                    .setSymbols('<')
                    .setForegroundColors(Color.MAGENTA)
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate CLEAR_GLASS_WINDOW =
            WorldObjectTemplateFactory
                    .initialize(true)
                    .setName("a window of clear glass")
                    .setSymbols('=')
                    .setForegroundColors(Color.CYAN.brighter())
                    .manufactureTerrainTemplate(true, false, false);
    public static TerrainTemplate SHALLOW_WATER =
            WorldObjectTemplateFactory
                    .initialize(true)
                    .setName("a forest stream")
                    .setSymbols('~')
                    .setForegroundColors(ColorStandards.FOREST_STREAM)
                    .manufactureTerrainTemplate(true, true, false);
    public static TerrainTemplate FLIGHT_STAIR =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("a pathway offering a hasty escape")
                    .setSymbols('<')
                    .setForegroundColors(Color.YELLOW)
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate REWARD_STAIR =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("a pathway offering a deliberate exit")
                    .setSymbols('<')
                    .setForegroundColors(Color.BLUE)
                    .manufactureTerrainTemplate(true, true, true);
    public static TerrainTemplate NEXT_FLOOR_STAIR =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("a pathway leading deeper into the dungeon")
                    .setSymbols('>')
                    .setForegroundColors(Color.ORANGE)
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
                    .manufactureTerrainTemplate(true, true, true);
}
