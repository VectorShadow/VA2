package main.progression.rewards;

import main.Session;
import main.extensible.Saveable;
import world.actor.Actor;
import world.dungeon.theme.ThemeDefinitions;
import world.item.inventory.ItemSlot;

public class Reward extends Saveable {
    private final long REWARD_EXPERIENCE;
    private final DropTable DROP_TABLE;

    public Reward(long xp) {
        this(xp, Loot.PLACEHOLDER); //todo - remove this method when we have drop tables to use, so all actor definitions can be found and updated!
    }

    public Reward(long xp, DropTable typeITable) {
        REWARD_EXPERIENCE = xp;
        DROP_TABLE = typeITable;
    }

    public long evaluateExperience(int playerLevel) {
        //the deeper the floor in a given dungeon, the better the experience todo - loot rewards should use this too
        double difference = playerLevel -
                (ThemeDefinitions.getDifficulty(Session.getCurrentDungeon().getTheme()) + Session.getCurrentFloor().DEPTH);
        double multiplier = 1.0 * Math.pow(0.92, difference);
        return (long)((double)REWARD_EXPERIENCE * multiplier);
    }

    public Reward finalize(Actor a){
        return new Reward(REWARD_EXPERIENCE, DROP_TABLE.initializeOn(a));
    }

    /**
     * finalize must be called before rollDrop()!
     */
    public ItemSlot rollDrop() {
        return DROP_TABLE.roll();
    }

    public static void testScaling() {
        Reward r = new Reward(128, null);
        for (int i = 0; i < 128; ++i) {
            System.out.println("Player level: " + i + " XP Awarded: " + r.evaluateExperience(i) + " / " + Experience.calculateXP(i));
        }
    }
}
