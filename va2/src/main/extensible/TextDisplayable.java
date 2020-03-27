package main.extensible;

import main.Session;
import resources.continuum.Continuum;
import resources.glyph.GlyphString;

import java.awt.*;
import java.util.ArrayList;

public abstract class TextDisplayable extends Saveable {
    protected final String DESCRIPTION;
    protected final String NAME;
    protected final Continuum<Color> DISPLAY_COLOR;

    public TextDisplayable(String d, String n, Color dc) {
        this(d, n, new Continuum<>(dc));
    }
    public TextDisplayable(String d, String n, Continuum<Color> dc) {
        DESCRIPTION = d;
        NAME = n;
        DISPLAY_COLOR = dc;
    }

    public String getDescription() {
        return DESCRIPTION;
    }

    public String getName() {
        return NAME;
    }
    public GlyphString getColoredName() {
        return new GlyphString(NAME, Session.getColorScheme().getBackground(), DISPLAY_COLOR.getBase(), new ArrayList<>(), DISPLAY_COLOR.getPairList());
    }
    @Override
    public boolean equals(Object o) {
        return o instanceof TextDisplayable && ((TextDisplayable) o).DESCRIPTION.equals(DESCRIPTION);
    }
}
