package io.out;

import main.Session;
import resources.DualityMode;
import resources.chroma.Chroma;
import resources.chroma.ChromaSet;
import resources.glyph.Glyph;
import resources.glyph.GlyphBuilder;
import world.ColorStandards;

import java.awt.*;

public class DisplayStandards {
    public static final Glyph getMessageWindowBorder() {
        ChromaSet cs = Session.getColorScheme();
        return GlyphBuilder
                .buildGlyph()
                .setDefaults(cs.getForeground(), cs.getBackground(), ' ')
                .build(DualityMode.SHORT_TEXT);
    }

    public static final Color GAME_DEFAULT_BACKGROUND = Chroma.BLACK;
    public static final Color MESSAGE_DEFAULT_BACKGROUND = GAME_DEFAULT_BACKGROUND;
    public static final Color MESSAGE_DEFAULT_FOREGROUND = Chroma.WHITE;

    public static final ChromaSet THEME_THE_DARK_GROVE = new ChromaSet(ColorStandards.WOODED_TWILIGHT, ColorStandards.ANCIENT_OAK, ColorStandards.FOREST_STREAM);
}
