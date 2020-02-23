package world.terrain;

import world.WorldObjectTemplate;

import java.awt.*;
import java.util.ArrayList;

/**
 * Contains information required to describe and represent a generic type of terrain.
 */
public class TerrainTemplate extends WorldObjectTemplate {
    public TerrainTemplate(ArrayList<Character> s, ArrayList<Color> b, ArrayList<Color> f) {
        super(s, b, f);
    }
}
