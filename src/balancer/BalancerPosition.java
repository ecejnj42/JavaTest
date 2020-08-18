package balancer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalancerPosition {

  final Map<String,Integer> frontends = new HashMap<String, Integer>();

  
  public void addFrontend(String frontend) {
    Integer count = frontends.get(frontend);
    frontends.put(frontend, (count == null) ? 1 : count + 1);
  }
  
  public int size() {
    return frontends.size();
  }

  @Override
  public String toString() {
    return "BalancerPosition [frontends=" + frontends + "]";
  }
  
  
}
