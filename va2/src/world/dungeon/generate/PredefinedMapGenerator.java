package world.dungeon.generate;

import util.Coordinate;
import world.dungeon.floor.Floor;
import world.dungeon.theme.DungeonTheme;
import world.dungeon.theme.ThemeDefinitions;
import world.terrain.Terrain;
import world.terrain.TerrainDefinitions;
import world.terrain.TerrainTemplate;

/**
 * The generator for predefined levels, such as the player estate.
 */
public class PredefinedMapGenerator extends FloorGenerator {
    @Override
    public Floor generate(DungeonTheme dungeonTheme, int floorDepth) {
        String[] definition;
        floor = new Floor(dungeonTheme);
        if (dungeonTheme == ThemeDefinitions.YSIAN_ESTATE) {
            definition = new String[] {
                    "*************************",
                    "**;*;;;*;;;*>**;*;;;**;**",
                    "*;;;*;;;;*;;;*;;;;;;*;;;*",
                    "**;;;;;;;*;;;;;;;;*;;;;;*",
                    "*;;;;*;;;;;*;;;;;;;;;;***",
                    "##==##==#*;;;;;*#==##==##",
                    "##......#**;;;**#......##",
                    "##......#*;;;;;*#......##",
                    "#`......#*;;;;;*#......0#",
                    "##......#*;;;;;*#......##",
                    "##......#***;***#......##",
                    "##......####;####......##",
                    "#1.....................9#",
                    "##.....................##",
                    "##.....................##",
                    "##.....................##",
                    "#2.....................8#",
                    "##.....................##",
                    "##.....................##",
                    "####3###4###5###6###7####",
                    "#########################",
            };
            floor.setPlayerSpawn(new Coordinate(1, 12));
            for (int i = 0; i < floor.getRows(); ++i) {
                for (int j = 0; j < floor.getCols(); ++j) {
                    floor.tileAt(i, j).setSeen(true); //the player remembers his own house
                }
            }
        } /*todo else if - other predefined levels? */ else {
            throw new IllegalArgumentException("Unknown theme.");
        }
        copy(definition, dungeonTheme);
        return floor;
    }
    private void copy(String[] s, DungeonTheme t) {
        for (int i = 0; i < floor.getRows(); ++i) {
            for (int j = 0; j < floor.getCols(); ++j) {
                char c = s[i].charAt(j);
                //todo - themes should map chars to terrain definitions.
                TerrainTemplate tt;
                switch (c) {
                    case '#':
                        tt = TerrainDefinitions.SIMPLE_WALL;
                        break;
                    case '.':
                        tt = TerrainDefinitions.SIMPLE_FLOOR;
                        break;
                    case ';':
                        tt = TerrainDefinitions.GRASSY_FLOOR;
                        break;
                    case '*':
                        tt = TerrainDefinitions.ANCIENT_OAK;
                        break;
                    case '>':
                        tt = TerrainDefinitions.FOREST_GATE;
                        break;
                    case '`':
                        tt = TerrainDefinitions.LIBRARY_PORTAL;
                        break;
                    case '1':
                        tt = TerrainDefinitions.HALL_OF_ARMS_PORTAL;
                        break;
                    case '2':
                        tt = TerrainDefinitions.ARCHERY_RANGE_PORTAL;
                        break;
                    case '3':
                        tt = TerrainDefinitions.LABORATORY_PORTAL;
                        break;
                    case '4':
                        tt = TerrainDefinitions.MAUSOLEUM_PORTAL;
                        break;
                    case '5':
                        tt = TerrainDefinitions.TROPHY_HALL_PORTAL;
                        break;
                    case '6':
                        tt = TerrainDefinitions.FORGE_PORTAL;
                        break;
                    case '7':
                        tt = TerrainDefinitions.WORKSHOP_PORTAL;
                        break;
                    case '8':
                        tt = TerrainDefinitions.WAREHOUSE_PORTAL;
                        break;
                    case '9':
                        tt = TerrainDefinitions.ARMORY_PORTAL;
                        break;
                    case '0':
                        tt = TerrainDefinitions.RITUAL_CHAMBER_PORTAL;
                        break;
                    case '=':
                        tt = TerrainDefinitions.CLEAR_GLASS_WINDOW;
                        break;
                    default:
                        throw new IllegalArgumentException("Unexpected symbol " + c);
                }
                floor.tileAt(i, j).setTerrain(new Terrain(tt));
            }
        }
    }
}
