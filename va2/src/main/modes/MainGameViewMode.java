package main.modes;

import io.out.FloorRenderer;
import io.out.GUIManager;
import main.Session;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

public class MainGameViewMode implements OperatingMode {

    FloorRenderer floorRenderer;

    public MainGameViewMode() {
        floorRenderer = new FloorRenderer();
    }

    @Override
    public void to() {
        GUIManager gm = Session.getGuiManager();
        gm.changeChannelToGameDisplay();
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
        //todo - access the current floor via Session, then call floorRenderer.update() to avoid access time conflicts
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
