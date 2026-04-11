package Synchronization;

public class Main {

    public static void main(String[] args) {
       Counter counter = new Counter();
       MyThread t1 = new MyThread(counter);
       MyThread t2 = new MyThread(counter);
       t1.start();
       t2.start();
       try {
           t1.join();
           t2.join();
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }
        System.out.println(counter.getCount());
    }
}
