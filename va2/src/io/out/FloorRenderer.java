package io.out;

import resources.continuum.Pair;
import util.Coordinate;
import world.actor.Actor;
import world.dungeon.floor.Floor;
import world.dungeon.floor.FloorTile;
import main.Session;
import resources.DualityMode;
import resources.glyph.Glyph;
import resources.glyph.GlyphBuilder;
import resources.glyph.GlyphMap;
import resources.glyph.ascii.SimpleGlyph;
import util.Direction;
import world.WorldObjectTemplate;
import world.light.Light;
import world.terrain.TerrainTemplate;

import java.awt.*;
import java.util.ArrayList;

public class FloorRenderer {
    final int SCREEN_CENTER_ROW;
    final int SCREEN_CENTER_COL;
    int rowOffset;
    int colOffset;
    private GlyphMap glyphMap;
    private ArrayList<VisibleCoordinate> playerView = new ArrayList<>();

    public FloorRenderer() {
        initialize();
        SCREEN_CENTER_ROW = (glyphMap.getRows() - Session.getGuiManager().getMessageWindowRows()) / 2;
        SCREEN_CENTER_COL = glyphMap.getCols() / 2;
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
        int floorRow, floorCol;
        Glyph g;
        FloorTile ft;
        WorldObjectTemplate wot;
        Coordinate camera = Session.getCamera().getAt();
        rowOffset = camera.getRow() - SCREEN_CENTER_ROW;
        colOffset = camera.getColumn() - SCREEN_CENTER_COL;
        for (int i = 0; i < glyphMap.getRows(); ++i) {
            for (int j = 0; j < glyphMap.getCols(); ++j) {
                g = SimpleGlyph.EMPTY_GLYPH;
                floorRow = i + rowOffset;
                floorCol = j + colOffset;
                if (f.inFloor(floorRow, floorCol)) {
                    ft = f.tileAt(floorRow, floorCol);
                    wot = ft.getTerrain().getTemplate();
                    if (ft.isSeen())
                        g = wot.memoryImage();
                }
                glyphMap.setGlyph(i, j, g);
            }
        }
        ArrayList<VisibleCoordinate> actorVision;
        Light actorLight;
        Light lightAtTile;
        boolean actorIsPlayer;
        for (Actor a : Session.getEngine().listActors()){
            actorIsPlayer = a == Session.getPlayer().getActor();
            actorLight = a.getLight();
            //only run lights for actors that have them, unless the player has no light, in which case he still tries to see.
            if (actorLight == null && !actorIsPlayer) continue;
            actorVision = listVisibleCoordinates(a);
            if (actorIsPlayer) setPlayerView(actorVision);
            for (VisibleCoordinate vc : actorVision) {
                floorRow = vc.getRow();
                floorCol = vc.getColumn();
                ft = f.tileAt(floorRow, floorCol);
                wot = ft.getTerrain().getTemplate();
                lightAtTile = (actorLight != null && vc.getDistance() <= actorLight.getBrightness())
                        ? Light.brighterOf(actorLight, ft.getLight())
                        : ft.getLight();
                if (actorIsPlayer && lightAtTile.compareTo(Light.UNLIGHTED) > 0) {
                    ft.setSeen(true);
                    if (ft.getActor() != null) {
                        wot = ft.getActor().getTemplate();
                    }
                    GlyphBuilder gb = wot.partialVisualImage();
                    if (wot.reflectsLight() && lightAtTile.doesFlicker()) {
                        gb.addForegroundColor(
                                new Pair<>(
                                        lightAtTile.getFlicker(),
                                        lightAtTile.getColor()
                                )
                        );
                    }
                    g = gb.build(DualityMode.TILE);
                    setGlyphAt(floorRow - rowOffset, floorCol - colOffset, g);
                }
            }
        }

        GUIManager gm = Session.getGuiManager();
        int enhanceRow =
                gm.from(GUIManager.CHANNEL_GAME, GUIManager.ZONE_MESSAGE_CENTER, true, true);
        int enhancementColumn =
                gm.from(GUIManager.CHANNEL_GAME, GUIManager.ZONE_PLAYER_STATS, false, false);
        glyphMap.setGlyph(enhanceRow, enhancementColumn, DisplayStandards.getEnhancementGlyph());
        //todo - display enhancement glyphs above this
        glyphMap.setGlyph(0, enhancementColumn, DisplayStandards.getWardGlyph());
        //todo - display ward glyphs left of this
        int afflictionColumn =
                gm.from(GUIManager.CHANNEL_GAME, GUIManager.ZONE_PLAYER_ACTIONS, false, true);
        glyphMap.setGlyph(0, afflictionColumn, DisplayStandards.getAfflictionGlyph());
        //todo - display affliction glyphs below this
    }
    private boolean continuePropogation(Actor a, Coordinate c, double d, Floor f) {
        return d < a.getSight() &&
                ((TerrainTemplate)f.tileAt(c.getRow(), c.getColumn()).getTerrain().getTemplate()).permitsLight();
    }
    private void addAtNearestDistance(VisibleCoordinate check, ArrayList<VisibleCoordinate> existing) {
        boolean needToAdd = true;
        for (VisibleCoordinate vc : existing) {
            if (vc.equals(check)) {
                if (check.getDistance() < vc.getDistance())
                    existing.remove(vc);
                else
                    needToAdd = false;
                break;
            }
        }
        if (needToAdd) existing.add(check);
    }
    private void checkCoordinate(
            Actor a,
            double currentDistance,
            Floor f,
            Coordinate from,
            Direction ray,
            Direction spread,
            ArrayList<VisibleCoordinate> vision
    ) {
        double nextDistance = currentDistance + (spread.isDiagonal() ? 1.5 : 1.0);
        Coordinate to = spread.shift(from);
        if (!f.inFloor(to.getRow(), to.getColumn())) return;
        VisibleCoordinate vc = new VisibleCoordinate(to, nextDistance);
        addAtNearestDistance(vc, vision);
        if (continuePropogation(a, to, nextDistance, f))
            propogate(a, f, to, ray, nextDistance, vision);
    }
    private void propogate(Actor a, Floor f, Coordinate from, Direction ray, double distance, ArrayList<VisibleCoordinate> vision) {
        checkCoordinate(a, distance, f, from, ray, ray.rotateCountClockwise(), vision);
        checkCoordinate(a, distance, f, from, ray, ray, vision);
        checkCoordinate(a, distance, f, from, ray, ray.rotateClockwise(), vision);
    }

