package world.dungeon.theme;

import main.Session;
import resources.continuum.Continuum;
import world.actor.ActorTemplate;

import java.io.Serializable;
import java.util.Random;

public class ActorSet implements Serializable {
    private final Continuum<ActorTemplate> VERMIN_SET;
    private final Continuum<ActorTemplate> MINION_SET;
    private final Continuum<ActorTemplate> REGULAR_SET;
    private final Continuum<ActorTemplate> CHAMPION_SET;
    private final Continuum<ActorTemplate> ELITE_SET;
    private final Continuum<ActorTemplate> FLOOR_BOSS_SET;
    private final ActorTemplate[] DUNGEON_BOSS_SET; //todo - this is the dungeon boss and his escort. we may want a class for this
    //todo - other sets
    public ActorSet(
            Continuum<ActorTemplate> verminSet,
            Continuum<ActorTemplate> minionSet,
            Continuum<ActorTemplate> regularSet,
            Continuum<ActorTemplate> championSet,
            Continuum<ActorTemplate> eliteSet,
            Continuum<ActorTemplate> floorBossSet,
            ActorTemplate[] dungeonBossSet
    ) {
        VERMIN_SET = verminSet;
        MINION_SET = minionSet;
        REGULAR_SET = regularSet;
        CHAMPION_SET = championSet;
        ELITE_SET = eliteSet;
        FLOOR_BOSS_SET = floorBossSet;
        DUNGEON_BOSS_SET = dungeonBossSet;
    }

    public ActorTemplate randomizeEnemy(int currentDepth) {
        ActorTemplate at;
        Random rng = Session.getRNG();
        do {
            double random = rng.nextDouble();
            if (random > 0.999 || (random > 0.92 && currentDepth > 8)) {
                at = ELITE_SET.getValue(rng);
            } else if (random > 0.92 || (random > 0.84 && currentDepth > 4)) {
                at = CHAMPION_SET.getValue(rng);
            } else if (random > 0.84 || (random > 0.68 && currentDepth > 2)) {
                at = REGULAR_SET.getValue(rng);
            } else if (random > 0.64 || (random > 0.36 && currentDepth > 1)) {
                at = MINION_SET.getValue(rng);
            } else {
                at = VERMIN_SET.getValue(rng);
            }
        } while (false); //todo - reroll until our actor template is allowed at our current depth
        return at;
    }
    public ActorTemplate randomizeFloorBoss(int currentDepth) {
        ActorTemplate at;
        do {
            at = FLOOR_BOSS_SET.getValue(Session.getRNG());
        } while (false); //todo - reroll until our actor template is allowed at our current depth
        return at;
    }
    public ActorTemplate[] getDungeonBossSet() {
        return DUNGEON_BOSS_SET;
    }
}
