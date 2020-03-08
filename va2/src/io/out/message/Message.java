package io.out.message;

import io.out.DisplayStandards;
import main.Session;
import resources.glyph.GlyphString;

import java.awt.*;

public class Message {
    final Color background;
    final Color foreground;
    String text;

    public Message() {
        this(DisplayStandards.MESSAGE_DEFAULT_BACKGROUND, DisplayStandards.MESSAGE_DEFAULT_FOREGROUND, "");
    }

    public Message(MessageType mt) {
        this(mt.background, mt.foreground, "");
    }

    public Message(Color b, Color f, String t) {
        background = b;
        foreground = f;
        text = t;
    }

    public Color getBackground() {
        return background == DisplayStandards.MESSAGE_DEFAULT_BACKGROUND ?
                Session.getColorScheme().getBackground() :
                background;
    }

    public Color getForeground() {
        return foreground;
    }

    public String getText() {
        return text;
    }

    public int length() {
        return text.length();
    }
    public void concatenate(Message m) {
        text = "  " + m.text + text;
    }
    public void setText(String t) {
        text = t;
    }

    public GlyphString asGlyphString() {
        return new GlyphString(text, background, foreground);
    }
}
