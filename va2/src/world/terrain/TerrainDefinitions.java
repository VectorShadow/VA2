package world.terrain;

import world.WorldObjectTemplateFactory;

import java.awt.*;

/**
 * Contains the definitions for each available TerrainTemplate.
 */
public class TerrainDefinitions {
    public static TerrainTemplate EMPTY =
            WorldObjectTemplateFactory.initialize().manufactureTerrainTemplate(true, false);
    public static TerrainTemplate SIMPLE_FLOOR =
            WorldObjectTemplateFactory
                    .initialize(8)
                    .setSymbols('.')
                    .manufactureTerrainTemplate(true, true);
    public static TerrainTemplate SIMPLE_WALL =
            WorldObjectTemplateFactory
                    .initialize(5)
                    .setSymbols('#')
                    .setForegroundColors(Color.LIGHT_GRAY.darker())
                    .manufactureTerrainTemplate(false, false);
    public static TerrainTemplate GRASSY_FLOOR =
            WorldObjectTemplateFactory
                    .initialize()
                    .setSymbols('.')
                    .setForegroundColors(Color.GREEN)
                    .manufactureTerrainTemplate(true, true);
    public static TerrainTemplate ANCIENT_OAK =
            WorldObjectTemplateFactory
                    .initialize()
                    .setSymbols('#')
                    .setForegroundColors(Color.GREEN.darker().darker())
                    .manufactureTerrainTemplate(true, false);
    public static TerrainTemplate FOREST_GATE =
            WorldObjectTemplateFactory
                    .initialize()
                    .setSymbols('>')
                    .setForegroundColors(Color.GREEN.darker())
                    .manufactureTerrainTemplate(true, true);
    public static TerrainTemplate LIBRARY_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setSymbols('<')
                    .setForegroundColors(Color.PINK)
                    .manufactureTerrainTemplate(true, true);
    public static TerrainTemplate HALL_OF_ARMS_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setSymbols('<')
                    .setForegroundColors(Color.RED)
                    .manufactureTerrainTemplate(true, true);
    public static TerrainTemplate ARCHERY_RANGE_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setSymbols('<')
                    .setForegroundColors(Color.GREEN)
                    .manufactureTerrainTemplate(true, true);
    public static TerrainTemplate LABORATORY_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setSymbols('<')
                    .setForegroundColors(Color.BLUE)
                    .manufactureTerrainTemplate(true, true);
    public static TerrainTemplate MAUSOLEUM_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setSymbols('<')
                    .setForegroundColors(Color.DARK_GRAY)
                    .manufactureTerrainTemplate(true, true);
    public static TerrainTemplate TROPHY_HALL_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setSymbols('<')
                    .setForegroundColors(Color.YELLOW)
                    .manufactureTerrainTemplate(true, true);
    public static TerrainTemplate FORGE_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setSymbols('<')
                    .setForegroundColors(Color.ORANGE)
                    .manufactureTerrainTemplate(true, true);
    public static TerrainTemplate WORKSHOP_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setSymbols('<')
                    .setForegroundColors(Color.GRAY)
                    .manufactureTerrainTemplate(true, true);
    public static TerrainTemplate WAREHOUSE_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setSymbols('<')
                    .setForegroundColors(Color.LIGHT_GRAY)
                    .manufactureTerrainTemplate(true, true);
    public static TerrainTemplate ARMORY_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setSymbols('<')
                    .setForegroundColors(Color.CYAN)
                    .manufactureTerrainTemplate(true, true);
    public static TerrainTemplate RITUAL_CHAMBER_PORTAL =
            WorldObjectTemplateFactory
                    .initialize()
                    .setSymbols('<')
                    .setForegroundColors(Color.MAGENTA)
                    .manufactureTerrainTemplate(true, true);
    public static TerrainTemplate CLEAR_GLASS_WINDOW =
            WorldObjectTemplateFactory
                    .initialize(2)
                    .setSymbols('=')
                    .setForegroundColors(Color.CYAN.brighter())
                    .manufactureTerrainTemplate(true, false);
}
