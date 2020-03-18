package world.actor;

import ai.AIDefinitions;
import combat.Combatant;
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
                            1_024,
                            new int[]{30, 30, 30, 30, 30},
                            new Combatant(
                                    FormDefinitions.UNTRAINED,
                                    MeleeWeaponDefinitions.BARE_HANDED,
                                    ArmorDefinitions.UNARMORED
                            ),
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
                            new int[] {36, 36, 0, 0, 0},
                            new Combatant(
                                    FormDefinitions.SMALL_BEAST,
                                    MeleeWeaponDefinitions.SPIDER_BITE,
                                    ArmorDefinitions.UNARMORED
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
                            new int[]{39, 32, 0, 10, 2},
                            new Combatant(
                                    FormDefinitions.SMALL_BEAST,
                                    MeleeWeaponDefinitions.SPIDER_BITE,
                                    ArmorDefinitions.UNARMORED
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
                            new int[] {40, 30, 10, 10, 5},
                            new Combatant(
                                    FormDefinitions.SMALL_BEAST,
                                    MeleeWeaponDefinitions.SPIDER_BITE,
                                    ArmorDefinitions.UNARMORED
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
                            256,
                            new int[]{24, 33, 30, 23, 20},
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
                            333,
                            new int[]{26, 30, 33, 23, 22},
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
                            484,
                            new int[] {28, 16, 28, 25, 37},
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
                            716,
                            new int[]{28, 13, 30, 22, 40},
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
                            2255,
                            new int[]{32, 26, 40, 25, 40},
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
                                    ArmorDefinitions.UNARMORED
                            ),
                            new Reward(256),
                            AIDefinitions.CCS_AI
                    );
}
