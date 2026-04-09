package threadMethods;

public class PriorityMethod extends Thread{


    public PriorityMethod(String name){
        super(name);
    }

    @Override
    public void run() {
        for(int i = 0;i < 5;i++){
            for(int j = 0;j< 10000000;j++){

            }
            System.out.println(Thread.currentThread().getName() + " --priority " + Thread.currentThread().getPriority() + " --count " + i);
            try {
               Thread.sleep(100);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
            PriorityMethod t1 = new PriorityMethod("Balwant");
            PriorityMethod t2 = new PriorityMethod("aditya");
            PriorityMethod t3 = new PriorityMethod("vishwah");

            t1.setPriority(Thread.MIN_PRIORITY);
            t2.setPriority(Thread.MAX_PRIORITY);
            t3.setPriority(Thread.NORM_PRIORITY);
            t1.start();
            t2.start();
            t3.start();
    }

}
