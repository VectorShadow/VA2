package main;

import world.actor.Actor;
import world.light.Light;

import java.io.Serializable;

/**
 * Contains all relevant information about the player character.
 */
public class Player implements Serializable {
    private Actor actor = null;

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
    public Light getLight() {
        //todo - hack!
        return Light.TORCH;
    }
    public int getSightRadius() {
        //todo - hack!
        return 6;
    }
}
