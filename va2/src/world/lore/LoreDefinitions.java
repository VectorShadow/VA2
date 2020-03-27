package world.lore;

import main.MetaData;
import world.dungeon.theme.DungeonTheme;

public class LoreDefinitions {
    public static final int THEME_GENERAL = 0;
    public static final int GENERAL_NEW_PLAYER = 0;
    public static final int GENERAL_NEW_CHARACTER = 1;
    public static final int GENERAL_ESTATE_MESSAGE = 2;
    public static final int GENERAL_HISTORY = 3;
    public static final int GENERAL_MYTHS = 4;
    public static final int THEME_DARK_GROVE = 1;
    public static final int DARK_GROVE_ARRIVAL = 0;
    public static final int DARK_GROVE_JOURNAL_PAGE_ONE = 1;
    public static final int DARK_GROVE_COMPLETION = 2; //todo - update as we add journal pages/other lore

    private static final LoreTreeBuilder LORE_TREE_BUILDER =
            LoreTreeBuilder
                    .plant()
                    .addTheme("General")
                        .addLeaf(
                                "New Player Message",
                                "\t\"The most merciful thing in the world, I think, is the inability of the " +
                                        "human mind to correlate all its contents. We live on a placid island of " +
                                        "ignorance in the midst of black seas of infinity, and it was not meant that " +
                                        "we should voyage far. The sciences, each straining in its own direction, " +
                                        "have hitherto harmed us little; but some day the piecing together of " +
                                        "dissociated knowledge will open up such terrifying vistas of reality, and " +
                                        "of our frightful position therein, that we shall either go mad from the " +
                                        "revelation or flee from the deadly light into the peace and safety of a " +
                                        "new dark age.\"" +
                                        "\n" +
                                        "\n-H. P. Lovecraft (The Call of Cthulhu)" +
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
                                        "interior is well-kept - or had been, until the vanishing of its former " +
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
                    .addLeaf("Message to the Heir",
                            "\t\"Samhein, 2993 A.R., Vor-Nyryk, Ys:" +
                                    "\n\n\tThis may be the last time I am able to write you - for what I am about to " +
                                    "attempt may be more dangerous than any of my previous explorations or " +
                                    "endeavors. " +
                                    "\n\n\tI have made a rather astonishing discovery. In these very woods, in a " +
                                    "deep grove so dark that not even the faint moonlight which illumines the rest " +
                                    "of the forest can penetrate, there is an ancient device of unknown origin, with " +
                                    "incredible power. It must have lain dormant for a very long time - perhaps it " +
                                    "was last used during the Dark Reign, nearly 2 millenia ago - or perhaps it has " +
                                    "been far, far longer than that. For it is very ancient - what little I have " +
                                    "discovered in my research that pertains, or might pertain, to it is not at all " +
                                    "direct, but the implications are that such devices as this were made not by " +
                                    "human hands, in aeons so distant that years are nearly useless in measuring " +
                                    "them. " +
                                    "\n\n\tThe date is significant as well - the festivals of the old Druids, around " +
                                    "which our calendar is based, are also far older than is commonly believed, and " +
                                    "the festivals themselves, or the cycles of time to which they correspond, have " +
                                    "a certain power. I believe I have discovered a way to re-activate this ancient " +
                                    "mechanism, but to be sure of my method, I must attempt it on this blackest of " +
                                    "nights. " +
                                    "\n\n\tIf my suspicions and hypotheses are correct, doing so will open up vast " +
                                    "episodes of earth's history which have been lost to time. I will be able to " +
                                    "confirm - or disprove - many of the connections and theories my research has " +
                                    "led me to discover, and illumine some of the deepest, darkest secrets recorded " +
                                    "now only in ancient, half-forgotten texts, most regarded by modern scholars " +
                                    "as entirely mythical, if not outright fanciful. " +
                                    "\n\n\tIt would appear that this device is part of a vast, ancient network, " +
                                    "connecting far corners of the earth - and perhaps even other worlds - at " +
                                    "prominent locations. It is rumored that the ancients knew of them, and any " +
                                    "civilization - indeed any race capable of establishing a great civilization, " +
                                    "for there have likely been countless cycles of civilization on this earth long " +
                                    "before humanity ever laid one stone atop another - would have been drawn to " +
                                    "them, and made use of them as they could. " +
                                    "\n\n\tProbably our own history has connections to them - the dark Tyrants of " +
                                    "nearby Vor-Nyryk, who presided over the Dark Reign, are said to have sent their " +
                                    "mages and explorers to distant corners of the earth, and they were rumored to " +
                                    "have known precisely how to find principal locations of elder power. Long " +
                                    "before them, beyond recorded history and into myth, those Angelic beings the " +
                                    "Church refers to as the Nephilim, or their supposed gods, the fabled golden " +
                                    "Annunaki, may have had a connection to them as well. The gleaming domes and " +
                                    "pillars of ancient and fallen Ur-Eden, or the even more ancient and ice-clad " +
                                    "ruins of lost Hyborea, known only to our most intrepid archaeologists, could be " +
                                    "laid bare to modern scholars at last. And who knows what else we might learn? " +
                                    "Perhaps the truth of those strange and terrible Hyborean murals depicting " +
                                    "warriors in battle against evil, twisted Serpent-men, or the truth of the even " +
                                    "older legend of Ultima Thule and the hairy half men who ruled the far north " +
                                    "aeons before the first Hyboreans, or the frightful and abhorrent whispers of " +
                                    "Typhon and the dread chimaeras... and of course, whatever lost race which " +
                                    "built this very network, so long ago. " +
                                    "\n\n\tI could write for hours of the endless arcane mysteries which might be " +
                                    "unlocked to us, but you know as well as I how many myth-cycles such as these " +
                                    "are hinted at in ancient books disregarded by, or totally unknown to, most in " +
                                    "our age. Better that I go and see for myself. As you will know if you ever read " +
                                    "this, I am leaving the estate to you if I fail to return from this adventure. " +
                                    "You are the best equipped to continue my research, if I should perish in it, " +
                                    "and certainly you are the least likely in the family to think me mad. The house " +
                                    "shall be yours to do with as you wish. I have begun several projects here, in " +
                                    "order to facilitate my research and explorations. I encourage you to expand " +
                                    "upon them as you can. Indeed, if I do not return from this expedition, you will " +
                                    "certainly need to be even more prepared than I am now - but I cannot wait " +
                                    "a full year, until next Samhein, to put my theories to the test." +
                                    "\n\n\tI wish you the best in your own adventures - though I hope I will yet have " +
                                    "the chance to do so in person.\"" +
                                    "\n\n\tSo... it seems the old man was involved in far more perilous enterprises " +
                                    "than he ever revealed in his missives. How much of what he writes, or hints at, " +
                                    "could possibly be true? As he noted, you are indeed aware of most of the myths " +
                                    "and legends he mentioned here, if apparently not so familiar with them as he " +
                                    "appears to have been. But even to your open and curious mind, most of those had " +
                                    "been just that - myths, perhaps allegorical in relation to known history, but " +
                                    "not with any great basis in fact." +
                                    "\n\n\tPre-human civilizations? Other worlds? A primal magic linked with the " +
                                    "turning of the seasons, and apparently far more powerful than anything known " +
                                    "to modern alchemists, or the mage-priests of the Church? " +
                                    "\n\n\tWell, certainly something unfortunate befell your old relative, if not " +
                                    "necessarily of eldritch nature. There are many natural dangers in a wood such " +
                                    "as this, without the need of any supernatural ones. You recall from certain of " +
                                    "his letters that there are packs of wolves, large spiders, bears, and other " +
                                    "dangerous beasts living in the forest, and for one so venerable, the danger was " +
                                    "likely even greater. Nevertheless, you resolve to make as thorough a search of " +
                                    "the forest as you can, in the coming days and weeks. Perhaps you will discover " +
                                    "what end he actually came to - from the date of the message, late last autumn, " +
                                    "you find it incredibly unlikely that he might still be among the living after " +
                                    "a winter in the wilderness. " +
                                    "\n\n\tPerhaps if you can locate this mysterious device, which had so fascinated " +
                                    "him that it apparently drew him on to that end, you might uncover further " +
                                    "evidence. And such a device, if indeed it behaves anything like his " +
                                    "description, would be well worth investigation in its own right. Hopefully, he " +
                                    "managed to complete whatever repair or rite was necessary to restore " +
                                    "whatever function it may have had, since it will be a long time yet before " +
                                    "Samhein comes this year..."
                        )
                        .addLeaf(
                                "Common Knowledge: History of Ys",
                                "\tThe history of the great empire of Ys goes back almost 3000 years, to the " +
                                        "founding of the fabled city of Avalon. These founders were greater than " +
                                        "ordinary humans in terms of intellect, physical prowess, and lifespan, " +
                                        "and became the benevolent rulers of the native men of Ys. The rustic tribal " +
                                        "peoples of Ys followed an ancient religion of earth magic led by mystical " +
                                        "nature priests called Druids, while the lords of Avalon followed a " +
                                        "sky-religion and revered vanished gods they called the Annunaki, and to a " +
                                        "lesser extent, their angelic servants, the Nephilim. This new faith slowly " +
                                        "replaced the old, and became the dominant religion of the island. " +
                                        "\n\n\tFor over a thousand years, the empire of Avalon grew in size and " +
                                        "power, expanding to cover a large part of the great continent south of the " +
                                        "island, as well as the frozen lands away to the north, over the western sea." +
                                        "This rise continued until the year 1263 in the Avalonian " +
                                        "Reckoning(henceforth A.R.), when Avalonian scholars made a disturbing " +
                                        "connection between a series of ancient scrolls preserved by a tribal people " +
                                        "from the desert lands far to the south and east of the continental colonies " +
                                        "carried back to Avalon by explorers and traders in those lands, and certain " +
                                        "artifacts discovered by archaeologists excavating the ancient ruins in the " +
                                        "north of frozen western lands. Certain mages in the Imperial House of Avalon " +
                                        "made use of the insights derived from this connection to attempt to unseal " +
                                        "certain places best left alone, and met with mixed success. Those who did " +
                                        "not perish in their attempts eventually tried to use their new powers to " +
                                        "take control of the Imperial House, resulting in a massive civil war that " +
                                        "led to the destruction of the Imperial House, the shattering of the empire, " +
                                        "and the isolation and abandonment of the more distant colonies. " +
                                        "\n\n\tFollowing the civil war was a dark age marked by continuous war " +
                                        "between the remaining noble and lesser royal houses of the empire. One of " +
                                        "these, the house of Vor-Nyryk, eventually triumphed by making use of the " +
                                        "same dark powers which had led to the civil war. Rumors unsubstantiated " +
                                        "by any credible historical records claim they employed strange hybrid " +
                                        "monsters, and even the walking dead, against their enemies. In any event, " +
                                        "they were able to destroy their enemies and sieze control of the empire, " +
                                        "relocating to and rebuilding the former capital of Avalon, thus beginning " +
                                        "what became known as the Dark Reign. These Tyrants continued the researches " +
                                        "of the evil mages of old Avalon, eventually exceeding even their most " +
                                        "terrible discoveries. They sent expeditions led by corrupt mages to even " +
                                        "more distant corners of the earth in search of even greater power drawn " +
                                        "from the buried secrets of earlier cycles, until finally their empire " +
                                        "suffered a catastrophic collapse. The cause of the collapse is unknown, " +
                                        "but modern historians and mages who have pursued some of the same avenues " +
                                        "with the intent of discovering that cause - rather than the lust for power " +
                                        "which drove the Tyrants - believe that they uncovered a power more " +
                                        "terrible than they could control, and were themselves destroyed." +
                                        "\n\n\tWhatever the cause, the collapse left the island of Ys in an even " +
                                        "darker age than that following the civil war. Avalon itself was transformed " +
                                        "into a nightmare ruin of lurking darkness and horror, and few who visited it " +
                                        "returned alive - and even fewer returned sane. After four hundred years of " +
                                        "relative darkness, though, the spirit of the Ysian empire rose again from " +
                                        "the ashes of its past in the form of the House of Ker. This house had a " +
                                        "a coastal holding on the southeastern shore of Ys, far from the ruins of " +
                                        "Avalon in the northwest, and least touched by the evils of the Dark Reign. " +
                                        "The house of Ker founded the nation of Ker-Ys, and slowly and peacefully " +
                                        "expanded their borders, unifying, stabilising, and enlightening the rest " +
                                        "of the isle as they brought the scattered and fragmented settlements back " +
                                        "into the fold. The new kingdom re-established the old Avalonian Reckoning, " +
                                        "rejecting the Black Calendar of the Tyrants, and marked its founding in the " +
                                        "year 2004 A.R., although this date is in some doubt due to the loss of " +
                                        "records during the Dark Reign and the prior and subsequent dark ages. Still, " +
                                        "it is accepted as at least generally accurate, to within a few decades, by " +
                                        "modern historians. " +
                                        "\n\n\tThe Kingdom of Ker-Ys continued to grow slowly, and once it again " +
                                        "covered the entire isle of Ys, it became known as the Empire of Ys, as it " +
                                        "remains to this day. Its rulers continued to style themselves as Kings, " +
                                        "rather than the Emperors of old Avalon, and employed a policy of " +
                                        "deliberate assimilation, internal growth of infrastructure, and focused " +
                                        "scientific and magical research, rather than the far-flung expansion " +
                                        "undertaken by Avalon. One consequence of this approach was the diffusion of " +
                                        "the higher blood of the founders of Avalon, which still ran strong in the " +
                                        "High Houses of Ys, throughout the general population, resulting in longer " +
                                        "and more productive lives by a larger proportion of its citizens. This came " +
                                        "with a corresponding increase in general magical ability, and in modern " +
                                        "times, magically able individuals come from all backgrounds, rather than " +
                                        "strictly noble and royal houses as in the past. " +
                                        "\n\n\tModern Ys has begun to slowly recover the old extent of the Avalonian " +
                                        "Empire, with coastal settlements on the continent, and a few distant " +
                                        "trading outposts in the eastern deserts, and south across the western " +
                                        "ocean near the distant and mysterious land of Mu. The old sky religion of " +
                                        "Avalon has merged to some extent with the nature worship of the old Druids, " +
                                        "presumably as a result of their significant aid in rebuilding the nation " +
                                        "from the ruins of the Dark Reign, and cleansing most of the rampant evil " +
                                        "that had spread beyond the nest of horror that Avalon had become. Avalon " +
                                        "itself remains shunned as a place of fear and loathing, but the rest " +
                                        "of Ys is a bright, prosperous land where the people work hard, yet have " +
                                        "time for leisure, worshipping, researching and exploring as they choose. "
                        )
                        .addLeaf(
                                "Common Knowledge: Myths of Wyrdys",
                                "\tTen thousand years ago, the great Annunaki Gods descended from Heaven and " +
                                        "created the Earth and the Seas and the Skies and everything within them, " +
                                        "including the first men. They ruled the earth, and the men who dwelt there, " +
                                        "for two thousand years. This was the Golden Age at the dawn of time, when " +
                                        "the World was young and bright and all was good and peaceful." +
                                        "\n\n\tAt the end of this age, the Gods returned to Heaven, though no tale " +
                                        "tells why. In their absence, the Spirits of Earth took up the guidance of " +
                                        "men, and the great servants of the Gods, the giants called the Nephilim, " +
                                        "dwelt in a land of paradise across the sea, seeking the reason they had " +
                                        "been abandoned by their Gods. This was the Silver Ages, when the world was " +
                                        "in its adolescence. Much that was good remained, but not all was peaceful, " +
                                        "and men began to war against men without the Gods to guide them, for the " +
                                        "Spirits of Earth were not revered as the gods were, and the children of " +
                                        "Gods were all gone over the sea." +
                                        "\n\n\tFive thousand years ago, the Nephilim came back over the sea - or " +
                                        "what few of them were left. For some tragedy had befallen them, some great " +
                                        "evil which they refused to tell to men. They settled in the ancient land " +
                                        "of Wyrden - a great island far larger than Ys, believed to have been to " +
                                        "the west of Ys. Here the greatest among men dwelt, and with the aid of the " +
                                        "Nephilim, they became even greater. Indeed, the Nephilim were created by " +
                                        "the Gods from the same substance as Men, and through them the very blood " +
                                        "of the Gods came among men on earth. These Nephilim were too few, and " +
                                        "eventually all of pure blood died, but their children among men became " +
                                        "the greatest among the men of earth, and continued to rule Wyrdys in their " +
                                        "name and the names of the Gods. In Ys itself, the ancestors of the modern " +
                                        "tribes arose in the forests, and worshipped the old Earth spirits, while " +
                                        "the Wyrden worshipped the true Gods. This was the Age of Bronze, or the " +
                                        "coming of age of the World. The old Gods and even their children were gone, " +
                                        "the old spirits of Nature had faded, and the time of Men was upon the " +
                                        "Earth. It was not all good, for much that was good had perished, and " +
                                        "Men fought many wars against other Men, with no living god but Himself to " +
                                        "guide him. " +
                                        "\n\n\tThree thousand years ago, a great tragedy befell the island continent " +
                                        "of Wyrdys, and it sank beneath the waves of the western ocean, but for a " +
                                        "tiny remnant, an inhospitable land of mountains, ice, and fire. No legend " +
                                        "tells of the reason for or the specific nature of this tragedy, but of " +
                                        "few among the Kings of Men who escaped it came the blood of Avalon. And for " +
                                        "these last three thousand years, the children of the Kings of Men, who were " +
                                        "the children of the Nephilim, who were the children of the Gods, have " +
                                        "fought terrible wars against one another. This is the Age of Iron, the old " +
                                        "age of the World. Most of that which was good in the beginning is now lost, " +
                                        "and the darkness ever encroaches. We live now in a time of relative piece, " +
                                        "with the brave and noble Kings of Ys holding this darkness at bay - for " +
                                        "the time being. The End of the World draws nigh, and men pray for the " +
                                        "of the Gods and the rebirth of the cycle of time."
                        )
                        .growTheme()
                    .addTheme("The Dark Grove")
                        .addLeaf(
                                "Dark Grove Arrival",
                                "\tAs you pass through the gate from the estate garden into the forest, you are " +
                                        "overcome with a sense of incredible age. This forest is full of ancient oak " +
                                        "trees, some of such massive size that they must have been growing for " +
                                        "centuries. " +
                                        "\n\n\tThe faint moonlight barely penetrates the canopy, and you can see only " +
                                        "by the light you carry with you. Beyond the glow of your torch, you fancy " +
                                        "that you can see the glow of hostile eyes staring in at you - and you can " +
                                        "hear faint scuttling and scurrying noises which surely are not fancy." +
                                        "\n\n\tYou remember the descriptions of some of the forest creatures from " +
                                        "old letters, and repress a shudder as you imagine spiders of the size those " +
                                        "letters suggested creeping about in this shadowed twilight... not to mention " +
                                        "the larger, and probably even more dangerous, predators which dwell in such " +
                                        "forests as these elsewhere in Ys. Here, in this ancient, undisturbed place, " +
                                        "it seems probable that they could grow to sizes unrivaled in forests closer " +
                                        "to human habitation." +
                                        "\n\n\tYou are glad of your sturdy bronze sword and leather armor as you set " +
                                        "forth into the darkness..."
                        )
                        .addLeaf(
                                "Journal Page 1",
                                "\t[a page torn from a journal]" +
                                        "\n\n\t...todo"
                        )
                        .addLeaf(
                                "Dark Grove Completion",
                                "\tThe silk wrapped abomination finally ceases its unwholesome lurching and " +
                                        "thrashing, and lies still. Its humanoid aspect chills you, and, suspecting " +
                                        "the worst, you steel yourself and clear away the silk over where the face " +
                                        "ought to be. " +
                                        "\n\n\tThough you had suspected, the shock is still very great and terrible, " +
                                        "and it takes a supreme effort of will to stifle the scream that threatens " +
                                        "to escape your tightly clenched jaw. For the face under that vile silken " +
                                        "wraps is indeed familiar to you - and now you know what terrible end your " +
                                        "old relative and friend had come to. " +
                                        "\n\n\tYet clearly some unholy force is at work here - despite the partial " +
                                        "mummification induced by the wrapping, there is evidence of some slight " +
                                        "decay - and the behavior you encountered was certainly not that of a living " +
                                        "human being, unless entirely mad... and even then, blinded as it was, no " +
                                        "human could have fought you so capably unless guided by powers other than " +
                                        "natural sight - nor indeed would any human have been able to use those " +
                                        "awful silken cords to attack as this abomination did. And the way it moved - " +
                                        "there was something terrible, almost spider-like, in that action." +
                                        "\n\n\tOn the corpse is what remains of a journal, battered and missing many " +
                                        "pages, and it confirms the identity of the thing. It also contains a truly " +
                                        "terrifying account of what must have been the last moments of its author: " +
                                        "\n\n\t\"I have succeeded in my attempt to reactivate the ancient mechanism " +
                                        "deep in that accursed wood, and, as I suspected, it has indeed proved to be " +
                                        "a gateway of sorts. A gateway to where, though, I have yet to determine. " +
                                        "Upon entering it, I found myself in this strange, terrible, and " +
                                        "frighteningly ancient place - if I am still on the Earth, it is not any part " +
                                        "of our normal understanding of it. I believe that this is somehow a space " +
                                        "inside of, or in between, the normal space of Earth, and perhaps it serves " +
                                        "as a sort of conduit or shortcut between locations within our natural world. " +
                                        "\n\n\tThis must be how the network functions - by entering this awful limbo, " +
                                        "a traveller should then be able to seek out other nodes like the one back in " +
                                        "that old forest behind my estate, and re-emerge in some location that would " +
                                        "take far longer to travel to by ordinary means. I believed I had found such " +
                                        "a node, and began making my way towards it, when I saw the most horrifying " +
                                        "creature I could have ever imagined ahead of me. It was like some vast " +
                                        "spider, greater even than the most monstrous spiders lurking in the grove " +
                                        "back in the outer world, but with a subtly different outline and form - " +
                                        "different in some way that evoked in me a creeping dread beyond the " +
                                        "natural loathsomeness inspired in some by ay spider, or the mortal fear of " +
                                        "a likely predatory monster of this size - I feel as though something out " +
                                        "of forgotten aeons has awoken and now hunts me in the present. " +
                                        "\n\n\tThat is, if this other world can still be considered to be a part of " +
                                        "that present. The walls and floor of this place seem woven from vines and " +
                                        "thorns of ancient plants, like those sometimes found frozen in stone, but " +
                                        "never alive and growing. Yet here, while they cannot still be growing, or " +
                                        "even alive in the normal sense, they are certainly not petrified, " +
                                        "suggesting instead a wooden construction, only made of the dead plants and " +
                                        "trees directly, rather than processed lumber. Covering these walls, though, " +
                                        "are terrible webs greater than anything I've ever seen in the natural " +
                                        "world - just the sort of web one might expect a creature like that arachnid " +
                                        "horror would weave. " +
                                        "\n\n\tIn any case, I retreated back towards the node from which I had " +
                                        "entered - only to find, to my growing terror, yet another of the giant " +
                                        "spider-horrors. I fled deeper into the darkness of this tangled realm, " +
                                        "hoping for some other escape... yet in the end, I found myself in a blind " +
                                        "corner. And now, as I write in the flickering light of my fading torch, " +
                                        "I can here a creaking, scuttling noise - enough to suggest that several " +
                                        "of those monstrosities are closing in on me. I shall prepare myself for a " +
                                        "final stand, but I fear that I will not prevail against such creatures...\"" +
                                        "\n\n\tSo that is how it ended. What an awful fate - trapped in the dark, " +
                                        "with unknown horrors encroaching - and to then be captured, mummified, and " +
                                        "horribly re-animated... You cannot help but shudder involuntarily. Yet " +
                                        "you yourself risk a similar fate. You have been fortunate to have faced " +
                                        "those monsters in smaller numbers - for certainly the horrors described " +
                                        "in the journal and the ones accompanying that shambling mummy were the same " +
                                        "class of entity. You can only hope that your experiences in combat so far " +
                                        "will increase your abilities, and allow you to prevail against stronger " +
                                        "opponents. " +
                                        "\n\n\tFor you cannot let your predecessor's quest end here. This discovery " +
                                        "is too great, too portentous to abandon in terror. Though the risk, and " +
                                        "the creeping, aeons-old horror, are great, you believe you can rise to the " +
                                        "task - or at least, you feel the need to make the attempt. You peer into the " +
                                        "gaping maw of the unholy ancient gateway before you, and look upon that " +
                                        "same nightmare realm described in the journal. But before braving its " +
                                        "dangers, known, unknown, and suspected, you decide to return to the estate " +
                                        "to make further preparations, as you can. There is also a younger relative, " +
                                        "from another branch of the family, who shares the same interests and " +
                                        "inclinations that led both you and your older relative to this dark, " +
                                        "time-lost realm of elder mystery. Perhaps you should send a similar missive, " +
                                        "in case you meet a similar end in your own future explorations. "
                        )
                        .growTheme()
                    .addTheme("The Desolation of Avalon")
                        .growTheme()
                    .addTheme("The Sunken Metropolis")
                        .growTheme()
                    .addTheme("The Catacombs of Ur-Eden")
                        .growTheme()
                    .addTheme("The Ophidian Temple")
                        .growTheme()
                    .addTheme("The Well of Corruption")
                        .growTheme()
                    .addTheme(" The Typhonian Inferno")
                        .growTheme()
                    .addTheme("The Crypt of Capac Urcu")
                        .growTheme()
                    .addTheme("The Realm of Eternal Night")
                        .growTheme()
                    .addTheme("The Bathyscape of Centaurus")
                        .growTheme()
                    .addTheme("The Glacial Prison")
                        .growTheme();
    private static final LoreTree STRING_TREE = LORE_TREE_BUILDER.getStringTree();
    private static LoreTree lockTree = LORE_TREE_BUILDER.getLockTree();

    public static LoreTree getLockTree() {
        return lockTree;
    }

    public static void setLockTree(LoreTree loreTree) {
        lockTree = loreTree;
    }

    public static String nameAt(int themeIndex, int loreIndex) {
        return ((StringLeaf)STRING_TREE.get(themeIndex, loreIndex)).NAME;
    }
    public static String loreAt(int themeIndex, int loreIndex) {
        return ((StringLeaf)STRING_TREE.get(themeIndex, loreIndex)).LORE;
    }

    /**
     * Used to unlock lore without displaying it via Session.unlockLore().
     */
    public static void silentUnlock(int themeIndex, int loreIndex) {
        ((LockLeaf)getLockTree().getBranch(themeIndex).get(loreIndex)).unlock();
    }
    public static boolean anyUnlocked(int themeIndex) {
        ThemeBranch tb = lockTree.getBranch(themeIndex);
        for (LoreLeaf ll : tb) {
            if (!((LockLeaf)ll).isLocked()) return true;
        }
        return false;
    }
    public static int themeIndex(DungeonTheme dt) {
        switch (dt.getDifficulty()) {
            case 0:
                return THEME_GENERAL;
            case 5:
                return THEME_DARK_GROVE;
            default:
                    throw new IllegalArgumentException("Unsupported theme " + dt.getName());
        }
    }
    public static int arrivalIndex(DungeonTheme dt) {
        switch (dt.getDifficulty()) {
            case 0:
                return GENERAL_NEW_PLAYER;
            case 5:
                return DARK_GROVE_ARRIVAL;
            default:
                throw new IllegalArgumentException("Unsupported theme " + dt.getName());
        }
    }
    public static int completionIndex(DungeonTheme dt) {
        switch (dt.getDifficulty()) {
            case 0:
                return GENERAL_NEW_CHARACTER;
            case 5:
                return DARK_GROVE_COMPLETION;
            default:
                throw new IllegalArgumentException("Unsupported theme " + dt.getName());
        }
    }
}
