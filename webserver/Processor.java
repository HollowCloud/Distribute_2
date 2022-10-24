package webserver;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;



public class Processor {
    
    private final Socket socket;
    private final HttpRequest request;
    

    public Processor(Socket socket, HttpRequest request) {
        this.socket = socket;
        this.request = request;
    }

    public void process() throws IOException, InterruptedException {
        System.out.println("Got request:");
        String some = request.getRequestLine();
        System.out.println(request.toString());
        
        System.out.flush();

        PrintWriter output = new PrintWriter(socket.getOutputStream());
        
        if("create".equals(some.substring(5, 11))){
            for(int i = 0; i < 5; i++){
                System.out.println("Number: " + i);
            }
            System.out.println();
            
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Know</title></head>");
            output.println("<body><h1>Yes, you are right</h1><br><h2>Just a test request</h2></body>");
            output.println("</html>");
            output.flush();
            output.close();
            
        }
        else if("compute".equals(some.substring(5, 12))){
            int size, i, j, temp;  
            int arr[] = new int[1000000];      
            Scanner scan = new Scanner(System.in); 
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
//        else if("file_created".equals(some.substring(5, 17))){
//            
//            File file = new File("C:\\Users\\asus\\Desktop\\My Education\\creation.txt");
//            boolean result;
//            try
//            {
//                result = file.createNewFile();  //creates a new file
//                if(result)      // test if successfully created a new file
//                {
//                    System.out.println("file created "+file.getCanonicalPath()); //returns the path string
//                }
//                else
//                {
//                    System.out.println("File already exist at location: "+file.getCanonicalPath());
//                }
//                
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();    //prints exception if any
//            }
//            
//            output.println("HTTP/1.1 200 OK");
//            output.println("Content-Type: text/html; charset=utf-8");
//            output.println();
//            output.println("<html>");
//            output.println("<head><title>Hello</title></head>");
//            output.println("<body><h1>File is Created</h1></body>");
//            output.println("</html>");
//            output.flush();
//            socket.close();
//            
//            
//        }
        else if("worker".equals(some.substring(5, 11))){
            
//            int n = 0;
            ThreadMain th = new ThreadMain();
            System.out.println("");
//            Scanner s = new Scanner(System.in);
//            n = s.nextInt();
//            th.somePool();
            
            System.out.println("");
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><h1>Workers are starting a riot.</h1></body>");
            output.println("</html>");
            output.flush();
            socket.close();
        }
        else if("queue".equals(some.substring(5, 10))){
            
//            ThreadMain th = new ThreadMain();
//            System.out.println("");
//            th.processQueue();

            QueueProcess p = new QueueProcess();
            p.exe();
            
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
        else{
            
            // We are returning a simple web page now.
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>Hello, world!</p> <h1>What if..?</h1></body>");
            output.println("</html>");
            output.flush();
            socket.close();
            
        }
    }
    
}
