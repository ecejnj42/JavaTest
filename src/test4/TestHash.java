package test4;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestHash {
  
  public static void main(String[] args) {
    Map<String,Object> mapData = new HashMap<>();
    Set<String> set = new HashSet<>();
    final int TOTAL=100;
    
    for (int i=0;i<TOTAL;++i) {
      set.add("String"+i);
    }
    mapData.put("data", set);
    // DO several times to avoid initialziation, etc
    long mean = 0;
    int iters = TOTAL*TOTAL;
    for (int i = 0; i < iters; ++i) {
      Instant start = Instant.now();
      @SuppressWarnings("unchecked")
      Set<String> dataStrs = (Set<String>) mapData.get("data");
      dataStrs.contains("String"+i%TOTAL);
      Instant finish = Instant.now();
      long timeElapsed = Duration.between(start, finish).toNanos();
      mean +=timeElapsed;
      System.out.println("Duration in nanos " + timeElapsed);
    }
    System.out.println("Mean " + mean/iters);
  }
}
