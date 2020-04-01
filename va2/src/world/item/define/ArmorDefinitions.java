package world.item.define;

import world.item.Armor;

/**
 * Quick load Armors from ItemDefinitions.
 */
public class ArmorDefinitions {
    //theme 0x0003_[0]000 (Innate)
    //quality 0x0003_0[0]00 (Innate)
    public static Armor UNARMORED() {return (Armor) ItemDefinitions.get(0x0003_0000).clone();}
    public static Armor ARACHNID_EXOSKELETON() {return (Armor) ItemDefinitions.get(0x0003_0001).clone();}
    public static Armor ANCIENT_ARACHNID_EXOSKELETON() {return (Armor) ItemDefinitions.get(0x0003_0002).clone();}
    public static Armor SNAKESKIN_SCALE_ARMOR() {return (Armor) ItemDefinitions.get(0x0003_0003).clone();}
    public static Armor SPIDER_SILK_COCOON() {return (Armor) ItemDefinitions.get(0x0003_0004).clone();}
    public static Armor WOLF_HIDE() {return (Armor) ItemDefinitions.get(0x0003_0005).clone();}
    public static Armor BEAR_HIDE() {return (Armor) ItemDefinitions.get(0x0003_0006).clone();}


    //theme 0x0003_[1]000 (Player Made)
    //quality 0x0003_1[1]00 (Innate)
    public static Armor LEATHER_VEST() {return (Armor) ItemDefinitions.get(0x0003_1100).clone();}

}
