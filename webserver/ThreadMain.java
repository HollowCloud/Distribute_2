package webserver;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadMain{
    
    public void somePool(){
        
        //FIrst task implementation
        int numOfThreads = 4;
        for (int i = 0; i < numOfThreads; i++) {
            Worker worker = new Worker(i);
            worker.start();
        }

//        ExecutorService ex = Executors.newFixedThreadPool(5);
//        
//        for(int i = 0; i < 20; i++){
//            ex.execute(new Worker(1));
//            ex.execute(new Worker(2));
//            ex.execute(new Worker(3));
//            ex.execute(new Worker(4));
//            ex.execute(new Worker(5));
//            
//        }
//        
//        ex.shutdown();
        
    }
    
    public synchronized void processQueue(){
        
        //CRETING A THREAD POOL
        ExecutorService ex = Executors.newFixedThreadPool(5);
       
        
        //QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ
        
        int numOfThreads = 5;
        int numOfItems = 30;
        
        ThreadSafeQueue<String> queue = new ThreadSafeQueue<>();

        // Starting consumer threads.
        for (int i = 0; i < numOfThreads; i++) {
            Consumer<String> cons = new Consumer<>(i, queue);
//            ex.execute(new Consumer("A"));
//            ex.execute(new Consumer("B"));
//            ex.execute(new Consumer("C")); 

            cons.start();
        }

        
        
        // Adding items in the queue for consumers.
        for (int i = 0; i < numOfItems; i++) {
            queue.add("inside " + i);
        }

        // Stopping consumers by sending them null values.
        for (int i = 0; i < numOfThreads; i++) {
            queue.add(null);
        }
        
        
        //QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ
        
        ex.shutdown();
        
        
    }
    
}