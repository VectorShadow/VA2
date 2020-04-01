package world.item.define;

import world.item.Resource;

public class LegacyResourceDefinitions {
    //theme 0x0000_[f]000 (Any)
    //quality 0x0000_0[1]00 (Mundane)
    public static Resource LUMBER() {return (Resource) ItemDefinitions.get(0x0000_f100).clone();}
    public static Resource STONE() {return (Resource) ItemDefinitions.get(0x0000_f101).clone();}
    public static Resource CLOTH() {return (Resource) ItemDefinitions.get(0x0000_f102).clone();}
    public static Resource SIMPLE_TOOLS() {return (Resource) ItemDefinitions.get(0x0000_f103).clone();}


    //quality 0x0000_0[3]00 (Scarce)
    //todo
}
