package pl.chwilaprogramowaniadladebila;

public class TimeThread extends Thread implements Runnable{

    public TimeThread(){
        start();
    }

    @Override
    public void run(){
        int start = (int) System.currentTimeMillis();
        while (!Minesweeper.isGameWin() || !Minesweeper.isGameLoose()){
            int actualTime = Minesweeper.timeAndScore.getTime();
            int end = (int) System.currentTimeMillis();

            Minesweeper.timeAndScore.setTime((end - start) / 1000);

            if (actualTime != Minesweeper.timeAndScore.getTime()){
                Minesweeper.timeAndScore.reload();
            }
        }

    }
}
