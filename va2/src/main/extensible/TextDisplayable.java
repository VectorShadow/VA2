package main.extensible;

import main.Session;
import resources.continuum.Continuum;
import resources.glyph.GlyphString;

import java.awt.*;
import java.util.ArrayList;

public abstract class TextDisplayable extends Saveable {
    protected final String description;
    protected final String name;
    protected final Continuum<Color> dislayColor;

    public TextDisplayable(String d, String n, Color dc) {
        this(d, n, new Continuum<>(dc));
    }
    public TextDisplayable(String d, String n, Continuum<Color> dc) {
        description = d;
        name = n;
        dislayColor = dc;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
    public GlyphString getColoredName() {
        return new GlyphString(name, Session.getColorScheme().getBackground(), dislayColor.getBase(), new ArrayList<>(), dislayColor.getPairList());
    }
    @Override
    public boolean equals(Object o) {
        return o instanceof TextDisplayable && ((TextDisplayable) o).description.equals(description);
    }
}
