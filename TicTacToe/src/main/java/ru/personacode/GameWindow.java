package ru.personacode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private static final int WINDOW_WIDTH = 555;
    public static final int WINDOW_HEIGHT = 555;

    JPanel panBottom = new JPanel(new GridLayout(1, 2));
    Map map = new Map();
    SettingWindow settingWindow = new SettingWindow(this);

    public GameWindow() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setTitle("TicTac Toe");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        // set location to center screen
        this.setLocation(width / 2 - WINDOW_WIDTH / 2, height / 2 - WINDOW_HEIGHT / 2);
        this.setResizable(false);
        JButton btnStart = new JButton("New Game");
        JButton btnExit = new JButton("Exit");

        panBottom.add(btnStart);
        panBottom.add(btnExit);

        this.add(map, BorderLayout.CENTER);
        this.add(panBottom, BorderLayout.SOUTH);

        this.setVisible(true);

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingWindow.setVisible(true);
            }
        });
    }

    public void startNewGame(int mode, int square, boolean firstMove) {
        // firstMove = true : Human
        // firstMove = false : PC
        map.setBackground(Color.WHITE);
        map.setVisible(true);
        map.setSquare(square);
        map.startNewGame(mode, square, firstMove);
    }
}
