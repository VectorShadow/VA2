package main.extensible;

import main.Session;
import resources.glyph.GlyphString;

import java.awt.*;

public abstract class TextDisplayable extends Saveable {
    protected final String description;
    protected final String name;
    protected final Color nameColor;

    public TextDisplayable(String d, String n, Color nc) {
        description = d;
        name = n;
        nameColor = nc;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
    public GlyphString getColoredName() {
        return new GlyphString(name, Session.getColorScheme().getBackground(), nameColor);
    }
    @Override
    public boolean equals(Object o) {
        return o instanceof TextDisplayable && ((TextDisplayable) o).description.equals(description);
    }
}
