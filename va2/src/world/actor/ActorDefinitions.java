package world.actor;

import combat.Combatant;
import resources.chroma.Chroma;
import world.WorldObjectTemplateFactory;

/**
 * Contains the definitions for each available ActorTemplate.
 */
public class ActorDefinitions {
    public static final ActorTemplate PLAYER_TEMPLATE =
            WorldObjectTemplateFactory
                    .initialize()
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
    public static final ActorTemplate FOREST_WOLF_TEMPLATE =
            WorldObjectTemplateFactory
                    .initialize()
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
}
