package pl.chwilaprogramowaniadladebila;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Board extends JPanel {

    private final int width, height, howManyMines = 40 ,fieldsInRow = 16;

    private int puttedMines = 0;

    private Field[][] fields;

    private ArrayList<Integer> freeFields = new ArrayList<>();

    public Board(int width, int height, int margin){
        this.width = width;
        this.height = height;

        launchGame();


        setVisible(true);
        setOpaque(false);
        setBounds(margin, margin*2, width, height);
        setLayout(null);
    }

    public void launchGame(){
        //make and add fields
        fields = makeAndAddFields();
        makeFreeFieldsArray();
        putMines();
        checkMinesAround();
    }

    public Field[][] makeAndAddFields(){
        Field[][] fields = new Field[fieldsInRow][fieldsInRow];

        for (int i = 0; i < fields.length; i++){
            for (int j = 0; j < fields[0].length; j++){
                fields[i][j] = new Field(width/fieldsInRow*j,height/fieldsInRow*i, width/fieldsInRow, height/fieldsInRow, i, j);
                add(fields[i][j]);
            }
        }
        return fields;
    }

    public void putMines(){
        for (int i = 0; i < fields.length; i++){
            for (int j = 0; j < fields[0].length; j++){
                int number = new Random().nextInt(6);
                if (number==1){
                    fields[i][j].setMine(true);
                    puttedMines++;
                }
                if (puttedMines >= howManyMines)
                    break;
            }
        }
        if (puttedMines < howManyMines)
            putMines();
    }

    public void checkMinesAround(){
        for (int i = 0; i < fields.length; i++){
            for (int j = 0; j < fields[0].length; j++){
                if (!fields[i][j].isMine()){
                    if (i-1 >= 0){
                        //X-0-0
                        //0-P-0
                        //0-0-0
                        if (j-1 >= 0){
                            if (fields[i-1][j-1].isMine()){
                                fields[i][j].setMinesAround(fields[i][j].getMinesAround() + 1);
                            }
                        }

                        //0-X-0
                        //0-P-0
                        //0-0-0
                        if (fields[i-1][j].isMine()){
                            fields[i][j].setMinesAround(fields[i][j].getMinesAround() + 1);
                        }

                        //0-0-X
                        //0-P-0
                        //0-0-0
                        if (j+1 <= fields.length-1) {
                            if (fields[i - 1][j + 1].isMine()) {
                                fields[i][j].setMinesAround(fields[i][j].getMinesAround() + 1);
                            }
                        }
                    }

                    //0-0-0
                    //X-P-0
                    //0-0-0
                    if (j-1 >= 0){
                        if (fields[i][j-1].isMine()){
                            fields[i][j].setMinesAround(fields[i][j].getMinesAround() + 1);
                        }
                    }

                    //0-0-0
                    //0-P-X
                    //0-0-0
                    if (j+1 <= fields.length-1) {
                        if (fields[i][j + 1].isMine()) {
                            fields[i][j].setMinesAround(fields[i][j].getMinesAround() + 1);
                        }
                    }

                    if (i+1 <= fields.length-1){

                        //0-0-0
                        //0-P-0
                        //X-0-0
                        if (j-1 >= 0){
                            if (fields[i+1][j-1].isMine()){
                                fields[i][j].setMinesAround(fields[i][j].getMinesAround() + 1);
                            }
                        }

                        //0-0-0
                        //0-P-0
                        //0-X-0
                        if (fields[i+1][j].isMine()){
                            fields[i][j].setMinesAround(fields[i][j].getMinesAround() + 1);
                        }

                        //0-0-0
                        //0-P-0
                        //0-0-X
                        if (j+1 <= fields.length-1) {
                            if (fields[i + 1][j + 1].isMine()) {
                                fields[i][j].setMinesAround(fields[i][j].getMinesAround() + 1);
                            }
                        }
                    }

                }
            }
        }
    }

    public void checkField(int i, int j){
        fields[i][j].setClicked(true);
        if (fields[i][j].isMine())
            loose();
        if (!fields[i][j].isMine()) {
            if (i - 1 >= 0) {
                //X-0-0
                //0-P-0
                //0-0-0
                if (j - 1 >= 0) {
                    if (freeFields.contains(fields[i - 1][j - 1].getMinesAround()) && !fields[i - 1][j - 1].isClicked() && fields[i][j].getMinesAround() == 0) {
                        fields[i - 1][j - 1].setClicked(true);
                    }
                }

                //0-X-0
                //0-P-0
                //0-0-0
                if (freeFields.contains(fields[i - 1][j].getMinesAround()) && !fields[i - 1][j].isClicked() && fields[i][j].getMinesAround() == 0) {
                    fields[i - 1][j].setClicked(true);
                }
                else if (fields[i][j].getMinesAround() == 0 && fields[i - 1][j].getMinesAround() == 0 && !fields[i - 1][j].isClicked()) {
                    checkField(i - 1, j);
                }

                //0-0-X
                //0-P-0
                //0-0-0
                if (j + 1 <= fields.length - 1) {
                    if (freeFields.contains(fields[i - 1][j + 1].getMinesAround()) && !fields[i - 1][j + 1].isClicked() && fields[i][j].getMinesAround() == 0) {
                        fields[i - 1][j + 1].setClicked(true);
                    }
                }
            }

            //0-0-0
            //X-P-0
            //0-0-0
            if (j - 1 >= 0) {
                if (freeFields.contains(fields[i][j - 1].getMinesAround()) && !fields[i][j - 1].isClicked() && fields[i][j].getMinesAround() == 0) {
                    fields[i][j - 1].setClicked(true);
                }
                else if (fields[i][j].getMinesAround() == 0 && fields[i][j - 1].getMinesAround() == 0 && !fields[i][j - 1].isClicked()) {
                    checkField(i, j - 1);
                }

            }

            //0-0-0
            //0-P-X
            //0-0-0
            if (j + 1 <= fields.length - 1) {
                if (freeFields.contains(fields[i][j + 1].getMinesAround()) && !fields[i][j + 1].isClicked() && fields[i][j].getMinesAround() == 0) {
                    fields[i][j + 1].setClicked(true);
                }
                else if (fields[i][j].getMinesAround() == 0 && fields[i][j + 1].getMinesAround() == 0 && !fields[i][j + 1].isClicked()) {
                    checkField(i, j + 1);
                }

            }

            if (i + 1 <= fields.length - 1) {

                //0-0-0
                //0-P-0
                //X-0-0
                if (j - 1 >= 0) {
                    if (freeFields.contains(fields[i + 1][j - 1].getMinesAround()) && !fields[i + 1][j - 1].isClicked() && fields[i][j].getMinesAround() == 0) {
                        fields[i + 1][j - 1].setClicked(true);
                    }
                }

                //0-0-0
                //0-P-0
                //0-X-0
                if (freeFields.contains(fields[i + 1][j].getMinesAround()) && !fields[i + 1][j].isClicked() && fields[i][j].getMinesAround() == 0) {
                    fields[i + 1][j].setClicked(true);
                }
                else if (fields[i][j].getMinesAround() == 0 && fields[i + 1][j].getMinesAround() == 0 && !fields[i + 1][j].isClicked()) {
                    checkField(i + 1, j);
                }


                //0-0-0
                //0-P-0
                //0-0-X
                if (j + 1 <= fields.length - 1) {
                    if (freeFields.contains(fields[i + 1][j + 1].getMinesAround()) && !fields[i + 1][j + 1].isClicked() && fields[i][j].getMinesAround() == 0) {
                        fields[i + 1][j + 1].setClicked(true);
                    }
                }
            }
        }

        refreshFields();
    }

    public void loose(){
        for (int i = 0; i < fields.length; i++){
            for (int j = 0; j < fields[0].length; j++){
                if (fields[i][j].isMine()){
                    fields[i][j].setClicked(true);
                }
                fields[i][j].removeMouseListener(fields[i][j].mouseAdapter);
            }
        }

        Minesweeper.setGameLoose(true);
    }

    public void makeFreeFieldsArray(){
        freeFields.add(1);
        freeFields.add(2);
        freeFields.add(3);
    }

    public void refreshFields(){
        for (int i = 0; i < fields.length; i++){
            for (int j = 0; j < fields[0].length; j++){
                fields[i][j].repaint();
                fields[i][j].revalidate();
            }
        }
    }

    @Override public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}
