package world;

import java.io.Serializable;

public abstract class WorldObject implements Serializable {
    private final WorldObjectTemplate TEMPLATE;

    public WorldObject(WorldObjectTemplate wot) {
        TEMPLATE = wot.clone();
    }

    public WorldObjectTemplate getTemplate() {
        return TEMPLATE;
    }
}
