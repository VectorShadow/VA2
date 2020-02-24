package io.out;

import resources.glyph.Glyph;

import java.awt.*;
import java.util.ArrayList;

/**
 * A set of methods for informing the construction of a glyph from an object which needs to be drawn to the screen.
 */
public interface GlyphSource {
    Character getBaseSymbol();
    ArrayList<Character> listAdditionalSymbols();
    Color getBaseBackgroundColor();
    ArrayList<Color> listAdditionalBackgroundColors();
    Color getBaseForegroundColor();
    ArrayList<Color> listAdditionalForegroundColors();
    Glyph memoryImage();
}
