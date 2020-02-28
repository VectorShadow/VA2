package world.actor;

import world.WorldObjectTemplateFactory;

/**
 * Contains the definitions for each available ActorTemplate.
 */
public class ActorDefinitions {
    public static final ActorTemplate PLAYER_TEMPLATE =
            WorldObjectTemplateFactory.initialize().setSymbols('@').manufactureActorTemplate(25);
}
