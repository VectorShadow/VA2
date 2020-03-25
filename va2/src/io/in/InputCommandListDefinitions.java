package io.in;

import static java.awt.event.KeyEvent.*;

public class InputCommandListDefinitions {
    //todo - INDICES
    public static final InputCommandList MAIN_GAME_VIEW_MODE =
            InputCommandListBuilder
                    .initialize()
                    //todo - add function keys here
                    .addInputCommand(new InputInfo("Arrows/Numpad", "Move/Attack"))
                    .addInputCommand(new InputInfo("Numpad 5", "Wait In Place"))
                    .addInputCommand(new InputInfo("?", "Help"))
                    .build();
    public static final int CURSOR_CYCLE_FAR = 2;
    public static final int CURSOR_CYCLE_NEAR = 3;
    public static final int CURSOR_EXIT = 4;
    public static final InputCommandList MAIN_GAME_CURSOR_MODE =
            InputCommandListBuilder
                    .initialize()
                    .addInputCommand(new InputInfo("Arrows/Numpad", "Move Cursor"))
                    .addInputCommand(new InputInfo("Numpad 5", "Re-center Cursor"))
                    .addInputCommand(new InputCommand(VK_ADD, "Cycle Target(Far)"))
                    .addInputCommand(new InputCommand(VK_SUBTRACT, "Cycle Target(Near)"))
                    .addInputCommand(new InputCommand(VK_ESCAPE, "Return to Game"))
                    .build();

    public static final int CAMERA_EXIT = 2;
    public static final InputCommandList MAIN_GAME_CAMERA_MODE =
            InputCommandListBuilder
                    .initialize()
                    .addInputCommand(new InputInfo("Arrows/Numpad", "Move Camera"))
                    .addInputCommand(new InputInfo("Numpad 5", "Re-center Camera"))
                    .addInputCommand(new InputCommand(VK_ESCAPE, "Return to Game"))
                    .build();
}
