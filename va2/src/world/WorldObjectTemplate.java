package world;

import io.out.GlyphSource;
import resources.DualityMode;
import resources.chroma.Chroma;
import resources.glyph.Glyph;
import resources.glyph.GlyphBuilder;

import java.awt.*;
import java.util.ArrayList;

/**
 * The base class for all WorldObjectTemplates. Templates for objects which extend WorldObject must extend this class.
 */
public abstract class WorldObjectTemplate implements GlyphSource {
    private final boolean reflectLight;
    private final ArrayList<Character> symbols;
    private final ArrayList<Color> backgroundColors;
    private final ArrayList<Color> foregroundColors;

    public WorldObjectTemplate(boolean reflect, ArrayList<Character> s, ArrayList<Color> b, ArrayList<Color> f) {
        reflectLight = reflect;
        symbols = s;
        backgroundColors = b;
        foregroundColors = f;
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
                .setDefaults(Color.DARK_GRAY, Chroma.dark(getBaseForegroundColor()), getBaseSymbol())
                .build(DualityMode.TILE);
    }
}
