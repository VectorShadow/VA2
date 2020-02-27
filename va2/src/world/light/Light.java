package world.light;

import java.awt.*;

public enum Light {
    UNLIGHTED(0, Color.BLACK),
    BIOLUM(1, Color.GREEN.darker().darker().darker()),
    TORCH(5, Color.ORANGE),
    SUN(15, Color.WHITE);

    int brightness;
    Color color;

    Light(int b, Color c) {
        brightness = b;
        color = c;
    }

    public int getBrightness() {
        return brightness;
    }

    public Color getColor() {
        return color;
    }

    public static Light brighter(Light l1, Light l2) {
        return l1.compareTo(l2) > 0 ? l1 : l2;
    }
}
