package test2.impl;

import test2.Executor;
import test2.Response;

public class ExecutorImpl implements Executor {



  @SuppressWarnings("unchecked")
  @Override
  //public <T extends Response<T>> Response<T> execute(boolean read){
  public  Response<?> execute(boolean read) {
    if ( read) {
      System.out.println("Creating for read");
      return new ReadResponseImpl();
    }
    System.out.println("Creating for write");
    return new CreateResponseImpl();
  }



//  @Override
//  public <T extends Response> T execute(boolean read) {
//    if ( read) {
//      System.out.println("Creating for read");
//      return (T) new ReadResponseImpl();
//    }
//    System.out.println("Creating for write");
//    return (T) new CreateResponseImpl();
//  }

//  @Override
//  public Response<?> execute(boolean read) {
//    if ( read) {
//      return new ReadResponseImpl();
//    }
//    return new CreateResponseImpl();
//  }

}
