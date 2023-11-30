package ru.personacode.ui;

import ru.personacode.ChatClient;
import ru.personacode.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatUI extends JFrame {
    ChatClient chat;
    private static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    JPanel windowPanel = new JPanel(new GridLayout(1, 2));
    JPanel textFieldAndChatPanel = new JPanel(new BorderLayout());
    JPanel listClientsAndButtonsPanel = new JPanel(new BorderLayout());
    JPanel buttonsChatPanel = new JPanel(new GridLayout(1, 2));

    JTextArea chatTextArea = new JTextArea();
    JTextField chatTextField = new JTextField();
    JButton btnSend = new JButton("Отправить");
    JButton btnExit = new JButton("Выйти");

    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> chatClients = new JList<>();

    public ChatUI(final ChatClient chat) {
        chatClients.setModel(listModel);
        this.chat = chat;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setTitle("ChatClient");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        // set location to center screen
        this.setLocation(width / 2 - WINDOW_WIDTH / 2, height / 2 - WINDOW_HEIGHT / 2);
        this.setResizable(false);

        this.chatTextArea.setEditable(false);
        this.chatTextArea.setLineWrap(true);
        this.chatTextArea.setRows(33);
        JScrollPane jScrollPane = new JScrollPane(chatTextArea);

        this.textFieldAndChatPanel.add(jScrollPane, BorderLayout.NORTH);
        this.textFieldAndChatPanel.add(chatTextField, BorderLayout.SOUTH);

        this.buttonsChatPanel.add(btnSend);
        this.buttonsChatPanel.add(btnExit);

        this.listClientsAndButtonsPanel.add(chatClients, BorderLayout.NORTH);
        this.listClientsAndButtonsPanel.add(buttonsChatPanel, BorderLayout.SOUTH);

        this.windowPanel.add(textFieldAndChatPanel);
        this.windowPanel.add(listClientsAndButtonsPanel);

        add(windowPanel);
        setVisible(false);

        chatTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    sendMessage();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        btnExit.addActionListener(e -> {
            int result = JOptionPane.showInternalConfirmDialog(null,
                    "Вы уверены, что хотите выйти?",
                    "Выход", JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                if (chat.exitChat()) {
                    updateClientList();
                    setVisible(false);
                    dispose(); //Destroy the JFrame object
                }
            }
        });

        btnSend.addActionListener(e -> sendMessage());
    }

    private Message generateMessage() {
        return new Message(chat.getUserLogin(), chatTextField.getText());
    }

    private void sendMessage() {
        if (!chatTextField.getText().isEmpty()) {
            Message message = generateMessage();
            if (chat.sendMessage(message)) {
                chatTextArea.append(message.generateToChatArea() + "\n");
                chatTextField.setText("");
            }
        }
    }

    private void updateClientList() {
        this.listModel.clear();
        for (String clientName : chat.getChatClients()) {
            this.listModel.addElement(clientName);
        }
    }
}
