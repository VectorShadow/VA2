package world.terrain;

import world.WorldObjectTemplateFactory;

import java.awt.*;

/**
 * Contains the definitions for each available TerrainTemplate, as well as themed sets of TerrainTemplates.
 */
public class TerrainDefinitions {
    public static TerrainTemplate EMPTY =
            WorldObjectTemplateFactory.initialize().manufactureTerrainTemplate(true, false);
    public static TerrainTemplate SIMPLE_FLOOR =
            WorldObjectTemplateFactory.initialize(true).setSymbols('.').manufactureTerrainTemplate(true, true);
    public static TerrainTemplate SIMPLE_WALL =
            WorldObjectTemplateFactory.initialize(true).setSymbols('#').manufactureTerrainTemplate(false, false);
    public static TerrainTemplate GRASSY_FLOOR =
            WorldObjectTemplateFactory.initialize().setSymbols('.').setForegroundColors(Color.GREEN).manufactureTerrainTemplate(true, true);
}
