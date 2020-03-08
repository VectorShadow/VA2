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
                                "\t\"The most merciful thing in the world, I think, is the inability of the human " +
                                        "mind to correlate all its contents. We live on a placid island of ignorance " +
                                        "in the midst of black seas of infinity, and it was not meant that we should " +
                                        "voyage far. The sciences, each straining in its own direction, have hitherto " +
                                        "harmed us little; but some day the piecing together of dissociated knowledge " +
                                        "will open up such terrifying vistas of reality, and of our frightful " +
                                        "position therein, that we shall either go mad from the revelation or flee " +
                                        "from the deadly light into the peace and safety of a new dark age.\"" +
                                        "\n" +
                                        "\n-H. P. Lovecraft, in The Call of Cthulhu" +
                                        "\n" +
                                        "\n\n\tThis appears to be your first time playing Chronicles of the Abyss on " +
                                        "this computer. " +
                                        "\n\n\n\tChronicles is the spiritual successor to Viridian Abyss, and takes " +
                                        "place in the distant past, a past referenced in some of the Grimoire texts " +
                                        "available within that game. It seeks to expand on the story and the world " +
                                        "first established there." +
                                        "\n\n\tIt is a roguelike dungeon crawler set in a fantasy/horror universe. You " +
                                        "progress through the game by exploring various dungeons and collecting " +
                                        "experience and items to increase your own power and delve ever deeper. " +
                                        "You'll begin in your ancestral estate, which serves as your base of " +
                                        "operations and can be upgraded as you acquire building materials, " +
                                        "blueprints, recipes, and other items on your travels." +
                                        "\n\n\tDeaths are permanent. If you die during the course of your " +
                                        "adventures, you'll need to create a new character and start over again. " +
                                        "However, any upgrades you've unlocked at your estate will persist, and " +
                                        "you'll be able to use any features available with your current items and " +
                                        "experience in any game. " +
                                        "\n\n\tFor more specific information about the game, press '?' to display a " +
                                        "list of commands available to you, or check out the included Manual text " +
                                        "file in the game directory. " +
                                        "\n\n\tIf you encounter any bugs during gameplay, feel free to contact the " +
                                        "developer at " + MetaData.contact() + ". Any fatal crashes which occur " +
                                        "should generate an error log. If this happens, please include that log with " +
                                        "your bug report. " +
                                        "\n\n\tThanks for playing, and enjoy the game."
                        )
                        .addLeaf(
                                "New Character Message",
                                "\tTwelfth of Ostara, 2994 A.R., Myr-Galyn, Ys:" +
                                        "\n\n\tToday I received notice of the unfortunate disappearance, and presumed " +
                                        "death, of a distant relative who had held the old ancestral estate in the " +
                                        "wooded highlands east of Old Vor-Nyryk. I am, perhaps, the only one who " +
                                        "will feel much regret at the passing of the old man, as he was not much " +
                                        "liked by the family, considered an eccentric hermit best left to his own " +
                                        "devices. I, however, shared many of his interests from an early age, and " +
                                        "had kept up some manner of correspondence with him over the past decade or " +
                                        "so. We both had a fascination with old and occult things, such as the " +
                                        "ancient rites of the old Druidic faith before their integration into the " +
                                        "modern Church, the half-mythical accounts of Wyrdys and its fall beneath " +
                                        "the waves, or the terrible rumours regarding the desolation of Avalon. " +
                                        "\n\n\tOur correspondence would center on my latest researches into some of " +
                                        "these accounts as a student of the University here in Myr-Galyn, or his own " +
                                        "experimentations with formulae found in ancient scrolls or runestones, or " +
                                        "speculation on some of the strange tales carried by travellers to the far " +
                                        "lands of Mu, or of the great central deserts far to the south-east, beyond " +
                                        "the dark continent. I would certainly miss these discussions, but there was " +
                                        "more to the notice - for it seemed his will had named me as the heir of the " +
                                        "old estate." +
                                        "\n\n\tBeing a younger child, with no prospects for inheriting any of the more " +
                                        "noted family holdings, this was both unexpected and quite welcome - for not " +
                                        "only would I be established in my own right, but I would also have both the " +
                                        "means and the freedom to pursue my own research to the fullest - or, indeed, " +
                                        "pick up his, wherever he may have left it. Certainly, too, I could choose " +
                                        "to investigate the odd circumstances surrounding his disappearance - " +
                                        "perhaps, after all, he is not dead, only off on some journey of discovery, " +
                                        "the fruits of which might exceed all either of us had yet uncovered..." +
                                        "\n\n\n\tNinth til Beltane, 2994 A.R., Vor-Nyryk, Ys:" +
                                        "\n\n\tIt has been several weeks since the notice of my inheritance. I " +
                                        "gathered my few personal possessions and some travelling gear and took my " +
                                        "leave of my family. They had congratulated me on my unexpected fortune, and " +
                                        "wished me well on my journey, but I am sure they were not too distraught " +
                                        "over my departure. I am certainly not as ill-regarded as the relative whose " +
                                        "estate I am now master of, but I suspect my inclinations had led me to " +
                                        "start down that same path..." +
                                        "\n\n\tIn any case, I have now arrived at the estate in question. It seems " +
                                        "even more ancient and weathered than I recall from my last visit as a " +
                                        "youth, but for all that it appears to be structurally sound, and the " +
                                        "interior is well-kept - or had been, until the vanishing of it former " +
                                        "owner. It is also more remote than I had remembered - I stayed the night at " +
                                        "an inn in the hamlet of Eth-sar two nights ago, and there have been no " +
                                        "other such settlements since then - only a few isolated cots and farms, and " +
                                        "those were all closer to Eth-sar. The wooded hills looming over the estate " +
                                        "are deep, dark, and foreboding, carrying a sense of primal mystery which " +
                                        "surely served to inspire some of my relative's mythical pursuits. Indeed, " +
                                        "there is a gateway leading from the back garden out into those woods, and " +
                                        "it appears he made not infrequent use of it. " +
                                        "\n\n\tBut there is more to this old mansion than just the proximity to those " +
                                        "timeless hills and ancient trees. There are many strange and interesting " +
                                        "rooms here, all dedicated to scientific, mystical, or adventurous pursuits. " +
                                        "There is an Armory of sorts, and several halls for weapons training - I had " +
                                        "not known the old man to have any martial interests, but the rooms are not " +
                                        "unused, nor are the weapons. It seems that at least some of his endeavors " +
                                        "had been more dangerous than I expected or inferred from his letters. " +
                                        "There is also an alchemical Laboratory, a sort of Taxidermy displaying some " +
                                        "very unusual, and, truth be told, unsettling, specimens, a Warehouse, " +
                                        "empty now but with evidence of having once contained some of the materials " +
                                        "used in constructing some of these specialized rooms, and a Forge and " +
                                        "Workshop. Again, I had not known of these areas of his interests, but as " +
                                        "with the Armory, both show clear and capable use. " +
                                        "\n\n\tMost interesting to me, at the moment, however, is the large and " +
                                        "well-provided Library he kept. That, I had known of, and have vague " +
                                        "memories of conversations there during that long-ago visit that resulted in " +
                                        "our correspondence, and perhaps indeed my current inheritance - but it has " +
                                        "certainly been expanded since I was last here. Volumes of every type line " +
                                        "the shelves - histories, mythologies, alchemical manuals, translations of " +
                                        "odd books from myriad sources, even a rare occult tome of which I had heard " +
                                        "rumors, but never managed to locate for my own perusal. " +
                                        "\n\n\tBut even that is not what catches my immediate attention. For on the " +
                                        "reading desk in the back corner, there are several books, presumably " +
                                        "related to whatever research project was ongoing when last this estate was " +
                                        "occupied. Upon opening the top-most book to a natural break in the pages, I " +
                                        "have discovered several pages of notes, written in a hand I well recognize " +
                                        "from years of correspondence. Perhaps they will offer some insight into " +
                                        "whatever strange events heralded my own arrival here..."
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
