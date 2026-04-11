package Synchronization;
import java.lang.Thread;

public class MyThread extends Thread {

    @Override
    public void run() {
        for(int i =0; i < 1000; i++){
            counter.increment();
        }
    }

    private final Counter counter;

    public MyThread(Counter counter){
        this.counter = counter;
    }

}
