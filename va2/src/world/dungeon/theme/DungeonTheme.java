package world.dungeon.theme;

import main.Session;
import main.progression.rewards.DropTable;
import main.progression.rewards.Loot;
import main.progression.rewards.Reward;
import resources.continuum.Continuum;
import resources.glyph.GlyphString;
import world.actor.Actor;
import world.actor.ActorTemplate;
import world.dungeon.generate.FloorGenerator;
import world.item.Item;
import world.light.Light;

import java.io.Serializable;
import java.util.Random;


/**
 * Contains all necessary information to generate a thematic dungeon.
 */
public class DungeonTheme implements Serializable {

    private final int VERMIN_SET = 0;
    private final int MINION_SET = 1;
    private final int REGULAR_SET = 2;
    private final int CHAMPION_SET = 3;
    private final int ELITE_SET = 4;
    private final int FLOOR_BOSS_SET = 5;

    private final Light AMBIENT_LIGHT;
    private final int MAXIMUM_ROWS;
    private final int MAXIMUM_COLS;
    private final int MAXIMUM_ENEMIES;
    private final int ROW_VARIANCE;
    private final int COL_VARIANCE;
    private final int ENEMY_VARIANCE;
    private final int DUNGEON_DEPTH;
    private final GlyphString DUNGEON_NAME;

    private final String[] FINAL_FLOOR_ACTORS;
    private final String[] FINAL_FLOOR_MAP;

    private final Continuum<FloorGenerator> GENERATORS;
    private final ActorSet ACTOR_SET;
    private final TerrainSet TERRAIN_SET;
    //todo - roomSet, which includes a list of room patterns and a mapping of chars to terrainTemplates
    //todo - roomPatternDefinitions, which contains all defined room patterns to be referenced by roomSets
    //todo - boss floor room and actor definitons

    DungeonTheme(
            Light light,
            int maxRows,
            int maxCols,
            int maxEnemies,
            int rowVar,
            int colVar,
            int enemyVar,
            int depth,
            GlyphString name,
            String[] actors,
            String[] map,
            Continuum<FloorGenerator> floorGen,
            ActorSet actorSet,
            TerrainSet terrainSet
    ) {
        if (maxRows - 2 * rowVar < 0 || maxCols - 2 * colVar < 0)
            throw new IllegalArgumentException("Size parameters out of range.");
        AMBIENT_LIGHT = light;
        MAXIMUM_ROWS = maxRows;
        MAXIMUM_COLS = maxCols;
        MAXIMUM_ENEMIES = maxEnemies;
        ROW_VARIANCE = rowVar;
        COL_VARIANCE = colVar;
        ENEMY_VARIANCE = enemyVar;
        DUNGEON_DEPTH = depth;
        DUNGEON_NAME = name;
        FINAL_FLOOR_ACTORS = actors;
        FINAL_FLOOR_MAP = map;
        GENERATORS = floorGen;
        ACTOR_SET = actorSet;
        TERRAIN_SET = terrainSet;
    }

    public Light getAmbientLight() {
        return AMBIENT_LIGHT;
    }
    public int randomizeRows() {
        return MAXIMUM_ROWS - Session.getRNG().nextInt(ROW_VARIANCE);
    }
    public int randomizeCols() {
        return MAXIMUM_COLS - Session.getRNG().nextInt(COL_VARIANCE);
    }
    public int finalFloorRows() {
        return FINAL_FLOOR_MAP.length;
    }
    public int finalFloorCols() {
        return FINAL_FLOOR_MAP[0].length();
    }
    public int randomizeEnemies() {
        return MAXIMUM_ENEMIES - Session.getRNG().nextInt(ENEMY_VARIANCE);
    }
    public FloorGenerator randomizeFloorGenerator() {
        return GENERATORS.getValue(Session.getRNG());
    }

    public String[] getFinalFloorActors() {
        return FINAL_FLOOR_ACTORS;
    }

    public String[] getFinalFloorMap() {
        return FINAL_FLOOR_MAP;
    }

    public ActorSet getActorSet() {
        return ACTOR_SET;
    }
    public TerrainSet getTerrainSet() {
        return TERRAIN_SET;
    }

    public int getDepth() {
        return DUNGEON_DEPTH;
    }

    public GlyphString getName() {
        return DUNGEON_NAME;
    }

