package test2;

import test2.impl.ExecutorImpl;

/**
 * Test curiosly recurring template pattern
 * @author Jorge
 *
 */

public class Test2 {

  public static void main(String[] args) {
    Executor executor = new ExecutorImpl();
    
    Response<ReadResponse> responseRead = executor.execute(true);
    ReadResponse readResp = responseRead.getData();
    readResp.read();
    Response<CreateResponse> responseCreate = executor.execute(false);
    CreateResponse createResp = responseCreate.getData();
    createResp.create();

    // uhm..
    responseRead = executor.execute(false);
    readResp = responseRead.getData();
    readResp.read();
  }

}
