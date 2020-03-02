package world;

import main.MetaData;
import world.dungeon.theme.DungeonTheme;
import world.dungeon.theme.ThemeDefinitions;

public class Lore {
    public static final int GENERAL = 0;
    public static final int GENERAL_NEW_PLAYER = 0;
    public static final int GENERAL_NEW_GAME = 1;
    public static final int DARK_GROVE = 1;
    private static final String[][] LORE = {
            { // 0 - GENERAL:
                // 0 - NEW_PLAYER:
                    "\t\tThis appears to be your first time playing Chronicles of the Abyss on this computer. " +
                            "\n\n\n\tChronicles is a roguelike dungeon crawler set in a fantasy/horror universe. You " +
                            "progress through the game by exploring various dungeons and collecting experience and " +
                            "items to increase your own power and delve ever deeper. You'll begin in your ancestral " +
                            "estate, which serves as your base of operations and can be upgraded as you acquire " +
                            "building materials, blueprints, recipes, and other items on your travels." +
                            "\n\n\tDeaths are permanent. If you die during the course of your adventures, you'll need " +
                            "to create a new character and start over again. However, any upgrades you've unlocked " +
                            "at your estate will persist, and you'll be able to use any features available with your " +
                            "current items and experience in any game. " +
                            "\n\n\tFor more specific information about the game, press '?' to display a list of " +
                            "commands available to you, or check out the included Manual text file in the game " +
                            "directory. " +
                            "\n\n\tIf you encounter any bugs during gameplay, feel free to contact the developer at " +
                            "" + MetaData.contact() + ". Any fatal crashes which occur should generate an error log. " +
                            "If this happens, please include that log with your bug report. " +
                            "\n\n\tThanks for playing, and enjoy the game.",
                    //1 - NEW_GAME:
                    "\t\t[Placeholder lore]",
            }
    };
    private boolean[][] UNLOCKED = {
            { //0 - GENERAL:
                //0 - NEW_PLAYER:
                false,
                    false,
            }
    };
    private int indexOfTheme(DungeonTheme dt) {
        if (dt == ThemeDefinitions.DARK_GROVE) return 1;
        //todo - other themes
        return -1;
    }
    public boolean isUnlocked(DungeonTheme dt, int loreIndex) {
        return isUnlocked(indexOfTheme(dt), loreIndex);
    }
    public boolean isUnlocked(int themeIndex, int loreIndex) {
        return UNLOCKED[themeIndex][loreIndex];
    }
    public String lore(DungeonTheme dt, int loreIndex) {
        return lore(indexOfTheme(dt), loreIndex);
    }
    public String lore(int groupIndex, int loreIndex) {
        unlock(groupIndex, loreIndex);
        return LORE[groupIndex][loreIndex];
    }
    private void unlock(int groupIndex, int loreIndex) {
        UNLOCKED[groupIndex][loreIndex] = true;
    }

    public boolean[][] getUnlocked() {
        return UNLOCKED;
    }

    public void setUnlocked(boolean[][] unlocked) {
        UNLOCKED = unlocked;
    }
}
