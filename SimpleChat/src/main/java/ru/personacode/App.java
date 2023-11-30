package ru.personacode;

import ru.personacode.ui.ChatSettingsUI;
import ru.personacode.ui.ServerUI;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ChatServer chatServer = new ChatServer("127.0.0.1", 9988);
        new ServerUI(chatServer);

        new ChatSettingsUI(chatServer);
    }
}
