package webserver;



public class Consumer<T> extends Thread {
    private final int id;
    private final ThreadSafeQueue<T> queue;

    public Consumer(int id, ThreadSafeQueue<T> queue) {
        this.id = id;
        this.queue = queue;
    }

//    String name;
//    public Consumer(String a){
//        this.name = a;
//    }
    
//    @Override
//    public synchronized void run() {
//        try {
//            for(int i = 0; i < 10; i++){
//                System.out.println("Thread " + name + " has processed " + i);
//            }
//            
//            Thread.sleep(1000);
//        }
//        catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
//    }
    
    
    @Override
    public void run() {
        try {
            while (true) {
                // Wait for new element.
                T elem = queue.pop();

                // Stop consuming if null is received.
                if (elem == null) {
                    return;
                }

                // Process element.
                System.out.println("Thread #" + id + ": get item: " + elem);
            }
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
}