package ru.personacode.interfaces;

import ru.personacode.Message;

import java.util.ArrayList;

public interface ChatInterface {
    boolean exitChat();
    boolean sendMessage(Message message);
    ArrayList<String> checkNewMessages();
    void checkListClients();
    boolean connectToServer();
}
