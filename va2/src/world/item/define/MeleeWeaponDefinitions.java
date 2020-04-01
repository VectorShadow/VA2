package world.item.define;
import world.item.MeleeWeapon;

/**
 * Quick load MeleeWeapons from ItemDefinitions.
 */
public class MeleeWeaponDefinitions {
    //theme 0x0002_[0]000 (Innate)
    //quality 0x0002_0[0]00 (Innate)
    public static MeleeWeapon BARE_HANDED() {return (MeleeWeapon) ItemDefinitions.get(0x0002_0001).clone();}
    public static MeleeWeapon SPIDER_BITE() {return (MeleeWeapon) ItemDefinitions.get(0x0002_0001).clone();}
    public static MeleeWeapon WOLF_BITE() {return (MeleeWeapon) ItemDefinitions.get(0x0002_0002).clone();}
    public static MeleeWeapon RABID_WOLF_BITE() {return (MeleeWeapon) ItemDefinitions.get(0x0002_0003).clone();}
    public static MeleeWeapon BEAR_SWAT() {return (MeleeWeapon) ItemDefinitions.get(0x0002_0004).clone();}
    public static MeleeWeapon BEAR_BITE() {return (MeleeWeapon) ItemDefinitions.get(0x0002_0005).clone();}
    public static MeleeWeapon ACID_STING() {return (MeleeWeapon) ItemDefinitions.get(0x0002_0006).clone();}
    public static MeleeWeapon MOCCASIN_FANG() {return (MeleeWeapon) ItemDefinitions.get(0x0002_0007).clone();}
    public static MeleeWeapon WEB_STRIKE() {return (MeleeWeapon) ItemDefinitions.get(0x0002_0008).clone();}


    //theme 0x0002_[1]000 (Player Made)
    //quality 0x0002_1[1]00 (Mundane)
    public static MeleeWeapon BRONZE_SHORT_SWORD() {return (MeleeWeapon) ItemDefinitions.get(0x0002_1100).clone();}
}
