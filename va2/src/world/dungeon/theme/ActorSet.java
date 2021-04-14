package world.dungeon.theme;

import main.Session;
import resources.continuum.Continuum;
import world.actor.Actor;
import world.actor.ActorTemplate;

import java.io.Serializable;
import java.util.Random;

public class ActorSet implements Serializable {
    private final Continuum<ActorTemplate>[] ENEMY_SET;
    private final ActorTemplate[] DUNGEON_BOSS_SET; //todo - this is the dungeon boss and his escort. we may want a class for this
    //todo - other sets
    public ActorSet(
            Continuum<ActorTemplate>[] enemySet,
            ActorTemplate[] dungeonBossSet
    ) {
        ENEMY_SET = enemySet;
        DUNGEON_BOSS_SET = dungeonBossSet;
    }

    Continuum<ActorTemplate> getEnemySet(int index) {
        return ENEMY_SET[index];
    }

    public boolean isFloorBoss(Actor a) {
        return ENEMY_SET[ENEMY_SET.length - 1].contains((ActorTemplate)a.getTemplate());
    }
    public boolean isDungeonBoss(Actor a) {
        return DUNGEON_BOSS_SET.length > 0 && DUNGEON_BOSS_SET[0] == a.getTemplate();
    }
    public ActorTemplate[] getDungeonBossSet() {
        return DUNGEON_BOSS_SET;
    }
}
