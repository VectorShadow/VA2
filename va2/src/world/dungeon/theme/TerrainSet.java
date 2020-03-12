package world.dungeon.theme;

import main.Session;
import resources.continuum.Continuum;
import world.terrain.TerrainDefinitions;
import world.terrain.TerrainTemplate;

import java.io.Serializable;

public class TerrainSet implements Serializable {
    private final Continuum<TerrainTemplate> PRIMARY_WALL_SET;
    private final Continuum<TerrainTemplate> ALTERNATE_WALL_SET;
    private final Continuum<TerrainTemplate> PRIMARY_FLOOR_SET;
    private final Continuum<TerrainTemplate> ALTERNATE_FLOOR_SET;
    private final TerrainTemplate SPAWN_TERRAIN;
    private final TerrainTemplate END_TERRAIN;
    //todo - other sets
    public TerrainSet(
            Continuum<TerrainTemplate> priWalls,
            Continuum<TerrainTemplate> altWalls,
            Continuum<TerrainTemplate> priFloors,
            Continuum<TerrainTemplate> altFloors,
            TerrainTemplate endTerrain
    ) {
        this(priWalls, altWalls, priFloors, altFloors, TerrainDefinitions.FLIGHT_STAIR, endTerrain);
    }
    public TerrainSet(
            Continuum<TerrainTemplate> priWalls,
            Continuum<TerrainTemplate> altWalls,
            Continuum<TerrainTemplate> priFloors,
            Continuum<TerrainTemplate> altFloors,
            TerrainTemplate spawnTerrain,
            TerrainTemplate endTerrain
    ) {
        PRIMARY_WALL_SET = priWalls;
        ALTERNATE_WALL_SET = altWalls;
        PRIMARY_FLOOR_SET = priFloors;
        ALTERNATE_FLOOR_SET = altFloors;
        SPAWN_TERRAIN = spawnTerrain;
        END_TERRAIN = endTerrain;
    }

    public TerrainTemplate getBasePrimaryWall() {
        return PRIMARY_WALL_SET.getBase();
    }
    public TerrainTemplate getRandomPrimaryWall() {
        return PRIMARY_WALL_SET.getValue(Session.getRNG());
    }
    public TerrainTemplate getBaseAlternateWall() {
        return ALTERNATE_WALL_SET.getBase();
    }
    public TerrainTemplate getRandomAlternateWall() {
        return ALTERNATE_WALL_SET.getValue(Session.getRNG());
    }
    public TerrainTemplate getBasePrimaryFloor() {
        return PRIMARY_FLOOR_SET.getBase();
    }
    public TerrainTemplate getRandomPrimaryFloor() {
        return PRIMARY_FLOOR_SET.getValue(Session.getRNG());
    }
    public TerrainTemplate getBaseAlternateFloor() {
        return ALTERNATE_FLOOR_SET.getBase();
    }
    public TerrainTemplate getRandomAlternateFloor() {
        return ALTERNATE_FLOOR_SET.getValue(Session.getRNG());
    }
    public TerrainTemplate getSpawnTerrain() {
        return SPAWN_TERRAIN;
    }
    public TerrainTemplate getEndTerrain() {
        return END_TERRAIN;
    }
}
