package world;

import java.io.Serializable;

public abstract class WorldObject implements Serializable {
    private final WorldObjectTemplate template;

    public WorldObject(WorldObjectTemplate wot) {
        template = wot;
    }

    public WorldObjectTemplate getTemplate() {
        return template;
    }
}
