package world.dungeon;

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

/**
 * Keeps track of a specific instance of a dungeon.
 */
public class Dungeon implements Serializable {
    private static final double EARLY_EXIT_PENALTY = 0.4;

    private final DungeonTheme DUNGEON_THEME;
    private ArrayList<Reward> rewards = new ArrayList<>();

    public Dungeon(DungeonTheme dt) {
        DUNGEON_THEME = dt;
    }
    public void addReward(Reward r) {
        rewards.add(r);
    }
    public void nextFloor() {
        Session.getMessageCenter().sendMessage("You make your way deeper into the dungeon.", MessageType.INFO);
        Session.setCurrentFloor(new Floor(Session.getCurrentFloor().DEPTH + 1, DUNGEON_THEME));
    }
    public void exitDungeon(boolean fullRewards) {
        Player player = Session.getPlayer();
        player.getActor().getCombatant().renewHealth();
        Experience experience = player.getExperience();
        int startLevel = experience.getLevel();
        for (Reward r : rewards) {
            if (fullRewards || Session.getRNG().nextDouble() < EARLY_EXIT_PENALTY) {
                experience.gainXP(r.evaluateExperience(experience.getLevel()));
                //todo - generate item loot as well.
            }
        }
        rewards = new ArrayList<>();
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
                        : MessageType.WARNING
        );
        Session.setCurrentFloor(new Floor(0, ThemeDefinitions.YSIAN_ESTATE));
        if (experience.getLevel() > startLevel) {
            Session.getMessageCenter().sendMessage(
                    "You have reached level " + experience.getLevel() + ".", MessageType.SUCCESS
            );
        }
    }

    public DungeonTheme getTheme() {
        return DUNGEON_THEME;
    }
}
