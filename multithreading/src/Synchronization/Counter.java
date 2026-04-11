package Synchronization;

public class Counter {

    private int count = 0;

    //An instance where increment function are calling and two or more thread are sharing single function
    //output is depending on relative timing of thread and giving unwanted answers
    //With the help of synchronized method only at a time one thread can call this function/method then another one
    //public synchronized void increment(){}  - critical section
    public void increment(){
        synchronized (this){ //block method
            count++;
        }
    }

    public int getCount(){
        return count;
    }

}
