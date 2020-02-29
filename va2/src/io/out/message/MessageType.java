package io.out.message;

import io.out.DisplayStandards;
import resources.chroma.Chroma;

import java.awt.*;

public enum MessageType {
    ERROR(Chroma.ORANGE),
    FAILURE(Chroma.RED),
    GAME(Chroma.AQUA),
    INFO(Chroma.GREY),
    OLD(Chroma.dim(Chroma.GREY)),
    SUCCESS(Chroma.GREEN),
    WARNING(Chroma.YELLOW),
    ;

    Color background;
    Color foreground;

    MessageType(Color c) {
        this(DisplayStandards.MESSAGE_DEFAULT_BACKGROUND, c);
    }
    MessageType(Color b, Color f) {
        background = b;
        foreground = f;
    }
}