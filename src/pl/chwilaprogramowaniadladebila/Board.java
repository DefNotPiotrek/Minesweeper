package pl.chwilaprogramowaniadladebila;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Board extends JPanel {

    private final int width, height, howManyMines = 40 ,fieldsInRow = 16;

    private int puttedMines = 0, availableFlag = howManyMines;

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
                if (puttedMines >= howManyMines)
                    break;
                int number = new Random().nextInt(6);
                if (number==1 && !fields[i][j].isMine()){
                    fields[i][j].setMine(true);
                    puttedMines++;
                }

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

    public void checkField(int i, int j) {
        fields[i][j].setClicked(true);
        if (fields[i][j].isMine()) {
//            if (Minesweeper.isFirstShoot()) {
//                fields[i][j].setMine(false);
//                puttedMines--;
//                putMines();
//                refreshFields();
//            }
//            else
                loose();
        }
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

    public void win(){
        Minesweeper.setGameWin(true);
        for (int i = 0; i < fields.length; i++){
            for (int j = 0; j < fields[0].length; j++){
                fields[i][j].removeMouseListener(fields[i][j].mouseAdapter);
            }
        }
    }

    public void makeFreeFieldsArray(){
        for (int i = 1; i <= 8; i++){
            freeFields.add(i);
        }
    }

    public void refreshFields(){
        for (int i = 0; i < fields.length; i++){
            for (int j = 0; j < fields[0].length; j++){
                fields[i][j].repaint();
                fields[i][j].revalidate();
            }
        }
        checkWin();
    }

    public void checkWin(){
        boolean isItWin = true;
        for (int i = 0; i < fields.length; i++){
            for (int j = 0; j < fields[0].length; j++){
                if ((fields[i][j].isMine() && !fields[i][j].isFlaged()) || (!fields[i][j].isMine() && !fields[i][j].isClicked()))
                    isItWin = false;
            }
        }
        if (isItWin){
            win();
        }
    }

    public void reset(){
        for (int i = 0; i < fields.length; i++){
            for (int j = 0; j < fields[0].length; j++){
                if (fields[i][j].isMine()){
                    fields[i][j].setMine(false);
                    puttedMines = 0;
                }
                if (fields[i][j].isFlaged()){
                    fields[i][j].getRidOfTheFlag();
                    availableFlag = howManyMines;
                }

                if (fields[i][j].isClicked()){
                    fields[i][j].setClicked(false);
                }

                fields[i][j].setMinesAround(0);
                fields[i][j].addMouseListener(fields[i][j].mouseAdapter);
            }
        }
        Minesweeper.setGameLoose(false);
        Minesweeper.setGameWin(false);
        putMines();
        checkMinesAround();
        Minesweeper.timeAndScore.reload();
        refreshFields();

    }

    public int getAvailableFlag() {
        return availableFlag;
    }

    public void setAvailableFlag(int availableFlag) {
        this.availableFlag = availableFlag;
    }

    @Override public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}
