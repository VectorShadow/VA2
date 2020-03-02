package io.in;

import static java.awt.event.KeyEvent.*;

public class KeyCodeInterpreter {
    public static int toInt(int keyCode) {
        switch (keyCode) {
            case VK_0: case VK_NUMPAD0: return 0;
            case VK_1: case VK_NUMPAD1: return 1;
            case VK_2: case VK_NUMPAD2: return 2;
            case VK_3: case VK_NUMPAD3: return 3;
            case VK_4: case VK_NUMPAD4: return 4;
            case VK_5: case VK_NUMPAD5: return 5;
            case VK_6: case VK_NUMPAD6: return 6;
            case VK_7: case VK_NUMPAD7: return 7;
            case VK_8: case VK_NUMPAD8: return 8;
            case VK_9: case VK_NUMPAD9: return 9;
            case VK_A: return 10;
            case VK_B: return 11;
            case VK_C: return 12;
            case VK_D: return 13;
            case VK_E: return 14;
            case VK_F: return 15;
            default: return -1;
        }
    }
}
