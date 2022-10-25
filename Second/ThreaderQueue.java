package w_what;

//import java.util.LinkedList;
//import java.util.Queue;
//
//public class ThreaderQueue<T> {
//    private final Queue<T> queue;
//
//    public ThreaderQueue() {
//        this.queue = new LinkedList<>();
//    }
//
//    public synchronized void add(T element) {
//        queue.add(element);
//        notify();
//    }
//
//    public synchronized T pop() throws InterruptedException {
//        while (queue.isEmpty()) {
//            wait();
//        }
//        return this.queue.poll();
//    }
//
//    public synchronized int size() {
//        return queue.size();
//    }
//}


import java.util.LinkedList;
import java.util.Queue;

public class ThreaderQueue<T> {
    
    private final Queue<T> queue;

    public ThreaderQueue() {
        this.queue = new LinkedList<>();
    }

    // Put element in the queue.
    public synchronized void add(T elem) {
        queue.add(elem);
        notify();
    }

    // Wait for new element in the queue and return it.
    public synchronized T pop() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        return this.queue.poll();
    }

    public synchronized int size() {
        return queue.size();
    }
    
}