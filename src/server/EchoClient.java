package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class EchoClient {
  private  /*static*/ SocketChannel client;
  private  /*static*/ ByteBuffer buffer;
  private  boolean connected= false; 
  //private static EchoClient instance;

//  public static EchoClient start() {
//      if (instance == null)
//          instance = new EchoClient();
//
//      return instance;
//  }

  //public static void stop() throws IOException {
  public void stop() throws IOException {
      client.close();
      buffer = null;
  }

  public/*private*/ EchoClient() {
    while (!connected) {
      try {
          client = SocketChannel.open(new InetSocketAddress("localhost", 5454));
          connected = true;
          buffer = ByteBuffer.allocate(256);
      } catch (IOException e) {
          e.printStackTrace();
          try {
            // Wait for server to start
            Thread.sleep(1000);
          } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
      }
    }
  }

  public String sendMessage(String msg) {
      buffer = ByteBuffer.wrap(msg.getBytes());
      String response = null;
      try {
          //System.out.println("buffer == "+  buffer);
          //System.out.println("client == "+  client);
          client.write(buffer);
          buffer.clear();
          client.read(buffer);
          response = new String(buffer.array()).trim();
          //System.out.println("response=" + response);
          buffer.clear();
      } catch (IOException e) {
          e.printStackTrace();
      }
      return response;

  }
}