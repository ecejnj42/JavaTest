package test2;

public interface Executor {
  public <T extends Response<T>> Response<T> execute(boolean read);
  //public Response<?> execute(boolean read);
}
