package ru.personacode.ui;

import ru.personacode.ChatClient;
import ru.personacode.ChatServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ChatSettingsUI extends JFrame {
    private static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_HEIGHT = 200;
    JPanel windowPanel = new JPanel(new GridLayout(5, 2));

    JLabel loginUserLabel = new JLabel("Логин: ");
    JLabel pwdUserLabel = new JLabel("Пароль: ");
    JLabel serverAddrLabel = new JLabel("Адрес сервера: ");
    JLabel serverPortLabel = new JLabel("Порт сервера: ");

    JTextField loginUser = new JTextField("Simple User");
    JPasswordField pwdUser = new JPasswordField();
    JTextField serverAddr = new JTextField("127.0.0.1");
    JTextField serverPort = new JTextField("9988");

    JButton btnEnter = new JButton("Войти");

    public ChatSettingsUI(final ChatServer chatServer) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setTitle("ChatSettings");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        // set location to center screen
        this.setLocation(width / 2 - WINDOW_WIDTH / 2, 200);
        this.setResizable(false);

        windowPanel.add(loginUserLabel);
        windowPanel.add(loginUser);
        windowPanel.add(pwdUserLabel);
        windowPanel.add(pwdUser);
        windowPanel.add(serverAddrLabel);
        windowPanel.add(serverAddr);
        windowPanel.add(serverPortLabel);
        windowPanel.add(serverPort);
        windowPanel.add(btnEnter);

        add(windowPanel);
        setVisible(true);

        btnEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChatClient chatClient = new ChatClient(serverAddr.getText(), Integer.parseInt(serverPort.getText()),
                        loginUser.getText(), Arrays.toString(pwdUser.getPassword()), chatServer);
                if (!chatClient.connectToServer()) {
                    JOptionPane.showMessageDialog(null, "Не удалось подключиться к серверу");
                    System.out.printf("Не удалось подключиться к серверу: %s:%s %s@******\n",
                            serverAddr.getText(), serverPort.getText(), loginUser.getText());
                }
                else {
                    ChatUI myChat = new ChatUI(chatClient);
                    for (String clientName: chatClient.getChatClients()) {
                        myChat.listModel.addElement(clientName);
                    }
                    for (String message:chatClient.checkNewMessages()) {
                        myChat.chatTextArea.append(message);
                        myChat.chatTextArea.append("\n");
                    }
                    myChat.setVisible(true);
                    setVisible(false);
                }
            }
        });
    }
}