    public Actor generateRandomActor(int currentDepth) {
        int enemySetIndex;
        ActorTemplate at;
        Random rng = Session.getRNG();
        boolean forceOutOfDepth = false;
        do {
            double random = rng.nextDouble();
            if (currentDepth > 8 && random < (1.0 / 128.0)) {
                enemySetIndex = ELITE_SET;
                forceOutOfDepth = true;
            } else if (currentDepth > 4 && random < (1.0 / 16.0)) {
                enemySetIndex = CHAMPION_SET;
                forceOutOfDepth = true;
            } else if (currentDepth > 2 && random < (1.0 / 4.0)) {
                enemySetIndex = REGULAR_SET;
                forceOutOfDepth = true;
            } else if (currentDepth > 1 && random < (1.0 / 2.0)) {
                enemySetIndex = MINION_SET;
                forceOutOfDepth = true;
            } else if (random < (1.0 / 4_096.0)) {
                enemySetIndex = ELITE_SET;
            } else if (random < (1.0 / 256.0)) {
                enemySetIndex = CHAMPION_SET;
            } else if (random < (1.0 / 32.0)) {
                enemySetIndex = REGULAR_SET;
            } else if (random < (1.0 / 8.0)){
                enemySetIndex = MINION_SET;
            } else {
                enemySetIndex = VERMIN_SET;
            }
            at = ACTOR_SET.getEnemySet(enemySetIndex).getValue(rng);
        } while (!forceOutOfDepth && currentDepth < at.getMinimumDepth());
        int rewardExperience = (int)((double)at.getRewardExperience() * Math.pow(1.2, enemySetIndex));
        int rewardQualityBias =
                enemySetIndex == VERMIN_SET || enemySetIndex == MINION_SET
                        ? Item.QUALITY_MUNDANE
                        : enemySetIndex == REGULAR_SET
                        ? Item.QUALITY_COMMON
                        : enemySetIndex == CHAMPION_SET
                        ? Item.QUALITY_SCARCE
                        : Item.QUALITY_EXOTIC;
        double noDropChance = 1.0 - (0.2 * (enemySetIndex + 1));
        Reward r = new Reward(
                rewardExperience,
                (enemySetIndex + 2) / 2,
                Loot.generateDropTable(
                        ThemeDefinitions.getIndex(this),
                        rewardQualityBias,
                        at.getRewardItemFamily(),
                        noDropChance
                )
        );
        return new Actor(at, r);
    }
    public Actor randomizeFloorBoss(int currentDepth) {
        ActorTemplate at;
        do {
            at = ACTOR_SET.getEnemySet(FLOOR_BOSS_SET).getValue(Session.getRNG());
        } while (at != null && currentDepth < at.getMinimumDepth());
        return at == null ? null : new Actor(
                at,
                new Reward(
                        (int)((double)at.getRewardExperience() * 2.5),
                        3,
                        Loot.generateDropTable(
                                ThemeDefinitions.getIndex(this),
                                Item.QUALITY_RARE,
                                at.getRewardItemFamily(),
                                0.0
                        )
                )
        );
    }
    public Actor generateBossFloor(char templateSymbol) {
        ActorTemplate at;
        int rewardQualityBias = Item.QUALITY_MUNDANE;
        ActorTemplate[] dbs = ACTOR_SET.getDungeonBossSet();
        switch (templateSymbol) {
            case ' ':
                at = null;
                break;
            case '0':
                at = dbs[0];
                rewardQualityBias = Item.QUALITY_EPIC;
                break;
            case '1':
                at = dbs[1];
                rewardQualityBias = Item.QUALITY_COMMON;
                break;
            case '2':
                at = dbs[2];
                rewardQualityBias = Item.QUALITY_SCARCE;
                break;
            case '3':
                at = dbs[3];
                rewardQualityBias = Item.QUALITY_SCARCE;
                break;
            case '4':
                at = dbs[4];
                rewardQualityBias = Item.QUALITY_EXOTIC;
                break;
            case '5':
                at = dbs[5];
                rewardQualityBias = Item.QUALITY_EXOTIC;
                break;
            case '6':
                at = dbs[6];
                rewardQualityBias = Item.QUALITY_RARE;
                break;
            case '7':
                at = dbs[7];
                rewardQualityBias = Item.QUALITY_RARE;
                break;
            case '8':
                at = dbs[8];
                rewardQualityBias = Item.QUALITY_RARE;
                break;
            case '9':
                at = dbs[9];
                rewardQualityBias = Item.QUALITY_HEROIC;
                break;
            default:
                throw new IllegalArgumentException("Unexpected symbol " + templateSymbol);
        }
        if (at == null) return null;
        return new Actor(
                at,
                new Reward(
                        (int)((double)at.getRewardExperience() * 4.0),
                        8,
                        Loot.generateDropTable(
                                ThemeDefinitions.getIndex(this),
                                rewardQualityBias,
                                at.getRewardItemFamily(),
                                0.0
                        )
                )
        );
    }
}
