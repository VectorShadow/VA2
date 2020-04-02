package main.progression.rewards;

import main.Session;
import resources.continuum.Continuum;
import resources.continuum.Pair;
import world.actor.Actor;
import world.item.Item;
import world.item.inventory.ItemSlot;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * DropTable supports two forms - an uninitialized form used in constant definitions,
 * and an initialized form used for generating drops.
 *
 * Uninitialized DropTables should be generated by Loot.generateDropTable according to the desired parameters.
 *
 * Initialized DropTables should be created at runtime when rewards need to be generated. These will implement the
 * source actor(if an actor was responsible for the DropTable) as well as the player's luck, which may vary.
 */
public class DropTable implements Serializable {
    private Continuum<ItemSlot> itemList;
    private ArrayList<Pair<ItemSlot>> probablePairs;
    private double lastProbability = 0.0;

    private final double NO_DROP_CHANCE;

    /**
     * The first type of drop table.
     */
    public DropTable(double noDropChance) {
        probablePairs = new ArrayList<>();
        NO_DROP_CHANCE = noDropChance;
    }

    /**
     * The second type of drop table.
     */
    private DropTable(double noDropChance, double luck, ArrayList<Pair<ItemSlot>> pairs) {
        NO_DROP_CHANCE = Double.NaN;
        Continuum c0 = new Continuum<>(null, pairs);
        Continuum c1 = c0.rebase(noDropChance);
        itemList = c1.adjust(luck);
    }

    /**
     * Applies to the first type of drop table, used in building the table.
     * Fails if called on the second type, since probablePairs is never instantiated.
     */
    public void add(Item i, int amount, double probability) {
        lastProbability += probability;
        probablePairs.add(new Pair<>(lastProbability, ItemSlot.make(i, amount)));
    }

    /**
     * Applies to the first type of drop table.
     * Used to generate a drop table of the second type for runtime use.
     * Fails if called on the second type, since un-instantiated probablePairs
     * will cause the Continuum constructor to fail.
     */
    public DropTable initializeOn(Actor actor) {
        if (actor != null) {
            //todo - add an item based on the actor as the perfect specimen drop chance,
            // with associated checks for whether such a specimen exists in player estate or trophy hall already
        }
        return new DropTable(NO_DROP_CHANCE, 1.0, probablePairs); //todo - access and use the player's luck
    }

    /**
     * Applies to the second type of drop table.
     * Used to resolve the dropped item or items.
     * Fails if called on the first type, since itemList is not instantiated.
     * @return null if no item is generated, or an itemSlot containing an item and amount to be awarded to the player.
     */
    public ItemSlot roll() {
        return itemList.getValue(Session.getRNG());
    }
}