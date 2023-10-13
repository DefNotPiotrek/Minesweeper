package pl.chwilaprogramowaniadladebila;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Minesweeper extends JFrame {

    private final int width = 600, height = 644, margin = 40;

    public static Font courier;

    public static Board board;

    public Minesweeper() {
        setTitle("2048");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setSize(width, height);
        setVisible(true);
        setFont();

        //Add Board
        add(board = new Board(width - margin*2, height - margin*3, margin));

        reload();
    }

    private void setFont(){
        try {
            courier = Font.createFont(Font.TRUETYPE_FONT, new File("res\\CourierPrimeSans.ttf")).deriveFont(150 * 0.2f);
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
