package main;

import engine.action.AdjacentMovementAction;
import main.progression.Experience;
import util.Direction;
import world.actor.Actor;
import world.light.Light;

import java.io.Serializable;

/**
 * Contains all relevant information about the player character.
 */
public class Player implements Serializable {
    private Actor actor = null;
    private Experience experience = new Experience();

    public Actor getActor() {
        return actor;
    }
    public Experience getExperience() {
        return experience;
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
    public AdjacentMovementAction getMove(Direction d) {
        //hack - todo: ask the player actor what it's move energy multiplier is
        return new AdjacentMovementAction(d, actor.getMoveEnergyMultiplier());
    }
}
