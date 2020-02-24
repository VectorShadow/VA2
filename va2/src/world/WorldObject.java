package world;

public abstract class WorldObject {
    private final WorldObjectTemplate template;

    public WorldObject(WorldObjectTemplate wot) {
        template = wot;
    }

    public WorldObjectTemplate getTemplate() {
        return template;
    }
}
