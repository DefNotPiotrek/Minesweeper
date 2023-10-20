package pl.chwilaprogramowaniadladebila;

public class TimeThread extends Thread implements Runnable{

    private int start;
    private volatile boolean stop = false;

    public TimeThread(){
        start();
    }

    @Override
    public void run(){
        start = (int) System.currentTimeMillis();
        while (!Minesweeper.isGameWin() || !Minesweeper.isGameLoose()){
            if (!stop){
                int actualTime = Minesweeper.timeAndScore.getTime();
                int end = (int) System.currentTimeMillis();

                Minesweeper.timeAndScore.setTime((end - start) / 1000);

                if (actualTime != Minesweeper.timeAndScore.getTime()){
                    Minesweeper.timeAndScore.reload();
                }
            }
        }
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void resetTime(){
        start = (int) System.currentTimeMillis();
    }
}
