package main.extensible;

import io.out.DisplayStandards;
import resources.DualityMode;
import resources.chroma.Chroma;
import resources.continuum.Pair;
import resources.glyph.Glyph;
import resources.glyph.GlyphBuilder;

import java.awt.*;
import java.util.ArrayList;

public abstract class GlyphDisplayable extends TextDisplayable {

    private final ArrayList<Character> symbols;
    private final ArrayList<Color> backgroundColors;
    private final ArrayList<Color> foregroundColors;

    public GlyphDisplayable(
            String d,
            String n,
            Color nc,
            ArrayList<Character> s,
            ArrayList<Color> b,
            ArrayList<Color> f
    ) {
        super(d, n, nc);
        symbols = s;
        backgroundColors = b;
        foregroundColors = f;
    }


    public Character getBaseSymbol() {
        return symbols.get(0);
    }

    public ArrayList<Character> listAdditionalSymbols() {
        return new ArrayList<>(symbols.subList(1, symbols.size()));
    }

    public Color getBaseBackgroundColor() {
        return backgroundColors.get(0);
    }

    public ArrayList<Color> listAdditionalBackgroundColors() {
        return new ArrayList<>(backgroundColors.subList(1, backgroundColors.size()));
    }

    public Color getBaseForegroundColor() {
        return foregroundColors.get(0);
    }

    public ArrayList<Color> listAdditionalForegroundColors() {
        return new ArrayList<>(foregroundColors.subList(1, foregroundColors.size()));
    }

    public Glyph memoryImage() {
        return GlyphBuilder
                .buildGlyph()
                .setDefaults(DisplayStandards.GAME_DEFAULT_BACKGROUND, Chroma.dim(getBaseForegroundColor()), getBaseSymbol())
                .build(DualityMode.TILE);
    }
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
}
