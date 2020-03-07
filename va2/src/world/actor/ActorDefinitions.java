package world.actor;

import combat.Combatant;
import resources.chroma.Chroma;
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
                                    1_000,
                                    128,
                                    128,
                                    128,
                                    128,
                                    128
                            )
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
                                    64,
                                    256,
                                    128,
                                    32,
                                    32,
                                    16
                            )
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
                                    64
                            )
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
                                    72
                            )
                    );
}
