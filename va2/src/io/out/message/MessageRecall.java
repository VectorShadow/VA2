package io.out.message;

import java.util.LinkedList;

public class MessageRecall {
    private static final int MAXIMUM_RECALL = 128;

    private LinkedList<Message> messageHistory = new LinkedList<>();

    MessageRecall(){}

    void add(Message m) {
        messageHistory.addFirst(m);
        if (messageHistory.size() > MAXIMUM_RECALL)
            messageHistory.removeLast();
    }

    public int size() {
        return messageHistory.size();
    }

    public Message get(int index) {
        return messageHistory.get(index);
    }

}
