package FairUnfairLock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    //Unfair lock - In this lock and unlock is random
    //fair lock - In this what ever get lock first get unlock first FIFO
    private Lock lock = new ReentrantLock(true);

    public void accessResource(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " acquired the lock.");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            System.out.println(Thread.currentThread().getName() + " acquired the unlock.");
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        Main main = new Main();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
               main.accessResource();
            }
        };

        Thread t1 = new Thread(runnable,"T1");
        Thread t2 = new Thread(runnable,"T2");
        Thread t3 = new Thread(runnable,"T3");
        t1.start();
        t2.start();
        t3.start();
    }

}

