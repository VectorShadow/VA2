package io.out.message;

import io.out.DisplayStandards;
import main.Session;

import java.awt.*;
import java.util.ArrayList;

public class MessageCenter {
    public class Message {
        final Color background;
        final Color foreground;
        String text;

        Message() {
            this(DisplayStandards.MESSAGE_DEFAULT_BACKGROUND, DisplayStandards.MESSAGE_DEFAULT_FOREGROUND, "");
        }

        Message(MessageType mt) {
            this(mt.background, mt.foreground, "");
        }

        Message(Color b, Color f, String t) {
            background = b;
            foreground = f;
            text = t;
        }

        public Color getBackground() {
            return background;
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
        void concatenate(Message m) {
            text = "  " + m.text + text;
        }
        void setText(String t) {
            text = t;
        }
    }

    private ArrayList<Message> messageHistory = new ArrayList<>();
    private Message onScreenMessages = new Message(MessageType.OLD);
    private Message lastMessage = new Message();

    public void sendMessage(String text, MessageType type) {
        if (lastMessage.length() > 0) onScreenMessages.concatenate(lastMessage);
        Message message = new Message(type.background, type.foreground, text);
        lastMessage = message;
        trimOnScreenMessages();
        messageHistory.add(message);
    }
    void trimOnScreenMessages() {
        int currentLength = onScreenMessages.length();
        int maxLength = maxLength() - lastMessage.length();
        if (currentLength < maxLength) return;
        onScreenMessages.setText(onScreenMessages.text.substring(currentLength - maxLength));
    }
    private int maxLength() {
        int rows = Session.getGuiManager().getMessageWindowRows();
        int cols = Session.getGuiManager().getMessageWindowCols();
        return rows * cols;
    }

    public ArrayList<Message> getMessageHistory() {
        return messageHistory;
    }

    public Message getOnScreenMessages() {
        return onScreenMessages;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

}
