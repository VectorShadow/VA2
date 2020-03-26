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
                            128,
                            new int[]{0, 0, 0, 0, 0},
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
                            8,
                            new int[] {4, 4, 1, 1, 1},
                            new Combatant(
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
                            16,
                            new int[]{3, 6, 1, 1, 2},
                            new Combatant(
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
                            48,
                            new int[] {8, 6, 2, 2, 5},
                            new Combatant(
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
                            128,
                            new int[]{10, 7, 3, 3, 8},
                            new Combatant(
                                    FormDefinitions.SMALL_BEAST,
                                    MeleeWeaponDefinitions.WOLF_BITE,
                                    ArmorDefinitions.UNARMORED
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
                            196,
                            new int[]{11, 6, 4, 3, 9},
                            new Combatant(
                                    FormDefinitions.SMALL_BEAST,
                                    MeleeWeaponDefinitions.WOLF_BITE,
                                    ArmorDefinitions.UNARMORED
                            ),
                            new Reward(32),
                            AIDefinitions.CCW_AI
                    );
    public static final ActorTemplate BLACK_BEAR_TEMPLATE =
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("black bear")
                    .setSymbols('b')
                    .setForegroundColors(ColorStandards.BLACK_BEAR_FUR)
                    .manufactureActorTemplate(
                            24,
                            225,
                            new int[] {9, 5, 10, 8, 12},
                            new Combatant(
                                    FormDefinitions.LARGE_BEAST,
                                    MeleeWeaponDefinitions.BEAR_BITE,
                                    ArmorDefinitions.UNARMORED
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
                            333,
                            new int[]{10, 4, 10, 10, 16},
                            new Combatant(
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
                                    ArmorDefinitions.UNARMORED
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
                            19,
                            512,
                            new int[]{12, 8, 12, 9, 18},
                            new Combatant(
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
                            300,
                            new int[]{16, 12, 16, 10, 10},
                            new Combatant(
                                    FormDefinitions.STRIKING_SERPENT,
                                    new Continuum<>(
                                            MeleeWeaponDefinitions.MOCCASIN_FANG
                                    ),
                                    ArmorDefinitions.SNAKESKIN_SCALE_ARMOR
                            ),
                            new Reward(196),
                            AIDefinitions.CCW_AI
                    );
}
