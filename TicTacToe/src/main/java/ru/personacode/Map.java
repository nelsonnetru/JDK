package ru.personacode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Map extends JPanel {
    private boolean isGameOver;
    private boolean isInitialized;
    private boolean whoMove = true;
    private int mode = 0;

    private int panelWidth;
    private int panelHeight;
    private int cellWidth;
    private int cellHeight;
    private int square;

    private static final Random RANDOM = new Random();
    private final int HUMAN_DOT = 1;
    private final int AI_DOT = 2;
    private final int EMPTY_DOT = 0;

    private char[][] field;
    private final int DOT_PADDING = 5;

    private int gameOverType;
    private static final int STATE_DRAW = 0;
    private static final int STATE_WIN_HUMAN = 1;
    private static final int STATE_WIN_AI = 2;

    private static final String MSG_DRAW = "Ничья!";
    private String MSG_WIN_HUMAN = "Победил игрок!";
    private String MSG_WIN_AI = "Победил компьютер!";


    public Map() {
        setVisible(false);
        isInitialized = false;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                clickUpdate(e);
            }
        });
    }

    private void printPanel() {
        for (int j = 0; j < square; j++) {
            for (int i = 0; i < square; i++) {
                if (field[i][j] == HUMAN_DOT) System.out.print("X");
                if (field[i][j] == AI_DOT) System.out.print("0");
                if (field[i][j] == EMPTY_DOT) System.out.print("-");

                System.out.print(" ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    private void clickUpdate(MouseEvent e) {
        if (!isInitialized || isGameOver) return;

        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;

        if (!isValidCell(cellX, cellY) || !isEmptyCell(cellX, cellY)) return;

        if (mode == 1) {
            if (whoMove)
                field[cellX][cellY] = HUMAN_DOT;
            else
                field[cellX][cellY] = AI_DOT;
        } else
            field[cellX][cellY] = HUMAN_DOT;

        this.whoMove = !this.whoMove;

        if (checkEndGame(HUMAN_DOT, STATE_WIN_HUMAN))
            return;

        if (!whoMove && mode == 0)
            aiTurn();
        repaint();

        if (checkEndGame(AI_DOT, STATE_WIN_AI))
            return;
        repaint();
    }

    public void startNewGame(int mode, int square, boolean firstMove) {
        System.out.printf("Mode: %d, square: %d, firstMove [Human = true | PC = false]: %b\n", mode, square, firstMove);
        this.mode = mode;
        if (mode == 1) {
            this.MSG_WIN_HUMAN = "Победил Игрок-1!";
            this.MSG_WIN_AI = "Победил Игрок-2!";
        }
        initMap(firstMove);
        isInitialized = true;
        isGameOver = false;
        if (!firstMove && mode == 0) aiTurn();
    }

    private void initMap(boolean whoMove) {
        this.whoMove = whoMove;
        field = new char[square][square];
        for (int i = 0; i < square; i++) {
            for (int j = 0; j < square; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < square && y >= 0 && y < square;
    }

    private boolean isEmptyCell(int x, int y) {
        return field[x][y] == EMPTY_DOT;
    }

    private void aiTurn() {
        int x, y;
        do {
            x = RANDOM.nextInt(square);
            y = RANDOM.nextInt(square);
        } while (!isEmptyCell(x, y));
        field[x][y] = AI_DOT;
        this.whoMove = true;
        this.repaint();
    }

    private boolean isMapFull() {
        for (int i = 0; i < square; i++) {
            for (int j = 0; j < square; j++) {
                if (field[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;
    }

    private boolean checkWin(int c) {
        boolean result = false;
        for (int x = 0; x < square; x++) {
            result = true;
            for (int y = 0; y < square; y++) {
                if (field[x][y] != c) {
                    result = false;
                    break;
                }
            }
            if (result) return true;
        }

        for (int y = 0; y < square; y++) {
            result = true;
            for (int x = 0; x < square; x++) {
                if (field[x][y] != c) {
                    result = false;
                    break;
                }
            }
            if (result) return true;
        }

        for (int y = 0; y < square; y++) {
            result = true;
            if (field[y][y] != c) {
                result = false;
                break;
            }
        }
        if (result) return true;

        for (int y = 0; y < square; y++) {
            result = true;
            if (field[y][square - y - 1] != c) {
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean checkEndGame(int dot, int gameOverType) {
        printPanel();
        if (checkWin(dot)) {
            this.gameOverType = gameOverType;
            this.isGameOver = true;
            this.repaint();
            return true;
        }
        if (isMapFull()) {
            this.gameOverType = STATE_DRAW;
            this.isGameOver = true;
            this.repaint();
            return true;
        }
        return false;
    }

    private void showMessageGameOver(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 170, getWidth(), 100);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Times new roman", Font.BOLD, 48));
        switch (gameOverType) {
            case STATE_DRAW:
                g.drawString(MSG_DRAW, 180, getHeight() / 2);
                break;
            case STATE_WIN_AI:
                g.drawString(MSG_WIN_AI, 55, getHeight() / 2);
                break;
            case STATE_WIN_HUMAN:
                g.drawString(MSG_WIN_HUMAN, 90, getHeight() / 2);
                break;
            default:
                throw new RuntimeException("Unexpected gameOver state: " + gameOverType);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {
        if (!isInitialized) return;

        panelHeight = this.getHeight();
        panelWidth = this.getWidth();
        cellHeight = panelHeight / square;
        cellWidth = panelWidth / square;

        g.setColor(Color.BLACK);
        for (int h = 1; h < square; h++) {
            int y = h * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }
        g.setColor(Color.BLACK);
        for (int w = 1; w < square; w++) {
            int x = w * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }

        for (int y = 0; y < square; y++) {
            for (int x = 0; x < square; x++) {
                if (field[x][y] == EMPTY_DOT) continue;

                if (field[x][y] == HUMAN_DOT) {
                    g.setColor(Color.BLUE);
                    g.fillOval(x * cellWidth + DOT_PADDING, y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeight - DOT_PADDING * 2);
                } else if (field[x][y] == AI_DOT) {
                    g.setColor(Color.RED);
                    g.fillOval(x * cellWidth + DOT_PADDING, y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeight - DOT_PADDING * 2);
                } else {
                    throw new RuntimeException("Unexpected value" + field[x][y] + " in cell: x=" + x + "y =" + y);
                }
            }
        }
        this.repaint();

        if (isGameOver) showMessageGameOver(g);
    }

    public void setSquare(int square) {
        this.square = square;
    }
}
