package world.lore;

import main.MetaData;

public class LoreDefinitions {
    public static final int THEME_GENERAL = 0;
    public static final int GENERAL_NEW_PLAYER = 0;
    public static final int GENERAL_NEW_CHARACTER = 1;
    public static final int THEME_DARK_GROVE = 1;

    private static final LoreTreeBuilder LORE_TREE_BUILDER =
            LoreTreeBuilder
                    .plant()
                    .addTheme("General")
                        .addLeaf(
                                "New Player Message",
                                "\tThis appears to be your first time playing Chronicles of the Abyss on this computer. " +
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
                                        "\n\n\tThanks for playing, and enjoy the game."
                        )
                        .addLeaf(
                                "New Character Message",
                                "\tIt's been several weeks since you received notice of the disappearance of a distant relative, " +
                                        "and your subsequent inheritance of the ancestral estate in the remote wooded " +
                                        "countryside. " +
                                        "\n\n\tYour reasons for undertaking your present journey are twofold - first, this " +
                                        "particular relative was known for an eccentricity, including an interest in old and " +
                                        "forgotten things such as the distant and half-mythical history of the kingdom, as well " +
                                        "as even older and yet more mythical folk tales and traditions like those kept alive by " +
                                        "the secretive order of the Druids - interests which you also share.  As a younger child " +
                                        "of your own branch of the family, your prospects at home are small, so this unexpected " +
                                        "inheritance is doubly welcome. " +
                                        "\n\n\tAfter preparing your limited belongings and taking leave of your family, you've " +
                                        "travelled for several weeks, and have just arrived as the sun sets. The estate is an " +
                                        "ancient, time-worn building in a very isolated location - the last town was two days' " +
                                        "journey back, and you haven't seen another sign of human habitation since yesterday " +
                                        "afternoon. The deep woods on the hillside above it are also rather foreboding - but at " +
                                        "the same time, there's a certain allure, a feeling of being drawn towards something deep " +
                                        "within. " +
                                        "\n\n\tAfter unlocking the front gate with the old brass key that arrived with the " +
                                        "notice of your inheritance, you begin your inspection of the interior. There are quite " +
                                        "a few interesting rooms - it appears your relative kept rather busy here. Perhaps you " +
                                        "can discover something about the circumstances leading up to the disappearance that " +
                                        "resulted in your current fortune. " +
                                        "\n\n\tAs you enter the large and well-stocked library, you notice several books, as " +
                                        "well as several pages of hand-written notes on the reading desk in the back corner. " +
                                        "You wonder if they'll offer some insight into the events that preceded your arrival..."
                        )
                        .growTheme()
                    .addTheme("The Dark Grove"); //todo - add leaves
    private static final LoreTree STRING_TREE = LORE_TREE_BUILDER.getStringTree();
    public static final LoreTree LOCK_TREE = LORE_TREE_BUILDER.getLockTree();

    public static String nameAt(int themeIndex, int loreIndex) {
        return ((StringLeaf)STRING_TREE.get(themeIndex, loreIndex)).NAME;
    }
    public static String loreAt(int themeIndex, int loreIndex) {
        return ((StringLeaf)STRING_TREE.get(themeIndex, loreIndex)).LORE;
    }
}