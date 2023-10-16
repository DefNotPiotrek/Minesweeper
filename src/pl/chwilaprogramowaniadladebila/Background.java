package pl.chwilaprogramowaniadladebila;

import javax.swing.*;
import java.awt.*;

public class Background extends JPanel {

    public Background(int width, int height){

        setVisible(true);
        setBounds(0, 0, width, height);
        setLayout(null);
        setBackground(Color.darkGray);
    }
}
