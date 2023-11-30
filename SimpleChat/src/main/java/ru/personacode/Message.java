package ru.personacode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private String author;
    private String textMessage;
    private Date timestampMessage;

    public Message(String author, String textMessage) {
        this.author = author;
        this.textMessage = textMessage;
        this.timestampMessage = new Date(System.currentTimeMillis());
    }

    public String generateToChatArea() {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        return author + " (" + df.format(timestampMessage) + "): " + textMessage;
    }
}
