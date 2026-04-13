package ThreadCommunication;


class SharedResource{

    private int data;
    private boolean hasData;


    public synchronized void produce(int value){
        while (hasData){
            try{
                wait();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        data = value;
        hasData = true;
        System.out.println("Produced: " + value);
        notify();
    }

    public synchronized int consumer(){
           while (!hasData){
               try {
                   wait();
               } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
               }
           }
           hasData = false;
           System.out.println("consumer: " + data);
           notify();
           return data;
    }
}

class Consumer implements Runnable{

    private SharedResource sharedResource;

    public Consumer(SharedResource sharedResource){
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {

        for(int i = 0; i < 10; i++){
           int value = sharedResource.consumer();
        }

    }
}


class Producer implements Runnable{

    private SharedResource sharedResource;

    public Producer(SharedResource sharedResource){
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {

        for(int i = 0; i < 10; i++){
           sharedResource.produce(i);
        }

    }
}


public class Main {
    public static void main(String[] args) {
            SharedResource resource = new SharedResource();
            Thread producerThread = new Thread(new Producer(resource));
            Thread consumerThread = new Thread(new Consumer(resource));

            producerThread.start();
            consumerThread.start();

    }


}
