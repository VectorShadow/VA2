package world.dungeon.theme;

import main.Session;
import resources.continuum.Continuum;
import world.actor.Actor;
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
            if (random < (1.0 / 4_096.0) || (random < (1.0 / 128.0) && currentDepth > 8)) {
                at = ELITE_SET.getValue(rng);
            } else if (random < (1.0 / 256.0) || (random < (1.0 / 16.0) && currentDepth > 4)) {
                at = CHAMPION_SET.getValue(rng);
            } else if (random < (1.0 / 32.0) || (random < (1.0 / 4.0) && currentDepth > 2)) {
                at = REGULAR_SET.getValue(rng);
            } else if (random < (1.0 / 8.0) || (random < (1.0 / 2.0) && currentDepth > 1)) {
                at = MINION_SET.getValue(rng);
            } else {
                at = VERMIN_SET.getValue(rng);
            }
        } while (currentDepth < at.getMinimumDepth());
        return at;
    }
    public ActorTemplate randomizeFloorBoss(int currentDepth) {
        ActorTemplate at;
        do {
            at = FLOOR_BOSS_SET.getValue(Session.getRNG());
        } while (at != null && currentDepth < at.getMinimumDepth());
        return at;
    }
    public boolean isFloorBoss(Actor a) {
        return FLOOR_BOSS_SET.contains((ActorTemplate)a.getTemplate());
    }
    public boolean isDungeonBoss(Actor a) {
        return DUNGEON_BOSS_SET.length > 0 && DUNGEON_BOSS_SET[0] == a.getTemplate();
    }
    public ActorTemplate[] getDungeonBossSet() {
        return DUNGEON_BOSS_SET;
    }
}
