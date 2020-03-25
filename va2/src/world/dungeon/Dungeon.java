package world.dungeon;

import io.out.message.MessageCenter;
import io.out.message.MessageType;
import main.Player;
import main.Session;
import main.progression.Experience;
import main.progression.Reward;
import world.dungeon.floor.Floor;
import world.dungeon.theme.DungeonTheme;
import world.dungeon.theme.ThemeDefinitions;

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

    private boolean dungeonBossAlive = true;

    public Dungeon(DungeonTheme dt) {
        DUNGEON_THEME = dt;
    }
    public void addReward(Reward r) {
        rewards.add(r);
    }
    public void nextFloor() {
        Session.getMessageCenter().sendMessage("You make your way deeper into the dungeon.", MessageType.INFO, MessageCenter.PRIORITY_MAX);
        Session.setCurrentFloor(new Floor(Session.getCurrentFloor().DEPTH + 1, DUNGEON_THEME));
        dispenseRewards(FLOOR_COMPLETION_REWARD); //partial reward payout for each cleared floor
    }
    public void exitDungeon(boolean fullRewards) {
        Player player = Session.getPlayer();
        player.getActor().getCombatant().renewHealth();
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
        //todo - if Session.isFinalFloor(), add bonus rewards
        Session.setCurrentFloor(new Floor(0, ThemeDefinitions.YSIAN_ESTATE));
        dispenseRewards(fullRewards ? 1.0 : EARLY_EXIT_PENALTY); //full reward payout, with or without exit penalty
        rewards = new ArrayList<>(); //then zero out the remaining rewards
        dungeonBossAlive = true; //and reset boss kill
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
            }
        }
        for (int level = startLevel + 1; level <= experience.getLevel(); ++level) {
            Session.getMessageCenter().sendMessage(
                    "You have reached level " + level + ".", MessageType.SUCCESS,
                    level == experience.getLevel() ? MessageCenter.PRIORITY_MAX : MessageCenter.PRIORITY_HIGH
            );
        }
    }
    public void killDungeonBoss() {
        dungeonBossAlive = false;
    }

    public boolean isDungeonBossAlive() {
        return dungeonBossAlive;
    }
}
