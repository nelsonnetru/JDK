package ru.personacode;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingWindow extends JFrame {
    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_HEIGHT = 300;

    JLabel label = new JLabel("Готовы начать?");
    JButton btnStart = new JButton("START");
    JLabel gameField = new JLabel("Установленный размер поля:");
    JSlider fieldSlider = new JSlider(3, 9, 3);
    JLabel gameFieldCount = new JLabel("" + fieldSlider.getValue());

    JLabel gameModeLabel = new JLabel("Тип игры:");
    JRadioButton humanVSpc = new JRadioButton("Человек против компьютера", true);
    JRadioButton humanVShuman = new JRadioButton("Человек против человека");
    ButtonGroup gameModeButtonGroup = new ButtonGroup();

    JLabel firstMove = new JLabel("Кто ходит первым:");
    JRadioButton firstMoveHuman = new JRadioButton("Человек", true);
    JRadioButton firstMovePC = new JRadioButton("Компьютер");
    ButtonGroup whoMoveFirstGroup = new ButtonGroup();
    GameWindow gameWindow;

    public SettingWindow(final GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(gameWindow);
        JPanel settingsPanel = new JPanel(new GridLayout(9, 1));
        JPanel labelSliderPanel = new JPanel(new FlowLayout());
        JPanel whoMoveFirstPanel = new JPanel(new GridLayout(1,2));
        whoMoveFirstGroup.add(firstMoveHuman);
        whoMoveFirstGroup.add(firstMovePC);
        whoMoveFirstPanel.add(firstMoveHuman);
        whoMoveFirstPanel.add(firstMovePC);
        labelSliderPanel.add(gameField);
        labelSliderPanel.add(gameFieldCount);
        settingsPanel.add(label);
        settingsPanel.add(labelSliderPanel);
        settingsPanel.add(fieldSlider);
        settingsPanel.add(firstMove);
        settingsPanel.add(whoMoveFirstPanel);
        gameModeButtonGroup.add(humanVSpc);
        gameModeButtonGroup.add(humanVShuman);
        settingsPanel.add(gameModeLabel);
        settingsPanel.add(humanVSpc);
        settingsPanel.add(humanVShuman);
        settingsPanel.add(btnStart);
        this.add(settingsPanel);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                gameWindow.startNewGame(humanVSpc.isSelected() ? 0 : 1, fieldSlider.getValue(), firstMoveHuman.isSelected());
            }
        });

        fieldSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                gameFieldCount.setText("" + fieldSlider.getValue());
            }
        });

        humanVShuman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    firstMoveHuman.setText("Игрок 1");
                    firstMovePC.setText("Игрок 2");
            }
        });

        humanVSpc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstMoveHuman.setText("Человек");
                firstMovePC.setText("Компьютер");
            }
        });

    }
}
