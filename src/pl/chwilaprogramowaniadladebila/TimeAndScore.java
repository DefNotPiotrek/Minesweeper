package pl.chwilaprogramowaniadladebila;

import javax.swing.*;
import java.awt.*;

public class TimeAndScore extends JPanel {

    private int width, height, margin;

    private TimeThread timeThread;

    private int time = 0;

    public TimeAndScore(int width, int height, int margin){
        this.width = width;
        this.height = height;
        this.margin = margin;
        setVisible(true);
        setBounds(margin, margin/2, width, height);
        setLayout(null);
        setOpaque(false);
    }

    public void countTime(){
        if (timeThread == null)
        timeThread = new TimeThread();
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public TimeThread getTimeThread() {
        return timeThread;
    }

    public void reload(){
        repaint();
        revalidate();
    }

    @Override public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.red);
        g.setFont(Minesweeper.timeAndScoreFont);
        g.drawString(String.valueOf(time), 0, margin);
        g.drawString(String.valueOf(Minesweeper.board.getAvailableFlag()), width/10*9, margin);
    }
}
