package world.actor;

import ai.AIDefinitions;
import combat.Combatant;
import combat.PlayerCombatant;
import combat.armor.ArmorDefinitions;
import combat.melee.forms.FormDefinitions;
import combat.melee.weapons.MeleeWeaponDefinitions;
import main.progression.Reward;
import resources.chroma.Chroma;
import resources.continuum.Continuum;
import resources.continuum.Pair;
import util.ArrayListBuilder;
import world.ColorStandards;
import world.WorldObjectTemplateFactory;
import world.item.MeleeWeapon;

import java.awt.*;

/**
 * Contains the definitions for each available ActorTemplate.
 */
public class ActorDefinitions {
    public static final ActorTemplate PLAYER_TEMPLATE =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("Ysian adventurer")
                    .setSymbols('@')
                    .manufactureActorTemplate(
                            25,
                            new PlayerCombatant(),
                            new Reward(0),
                            AIDefinitions.PLAYER_AI
                    );
    public static final ActorTemplate WOODLAND_SPIDER =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("woodland spider")
                    .setSymbols('s')
                    .setForegroundColors(
                            new Color[] {
                                    Chroma.BROWN,
                            }
                    )
                    .manufactureActorTemplate(
                            38,
                            new Combatant(
                                    8,
                                    new int[] {4, 4, 1, 1, 1},
                                    FormDefinitions.SMALL_BEAST,
                                    MeleeWeaponDefinitions.SPIDER_BITE,
                                    ArmorDefinitions.ARACHNID_EXOSKELETON
                            ),
                            new Reward(4),
                            AIDefinitions.CCW_AI
                    );
    public static final ActorTemplate LARGE_HUNTING_SPIDER =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("large hunting spider")
                    .setSymbols('s')
                    .setForegroundColors(
                            new Color[] {
                                    ColorStandards.HUNTING_SPIDER_BODY,
                                    ColorStandards.HUNTING_SPIDER_BODY,
                                    Chroma.BROWN,
                                    Chroma.BEIGE
                            }
                    )
                    .manufactureActorTemplate(
                            37,
                            new Combatant(
                                    16,
                                    new int[]{6, 6, 1, 1, 2},
                                    FormDefinitions.SMALL_BEAST,
                                    MeleeWeaponDefinitions.SPIDER_BITE,
                                    ArmorDefinitions.ARACHNID_EXOSKELETON
                            ),
                            new Reward(8),
                            AIDefinitions.CCW_AI
                    );
    public static final ActorTemplate GIANT_HUNTING_SPIDER =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("giant hunting spider")
                    .setSymbols('s')
                    .setForegroundColors(
                            new Color[] {
                                    ColorStandards.HUNTING_SPIDER_BODY,
                                    ColorStandards.HUNTING_SPIDER_BODY,
                                    ColorStandards.HUNTING_SPIDER_BODY,
                                    ColorStandards.HUNTING_SPIDER_BODY,
                                    ColorStandards.HUNTING_SPIDER_BODY,
                                    ColorStandards.HUNTING_SPIDER_BODY,
                                    ColorStandards.HUNTING_SPIDER_HOURGLASS
                            }
                            )
                    .manufactureActorTemplate(
                            36,
                            new Combatant(
                                    48,
                                    new int[] {8, 6, 2, 2, 5},
                                    FormDefinitions.SMALL_BEAST,
                                    MeleeWeaponDefinitions.SPIDER_BITE,
                                    ArmorDefinitions.ARACHNID_EXOSKELETON
                            ),
                            new Reward(16),
                            AIDefinitions.CCW_AI
                    );
    public static final ActorTemplate FOREST_WOLF_TEMPLATE =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("forest wolf")
                    .setSymbols('w')
                    .setForegroundColors(Chroma.BROWN)
                    .manufactureActorTemplate(
                            32,
                            new Combatant(
                                    128,
                                    new int[]{16, 7, 8, 3, 10},
                                    FormDefinitions.SMALL_BEAST,
                                    MeleeWeaponDefinitions.WOLF_BITE,
                                    ArmorDefinitions.WOLF_HIDE
                            ),
                            new Reward(20),
                            AIDefinitions.CCW_AI
                    );
    public static final ActorTemplate SILVER_WOLF_TEMPLATE =
            WorldObjectTemplateFactory
                    .initialize(true)
                    .setName("silver wolf")
                    .setSymbols('w')
                    .setForegroundColors(Chroma.METALLIC_SILVER)
                    .manufactureActorTemplate(
                            30,
                            new Combatant(
                                    196,
                                    new int[]{18, 6, 10, 5, 12},
                                    FormDefinitions.SMALL_BEAST,
                                    MeleeWeaponDefinitions.WOLF_BITE,
                                    ArmorDefinitions.WOLF_HIDE
                            ),
                            new Reward(32),
                            AIDefinitions.CCW_AI
                    );
    public static final ActorTemplate RABID_WOLF_TEMPLATE =
            WorldObjectTemplateFactory
                    .initialize(false)
                    .setName("rabid wolf")
                    .setSymbols('w')
                    .setForegroundColors(
                            new Color[] {
                                    Chroma.BROWN,
                                    Chroma.BROWN,
                                    Chroma.BROWN,
                                    Chroma.BROWN,
                                    Chroma.CRIMSON
                            })
                    .manufactureActorTemplate(
                            33,
                            new Combatant(
                                    116,
                                    new int[]{16, 7, 8, 3, 10},
                                    FormDefinitions.SMALL_BEAST,
                                    MeleeWeaponDefinitions.RABID_WOLF_BITE,
                                    ArmorDefinitions.WOLF_HIDE
                            ),
                            new Reward(28),
                            AIDefinitions.CC_RABID_AI
                    );
    public static final ActorTemplate BLACK_BEAR_TEMPLATE =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("black bear")
                    .setSymbols('b')
                    .setForegroundColors(ColorStandards.BLACK_BEAR_FUR)
                    .manufactureActorTemplate(
                            24,
                            new Combatant(
                                    400,
                                    new int[] {9, 5, 10, 16, 12},
                                    FormDefinitions.LARGE_BEAST,
                                    MeleeWeaponDefinitions.BEAR_BITE,
                                    ArmorDefinitions.BEAR_HIDE
                            ),
                            new Reward(48),
                            AIDefinitions.CCW_AI
                    );
    public static final ActorTemplate CAVE_BEAR_TEMPLATE =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("giant cave bear")
                    .setSymbols('b')
                    .setForegroundColors(
                            new Color[]{
                                    Chroma.BROWN,
                                    Chroma.GREY,
                                    ColorStandards.BLACK_BEAR_FUR
                            }
                            )
                    .manufactureActorTemplate(
                            24,
                            new Combatant(
                                    555,
                                    new int[]{10, 4, 10, 20, 20},
                                    FormDefinitions.LARGE_BEAST,
                                    new Continuum<MeleeWeapon>(
                                            MeleeWeaponDefinitions.BEAR_SWAT,
                                            ArrayListBuilder
                                                    .initialize()
                                                    .addElement(
                                                            new Pair<>(
                                                                    0.4,
                                                                    MeleeWeaponDefinitions.BEAR_BITE
                                                            )
                                                    )
                                                    .build()
                                            ),
                                    ArmorDefinitions.BEAR_HIDE
                            ),
                            new Reward(72),
                            AIDefinitions.CCW_AI
                    );
    public static final ActorTemplate ANCIENT_WEB_CRAWLER_TEMPLATE =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("ancient web crawler")
                    .setDescription("a sinister looking arachnid of unbelievable size")
                    .setSymbols('s')
                    .setForegroundColors(
                            new Color[]{
                                    Chroma.BROWN,
                                    Chroma.ICHOR_BLUE,
                                    Chroma.ELEMENTAL_ACID,
                                    Chroma.RED,
                                    ColorStandards.HUNTING_SPIDER_HOURGLASS
                            }
                    )
                    .manufactureActorTemplate(
                            21,
                            new Combatant(
                                    650,
                                    new int[]{12, 8, 24, 8, 18},
                                    FormDefinitions.LARGE_BEAST,
                                    new Continuum<MeleeWeapon>(
                                            MeleeWeaponDefinitions.SPIDER_BITE,
                                            ArrayListBuilder
                                                    .initialize()
                                                    .addElement(
                                                            new Pair<>(
                                                                    0.33,
                                                                    MeleeWeaponDefinitions.ACID_STING
                                                            )
                                                    )
                                                    .build()
                                    ),
                                    ArmorDefinitions.ANCIENT_ARACHNID_EXOSKELETON
                            ),
                            new Reward(256),
                            AIDefinitions.CCS_AI
                    );
    public static final ActorTemplate GIANT_WATER_MOCCASIN =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("giant water moccasin")
                    .setSymbols('S')
                    .setForegroundColors(
                            new Color[]{
                                    Chroma.BROWN,
                                    Chroma.BROWN,
                                    Chroma.BROWN,
                                    Chroma.BROWN,
                                    ColorStandards.FOREST_STREAM,
                                    ColorStandards.FOREST_STREAM,
                                    Chroma.VENOM_GREEN
                            }
                    )
                    .manufactureActorTemplate(
                            30,
                            new Combatant(
                                    325,
                                    new int[]{21, 20, 8, 15, 12},
                                    FormDefinitions.STRIKING_SERPENT,
                                    MeleeWeaponDefinitions.MOCCASIN_FANG,
                                    ArmorDefinitions.SNAKESKIN_SCALE_ARMOR
                            ),
                            new Reward(196),
                            AIDefinitions.CCW_AI
                    );
    public static final ActorTemplate SILK_MUMMY_TEMPLATE =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("silken mummy")
                    .setDescription("a shambling humanoid figure, wrapped in heavy layers of what appears to be spider silk")
                    .setSymbols('M')
                    .setForegroundColors(
                            new Color[]{
                                    Chroma.WHITE,
                                    Chroma.WHITE,
                                    Chroma.WHITE,
                                    Chroma.WHITE,
                                    Chroma.WHITE,
                                    Chroma.GREY,
                                    Chroma.GREY,
                                    Chroma.BROWN,
                                    Chroma.BROWN,
                                    Chroma.FLESH
                            }
                    )
                    .manufactureActorTemplate(
                            19,
                            new Combatant(
                                    2_048,
                                    new int[]{32, 12, 16, 32, 28},
                                    FormDefinitions.SHAMBLER,
                                    new Continuum<MeleeWeapon>(
                                            MeleeWeaponDefinitions.BARE_HANDED,
                                            ArrayListBuilder
                                                    .initialize()
                                                    .addElement(
                                                            new Pair<>(
                                                                    0.25,
                                                                    MeleeWeaponDefinitions.WEB_STRIKE
                                                            )
                                                    )
                                                    .build()
                                    ),
                                    ArmorDefinitions.SPIDERSILK_COCOON
                            ),
                            new Reward(1_024),
                            AIDefinitions.CCS_AI
                    );
}
