package world.item;

import io.out.message.MessageCenter;
import io.out.message.MessageType;
import main.Session;
import world.lore.language.Language;
import world.lore.language.LanguageKnowledge;
import world.lore.LockLeaf;
import world.lore.LoreDefinitions;

import java.util.Random;

public class Text extends StackableItem {

    final Language PRIMARY;
    final Language SECONDARY;
    final int LEARNING_POWER;

    final int GRIMOIRE_ID;

    final int LORE_INDEX_MINIMUM;
    final int LORE_INDEX_MAXIMUM;



    public Text(ItemTemplate it, Language l, int lp, int lMin, int lMax) {
        this(it, l, lp, -1, lMin, lMax);
    }

    public Text(ItemTemplate it, Language l, int lp, int g, int lMin, int lMax) {
        this(it, l, null, lp, g, lMin, lMax);
    }

    public Text(ItemTemplate it, Language p, Language s, int lp, int lMin, int lMax) {
        this(it, p, s, lp, -1, lMin, lMax);
    }

    public Text(ItemTemplate it, Language p, Language s, int lp, int g, int lMin, int lMax) {
        super(it);
        PRIMARY = p;
        SECONDARY = s;
        LEARNING_POWER = lp;
        GRIMOIRE_ID = g;
        LORE_INDEX_MINIMUM = lMin;
        LORE_INDEX_MAXIMUM = lMax;
    }
    private Text(Text t) {
        this((ItemTemplate)t.getTemplate(), t.PRIMARY, t.SECONDARY, t.LEARNING_POWER, t.LORE_INDEX_MINIMUM, t.LORE_INDEX_MAXIMUM);
    }

    @Override
    public Item clone() {
        return new Text(this);
    }

    @Override
    public double getIntegrityPercent() {
        return 1.0;
    }

    public boolean study() {
        LanguageKnowledge lk = Session.getLanguageKnowledge();
        MessageCenter mc = Session.getMessageCenter();
        Random rng = Session.getRNG();
        double primaryKnowledge = lk.getKnowledge(PRIMARY);
        double secondaryKnowledge = SECONDARY == null ? -1.0 : lk.getKnowledge(SECONDARY);
        mc.clearNewMessages();
        if (primaryKnowledge <= 0.0 && secondaryKnowledge <= 0.0) {
            mc.sendMessage(
                    "You do not recognize any language within this text.",
                    MessageType.WARNING,
                    MessageCenter.PRIORITY_MAX
            );
            return false;
        }
        double initialLearning = (0.05 + (rng.nextDouble() / 10.0));
        if (primaryKnowledge > 0.0 && secondaryKnowledge == 0.0) {
            lk.setKnowledge(PRIMARY, PRIMARY.learn(primaryKnowledge, LEARNING_POWER));
            lk.setKnowledge(SECONDARY, initialLearning / ((double)rng.nextInt(SECONDARY.ordinal())));
            mc.sendMessage(
                    primaryKnowledge == 1.0
                            ? "You already fully understand " + PRIMARY.getName() + "."
                            : "You gain a better understanding of " + PRIMARY.getName() + ".",
                    MessageType.INFO,
                    MessageCenter.PRIORITY_MAX
            );
            mc.sendMessage(
                    "You have learned something about " + SECONDARY.getName() + "!",
                    MessageType.SUCCESS,
                    MessageCenter.PRIORITY_MAX
            );
        } else if (primaryKnowledge == 0.0 && secondaryKnowledge > 0.0) {
            lk.setKnowledge(SECONDARY, SECONDARY.learn(secondaryKnowledge, LEARNING_POWER));
            lk.setKnowledge(PRIMARY, initialLearning / ((double)rng.nextInt(PRIMARY.ordinal())));
            mc.sendMessage(
                    secondaryKnowledge == 1.0
                            ? "You already fully understand " + SECONDARY.getName() + "."
                            : "You gain a better understanding of " + SECONDARY.getName() + ".",
                    MessageType.INFO,
                    MessageCenter.PRIORITY_MAX
            );
            mc.sendMessage(
                    "You have learned something about " + PRIMARY.getName() + "!",
                    MessageType.SUCCESS,
                    MessageCenter.PRIORITY_MAX
            );
        } else {
            lk.setKnowledge(PRIMARY, PRIMARY.learn(primaryKnowledge, LEARNING_POWER));
            mc.sendMessage(
                    primaryKnowledge == 1.0
                            ? "You already fully understand " + PRIMARY.getName() + "."
                            : "You gain a better understanding of " + PRIMARY.getName() + ".",
                    MessageType.INFO,
                    MessageCenter.PRIORITY_MAX
            );
            if (SECONDARY != null) {
                lk.setKnowledge(SECONDARY, SECONDARY.learn(secondaryKnowledge, LEARNING_POWER));
                mc.sendMessage(
                        secondaryKnowledge == 1.0
                                ? "You already fully understand " + SECONDARY.getName() + "."
                                : "You gain a better understanding of " + SECONDARY.getName() + ".",
                        MessageType.INFO,
                        MessageCenter.PRIORITY_MAX
                );
            }
        }
        int loreTheme = LoreDefinitions.themeIndex(getThemeIndex());
        //todo - if Lore theme == Ysian Estate, this is a recipe or blueprint. We'll define these somewhere.
        if (loreTheme > LoreDefinitions.THEME_GENERAL) {
            int leafIndex = rng.nextInt((LORE_INDEX_MAXIMUM - LORE_INDEX_MINIMUM) + 1) + LORE_INDEX_MINIMUM;
            if (((LockLeaf) (LoreDefinitions.getLockTree().getBranch(loreTheme).get(leafIndex))).isLocked()) {
                Session.unlockLore(loreTheme, leafIndex, null);
                mc.sendMessage(
                        "You learn something new.",
                        MessageType.INFO,
                        MessageCenter.PRIORITY_MAX
                );
            } else {
                mc.sendMessage(
                        "You are unable to glean any especially interesting information from this text.",
                        MessageType.INFO,
                        MessageCenter.PRIORITY_MAX
                );
            }
        }
        return true;
    }

    public boolean isGrimoire() {
        return GRIMOIRE_ID > 0;
    }

    //todo - public Grimoire getGrimoire()
    /*
    {
        return (Grimoire)ItemDefinitions.get(GRIMOIRE_ID);
    }
     */
}
