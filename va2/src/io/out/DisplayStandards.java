package io.out;

import resources.DualityMode;
import resources.chroma.Chroma;
import resources.glyph.Glyph;
import resources.glyph.GlyphBuilder;

import java.awt.*;

public class DisplayStandards {
    public static final Glyph MESSAGE_WINDOW_BORDER =
            GlyphBuilder
                    .buildGlyph()
                    .setDefaults(Chroma.TURQUOISE, Chroma.BLACK, ' ')
                    .build(DualityMode.TEXT);

    public static final Color MESSAGE_DEFAULT_BACKGROUND = Chroma.BLACK;
    public static final Color MESSAGE_DEFAULT_FOREGROUND = Chroma.WHITE;
}
