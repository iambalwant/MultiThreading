package Locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private int balance = 100;

    public synchronized void withdrawSyn(int amount) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
        if(balance >= amount){
            System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
            Thread.sleep(10000);
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining amount " + amount);
        }else {
            System.out.println(Thread.currentThread().getName() + " insufficient balance");
        }
    }

    //Explicit lock

    private final Lock lock = new ReentrantLock();

    public void withdrawExp(int amount) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);

        try {
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
               if(balance >= amount){
                   try {
                       System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
                       Thread.sleep(10000);
                       balance -= amount;
                       System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining amount " + amount);
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }finally {
                       lock.unlock();
                   }
               }else{
                   System.out.println(Thread.currentThread().getName() + " insufficient balance");
            }
            }else {
                System.out.println(Thread.currentThread().getName() + " could not acquire the lock, will try later");

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        }
}
