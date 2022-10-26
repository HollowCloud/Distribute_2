package w_what;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Threader<T> extends Thread {
    private int id;
    public ThreaderQueue<T> queue;

    public Threader(int id, ThreaderQueue<T> queue) {
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            // Get request
            while (true) {
                T elem = queue.pop();
                Socket socket = (Socket) elem;
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                HttpRequest request = HttpRequest.parse(input);
                // Process request
                long startTime = System.nanoTime();
                Processor proc = new Processor(socket, request);
                proc.process();
                if(input == null) return;
                long endTime = System.nanoTime();
                System.out.println("##########################################");
                System.out.println("Thread " + id + " took: " + ((float) (endTime - startTime) / 1000000000) + " to process" + " and second with the element: " + elem);
                System.out.println("##########################################");
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



