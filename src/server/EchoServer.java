package server;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class EchoServer implements Closeable {
  private final Selector selector;
  private final ServerSocketChannel serverSocket;
  private final AtomicBoolean running = new AtomicBoolean(true);
  private final ExecutorService executor;

  private static final int BUFFER_SIZE = 256;
  private static final String POISON_PILL = "POISON_PILL";
  private static final long TIMEOUT = 100;// millis
  public final static int THREADS = 2;

  public EchoServer(String addr, int port) throws IOException {
    selector = Selector.open();
    serverSocket = ServerSocketChannel.open();

    executor = Executors.newFixedThreadPool(THREADS);

    serverSocket.bind(new InetSocketAddress(addr, port));
    serverSocket.configureBlocking(false);
    serverSocket.register(selector, SelectionKey.OP_ACCEPT);
  }


  /**
   * Execute the main loop
   */
  public void execute() {

    while (running.get()) {
      try {
        if (selector.select(TIMEOUT) == 0) {
          continue;
        }

        Set<SelectionKey> selectedKeys = selector.selectedKeys();
        Iterator<SelectionKey> iter = selectedKeys.iterator();
        while (iter.hasNext()) {

          SelectionKey key = iter.next();

          if (key.isAcceptable()) {
            register(selector, serverSocket);
          }

          if (key.isReadable()) {
            answerWithEcho(key);
          }
          iter.remove();
        }
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        continue;
      }
    }

  }



  public static void main(String[] args) throws IOException {



    EchoServer server = new EchoServer("localhost", 5454);
    Runtime.getRuntime().addShutdownHook(new Thread(server.shutdowThread()));
    server.execute();
    server.close();

  }

  private Runnable shutdowThread() {
    Runnable runnable = () -> {
      System.out.println("Shutting down ...");
      running.set(false);
    };
    return runnable;
  }


  private void answerWithEcho(SelectionKey key) throws IOException {
    executor.execute( () -> {
      System.out.println("Shutting down ...");
      try {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        // assuming this SocketChannel is thread safe
        SocketChannel client = (SocketChannel) key.channel();
        client.read(buffer);
        if (new String(buffer.array()).trim().equals(POISON_PILL)) {
          client.close();
          System.out.println("Not accepting client messages anymore");
        }

        buffer.flip();
        client.write(buffer);
        buffer.clear();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    });
    
  }

  private void register(Selector selector, ServerSocketChannel serverSocket) throws IOException {

    SocketChannel client = serverSocket.accept();
    client.configureBlocking(false);
    client.register(selector, SelectionKey.OP_READ);
  }

  public static Process start() throws IOException, InterruptedException {
    String javaHome = System.getProperty("java.home");
    String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
    String classpath = System.getProperty("java.class.path");
    String className = EchoServer.class.getCanonicalName();

    ProcessBuilder builder = new ProcessBuilder(javaBin, "-cp", classpath, className);

    return builder.start();
  }

  @Override
  public void close() throws IOException {

    executor.shutdownNow();

  }
}
