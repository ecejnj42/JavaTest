package test2;

@SuppressWarnings("rawtypes")
public interface  Response<T extends Response> {
  T getData();

}
