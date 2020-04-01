package world.actor;

import ai.AIDefinitions;
import combat.Combatant;
import combat.PlayerCombatant;
import world.item.define.ArmorDefinitions;
import combat.melee.forms.FormDefinitions;
import world.item.define.MeleeWeaponDefinitions;
import main.progression.rewards.Reward;
import resources.chroma.Chroma;
import resources.continuum.Continuum;
import resources.continuum.Pair;
import util.ArrayListBuilder;
import world.ColorStandards;
import world.WorldObjectTemplateFactory;
import world.item.MeleeWeapon;
import world.light.Light;

import java.awt.*;
import static io.out.GUIManager.*;

/**
 * Contains the definitions for each available ActorTemplate.
 */
public class ActorDefinitions {
    public static final ActorTemplate PLAYER_TEMPLATE =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("Ysian adventurer")
                    .setSymbols('@')
                    .setForegroundColors(Chroma.FLESH)
                    .setSecondaryColors(Light.TORCH.getColor()) //todo - update light and armor colors on player glyph!
                    .setTertiaryColors(ArmorDefinitions.LEATHER_VEST().getTemplate().memoryImage().getFaceColor())
                    .setImage(GFX_ROW_ACTOR, 1)
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
                    .setSecondaryColors(Chroma.RED)
                    .setImage(GFX_ROW_ACTOR, 2)
                    .manufactureActorTemplate(
                            38,
                            new Combatant(
                                    8,
                                    -1,
                                    -1,
                                    new int[] {4, 4, 1, 1, 1},
                                    FormDefinitions.SMALL_BEAST,
                                    MeleeWeaponDefinitions.SPIDER_BITE(),
                                    ArmorDefinitions.ARACHNID_EXOSKELETON()
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
                    .setSecondaryColors(Chroma.GREEN)
                    .setImage(GFX_ROW_ACTOR, 2)
                    .manufactureActorTemplate(
                            37,
                            new Combatant(
                                    16,
                                    -1,
                                    -1,
                                    new int[]{6, 6, 1, 1, 2},
                                    FormDefinitions.SMALL_BEAST,
                                    MeleeWeaponDefinitions.SPIDER_BITE(),
                                    ArmorDefinitions.ARACHNID_EXOSKELETON()
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
                    .setSecondaryColors(
                            new Color[] {
                                    Color.GREEN,
                                    Color.RED
                            }
                    )
                    .setImage(GFX_ROW_ACTOR, 2)
                    .manufactureActorTemplate(
                            36,
                            new Combatant(
                                    48,
                                    -1,
                                    -1,
                                    new int[] {8, 6, 2, 2, 5},
                                    FormDefinitions.SMALL_BEAST,
                                    MeleeWeaponDefinitions.SPIDER_BITE(),
                                    ArmorDefinitions.ARACHNID_EXOSKELETON()
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
                    .setSecondaryColors(Chroma.BLACK)
                    .setImage(GFX_ROW_ACTOR, 3)
                    .manufactureActorTemplate(
                            32,
                            new Combatant(
                                    128,
                                    32,
                                    16,
                                    new int[]{16, 7, 8, 3, 10},
                                    FormDefinitions.SMALL_BEAST,
                                    MeleeWeaponDefinitions.WOLF_BITE(),
                                    ArmorDefinitions.WOLF_HIDE()
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
                    .setSecondaryColors(Chroma.TURQUOISE)
                    .setImage(GFX_ROW_ACTOR, 3)
                    .manufactureActorTemplate(
                            30,
                            new Combatant(
                                    196,
                                    32,
                                    16,
                                    new int[]{18, 6, 10, 5, 12},
                                    FormDefinitions.SMALL_BEAST,
                                    MeleeWeaponDefinitions.WOLF_BITE(),
                                    ArmorDefinitions.WOLF_HIDE()
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
                    .setSecondaryColors(Chroma.GREEN)
                    .setImage(GFX_ROW_ACTOR, 3)
                    .manufactureActorTemplate(
                            33,
                            new Combatant(
                                    116,
                                    32,
                                    16,
                                    new int[]{16, 7, 8, 3, 10},
                                    FormDefinitions.SMALL_BEAST,
                                    MeleeWeaponDefinitions.RABID_WOLF_BITE(),
                                    ArmorDefinitions.WOLF_HIDE()
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
                    .setSecondaryColors(Chroma.RED)
                    .setImage(GFX_ROW_ACTOR, 4)
                    .manufactureActorTemplate(
                            24,
                            new Combatant(
                                    400,
                                    32,
                                    16,
                                    new int[] {9, 5, 10, 16, 12},
                                    FormDefinitions.LARGE_BEAST,
                                    MeleeWeaponDefinitions.BEAR_BITE(),
                                    ArmorDefinitions.BEAR_HIDE()
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
                    .setSecondaryColors(Chroma.RED)
                    .setImage(GFX_ROW_ACTOR, 4)
                    .manufactureActorTemplate(
                            24,
                            new Combatant(
                                    555,
                                    32,
                                    16,
                                    new int[]{10, 4, 10, 20, 20},
                                    FormDefinitions.LARGE_BEAST,
                                    new Continuum<MeleeWeapon>(
                                            MeleeWeaponDefinitions.BEAR_SWAT(),
                                            ArrayListBuilder
                                                    .initialize()
                                                    .addElement(
                                                            new Pair<>(
                                                                    0.4,
                                                                    MeleeWeaponDefinitions.BEAR_BITE()
                                                            )
                                                    )
                                                    .build()
                                            ),
                                    ArmorDefinitions.BEAR_HIDE()
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
                    .setSecondaryColors(Chroma.SLIME)
                    .setTertiaryColors(Chroma.WHITE)
                    .setImage(GFX_ROW_ACTOR, 5)
                    .manufactureActorTemplate(
                            21,
                            new Combatant(
                                    650,
                                    425,
                                    128,
                                    new int[]{12, 8, 24, 8, 18},
                                    FormDefinitions.LARGE_BEAST,
                                    new Continuum<MeleeWeapon>(
                                            MeleeWeaponDefinitions.SPIDER_BITE(),
                                            ArrayListBuilder
                                                    .initialize()
                                                    .addElement(
                                                            new Pair<>(
                                                                    0.05,
                                                                    MeleeWeaponDefinitions.WEB_STRIKE()
                                                            )
                                                    )
                                                    .addElement(
                                                            new Pair<>(
                                                                    0.25,
                                                                    MeleeWeaponDefinitions.ACID_STING()
                                                            )
                                                    )
                                                    .build()
                                    ),
                                    ArmorDefinitions.ANCIENT_ARACHNID_EXOSKELETON()
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
                    .setSecondaryColors(Chroma.CRIMSON)
                    .setImage(GFX_ROW_ACTOR, 6)
                    .manufactureActorTemplate(
                            30,
                            new Combatant(
                                    325,
                                    24,
                                    12,
                                    new int[]{21, 20, 8, 15, 12},
                                    FormDefinitions.STRIKING_SERPENT,
                                    MeleeWeaponDefinitions.MOCCASIN_FANG(),
                                    ArmorDefinitions.SNAKESKIN_SCALE_ARMOR()
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
                    .setSecondaryColors(Chroma.ANTIAMBER)
                    .setImage(GFX_ROW_ACTOR, 7)
                    .manufactureActorTemplate(
                            19,
                            new Combatant(
                                    2_048,
                                    -1,
                                    -1,
                                    new int[]{32, 12, 16, 32, 28},
                                    FormDefinitions.SHAMBLER,
                                    new Continuum<MeleeWeapon>(
                                            MeleeWeaponDefinitions.BARE_HANDED(),
                                            ArrayListBuilder
                                                    .initialize()
                                                    .addElement(
                                                            new Pair<>(
                                                                    0.25,
                                                                    MeleeWeaponDefinitions.WEB_STRIKE()
                                                            )
                                                    )
                                                    .build()
                                    ),
                                    ArmorDefinitions.SPIDER_SILK_COCOON()
                            ),
                            new Reward(1_024),
                            AIDefinitions.CCS_AI
                    );
}
