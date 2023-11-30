package ru.personacode;

import ru.personacode.interfaces.ChatInterface;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class ChatClient implements ChatInterface {
    private final String chatServerAddr;
    private final int chatServerPort;
    private final String userLogin;
    private String userPwd;
    private final ChatServer chatServer;
    private ArrayList<String> chatClients;

    public ChatClient(String chatServerAddr, int serverPort, String userLogin, String userPwd, ChatServer chatServer) {
        this.chatServerAddr = chatServerAddr;
        this.chatServerPort = serverPort;
        this.userLogin = userLogin;
        this.userPwd = userPwd;
        this.chatServer = chatServer;
        this.chatClients = new ArrayList<>();
    }

    @Override
    public boolean exitChat() {
        return chatServer.exitClient(userLogin);
    }

    @Override
    public boolean sendMessage(Message message) {
        return chatServer.postMessage(message);
    }

    @Override
    public ArrayList<String> checkNewMessages() {
        return chatServer.getHistoryMessage();
    }

    @Override
    public void checkListClients() {
        chatClients = chatServer.getChatClients();
    }

    public boolean connectToServer() {
        ///// имитация соединения с сервером ////
        if (chatServer.statusServer() &&
                chatServer.getAddress().equals(chatServerAddr) && chatServer.getPort() == chatServerPort) {
            return chatServer.enterClient(userLogin);
        }
        return false;
    }

    public ArrayList<String> getChatClients() {
        checkListClients();
        System.out.println(chatClients);
        return chatClients;
    }

    public String getUserLogin() {
        return userLogin;
    }

}
