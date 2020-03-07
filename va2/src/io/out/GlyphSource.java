package io.out;

import resources.glyph.Glyph;

import java.awt.*;
import java.util.ArrayList;

/**
 * A set of methods for informing the construction of a glyph from an object which needs to be drawn to the screen.
 */

//todo - make this actually return a glyph, and use continua to do so. Requires overhaul of all implementing classes.
public interface GlyphSource {
    Character getBaseSymbol();
    ArrayList<Character> listAdditionalSymbols();
    Color getBaseBackgroundColor();
    ArrayList<Color> listAdditionalBackgroundColors();
    Color getBaseForegroundColor();
    ArrayList<Color> listAdditionalForegroundColors();
    Glyph memoryImage();
}
