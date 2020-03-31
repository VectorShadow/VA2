package main.extensible;

import io.out.DisplayStandards;
import resources.DualityMode;
import resources.chroma.Chroma;
import resources.continuum.Continuum;
import resources.glyph.BalancedGlyphTemplate;
import resources.glyph.Glyph;
import resources.glyph.GlyphBuilder;

import java.awt.*;

public abstract class GlyphDisplayable extends TextDisplayable {

    protected final BalancedGlyphTemplate BALANCED_GLYPH_TEMPLATE;

    public GlyphDisplayable(
            String d,
            String n,
            BalancedGlyphTemplate bgt
    ) {
        super(d, n, new Continuum<>(bgt.getForegroundColors()));
        BALANCED_GLYPH_TEMPLATE = bgt;
    }

    public Glyph memoryImage() {
        return GlyphBuilder
                .buildGlyph()
                .setDefaults(
                        DisplayStandards.GAME_DEFAULT_BACKGROUND,
                        Chroma.dim(BALANCED_GLYPH_TEMPLATE.getBaseForegroundColor()),
                        BALANCED_GLYPH_TEMPLATE.getBaseSymbol()
                )
                .setImageRowAndColumn(BALANCED_GLYPH_TEMPLATE.getImageRow(), BALANCED_GLYPH_TEMPLATE.getImageCol())
                .build(DualityMode.TILE);
    }
    public GlyphBuilder partialVisualImage() {
        return BALANCED_GLYPH_TEMPLATE.partialBalancedGlyph();
    }
}
