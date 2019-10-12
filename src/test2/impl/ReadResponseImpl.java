package test2.impl;

import test2.ReadResponse;

public class  ReadResponseImpl implements ReadResponse {

  @Override
  public Integer read() {
  System.out.println("Read");
  return 7;
  }

  @Override
  public ReadResponse getData() {
    // TODO Auto-generated method stub
    return this;
  }

}
