package w_what;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Processor of HTTP request.
 */
public class Processor {
    private final Socket socket;
    private final HttpRequest request;

    public Processor(Socket socket, HttpRequest request) {
        this.socket = socket;
        this.request = request;
    }

    public void process() throws Exception {
        // Print request that we received.
        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.flush();

        // To send response back to the client.
        PrintWriter output = new PrintWriter(socket.getOutputStream());

        // We are returning a simple web page now.
        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<html>");
        output.println("<head><title>Some Page</title></head>");

        String data = request.getRequestLine();
        String data1 = data.substring(5, 11);
        String data4 = data.substring(5, 9);
        String data5 = data.substring(5, 10);

        
        if (data.equals("GET / HTTP/1.1")) {
            //Thread.sleep(5000);
            Processor proc = new Processor(socket, request);
            proc.firstPage();
        }
        if (data1.equals("create")) {
            String name_of_the_file1 = data.substring(12, (data.length() - 9));
            System.out.println(name_of_the_file1);
            createFile(name_of_the_file1);
            Processor proc1 = new Processor(socket, request);
            proc1.create();
        }

        if (data4.equals("exec")) {
            Processor proc3 = new Processor(socket, request);
            proc3.compute();
        }
        
        if (data5.equals("queue")) {
//            ProcessQueue p = new ProcessQueue();
//            p.exe();
            
            System.out.println("");
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><h1>The queue is too huge....</h1></body>");
            output.println("</html>");
            output.flush();
            socket.close();
        }
        
        output.println("<body><p></p></body>");
        output.println("</html>");
        output.flush();

        socket.close();
    }
    public void firstPage() throws Exception {
        // Print request that we received.
        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.flush();

        // To send response back to the client.
        PrintWriter output = new PrintWriter(socket.getOutputStream());
        // We are returning a simple web page now.
        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<html>");
        output.println("<head><title>Some Page</title></head>");
        output.println("<body><p>Welcoming You.</p></body>");
        //output.println("<p>This is check number" + i + "</p>");
        output.println("</html>");
        output.flush();

        socket.close();
    }
    public void create() throws IOException {
        // Print request that we received.
        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.flush();

        // To send response back to the client.
        PrintWriter output = new PrintWriter(socket.getOutputStream());

        // We are returning a simple web page now.
        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<html>");
        output.println("<head><title>Some Page</title></head>");
        output.println("<body><p>The file was created!</p></body>");
        output.println("</html>");
        output.flush();

        socket.close();
    }

    public void compute() throws IOException {
        PrintWriter output = new PrintWriter(socket.getOutputStream());

        int size, i, j, temp;  
            int arr[] = new int[1000000];      
            long startTime = System.currentTimeMillis();

            System.out.print("Enter Array Size : ");  
            size = 100000;
            System.out.println("SIze: " + size);
            System.out.println("");
            System.out.print("ENTERED THE NUMBERS");
            System.out.println("");
            
            for(i=0; i<size; i++)  
            {  
                arr[i] = i;  
            }  

            System.out.print("Sorting Array using Selection Sort Technique..\n");  
            for(i=0; i<size; i++)  
            {  
                for(j=i+1; j<size; j++)  
                {  
                    if(arr[i] > arr[j])  
                    {  
                        temp = arr[i];  
                        arr[i] = arr[j];  
                        arr[j] = temp;  
                    }  
                }  
            }  

            System.out.print("Now the Array after Sorting is :\n");  
            for(i=0; i<size; i++)  
            {  
                System.out.print(arr[i]+ "  ");  
            }  
            
            long endTime = System.currentTimeMillis();
            System.out.println("");
            System.out.println("###################################################");
            System.out.println("That took " + (endTime - startTime) + " milliseconds");
            System.out.println("###################################################");
            
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><h1>COMPUTED</h1></body>");
            output.println("</html>");
            output.flush();
            socket.close();
    }

    private static void createFile(String name_of_the_file1) {
        try {
            File myObj = new File(name_of_the_file1);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