    private ArrayList<VisibleCoordinate> listVisibleCoordinates(Actor a) {
        Floor floor = Session.getCurrentFloor();
        ArrayList<VisibleCoordinate> vision = new ArrayList<>();
        VisibleCoordinate origin = new VisibleCoordinate(a.getLocation(), 0.0);
        vision.add(origin);
        for (Direction direction : Direction.values()) {
            if (direction == Direction.SELF) continue;
            Coordinate c = direction.shift(origin);
                vision.add(new VisibleCoordinate(c, 0.0));
                if (continuePropogation(a, c, 1.0, floor))
                    propogate(a, floor, c, direction, 1.0, vision);
            }
        return vision;
    }

    public int countRows() {
        return glyphMap.getRows();
    }
    public int countColumns() {
        return glyphMap.getCols();
    }

    /**
     * Return whether the specified indices are within the display.
     */
    private boolean inBounds(int row, int col) {
        return row >= 0 && col >= 0 && row < glyphMap.getRows() && col < glyphMap.getCols();
    }

    /**
     * Returns whether a coordinate within a floor is within the display.
     */
    public boolean onScreen(Coordinate c) {
        return inBounds(c.getRow() - rowOffset, c.getColumn() - colOffset);
    }

    /**
     * Convert a coordinate within a floor to a coordinate on the display.
     */
    public Coordinate floorToDisplay(Coordinate c) {
        return new Coordinate(c.getRow() - rowOffset, c.getColumn() - colOffset);
    }
    public Glyph getGlyphAt(int row, int col) {
        return inBounds(row, col) ? glyphMap.getGlyph(row, col) : SimpleGlyph.EMPTY_GLYPH;
    }
    private void setGlyphAt(int row, int col, Glyph g) {
        if (inBounds(row, col)) glyphMap.setGlyph(row, col, g);
    }
    public ArrayList<VisibleCoordinate> getPlayerView() {
        return playerView;
    }
    private void setPlayerView(ArrayList<VisibleCoordinate> pv) {
        playerView = pv;
    }
}
