package world;

import io.out.DisplayStandards;
import io.out.GlyphSource;
import resources.DualityMode;
import resources.chroma.Chroma;
import resources.continuum.Pair;
import resources.glyph.Glyph;
import resources.glyph.GlyphBuilder;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The base class for all WorldObjectTemplates. Templates for objects which extend WorldObject must extend this class.
 */
public abstract class WorldObjectTemplate implements GlyphSource, Serializable {
    private final String name;
    private final String description;
    private final boolean reflectLight;
    private final ArrayList<Character> symbols;
    private final ArrayList<Color> backgroundColors;
    private final ArrayList<Color> foregroundColors;

    public WorldObjectTemplate(String n, String desc, boolean reflect, ArrayList<Character> s, ArrayList<Color> b, ArrayList<Color> f) {
        name = n;
        description = desc;
        reflectLight = reflect;
        symbols = s;
        backgroundColors = b;
        foregroundColors = f;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean reflectsLight() {
        return reflectLight;
    }

    @Override
    public Character getBaseSymbol() {
        return symbols.get(0);
    }

    @Override
    public ArrayList<Character> listAdditionalSymbols() {
        return new ArrayList<>(symbols.subList(1, symbols.size()));
    }

    @Override
    public Color getBaseBackgroundColor() {
        return backgroundColors.get(0);
    }

    @Override
    public ArrayList<Color> listAdditionalBackgroundColors() {
        return new ArrayList<>(backgroundColors.subList(1, backgroundColors.size()));
    }

    @Override
    public Color getBaseForegroundColor() {
        return foregroundColors.get(0);
    }

    @Override
    public ArrayList<Color> listAdditionalForegroundColors() {
        return new ArrayList<>(foregroundColors.subList(1, foregroundColors.size()));
    }

    @Override
    public Glyph memoryImage() {
        return GlyphBuilder
                .buildGlyph()
                .setDefaults(DisplayStandards.GAME_DEFAULT_BACKGROUND, Chroma.dim(getBaseForegroundColor()), getBaseSymbol())
                .build(DualityMode.TILE);
    }
    @Override
    public GlyphBuilder partialVisualImage() {
        GlyphBuilder gb =
                GlyphBuilder
                        .buildGlyph()
                        .setDefaults(
                                getBaseBackgroundColor(),
                                getBaseForegroundColor(),
                                getBaseSymbol()
                        );
        int count = backgroundColors.size();
        double pctEach = 1.0 / (double)count;
        double pctNext = pctEach;
        for (Color b : listAdditionalBackgroundColors()) {
            gb.addBackgroundColor(new Pair<>(pctNext, b));
            pctNext += pctEach;
        }
        count = foregroundColors.size();
        pctEach = 1.0 / (double)count;
        pctNext = pctEach;
        for (Color f : listAdditionalForegroundColors()) {
            gb.addForegroundColor(new Pair<>(pctNext, f));
            pctNext += pctEach;
        }
        count = symbols.size();
        pctEach = 1.0 / (double)count;
        pctNext = pctEach;
        for (char s : listAdditionalSymbols()) {
            gb.addSymbol(new Pair<>(pctNext, s));
            pctNext += pctEach;
        }
        return gb;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof WorldObjectTemplate && ((WorldObjectTemplate) o).description.equals(description);
    }
}
