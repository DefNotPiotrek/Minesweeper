package pl.chwilaprogramowaniadladebila;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Minesweeper extends JFrame {

    private final int width = 650, height = 684, margin = 40;

    public static Font courier, looseScreen, timeAndScoreFont;

    public static Board board;
    public static LooseAndWinScreen looseAndWinScreen;
    public static TimeAndScore timeAndScore;
    public static RefreshButton refreshButton;

    private static boolean gameLoose = false, gameWin = false, firstShoot = true;

    public Minesweeper() {
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setSize(width, height);
        setVisible(true);
        setFont();

        //Add Ending Screen
        add(looseAndWinScreen = new LooseAndWinScreen(width, height));

        //Add Board
        add(board = new Board(width - margin*2, height - margin*3, margin));

        //Add Refresh Button
        add(refreshButton = new RefreshButton(margin, margin, width/2-margin));

        //Add Time and score board
        add(timeAndScore = new TimeAndScore(width - margin*2, margin*2, margin));

        //Add Background
        add(new Background(width,height));

        reload();
    }

    public static boolean isGameLoose() {
        return gameLoose;
    }

    public static void setGameLoose(boolean gameLoose) {
        Minesweeper.gameLoose = gameLoose;
    }

    public static boolean isGameWin() {
        return gameWin;
    }

    public static void setGameWin(boolean gameWin) {
        Minesweeper.gameWin = gameWin;
    }

    public static boolean isFirstShoot() {
        return firstShoot;
    }

    public static void setFirstShoot(boolean firstShoot) {
        Minesweeper.firstShoot = firstShoot;
    }

    private void setFont(){
        try {
            courier = Font.createFont(Font.TRUETYPE_FONT, new File("res\\CourierPrimeSans.ttf")).deriveFont(150 * 0.2f);
            looseScreen = Font.createFont(Font.TRUETYPE_FONT, new File("res\\CourierPrimeSans.ttf")).deriveFont(150 * 1.0f);
            timeAndScoreFont = Font.createFont(Font.TRUETYPE_FONT, new File("res\\CourierPrimeSans.ttf")).deriveFont(150 * 0.3f);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void reload(){
        repaint();
        revalidate();
    }
}
