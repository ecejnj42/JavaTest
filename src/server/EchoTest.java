package server;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class EchoTest {

  Process server;
  AtomicBoolean run = new AtomicBoolean(true);
  //EchoClient client;
public final static int THREADS = 100;
public final static int THREAD_WAIT_MILLIS = 100;


  public void setupServer() throws IOException, InterruptedException {
      server = EchoServer.start();
  }

  public void teardownServer() throws IOException {
      server.destroy();
  }
  
  public boolean isRunning() {
    return run.get();
  }
  
//  public void setupClient() throws IOException, InterruptedException {
//    client = EchoClient.start();
//}



//  public void sendMsgAndGetResponse(String id) {
//      String resp1 = client.sendMessage("hello" + id);
//      String resp2 = client.sendMessage("world" + id);
//      System.out.println("hello == "+  resp1);
//      System.out.println("world ==" + resp2);
//  }

  public void sendMsg(EchoClient client, String id, int msgId) {
    //System.out.println("ID on send == "+  id);    
    //client.start();
    String resp1 = client.sendMessage("hello" + id+ "-"+ msgId);
    System.out.println("Response = "+  resp1);
  }
  
  Runnable createTask(String id) {
    Runnable runnableTask = () -> {
      try {
        EchoClient client = new EchoClient();
        //System.out.println("ID == "+  id);
        int i=0;
        while (isRunning()) {
          sendMsg(client, id, i++);
          try {
            Thread.sleep(THREAD_WAIT_MILLIS);
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        client.stop();
        //  TimeUnit.MILLISECONDS.sleep(300);
//      } catch (InterruptedException e) {
      } catch (IOException e) {
          e.printStackTrace();
      }
  };
    return runnableTask;
  }

  public Runnable shutdowThread() {
    Runnable runnable = () -> {
      System.out.println("Shutting down ...");
      try {
        teardownServer();
        run.set(false);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
  };
    return runnable;
  }
  

  public static void main(String[] args) throws IOException, InterruptedException {
    EchoTest echoTest = new EchoTest();
    
    ExecutorService executor = Executors.newFixedThreadPool(THREADS);
    
    Runtime.getRuntime().addShutdownHook( new Thread(echoTest.shutdowThread()));
    
    echoTest.setupServer();
    for (int i=0; i<THREADS; i++) {
      executor.execute(echoTest.createTask(Integer.toString(i)));
    }
    
    while (echoTest.isRunning()) {
      Thread.sleep(200);
    }
    
    // Clean up
    //executor.awaitTermination(5, TimeUnit.SECONDS);
    //echoTest.givenServerClient_whenServerEchosMessage_thenCorrect();
    // echoTest.teardownServer();
    executor.shutdown();
    try {
        if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
          executor.shutdownNow();
        } 
    } catch (InterruptedException e) {
      executor.shutdownNow();
    }
    
  }

}
