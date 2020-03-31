package main.modes;

import io.in.InputCommandList;
import io.out.GUIManager;
import main.Camera;
import main.Session;
import util.Direction;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;
import static io.in.InputCommandListDefinitions.*;

public class MainGameCameraMode extends MainGameViewMode {

    @Override
    public InputCommandList getInput() {
        return MAIN_GAME_CAMERA_MODE;
    }

    @Override
    public void to() {
        Session.getCamera().trackPlayer();
        GUIManager gm = Session.getGuiManager();
        gm.changeChannelToGameDisplay();
        out();
    }

    @Override
    public void in(KeyEvent ke) {
        if (OperatingMode.overrideHandleInput(this, ke)) return;
        Camera camera = Session.getCamera();
        switch (getInput().test(ke)) {
            case CAMERA_EXIT:
                Session.getModeManager().revert();
                return;
                default:
                    switch (ke.getKeyCode()) {
                        case VK_UP: case VK_NUMPAD8:
                            camera.shift(Direction.NORTH);
                            break;
                        case VK_NUMPAD9:
                            camera.shift(Direction.NORTH_EAST);
                            break;
                        case VK_RIGHT: case VK_NUMPAD6:
                            camera.shift(Direction.EAST);
                            break;
                        case VK_NUMPAD3:
                            camera.shift(Direction.SOUTH_EAST);
                            break;
                        case VK_DOWN: case VK_NUMPAD2:
                            camera.shift(Direction.SOUTH);
                            break;
                        case VK_NUMPAD1:
                            camera.shift(Direction.SOUTH_WEST);
                            break;
                        case VK_LEFT: case VK_NUMPAD4:
                            camera.shift(Direction.WEST);
                            break;
                        case VK_NUMPAD7:
                            camera.shift(Direction.NORTH_WEST);
                            break;
                        case VK_NUMPAD5:
                            camera.trackPlayer();
                            break;
                    }
        }
        out();
    }

    @Override
    public void from() {
        Session.getCamera().trackPlayer();
    }
}
