package io.out.message;

import java.util.ArrayList;


public class MessageCenter {

    public static final int PRIORITY_MAX = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = 2;
    public static final int PRIORITY_MIN = 3;

    public static final Message MORE = new Message(MessageType.MORE, "[...]");

    private int priorityThreshold = PRIORITY_MIN;

    private MessageRecall messageRecall = new MessageRecall();
    private ArrayList<Message> newMessages = new ArrayList<>();

    public void sendMessage(Message m, int priority) {
        if (priority > priorityThreshold) return; //ignore this message
        newMessages.add(m);
        messageRecall.add(m);
    }

    public void sendMessage(String text, MessageType type, int priority) {
        Message m = new Message(type.background, type.foreground, text);
        sendMessage(m, priority);
    }

    public MessageRecall getMessageRecall() {
        return messageRecall;
    }

    public ArrayList<Message> getNewMessages() {
        return newMessages;
    }

    public void clearNewMessages() {
        newMessages = new ArrayList<>();
    }

    public void setPriorityThreshold(int pt) {
        priorityThreshold = pt;
    }

    public int getPriorityThreshold() {
        return priorityThreshold;
    }

    public void increasePriorityThreshold(){
        priorityThreshold++;
        if (priorityThreshold > PRIORITY_MIN) priorityThreshold = PRIORITY_MIN;
    }
    public void decreasePriorityThreshold(){
        priorityThreshold--;
        if (priorityThreshold < PRIORITY_MAX) priorityThreshold = PRIORITY_MAX;
    }
}
