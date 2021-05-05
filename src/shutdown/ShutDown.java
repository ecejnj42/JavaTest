package shutdown;

import java.util.concurrent.atomic.AtomicBoolean;

public class ShutDown {

  public static void main(String[] args) {
    AtomicBoolean running = new AtomicBoolean(true);
    Thread printingHook = new Thread(() -> {System.out.println("In the middle of a shutdown"); running.set(false);});
    Thread thread = new Thread(){
      public void run(){
        while (running.get()) {
          System.out.println("Thread Running");
          try {
            Thread.sleep(10000);
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        System.out.println("Thread Existing");
      }
    };
    thread.start();
    Runtime.getRuntime().addShutdownHook(printingHook);
    System.out.println("Main thread exiting");
  }
}
