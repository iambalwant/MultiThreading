package threadMethods;

public class SetDemonMethod extends Thread{

//DEMON thread are running parallel in background of main threads
//If all thread are completed JVM will end the demon thread too


    @Override
    public void run() {
        //even after infinity loop - DEMON thread are terminated
       while (true){
           System.out.println("Hello world");
       }
    }

    public static void main(String[] args) {
        SetDemonMethod t1 = new SetDemonMethod();
        t1.setDaemon(true);
        t1.start();
        System.out.println("Main done");
    }

}
