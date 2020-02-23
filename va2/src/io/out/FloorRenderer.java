package io.out;

import floor.Floor;
import main.Session;
import resources.glyph.Glyph;
import resources.glyph.GlyphMap;
import resources.glyph.ascii.SimpleGlyph;

import java.awt.*;

public class FloorRenderer {
    private GlyphMap glyphMap;

    public FloorRenderer() {
        initialize();
    }

    private void initialize() {
        Dimension d = Session.getGuiManager().getScreenDimension();
        glyphMap = new GlyphMap(d.height, d.width);
    }

    private void clear() {
        for (int i = 0; i < glyphMap.getRows(); ++i) {
            for (int j = 0; j < glyphMap.getCols(); ++j) {
                glyphMap.setGlyph(i, j, SimpleGlyph.EMPTY_GLYPH);
            }
        }
    }
    public void update(Floor f) {
        //todo - access the camera position and calculate row and column offsets such that the camera location
        // corresponds to the center of the glyphMap.
        //todo - each glyph being initialized as the memory image of the terrain at that map coordinate if it has ever
        // been seen, or the empty glyph if not, or if the coordinate is not a part of the floor. Then we will generate
        // a list of coordinates corresponding to the player's vision, using the method from ACE, and lists of
        // coordinates corresponding to tiles brightly lit and dimly lit by the player's light source. Finally, we'll
        // iterate through every map coordinate in the list of visible tiles, marking the map as seen for each, then
        // determining the brightest light on the tile, evaluating the player's bright light if the tile is in that
        // list, or the player's dim light if in that list, against the ambient light of the map, or simply using the
        // ambient light of the map if neither list contains the tile.
        //todo - Now, if there is an actor here, we'll apply the light to the actor image to generate the foreground.
        // Then we'll check the temporary effect, applying light if necessary, and using it's foreground image if
        // applicable, and its background image regardless. Finally, if no foreground image is set, we'll apply light
        // to the terrain's image and use that for the foreground. We'll generate a glyph from this information and
        // write it to the glyph array at the appropriate location.
    }

    public int countRows() {
        return glyphMap.getRows();
    }
    public int countColumns() {
        return glyphMap.getCols();
    }
    public Glyph getGlyphAt(int row, int col) {
        return glyphMap.getGlyph(row, col);
    }
}
