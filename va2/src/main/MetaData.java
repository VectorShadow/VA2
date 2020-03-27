package main;

public class MetaData {
    public static String gameTitle() {
        return "Chronicles of the Abyss";
    }
    public static String studio() {
        return "Vector Shadow Digital Labs";
    }
    public static String version() {
        return "[a.0.1 - 27 MAR 2020]";
    }
    public static String contact() {
        return "vectorshadowdigitallabs@gmail.com";
    }
    public static String inGameHelp() {
        return "[All Commands]" +
                "\n\nMeta Commands:" +
                "\n\tHelp Menu - ?" +
                "\n\tSave Game - Ctrl s" +
                "\n\tDelete Game - Ctrl q" +
                "\n\nMovement Commands:" +
                "\n\tRest - Numpad 5" +
                "\n\tWalk - Arrow Keys/Numpad" +
                "\n\tChange Floor/Enter An Estate Room - </>" +
                "\n\nInfo Commands:" +
                "\n\tRecall Messages - Shift r" +
                "\n\tAdjust Message Verbosity - v(less verbose)/V(more verbose)" +
                "\n\tLook Around - l(Cursor) / Shift l(Camera)" +
                "\n\t\tMove View - Arrow Keys/Numpad" +
                "\n\t\tCycle Targets(Cursor only) - Numpad +/-" +
                "\n\t\tCenter View on Player - Numpad 5";
    }
}
