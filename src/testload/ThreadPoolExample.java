package testload;
 
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
 
public class ThreadPoolExample 
{
  private static final int THS = 250;
    public static void main(String[] args) 
    {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(THS);
         
        for (int i = 1; i <= THS; i++) 
        {
            Task task = new Task("Task " + i);
            System.out.println("Created : " + task.getName());
 
            executor.execute(task);
        }
        executor.shutdown();
    }
}