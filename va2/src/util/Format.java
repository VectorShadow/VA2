package util;

import main.Session;
import resources.chroma.Chroma;
import resources.continuum.Pair;
import resources.glyph.GlyphString;

import java.awt.*;
import java.util.ArrayList;

public class Format {
    public static String percent(double pct, int precision) {
        double scalingFactor = Math.pow(10.0, precision);
        double value = (double)((int)(pct * scalingFactor)) / scalingFactor;
        return (precision > 0 ? value : ((int)value + "")) + "%";
    }
    public static GlyphString colorCode(String name, double pct) {
        Color background = Session.getColorScheme().getBackground();
        Color foreground = pct > 1.0
                ? Chroma.BLUE
                : pct > .9
                ? Chroma.GREEN
                : pct > .667
                ? Chroma.YELLOW
                : pct > .333
                ? Chroma.ORANGE
                : Chroma.RED;
        return new GlyphString(
                name,
                background,
                foreground,
                new ArrayList<>(),
                pct > 0.1
                ? new ArrayList<>()
                : ArrayListBuilder
                        .initialize()
                        .addElement(new Pair(0.33, Chroma.BLOOD_RED))
                        .addElement(new Pair(0.667, Session.getColorScheme().getBackground()))
                        .build());
    }
}
