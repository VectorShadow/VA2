package world.actor;

import world.WorldObjectTemplateFactory;

/**
 * Contains the definitions for each available ActorTemplate, as well as themed sets of ActorTemplates.
 */
public class ActorDefinitions {
    public static final ActorTemplate PLAYER_TEMPLATE =
            WorldObjectTemplateFactory.initialize().manufactureActorTemplate(25);
}
