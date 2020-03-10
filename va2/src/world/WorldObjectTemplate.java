package world;

import main.extensible.GlyphDisplayable;
import resources.glyph.BalancedGlyphTemplate;

import java.awt.*;

/**
 * The base class for all WorldObjectTemplates. Templates for objects which extend WorldObject must extend this class.
 */
public abstract class WorldObjectTemplate extends GlyphDisplayable {
    private final boolean reflectsLight;

    public WorldObjectTemplate(
            String d,
            String n,
            Color nc,
            BalancedGlyphTemplate bgt,
            boolean r
    ) {
        super(d, n, nc, bgt);
        reflectsLight = r;
    }


    public boolean reflectsLight() {
        return reflectsLight;
    }
}
