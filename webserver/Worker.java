package webserver;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Worker extends Thread{
    
    int threadNumber;
    
    public Worker(int threadNumber){
        this.threadNumber = threadNumber;
    }
    
    @Override 
    public void run(){
        int a[]={1,2,5,6,3,2, 22, 121, 199}; 
        
        System.out.println("Thread #" + threadNumber + " was created");
//        for(int i = 0; i < threadNumber; i++){
//            System.out.println("--------------------------------------------");
//            System.out.println("#" + threadNumber + " Thread was created! ");
//            System.out.println("--------------------------------------------");
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
    
}
