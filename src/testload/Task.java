package testload;
 
import java.util.concurrent.TimeUnit;
 
public class Task implements Runnable {
    private String name;
 
    public Task(String name) {
        this.name = name;
    }
 
    public String getName() {
        return name;
    }
 
    public void run() {
        try {
            Long sleep = 100L;
            Long duration = 0L;
            while (true) {
              duration ++;
         
              System.out.println("Executing : " + name + " data "+ duration);
            TimeUnit.NANOSECONDS.sleep(sleep);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}