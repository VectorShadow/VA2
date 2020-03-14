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

    private final BalancedGlyphTemplate balancedGlyphTemplate;

    public GlyphDisplayable(
            String d,
            String n,
            BalancedGlyphTemplate bgt
    ) {
        super(d, n, new Continuum<>(bgt.getForegroundColors()));
        balancedGlyphTemplate = bgt;
    }

    public Glyph memoryImage() {
        return GlyphBuilder
                .buildGlyph()
                .setDefaults(
                        DisplayStandards.GAME_DEFAULT_BACKGROUND,
                        Chroma.dim(balancedGlyphTemplate.getBaseForegroundColor()),
                        balancedGlyphTemplate.getBaseSymbol()
                )
                .build(DualityMode.TILE);
    }
    public GlyphBuilder partialVisualImage() {
        return balancedGlyphTemplate.partialBalancedGlyph();
    }
}
