package world.dungeon.generate;

import util.Coordinate;
import world.dungeon.floor.Floor;
import world.dungeon.theme.DungeonTheme;
import world.dungeon.theme.TerrainSet;
import world.dungeon.theme.ThemeDefinitions;
import world.terrain.Terrain;
import world.terrain.TerrainDefinitions;
import world.terrain.TerrainTemplate;

/**
 * The generator for predefined levels, such as the player estate.
 */
public class PredefinedMapGenerator extends FloorGenerator {
    @Override
    public Floor generate(int depth, DungeonTheme theme) {
        String[] definition;
        dungeonTheme = theme;
        floorDepth = depth;
        floor = new Floor(floorDepth, dungeonTheme);
        if (dungeonTheme == ThemeDefinitions.YSIAN_ESTATE) {
            definition = new String[] {
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
            };
            for (int i = 0; i < floor.ROWS; ++i) {
                for (int j = 0; j < floor.COLS; ++j) {
                    floor.tileAt(i, j).setSeen(true); //the player remembers his own house
                }
            }
        } /*todo else if - other predefined levels? */ else {
            throw new IllegalArgumentException("Unknown theme.");
        }
        copy(definition);
        return floor;
    }
    private void copy(String[] s) {
        for (int i = 0; i < floor.ROWS; ++i) {
            for (int j = 0; j < floor.COLS; ++j) {
                char c = s[i].charAt(j);
                //todo - themes should map chars to terrain definitions.
                TerrainSet ts = dungeonTheme.getTerrainSet();
                TerrainTemplate tt;
                switch (c) {
                    case '!':
                        tt = TerrainDefinitions.PERMANENT_WALL;
                    case '#':
                        tt = ts.getBasePrimaryWall();
                        break;
                    case '.':
                        tt = ts.getBasePrimaryFloor();
                        break;
                    case ';':
                        tt = ts.getBaseAlternateFloor();
                        break;
                    case '*':
                        tt = ts.getBaseAlternateWall();
                        break;
                    case '>': case '<':
                        tt = ts.getSpawnTerrain();
                        floor.setPlayerSpawn(new Coordinate(i, j));
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
