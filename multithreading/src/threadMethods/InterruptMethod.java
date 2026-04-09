package threadMethods;

public class InterruptMethod extends Thread {

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        InterruptMethod t1 = new InterruptMethod();
        t1.start();
        t1.interrupt(); //Stop what ever you are doing

    }


}
