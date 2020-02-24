package world;

import io.out.GlyphSource;

import java.awt.*;
import java.util.ArrayList;

public abstract class WorldObjectTemplate implements GlyphSource {
    private final ArrayList<Character> symbols;
    private final ArrayList<Color> backgroundColors;
    private final ArrayList<Color> foregroundColors;

    public WorldObjectTemplate(ArrayList<Character> s, ArrayList<Color> b, ArrayList<Color> f) {
        symbols = s;
        backgroundColors = b;
        foregroundColors = f;
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
}