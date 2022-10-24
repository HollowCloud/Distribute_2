
package webserver;


import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QueueProcess {
  public void exe() throws InterruptedException{
    WorkQueue queue = new WorkQueue();

    int numWorkers = 2;
//    Worker[] workers = new Worker[numWorkers];
//      workers[0] = new Worker(0, queue);
//      workers[1] = new Worker(1, queue);
//      
//      workers[0].start();
//      workers[1].start();
//
//    for (int i = 0; i < 100; i++) {
//      queue.addWork(i);
//    }
//    
//    // Stopping consumers by sending them null values.
//    for (int i = 0; i < 100; i++) {
//        queue.addWork(null);
//    }
    
    Thread w1 = new Thread(new Workers(queue), "Worker 1");
    Thread w2 = new Thread(new Workers(queue), "Worker 2");
    Thread w3 = new Thread(new Workers(queue), "Worker 3");
    

    w1.start();
    w2.start();
    w3.start();
    
//    Thread.sleep(1000);
    
    for (int i = 0; i < 20; i++) {
      queue.addWork(i);
    }
    
    // Stopping consumers by sending them null values.
    for (int i = 0; i < 20; i++) {
        queue.addWork(null);
    }
    
  }
}

class WorkQueue {
  LinkedList<Object> queue = new LinkedList<Object>();
  
  int counter = 0;
  
  public synchronized void addWork(Object o) {
    queue.addLast(o);
    notify();
  }

  public synchronized Object getWork() throws InterruptedException {
    while (queue.isEmpty()) {
      wait();
    }
    return queue.removeFirst();
  }
  
  public void som(int id){
      this.counter = id;
  }
  
}

class Workers implements Runnable {
  WorkQueue q;
  int id;
  String name;
  
  Workers(WorkQueue q) {
//    this.id = id;
//    this.name = n;
    this.q = q;
  }

  @Override
  public void run() {
      
//    try {
//      while (true) {
//        Object x = q.getWork();
//
//        if (x == null) {
//          break;
//        }
//        Worker.printed(x);
//      }
//    } catch (InterruptedException e) {
//    }
      
      Workers.printed();

}

  
  
  public static synchronized void printed(){
    
    for(int i = 0; i < 20; i++){
        System.out.println(Thread.currentThread().getName() + " has processed " + i);
    }
  }
  
}