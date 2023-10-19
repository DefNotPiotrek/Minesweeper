package pl.chwilaprogramowaniadladebila;

import javax.swing.*;
import java.awt.*;

public class LooseAndWinScreen extends JPanel {

    private int width, height;

    public LooseAndWinScreen(int width, int height){
        this.width = width;
        this.height = height;
        setVisible(true);
        setBounds(0, 0, width, height);
        setLayout(null);
        setOpaque(false);
    }

    @Override public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.black);
        g.setFont(Minesweeper.looseScreen);
        if (Minesweeper.isGameLoose())
            g.drawString("LOOSE", width/2 - g.getFontMetrics().stringWidth("LOOSE")/2, height/2);
        else if (Minesweeper.isGameWin())
            g.drawString("WIN", width/2 - g.getFontMetrics().stringWidth("WIN")/2, height/2);
    }
}
