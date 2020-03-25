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
    public static String toString(int keyCode) {
        switch (keyCode) {
            case VK_0: return "0";
            case VK_1: return "1";
            case VK_2: return "2";
            case VK_3: return "3";
            case VK_4: return "4";
            case VK_5: return "5";
            case VK_6: return "6";
            case VK_7: return "7";
            case VK_8: return "8";
            case VK_9: return "9";
            case VK_A: return "a";
            case VK_B: return "b";
            case VK_C: return "c";
            case VK_D: return "d";
            case VK_E: return "e";
            case VK_F: return "f";
            case VK_G: return "g";
            case VK_H: return "h";
            case VK_I: return "i";
            case VK_J: return "j";
            case VK_K: return "k";
            case VK_L: return "l";
            case VK_M: return "m";
            case VK_N: return "n";
            case VK_O: return "o";
            case VK_P: return "p";
            case VK_Q: return "q";
            case VK_R: return "r";
            case VK_S: return "s";
            case VK_T: return "t";
            case VK_U: return "u";
            case VK_V: return "v";
            case VK_W: return "w";
            case VK_X: return "x";
            case VK_Y: return "y";
            case VK_Z: return "z";
            case VK_COMMA: return  ",";
            case VK_PERIOD: return ".";
            case VK_SLASH: return "/";
            case VK_ADD: return "(n)+";
            case VK_SUBTRACT: return "(n)-";
            case VK_NUMPAD0: return "(n)0";
            case VK_NUMPAD1: return "(n)1";
            case VK_NUMPAD2: return "(n)2";
            case VK_NUMPAD3: return "(n)3";
            case VK_NUMPAD4: return "(n)4";
            case VK_NUMPAD5: return "(n)5";
            case VK_NUMPAD6: return "(n)6";
            case VK_NUMPAD7: return "(n)7";
            case VK_NUMPAD8: return "(n)8";
            case VK_NUMPAD9: return "(n)9";
            case VK_ENTER: return "<Enter>";
            case VK_ESCAPE: return "<Escape>";
            case VK_F1: return "<F1>";
            case VK_F2: return "<F2>";
            case VK_F3: return "<F3>";
            case VK_F4: return "<F4>";
            case VK_F5: return "<F5>";
            case VK_F6: return "<F6>";
            case VK_F7: return "<F7>";
            case VK_F8: return "<F8>";
            case VK_F9: return "<F9>";
            case VK_F10: return "<F10>";
            case VK_F11: return "<F11>";
            case VK_F12: return "<F12>";
            default: return "";
        }
    }
}
