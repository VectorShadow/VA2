package world.light;

import java.awt.*;

/**
 * Define lights for illuminating the game world.
 * Enumeration is in ascending order of precedence - higher ordinal lights display over lower ones,
 * so ordering by brightness should be maintained.
 * Brightness determines radius for radial lights.
 * Color applies to all reflective WorldObjects.
 */
public enum Light {
    UNLIGHTED(0, Color.BLACK, 0.0),
    MOONLIGHT(3, Color.LIGHT_GRAY, 0.05),
    DIM_LAMP(4, Color.YELLOW.darker().darker(), 0.25),
    TORCH(5, Color.ORANGE, 0.4),
    SUN(255, Color.WHITE, 0.999);

    int brightness;
    Color color;
    double flicker;

    Light(int b, Color c, double f) {
        brightness = b;
        color = c;
        flicker = f;
    }

    public int getBrightness() {
        return brightness;
    }

    public Color getColor() {
        return color;
    }

    public boolean doesFlicker() {
        return flicker > 0.0;
    }
    public double getFlicker() {
        return flicker;
    }

    public static Light brighterOf(Light l1, Light l2) {
        return l1.compareTo(l2) > 0 ? l1 : l2;
    }

}
