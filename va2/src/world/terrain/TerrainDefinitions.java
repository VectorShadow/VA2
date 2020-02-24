package world.terrain;

import world.WorldObjectTemplateFactory;

/**
 * Contains the definitions for each available TerrainTemplate, as well as themed sets of TerrainTemplates.
 */
public class TerrainDefinitions {
    public static TerrainTemplate EMPTY = WorldObjectTemplateFactory.initialize().manufactureTerrainTemplate();
    public static TerrainTemplate SIMPLE_FLOOR =
            WorldObjectTemplateFactory.initialize().setSymbols('.').manufactureTerrainTemplate();
}
