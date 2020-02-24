package world.actor;

import world.WorldObject;
import world.WorldObjectTemplate;

/**
 * Contains all the information to describe a specific actor within a game level map.
 */
public class Actor extends WorldObject {
    public Actor(WorldObjectTemplate t/* todo - replace with ActorTemplate*/) {
        super(t);
    }
}
