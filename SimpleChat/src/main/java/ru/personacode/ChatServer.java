package ru.personacode;
import ru.personacode.CSV.LoadFromCSV;
import ru.personacode.CSV.SaveToCSV;
import ru.personacode.interfaces.ServerInterface;

import java.util.ArrayList;
import java.util.Arrays;

public class ChatServer implements ServerInterface {
    private final String address;
    private final int port ;
    private boolean status = false;
    private final ArrayList<String> chatClients;
    private final SaveToCSV saveService = new SaveToCSV();
    private LoadFromCSV loadService = new LoadFromCSV(saveService.getPath());

    public ChatServer(String address, int port) {
        this.port = port;
        this.address = address;
        String[] simple_clients = { "Клиент-1", "Клиент-2", "Клиент-3"};
        chatClients = new ArrayList<>(Arrays.asList(simple_clients));
    }

    @Override
    public void startServer() {
        this.status = true;
    }

    @Override
    public void stopServer() {
        this.status = false;
    }

    @Override
    public boolean statusServer() {
        return this.status;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public int getPort() {
        return port;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean postMessage (Message message) {
        saveService.save(message);
        return true;
    }

    public ArrayList<String> getChatClients() {
        return chatClients;
    }

    boolean enterClient (String clientName) {
        chatClients.add(clientName);
        return true;
    }

    boolean exitClient (String clientName) {
        chatClients.remove(clientName);
        return true;
    }

    public ArrayList<String> getHistoryMessage () {
        return loadService.load();
    }
}
