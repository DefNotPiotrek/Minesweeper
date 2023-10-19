package pl.chwilaprogramowaniadladebila;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RefreshButton extends JButton {

    private int width, height, margin;

    public MouseAdapter mouseAdapter;

    public RefreshButton(int width, int height, int margin){
        this.width = width;
        this.height = height;
        this.margin = margin;
        setVisible(true);
        setBounds(margin, width/2, width, height);
        setLayout(null);
        setOpaque(false);

        setBorderPainted(false);

        MouseListener();
    }

    public void reload(){
        repaint();
        revalidate();
    }

    public void MouseListener(){
        addMouseListener(mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    Minesweeper.board.reset();
                }
            }
        });
    }

    @Override public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.red);
        g.fillRect(0,0,width,height);
    }
}
