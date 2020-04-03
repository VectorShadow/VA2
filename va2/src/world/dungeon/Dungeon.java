package world.dungeon;

import io.out.message.MessageCenter;
import io.out.message.MessageType;
import main.Player;
import main.Session;
import main.modes.ModeManager;
import main.modes.ScrollingTextMode;
import main.modes.TransitiveScrollingTextMode;
import main.progression.rewards.Experience;
import main.progression.rewards.Reward;
import world.dungeon.floor.Floor;
import world.dungeon.theme.DungeonTheme;
import world.dungeon.theme.ThemeDefinitions;
import world.item.Item;
import world.item.Resource;
import world.item.StackableItem;
import world.item.inventory.Inventory;
import world.item.inventory.ItemSlot;
import world.lore.LockLeaf;
import world.lore.LoreDefinitions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Keeps track of a specific instance of a dungeon.
 */
public class Dungeon implements Serializable {
    private static final double FLOOR_COMPLETION_REWARD = 0.15;
    private static final double EARLY_EXIT_PENALTY = 0.4;

    private final DungeonTheme DUNGEON_THEME;
    private ArrayList<Reward> rewards = new ArrayList<>();
    private Inventory accumulatedItems = new Inventory();

    private boolean dungeonBossAlive = true;

    public Dungeon(DungeonTheme dt) {
        DUNGEON_THEME = dt;
    }
    public void addReward(Reward r) {
        rewards.add(r);
    }
    public void nextFloor() {
        Session.getMessageCenter().clearNewMessages();
        Session.getMessageCenter().sendMessage("You make your way deeper into the dungeon.", MessageType.INFO, MessageCenter.PRIORITY_MAX);
        Session.setCurrentFloor(new Floor(Session.getCurrentFloor().DEPTH + 1, DUNGEON_THEME));
        dispenseRewards(FLOOR_COMPLETION_REWARD); //partial reward payout for each cleared floor
    }
    public void exitDungeon(boolean fullRewards) {
        Player player = Session.getPlayer();
        Session.getMessageCenter().clearNewMessages();
        Session.getMessageCenter().sendMessage(
                fullRewards
                        ? Session.isFinalFloor()
                        ? "You have cleared the dungeon."
                        : "You leave the dungeon and return to your estate."
                        : "You flee the dungeon and escape to your estate.",
                fullRewards
                        ? Session.isFinalFloor()
                        ? MessageType.SUCCESS
                        : MessageType.INFO
                        : MessageType.WARNING,
                MessageCenter.PRIORITY_MAX
        );
        if (fullRewards && Session.isFinalFloor()) {
            //todo - add bonus rewards
            if (((LockLeaf) LoreDefinitions.getLockTree().get(
                    LoreDefinitions.themeIndex(getTheme()),
                    LoreDefinitions.bracketLoreIndex(getTheme(), false)
            )).isLocked()) {
                Session.unlockLore(
                        LoreDefinitions.themeIndex(getTheme()),
                        LoreDefinitions.bracketLoreIndex(getTheme(), false),
                        null);
            }
            /**
             * If the player clears the final dungeon, end the game as a win.
             * todo : keep this updated as we add themes!
             */
            if (ThemeDefinitions.getIndex(getTheme()) == ThemeDefinitions.DARK_GROVE) {
                Session.getModeManager().revert();
                Session.killActor(Session.getPlayer().getActor(), true);
            }
        }
        dispenseRewards(fullRewards ? 1.0 : EARLY_EXIT_PENALTY); //full reward payout, with or without exit penalty
        //player recuperates at their estate until they reach full health.
        player.getActor().getCombatant().renewHealth();
        //player recovers 5% sanity for each completed floor this dungeon.
        player.getActor().getCombatant().renewSanity(
                0.05 * (Session.getCurrentFloor().DEPTH - (fullRewards ? 0 : 1)));
        //player recovers 50% soul for clearing a dungeon.
        if (Session.isFinalFloor() && fullRewards) player.getActor().getCombatant().renewSoul(.5);
        rewards = new ArrayList<>(); //then zero out the remaining rewards
        dungeonBossAlive = true; //and reset boss kill
        Session.setCurrentFloor(new Floor(0, ThemeDefinitions.DUNGEON_THEMES[ThemeDefinitions.YSIAN_ESTATE]));
        awardAccumulatedItems(); //experience is added dynamically as the player gains it, but items are all awarded at once, on dungeon exit
    }

    public DungeonTheme getTheme() {
        return DUNGEON_THEME;
    }
    private void dispenseRewards(double threshold) {
        Player player = Session.getPlayer();
        Experience experience = player.getExperience();
        int startLevel = experience.getLevel();
        for (Iterator<Reward> i = rewards.iterator(); i.hasNext();) {
            Reward r = i.next();
            if (Session.getRNG().nextDouble() < threshold) {
                i.remove();
                experience.gainXP(r.evaluateExperience(experience.getLevel()));
                ItemSlot itemSlot = r.rollDrop();
                if (itemSlot != null) {
                    accumulatedItems.add(itemSlot.peekItem(), Session.getRNG().nextInt(itemSlot.count()) + 1);
                }
            }
        }
        for (int level = startLevel + 1; level <= experience.getLevel(); ++level) {
            Session.getMessageCenter().sendMessage(
                    "You have reached experience level " + level + ".", MessageType.SUCCESS,
                    level == experience.getLevel() ? MessageCenter.PRIORITY_MAX : MessageCenter.PRIORITY_HIGH
            );
        }
    }
    private void awardAccumulatedItems() {
        for (ItemSlot is : accumulatedItems) {
            Item i = is.peekItem();
            if (i instanceof Resource && ((Resource) i).isLegacy())
                Session.getLegacyResources().add(i, is.count());
            else {
                //todo - reassign accumulatedItems to appropriate player inventories - remember to clone DegradableItems!
            }
        }
        Session.getModeManager().transitionTo(new ScrollingTextMode("You found the following items:\n\n" + accumulatedItems));
        accumulatedItems = new Inventory(); //reset this inventory
    }
    public void killDungeonBoss() {
        dungeonBossAlive = false;
    }

    public boolean isDungeonBossAlive() {
        return dungeonBossAlive;
    }
}
