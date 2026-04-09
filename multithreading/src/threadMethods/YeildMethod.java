package threadMethods;

public class YeildMethod extends Thread{

    @Override
    public void run() {
       for(int i = 0;i < 5;i++){
           System.out.println(Thread.currentThread().getName() + " is running");
           Thread.yield();
       }
    }

    public static void main(String[] args) {

        YeildMethod t1 = new YeildMethod();
        YeildMethod t2 = new YeildMethod();

        t1.start();
        t2.start();

    }

}

