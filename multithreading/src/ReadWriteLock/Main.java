package ReadWriteLock;


import java.util.SortedMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

    private int count = 0;
    //ReadWrite Lock allow multiple Thread to read resources concurrently as long as no thread writing to it
    //is insuring excusive access to write operation
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void increment() {
       writeLock.lock();
        try {
            count++;
        } finally {
        writeLock.unlock();
        }
    }

    public int getCount(){
        //multiple thread can acquire this lock
        //but this can be acquired only when no thread acquired write method lock
        readLock.lock();
        try {
            return count;
        } finally {
        readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
      Main main = new Main();

      Runnable readTask = new Runnable() {
          @Override
          public void run() {
           for(int i = 0;i < 10;i++){
               System.out.println(Thread.currentThread().getName() + " Read: " + main.getCount());
           }
          }
      };

      Runnable writeTask = new Runnable() {
          @Override
          public void run() {
              for(int i = 0; i < 10; i++){
                  main.increment();
                  System.out.println(Thread.currentThread().getName() + " increment: " + main.getCount());
              }
          }
      };

      Thread writeThread = new Thread(writeTask);
      Thread readThread1 = new Thread(readTask);
      Thread readThread2 = new Thread(readTask);

      writeThread.start();
      readThread1.start();
      readThread2.start();

      writeThread.join();
      readThread1.join();
      readThread2.join();

    }


}
