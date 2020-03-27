package world;

import main.extensible.GlyphDisplayable;
import resources.glyph.BalancedGlyphTemplate;

/**
 * The base class for all WorldObjectTemplates. Templates for objects which extend WorldObject must extend this class.
 */
public abstract class WorldObjectTemplate extends GlyphDisplayable {
    private final boolean reflectsLight;

    public WorldObjectTemplate(
            String d,
            String n,
            BalancedGlyphTemplate bgt,
            boolean r
    ) {
        super(d, n, bgt);
        reflectsLight = r;
    }


    public boolean reflectsLight() {
        return reflectsLight;
    }

    @Override
    public abstract WorldObjectTemplate clone();
}
