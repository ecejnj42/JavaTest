package test2.impl;

import test2.CreateResponse;

public class CreateResponseImpl implements CreateResponse {




  @Override
  public void create() {
    System.out.println("Created");
    
  }

  @Override
  public CreateResponse getData() {
    return this;
  }

}
