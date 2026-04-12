package Locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {

    private final Lock lock = new ReentrantLock();


    //DeadLock situation
    //In this deadlock situation Reentrant work to solve this deadlock by using lock nesting
    //Like it will count and series of lock and look for unlock pair


    public void outerMethod(){
        lock.lock();
        try {
            System.out.println("Outer method");
            innerMethod();
        }finally {
            lock.unlock();
        }
    }

    public void innerMethod(){
        lock.lock();
        try {
            System.out.println("inner method");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantExample example = new ReentrantExample();
        example.outerMethod();
    }



}
