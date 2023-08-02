package com.app.explore;

public class Message {

    public static String SENT_BY_ME = "me";
    public static String SENT_BY_BOT = "bot";

    String message;
    String sendBy;

    public Message(String message, String sendBy) {
        this.message = message;
        this.sendBy = sendBy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSendBy(String sendBy) {
        this.sendBy = sendBy;
    }

    public String getSentBy() {
        return sendBy;
    }
}