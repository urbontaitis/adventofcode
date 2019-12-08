package io.github.urbontaitis.adventofcode.day6;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

class UniversalOrbitMap {

  private final List<String> initialData;

  UniversalOrbitMap(List<String> initialData) {
    this.initialData = initialData;
  }

  DefaultDirectedGraph<String, DefaultEdge> generateMap() {
    DefaultDirectedGraph<String, DefaultEdge> directedGraph =
        new DefaultDirectedGraph<>(DefaultEdge.class);

    for (String orbitalRelationship : initialData) {
      OrbitalRelationship r = new OrbitalRelationship(orbitalRelationship);
      directedGraph.addVertex(r.getSource());
      directedGraph.addVertex(r.getTarget());
      directedGraph.addEdge(r.getSource(), r.getTarget());
    }

    return directedGraph;
  }

  int totalNumberOfDirectAndIndirectOrbits() {
    int count = 0;
    DefaultDirectedGraph<String, DefaultEdge> orbitMap = generateMap();
    for (String vertex : orbitMap.vertexSet()) {
      count += orbitMap.outDegreeOf(vertex);
      //            count += orbitMap.edgesOf(vertex).stream().count();
    }

    return count;
  }

  // ([COM, B, C, D, E, F, G, H, I, J, K, L],
  // [
  // (COM,B), (B,C), (C,D), (D,E), (E,F), (B,G), (G,H),
  // (D,I), (E,J), (J,K), (K,L)
  // ])
  int countNumberOfDirectAndIndirectOrbitsBy(String orbit) {
    DefaultDirectedGraph<String, DefaultEdge> orbitMap = generateMap();

    return 0;
  }

  // Function to perform BFS traversal from the source vertex in the graph to
  // determine if the destination vertex is reachable from the source or not
  public static boolean isConnected(Graph graph, String src, String dest, boolean[] discovered) {
    // create a queue used to do BFS
    Queue<String> q = new ArrayDeque<>();

    // mark source vertex as discovered
    discovered[src] = true;

    // push source vertex into the queue
    q.add(src);

    // run till queue is not empty
    while (!q.isEmpty()) {
      // pop front node from queue and print it
      String v = q.poll();

      // if destination vertex is found
      if (v.equals(dest)) {
        return true;
      }

      // do for every edge (v -> u)
      for (int u : graph.adjList.get(v)) {
        if (!discovered[u]) {
          // mark it discovered and push it into queue
          discovered[u] = true;
          q.add(u);
        }
      }
    }

    return false;
  }
}
