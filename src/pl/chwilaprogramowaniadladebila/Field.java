package pl.chwilaprogramowaniadladebila;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Field extends JButton {

    private final int width, height, i, j;

    private int minesAround = 0;

    private boolean isMine = false, clicked = false;

    public Field(int x, int y, int width, int height, int i, int j){
        this.i = i;
        this.j = j;
        this.width = width;
        this.height = height;

        //mouse
        MouseListener();

        setVisible(true);
        setOpaque(false);
        setBounds(x, y, width, height);
        setLayout(null);
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public int getMinesAround() {
        return minesAround;
    }

    public void setMinesAround(int minesAround) {
        this.minesAround = minesAround;
    }

    public void MouseListener(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    Minesweeper.board.checkField(i,j);
                    clicked = true;
                }
            }
        });
    }

    @Override public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //drawFields
        if (!clicked)
            g.setColor(new Color(211,211,211));
        else
            g.setColor(Color.lightGray);
        g.fillRect(0,0, width, height);
        g.setColor(Color.darkGray);
        g.drawRect(0,0, width-1, height-1);

        if (clicked){
            //showBombs
            if (isMine){
                g.setColor(Color.black);
                g.setFont(Minesweeper.courier);
                g.drawString("X", 12, 25);
            }
            else{
                g.setColor(Color.red);
                g.setFont(Minesweeper.courier);
                g.drawString(String.valueOf(minesAround), 12, 25);
            }
        }
    }
}
