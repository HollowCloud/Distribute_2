package w_what;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Threader<T> extends Thread {
    private int id;
    private Socket socket;
    public ThreaderQueue<BufferedReader> queue;
    public ThreaderQueue<T> queue1;

    public Threader(ThreaderQueue<BufferedReader> queue,int id, Socket socket) {
        this.queue = queue;
        this.id = id;
        this.socket = socket;
    }
    
    public Threader(int id, Socket socket, ThreaderQueue<T> queue1) {
        this.id = id;
        this.socket = socket;
        this.queue1 = queue1;
    }

    @Override
    public void run() {
        try {
            // Get request
            while (true) {
//                BufferedReader input = queue.pop();
                T elem = queue1.pop();
//                if(input == null) return;
//                HttpRequest request = HttpRequest.parse(input);
//                // Process request
                // Stop consuming if null is received.
                if (elem == null) {
                    return;
                }
                long startTime = System.nanoTime();
//                Processor proc = new Processor(socket, request);
//                proc.process();
                long endTime = System.nanoTime();
                System.out.println("#################################################");
                System.out.println("Thread " + id + " took about: " + ((float) (endTime - startTime) / 1000000000) + " seconds with ");
                System.out.println("");
                System.out.println("ELEMENT: " + elem);
                System.out.println("");
                
                System.out.println(queue1);
                
                System.out.println("#################################################");
                socket.close();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



