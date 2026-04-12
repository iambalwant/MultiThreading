package Deadlock;


class Pen{
    public synchronized void writeWithPenAndPaper(Paper paper){

        System.out.println(Thread.currentThread().getName() + " is using pen " + this + " and trying");
        paper.finishWriting();
    }

    public synchronized void finishWriting(){
        System.out.println(Thread.currentThread().getName() + " finished using pen " + this);
    }
}

class Paper{

    public synchronized void writeWithPaperAndPen(Pen pen){
        System.out.println(Thread.currentThread().getName() + " is using pen " + this + " and trying");
        pen.finishWriting();
    }
    public synchronized void finishWriting(){
        System.out.println(Thread.currentThread().getName() + " finished using pen " + this);
    }

}

class Task1 implements Runnable{

    private Pen pen;
    private Paper paper;

    public Task1(Pen pen, Paper paper){
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
        pen.writeWithPenAndPaper(paper); //thread1 lock pen and tries to lock paper

    }
}

class Task2 implements Runnable{

    private Pen pen;
    private Paper paper;

    public Task2(Pen pen, Paper paper){
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
        //we did consistent ordering of locking and this will run when paper have lock of pen
        // deadlock resolve
        synchronized (pen){
            paper.writeWithPaperAndPen(pen); //thread2 lock paper and tries to lock pen
        }
//       paper.writeWithPaperAndPen(pen); //thread2 lock paper and tries to lock pen
    }
}


public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        Pen pen = new Pen();
        Paper paper = new Paper();

        Thread t1 = new Thread(new Task1(pen,paper), "Thread-1");
        Thread t2 = new Thread(new Task2(pen,paper), "Thread-2");

        t1.start();
        t2.start();

    }
}
