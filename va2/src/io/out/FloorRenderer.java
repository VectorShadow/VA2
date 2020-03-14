package io.out;

import resources.continuum.Pair;
import util.Coordinate;
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
    private GlyphMap glyphMap;

    public FloorRenderer() {
        initialize();
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
        //todo - access the camera position and calculate row and column offsets such that the camera location
        // corresponds to the center of the glyphMap.
        Coordinate camera = Session.getCamera().getAt();
        final int SCREEN_CENTER_ROW = (glyphMap.getRows() - Session.getGuiManager().getMessageWindowRows()) / 2;
        final int SCREEN_CENTER_COL = glyphMap.getCols() / 2;
        final int ROW_OFFSET = camera.getRow() - SCREEN_CENTER_ROW;
        final int COL_OFFSET = camera.getColumn() - SCREEN_CENTER_COL;
        int floorRow, floorCol;
        Glyph g;
        FloorTile ft;
        WorldObjectTemplate wot;
        for (int i = 0; i < glyphMap.getRows(); ++i) {
            for (int j = 0; j < glyphMap.getCols(); ++j) {
                g = SimpleGlyph.EMPTY_GLYPH;
                floorRow = i + ROW_OFFSET;
                floorCol = j + COL_OFFSET;
                if (f.inFloor(floorRow, floorCol)) {
                    ft = f.tileAt(floorRow, floorCol);
                    wot = ft.getTerrain().getTemplate();
                    if (ft.isSeen())
                        g = wot.memoryImage();
                }
                glyphMap.setGlyph(i, j, g);
            }
        }
        ArrayList<VisibleCoordinate> playerVision = listVisibleCoordinates();
        Light playerLight = Session.getPlayer().getLight();
        Light lightAtTile;
        for (VisibleCoordinate vc : playerVision) {
            floorRow = vc.getCoordinate().getRow();
            floorCol = vc.getCoordinate().getColumn();
            ft = f.tileAt(floorRow, floorCol);
            wot = ft.getTerrain().getTemplate();
            lightAtTile = vc.getDistance() <= playerLight.getBrightness()
                    ? Light.brighterOf(playerLight, ft.getLight())
                    : ft.getLight();
            if (lightAtTile.compareTo(Light.UNLIGHTED) > 0) {
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
                glyphMap.setGlyph(floorRow - ROW_OFFSET, floorCol - COL_OFFSET, g);
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
    private boolean continuePropogation(Coordinate c, double d, Floor f) {
        return d < Session.getPlayer().getSightRadius() &&
                ((TerrainTemplate)f.tileAt(c.getRow(), c.getColumn()).getTerrain().getTemplate()).permitsLight();
    }
    private void addAtNearestDistance(VisibleCoordinate check, ArrayList<VisibleCoordinate> existing) {
        boolean needToAdd = true;
        for (VisibleCoordinate vc : existing) {
            if (vc.getCoordinate().equals(check.getCoordinate())) {
                if (check.getDistance() < vc.getDistance())
                    existing.remove(vc);
                else
                    needToAdd = false;
                break;
            }
        }
        if (needToAdd) existing.add(check);
    }
    private void checkCoordinate(double currentDistance, Floor f, Coordinate from, Direction ray, Direction spread, ArrayList<VisibleCoordinate> vision) {
        double nextDistance = currentDistance + (spread.isDiagonal() ? 1.5 : 1.0);
        Coordinate to = spread.shift(from);
        if (!f.inFloor(to.getRow(), to.getColumn())) return;
        VisibleCoordinate vc = new VisibleCoordinate(to, nextDistance);
        addAtNearestDistance(vc, vision);
        if (continuePropogation(to, nextDistance, f))
            propogate(f, to, ray, nextDistance, vision);
    }
    private void propogate(Floor f, Coordinate from, Direction ray, double distance, ArrayList<VisibleCoordinate> vision) {
        checkCoordinate(distance, f, from, ray, ray.rotateCountClockwise(), vision);
        checkCoordinate(distance, f, from, ray, ray, vision);
        checkCoordinate(distance, f, from, ray, ray.rotateClockwise(), vision);
    }

    private ArrayList<VisibleCoordinate> listVisibleCoordinates() {
        Floor floor = Session.getCurrentFloor();
        ArrayList<VisibleCoordinate> vision = new ArrayList<>();
        VisibleCoordinate origin = new VisibleCoordinate(Session.getPlayer().getActor().getLocation(), 0.0);
        vision.add(origin);
        for (Direction direction : Direction.values()) {
            if (direction == Direction.SELF) continue;
            Coordinate c = direction.shift(origin.getCoordinate());
                vision.add(new VisibleCoordinate(c, 0.0));
                if (continuePropogation(c, 1.0, floor))
                    propogate(floor, c, direction, 1.0, vision);
            }
        return vision;
    }

    public int countRows() {
        return glyphMap.getRows();
    }
    public int countColumns() {
        return glyphMap.getCols();
    }
    public Glyph getGlyphAt(int row, int col) {
        return glyphMap.getGlyph(row, col);
    }
}
