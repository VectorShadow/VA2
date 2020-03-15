package world.actor;

import ai.AIDefinitions;
import combat.Combatant;
import combat.melee.forms.FormDefinitions;
import combat.melee.weapons.WieldedMeleeWeapon;
import combat.melee.weapons.WeaponDefinitions;
import main.progression.Reward;
import resources.chroma.Chroma;
import resources.continuum.Continuum;
import resources.continuum.Pair;
import util.ArrayListBuilder;
import world.ColorStandards;
import world.WorldObjectTemplateFactory;

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
                            new Combatant(
                                    1_024,
                                    128,
                                    128,
                                    128,
                                    128,
                                    128,
                                    FormDefinitions.UNTRAINED,
                                    WeaponDefinitions.BARE_HANDED
                            ),
                            new Reward(0, 0),
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
                                    16,
                                    196,
                                    196,
                                    8,
                                    16,
                                    8,
                                    FormDefinitions.SMALL_BEAST,
                                    WeaponDefinitions.SPIDER_BITE
                            ),
                            new Reward(1, 2),
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
                                    24,
                                    224,
                                    144,
                                    16,
                                    32,
                                    12,
                                    FormDefinitions.SMALL_BEAST,
                                    WeaponDefinitions.SPIDER_BITE
                            ),
                            new Reward(1, 6),
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
                                    256,
                                    128,
                                    32,
                                    32,
                                    16,
                                    FormDefinitions.SMALL_BEAST,
                                    WeaponDefinitions.SPIDER_BITE
                            ),
                            new Reward(1, 16),
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
                                    256,
                                    84,
                                    155,
                                    128,
                                    72,
                                    64,
                                    FormDefinitions.SMALL_BEAST,
                                    WeaponDefinitions.WOLF_BITE
                            ),
                            new Reward(2, 12),
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
                                    300,
                                    96,
                                    128,
                                    155,
                                    84,
                                    72,
                                    FormDefinitions.SMALL_BEAST,
                                    WeaponDefinitions.WOLF_BITE
                            ),
                            new Reward(2, 20),
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
                            new Combatant(
                                    512,
                                    112,
                                    48,
                                    112,
                                    96,
                                    192,
                                    FormDefinitions.LARGE_BEAST,
                                    WeaponDefinitions.BEAR_BITE
                            ),
                            new Reward(3, 24),
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
                                    768,
                                    116,
                                    40,
                                    128,
                                    72,
                                    256,
                                    FormDefinitions.LARGE_BEAST,
                                    new Continuum<WieldedMeleeWeapon>(
                                            WeaponDefinitions.BEAR_SWAT,
                                            ArrayListBuilder
                                                    .initialize()
                                                    .addElement(
                                                            new Pair<WieldedMeleeWeapon>(
                                                                    0.4,
                                                                    WeaponDefinitions.BEAR_BITE
                                                            )
                                                    )
                                                    .build()
                                            )
                            ),
                            new Reward(5, 32),
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
                            new Combatant(
                                    2_048,
                                    144,
                                    96,
                                    256,
                                    96,
                                    256,
                                    FormDefinitions.LARGE_BEAST,
                                    new Continuum<WieldedMeleeWeapon>(
                                            WeaponDefinitions.SPIDER_BITE,
                                            ArrayListBuilder
                                                    .initialize()
                                                    .addElement(
                                                            new Pair<WieldedMeleeWeapon>(
                                                                    0.33,
                                                                    WeaponDefinitions.ACID_STING
                                                            )
                                                    )
                                                    .build()
                                    )
                            ),
                            new Reward(8, 256),
                            AIDefinitions.CCS_AI
                    );
}
