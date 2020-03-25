package main;

import resources.chroma.Chroma;
import resources.glyph.Glyph;
import resources.glyph.GlyphBuilder;
import util.Coordinate;
import util.Direction;
import world.dungeon.floor.FloorTile;

/**
 * Specifies the location of a cursor on the screen for player information.
 * TargettingCursor should extend this and offer specific information about spell line of sight, area of effect, etc.
 */
public class Cursor {
    private Coordinate at = targetList().get().getLocation();

    private TargetList targetList() {
        return Session.getTargetList();
    }

    /**
     * Return the coordinate the cursor is located at.
     */
    public Coordinate at() {
        return at;
    }

    /**
     * Reset the cursor to the player's location.
     */
    public void self() {
        targetList().clearTarget();
        at = Session.getPlayer().getActor().getLocation();
    }

    /**
     * Cycle targets forward or backward.
     */
    public void cycle(boolean forward) {
        targetList().cycle(forward);
        at = targetList().get().getLocation();
    }

    /**
     * Move the cursor in the specified direction.
     */
    public void shift(Direction d) {
        targetList().clearTarget();
        Coordinate c = d.shift(at);
        if (Session.getFloorRenderer().onScreen(c)) {
            at = c;
            targetList().setTarget(at);
        }
    }

    /**
     * Return a glyph representing the cursor.
     */
    public Glyph draw() {
        boolean visible = targetList().visible(at);
        boolean isActor = targetList().setTarget(at);
        boolean isPlayer = at.equals(Session.getPlayer().getActor().getLocation());
        return GlyphBuilder
                .buildGlyph()
                .setDefaults(
                        visible
                        ? isPlayer
                                ? Chroma.GREEN
                                : isActor
                                ? Chroma.RED
                                : Chroma.YELLOW
                                : Chroma.GREY,
                        visible
                        ? isPlayer
                                ? Chroma.WHITE
                                : isActor
                                ? Chroma.GREY
                                : Chroma.BLACK
                                : Chroma.ORANGE,
                        visible
                        ? isPlayer
                                ? '@'
                                : isActor
                                ? 'X'
                                : ' '
                                : '?'
                ).build();
    }
}
