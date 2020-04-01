package main;

import engine.action.AdjacentMovementAction;
import main.progression.rewards.Experience;
import util.Direction;
import world.actor.Actor;
import world.item.inventory.Inventory;
import world.item.loadout.Equipment;

import java.io.Serializable;

/**
 * Contains all relevant information about the player character.
 */
public class Player implements Serializable {



    private Actor actor = null;
    private Equipment equipment = new Equipment();
    private Experience experience = new Experience();
    private Inventory transientResources = new Inventory(255);


    public Actor getActor() {
        return actor;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
    public AdjacentMovementAction getMove(Direction d) {
        //hack - todo: ask the player actor what it's move energy multiplier is
        return new AdjacentMovementAction(d, actor.getMoveEnergyMultiplier());
    }

    public Inventory getTransientResources() {
        return transientResources;
    }
}
