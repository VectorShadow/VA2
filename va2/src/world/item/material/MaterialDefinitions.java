package world.item.material;

import static world.item.material.Material.*;

public class MaterialDefinitions {
    /**
     * The standard for hardness is Iron, which has a hardness of 32. Common stone is half that, at 16,
     * with harder stone such as granite and softer metals like bronze ranging between the two values.
     * Common wood has a hardness of 8, with hard woods, soft stones, and certain non-metallic items from 9-15.
     * Leather has a hardness of 4, with harder leather and similar materials from 5-7, while cloth has a hardness of 2.
     * Harder metals like steel might be has high as 35 or 40, and certain rare and exotic substances like Orychalchum
     * as high as 50 or 60. Annealed Titanium, used by ancient spacefarers, is rated at 64. Anything above this is
     * exceptionally rare and powerful.
     *
     * Volatility is by prime factorization - for example, the oxidization reaction has a subject(2) and a catalyst(3),
     * for a product of 6. The volatility factors of two interacting items are multiplied together, and for each
     * reaction that results, the damage is doubled. Two iron weapons, with the oxidization subject of 2, will not
     * react, since those factors will mutliply together to result in 4, which does not specify a reaction.
     */
    public static final Material FLESH = new Material(
            "flesh",
            3,
            damageTypeArray(-1, 0, 0, null, 0, +1, 0, null, 0, -1, null),
            ORGANIC
    );
    public static final Material TOOTH = new Material(
            "tooth",
            12,
            damageTypeArray(+2, 0, +1, null, +1, 0, +2, null, +3, +3, null),
            ORGANIC
    );
    public static final Material BRONZE = new Material(
            "bronze",
            24,
            damageTypeArray(+3, -3, 0, null, 0, 0, -2, null, -1, +1, null)
    );
    public static final Material SOFT_LEATHER = new Material(
            "soft leather",
            3,
            damageTypeArray(-3, +2, +2, null, 0, +2, +4, null, -1, 0, null),
            ORGANIC
    );
    public static final Material CHITIN = new Material(
            "chitin",
            9,
            damageTypeArray(+2, +1, -3, null, -2, -1, 0, null, +1, +2, null),
            ORGANIC
    );
    public static final Material ANCIENT_CHITIN = new Material(
            "ancient chitin",
            13,
            damageTypeArray(+4, +2, -3, null, -2, -1, 0, null, +2, +3, null),
            ORGANIC
    );
    public static final Material SNAKESKIN = new Material(
            "snakeskin",
            4,
            damageTypeArray(+2, +1, -2, null, 0, 0, -2, null, 0, +1, null),
            ORGANIC
    );
    public static final Material SPIDER_SILK = new Material(
            "spider silk",
            2,
            damageTypeArray(-3, +2, +2, null, 0, +3, +2, null, -1, 0, null),
            ORGANIC
    );
    public static final Material BEAST_HIDE_AND_FUR = new Material(
            "furry beast hide",
            4,
            damageTypeArray(-2, +1, +4, null, 0, +1, 0, null, -1, -3, null),
            ORGANIC
    );
    public static final Material PARCHMENT = new Material(
            "parchment",
            1,
            damageTypeArray(-2, +1, 0, null, -4, 0, 0, null, -1, -1, null),
            ORGANIC
    );
    public static final Material COMMON_STONE = new Material(
            "common stone",
            16,
            damageTypeArray(+1, 0, 0, null, 0, -1, +3, null, +2, +2, null)
    );
}
