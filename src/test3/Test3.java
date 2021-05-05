package test3;

import java.util.function.Function;

//import com.titisan.ldap.LdapScraper;

public class Test3 {
  
  private static <T> void doHola(T a, Function<T,String> func){
    System.out.println(func.apply(a));
  }
  
//  private static String getInfo(Object a) {
//    return "Not Implemented";
//  }
  
//  private static String getInfo(TestA a) {
//    return a.getName();
//  }
//  
//  private static String getInfo(TestB a) {
//    return a.getName();
//  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    //LdapScraper ldap;
    doHola(new TestA(), TestA::getName);
    doHola(new TestB(),TestB::getName);
    //doHola(new TestC(), () -> {return "hola";});
    
    doHola(new TestC(), p ->  p.getClass().getCanonicalName());
  }

}
