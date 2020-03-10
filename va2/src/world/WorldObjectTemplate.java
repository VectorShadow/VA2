package world;

import main.extensible.GlyphDisplayable;

import java.awt.*;
import java.util.ArrayList;

/**
 * The base class for all WorldObjectTemplates. Templates for objects which extend WorldObject must extend this class.
 */
public abstract class WorldObjectTemplate extends GlyphDisplayable {
    private final boolean reflectsLight;

    public WorldObjectTemplate(
            String d,
            String n,
            Color nc,
            ArrayList<Character> s,
            ArrayList<Color> b,
            ArrayList<Color> f,
            boolean r
    ) {
        super(d, n, nc, s, b, f);

        reflectsLight = r;
    }


    public boolean reflectsLight() {
        return reflectsLight;
    }
}
