package ru.personacode.ui;

import ru.personacode.ChatServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerUI extends JFrame {
    private static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_HEIGHT = 150;
    JPanel windowPanel = new JPanel();

    JLabel serverAddrLabel = new JLabel("Адрес сервера: ");
    JLabel serverportLabel = new JLabel("Порт сервера: ");
    JLabel serverStatusLabel = new JLabel("Статус сервера: ");
    JLabel serverAddr = new JLabel();
    JLabel serverPort = new JLabel();
    JLabel serverStatus = new JLabel();
    JPanel serverInfoPanel = new JPanel(new GridLayout(3, 2));
    JButton btnStartServer = new JButton("Запустить сервер");
    JButton btnStopServer = new JButton("Остановить сервер");
    JPanel btnStartStopPanel = new JPanel(new GridLayout(1, 2));
    ChatServer chatServer;

    public ServerUI(final ChatServer chatServer) {
        this.chatServer = chatServer;
        this.serverAddr.setText(chatServer.getAddress());
        this.serverPort.setText("" + chatServer.getPort());

        checkServer();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setTitle("ChatServer");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        // set location to center screen
        this.setLocation(width / 2 - WINDOW_WIDTH / 2, 50);
        this.setResizable(false);

        serverInfoPanel.add(serverAddrLabel);
        serverInfoPanel.add(serverAddr);
        serverInfoPanel.add(serverportLabel);
        serverInfoPanel.add(serverPort);
        serverInfoPanel.add(serverStatusLabel);
        serverInfoPanel.add(serverStatus);

        btnStartStopPanel.add(btnStartServer);
        btnStartStopPanel.add(btnStopServer);

        windowPanel.add(serverInfoPanel);
        windowPanel.add(btnStartStopPanel);

        add(windowPanel);
        setVisible(true);

        btnStartServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatServer.startServer();
                checkServer();
            }
        });

        btnStopServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatServer.stopServer();
                checkServer();
            }
        });
    }

    private void checkServer() {
        if (chatServer.statusServer()) {
            this.serverStatus.setText("Запущен");
            btnStopServer.setEnabled(true);
            btnStartServer.setEnabled(false);
        } else {
            this.serverStatus.setText("Остановлен");
            btnStopServer.setEnabled(false);
            btnStartServer.setEnabled(true);
        }
    }
}
