package io.out.message;

import main.Session;


public class MessageCenter {

    private MessageRecall messageRecall = new MessageRecall();
    private Message onScreenMessages = new Message(MessageType.OLD);
    private Message lastMessage = new Message();

    public void sendMessage(Message m) {
        if (lastMessage.length() > 0) onScreenMessages.concatenate(lastMessage);
        lastMessage = m;
        trimOnScreenMessages();
        messageRecall.add(m);
    }

    public void sendMessage(String text, MessageType type) {
        Message m = new Message(type.background, type.foreground, text);
        sendMessage(m);
    }
    void trimOnScreenMessages() {
        int currentLength = onScreenMessages.length();
        int maxLength = maxLength() - lastMessage.length();
        if (currentLength < maxLength) return;
        onScreenMessages.setText(onScreenMessages.text.substring(0, maxLength));
    }
    private int maxLength() {
        int rows = Session.getGuiManager().getMessageWindowRows();
        int cols = Session.getGuiManager().getMessageWindowCols();
        return rows * cols;
    }

    public MessageRecall getMessageRecall() {
        return messageRecall;
    }

    public Message getOnScreenMessages() {
        return onScreenMessages;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

}
