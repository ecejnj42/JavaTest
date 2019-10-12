package test1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Test static initializer
 * 
 * @author Jorge
 *
 */
public class Test1 {
  
  public static void createTestA() {
    new TestA();
  }
public static void createTestB( ) {
  new TestB();
  }
  
  private static void createClass(Class<?> test) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    //Class<Test1> test1 = null;
    System.out.println("Class "+ test.getSimpleName());
    Method m = Test1.class.getDeclaredMethod("create"+test.getSimpleName());
    m.invoke(null, null);
    
  }
  


  public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
    System.out.println("Hi there");
    createClass(Class.forName("test1.TestA"));
    createClass(Class.forName("test1.TestB"));
  }

}
