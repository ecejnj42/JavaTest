//import java.util.Arrays;
//import edu.princeton.cs.algs4.Digraph;
//import edu.princeton.cs.algs4.Queue;
//import edu.princeton.cs.algs4.StdOut;
//
//public class BreadthDepthSearch {
//
//  private boolean[] marked;
//  private int[] edgeTo;
//  private int[]distTo;
//  private Digraph diGraph;
//
//  public BreadthDepthSearch(Digraph diGraph) {
//    this.diGraph = diGraph;
//    marked = new boolean[diGraph.V()];
//    edgeTo = new int[diGraph.V()];
//    distTo = new int[diGraph.V()];
//  }
//
//  private void bfs( int s)
//  {
//    Queue<Integer> q = new Queue<Integer>();
//    q.enqueue(s);
//    Arrays.fill( marked,  false);
//    
//    marked[s] = true;
//    distTo[s] = 0;
//    edgeTo[s] = -1;
//    while (!q.isEmpty())
//    {
//      int v = q.dequeue();
//      for (int w : diGraph.adj(v))
//      {
//        if (!marked[w])
//        {
//          q.enqueue(w);
//          marked[w] = true;
//          edgeTo[w] = v;
//          distTo[w] = distTo[v] +1;
//        } else {
//          StdOut.println( "Already visited "+ w + " from "+ edgeTo[w]);
//        }
//      }
//    }
//  }
//  
//}