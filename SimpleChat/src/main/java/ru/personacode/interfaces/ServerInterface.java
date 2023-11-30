package ru.personacode.interfaces;

public interface ServerInterface {
    void startServer();
    void stopServer();
    boolean statusServer();
    String getAddress();
    int getPort();
}
