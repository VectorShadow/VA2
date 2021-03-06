package world.dungeon.generate;

import main.Session;
import util.Coordinate;
import world.actor.Actor;
import world.actor.ActorTemplate;
import world.dungeon.floor.Floor;
import world.dungeon.floor.FloorTile;
import world.dungeon.theme.DungeonTheme;
import world.dungeon.theme.TerrainSet;
import world.dungeon.theme.ThemeDefinitions;
import world.terrain.Terrain;
import world.terrain.TerrainDefinitions;
import world.terrain.TerrainTemplate;

/**
 * The generator for predefined levels, such as the player estate.
 */
//todo - for themes other than YsianEstate, use definition = dungeonTheme.layout/placement definitions.
public class PredefinedMapGenerator extends FloorGenerator {
    @Override
    public Floor generate(Floor f) {
        floor = f;
        if (ThemeDefinitions.getIndex(floor.THEME) == ThemeDefinitions.YSIAN_ESTATE) {
            for (int i = 0; i < floor.ROWS; ++i) {
                for (int j = 0; j < floor.COLS; ++j) {
                    floor.tileAt(i, j).setSeen(true); //the player remembers his own house
                }
            }
        }
        copyMap();
        copyActors();
        return floor;
    }
    private void copyActors() {
        String[] s = floor.THEME.getFinalFloorActors();
        Actor a;
        for (int i = 0; i < floor.ROWS; ++i) {
            for (int j = 0; j < floor.COLS; ++j) {
                a = floor.THEME.generateBossFloor(s[i].charAt(j));
                if (a != null)
                    Session.addActor(a, new Coordinate(i, j));
            }
        }
    }
    private void copyMap() {
        String[] s = floor.THEME.getFinalFloorMap();
        char c;
        //todo - themes should map chars to terrain definitions.
        TerrainSet ts = floor.THEME.getTerrainSet();
        TerrainTemplate tt;
        for (int i = 0; i < floor.ROWS; ++i) {
            for (int j = 0; j < floor.COLS; ++j) {
                c = s[i].charAt(j);
                switch (c) {
                    case '!':
                        tt = TerrainDefinitions.PERMANENT_WALL;
                        break;
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
                    case '<':
                        tt = TerrainDefinitions.FLIGHT_STAIR;
                        floor.setPlayerSpawn(new Coordinate(i, j));
                        break;
                    case '$':
                        tt = floor.THEME.getTerrainSet().getEndTerrain();
                        break;
                    case '>':
                        tt = TerrainDefinitions.FOREST_GATE;
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
                        tt = TerrainDefinitions.TROPHY_HALL_PORTAL;
                        break;
                    case '5':
                        tt = TerrainDefinitions.MAUSOLEUM_PORTAL;
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
