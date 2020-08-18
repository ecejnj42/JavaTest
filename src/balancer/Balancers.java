package balancer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import test2.CreateResponse;
import test2.Executor;
import test2.ReadResponse;
import test2.Response;
import test2.impl.ExecutorImpl;

public class Balancers {
  int max = 0;
  Map<String, List<String>> balancers = new HashMap<String, List<String>>();;
  
  public void addBalancer(String name, List<String> frontends) {
    balancers.put(name, frontends);
    if ( frontends.size() > max) {
      max = frontends.size();
    }
  }

  public List<BalancerPosition> generatePositions() {
    List<BalancerPosition> positions = new ArrayList<BalancerPosition>();

    for (int i=0; i<max; i++) {
      BalancerPosition position = new BalancerPosition();

      for (List<String> values: balancers.values()) {
        if ( i<= values.size()) {
          String value = values.get(i);
          position.addFrontend(value);
        }        
      }
      positions.add(position);
    }

    return positions;
  }

  @Override
  public String toString() {
    return "Balancers [balancers=" + balancers + "]";
  }



  public static void main(String[] args) {
    String balancer[][] = {
        {"b1","b2","b3"},
        {"b3","b2","b1"}
    };
    
    Balancers balancers = new Balancers();
    for (int i =0; i <balancer.length; ++i) {
      balancers.addBalancer("balancer-"+i, Arrays.asList(balancer[i]));
    }
    System.out.println(balancers.toString());
    System.out.println(balancers.generatePositions());
  }
}
