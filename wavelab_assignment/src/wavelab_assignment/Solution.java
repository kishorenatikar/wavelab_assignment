package wavelab_assignment;

import java.util.*;

public class Solution {
	
    int currentTime = 0;
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> criticalConnections = new ArrayList<>();
        Map<Integer, List<Integer>> graph = buildGraph(n, connections);
        int[] discoveryTimes = new int[n];
        Arrays.fill(discoveryTimes, -1);
        dfs(0, -1, graph, discoveryTimes, criticalConnections);
        return criticalConnections;
    }
    
    private Map<Integer, List<Integer>> buildGraph(int n, List<List<Integer>> connections) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (List<Integer> connection : connections) {
            int from = connection.get(0);
            int to = connection.get(1);
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        return graph;
    }
    
    private void dfs(int node, int parent, Map<Integer, List<Integer>> graph, int[] discoveryTimes, List<List<Integer>> criticalConnections) {
        discoveryTimes[node] = currentTime++;
        
        for (int neighbor : graph.get(node)) {
            if (neighbor == parent) {
                continue;
            }
            
            if (discoveryTimes[neighbor] == -1) {
                dfs(neighbor, node, graph, discoveryTimes, criticalConnections);
                if (discoveryTimes[node] < discoveryTimes[neighbor]) {
                    criticalConnections.add(Arrays.asList(node, neighbor));
                }
            }
            
            discoveryTimes[node] = Math.min(discoveryTimes[node], discoveryTimes[neighbor]);
        }
    }
}
