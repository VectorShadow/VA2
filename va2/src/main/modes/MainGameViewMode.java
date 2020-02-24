package main.modes;

import floor.Floor;
import io.out.FloorRenderer;
import io.out.GUIManager;
import main.Session;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

public class MainGameViewMode implements OperatingMode {

    FloorRenderer floorRenderer;

    public MainGameViewMode() {
    }

    @Override
    public void to() {
        Session.setCurrentFloor(new Floor(16, 16)); //todo - hack to test floorRenderer
        GUIManager gm = Session.getGuiManager();
        gm.changeChannelToGameDisplay();
        floorRenderer = new FloorRenderer();
        out();
    }

    @Override
    public void in(KeyEvent ke) {
        if (OperatingMode.overrideHandleInput(ke)) return;
        switch (ke.getKeyCode()) {
            //todo - lots. breaks when we want to redraw the screen, returns if not
        }
        out();
    }

    @Override
    public void out() {
        GUIManager gm = Session.getGuiManager();
        //update the renderer's glyphMap from the current floor before drawing it to the screen
        floorRenderer.update(Session.getCurrentFloor());
        for (int r = 0; r < floorRenderer.countRows(); ++r) {
            for (int c = 0; c < floorRenderer.countColumns(); ++c) {
                gm.printGlyph(r, c, floorRenderer.getGlyphAt(r, c));
            }
        }
    }

    @Override
    public void from() {
        //nothing to do
    }
}
