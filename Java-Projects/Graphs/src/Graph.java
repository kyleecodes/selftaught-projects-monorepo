// Name: Kylee Fields
// Description: Graph data structure class

import java.util.*;

// Constructor class for Graph
public class Graph<V> {
    // Starting node of the graph
    public V startingNode = null;

    // Map all the vertex name (String) to a corresponding Vertex
    Map<String, V> vertices = new HashMap<>();

    Map<V, ArrayList<V>> adjacencyList = new HashMap<>();

    Set<V> visited = new HashSet<>();

    ParenthesizedList hierarchy = new ParenthesizedList();
    Hierarchy parenthesizedList = new Hierarchy();

    boolean cycle;

    Set<V> discovered = new HashSet<>();

    // Class method for initializing DFS
    public void depthFirstSearch() {
        cycle = false;
        dfs(startingNode);
    }

    // Class method for searching list in Depth First Order
    private void dfs(V node) {
        if (discovered.contains(node)) {
            cycle = true;

            hierarchy.cycleDetected();
            parenthesizedList.cycleDetected();
            return;
        }

        hierarchy.processVertex((Vertex) node);
        parenthesizedList.processVertex((Vertex) node);
        hierarchy.descendVertex((Vertex) node);
        parenthesizedList.descendVertex((Vertex) node);
        discovered.add(node);
        visited.add(node);

        ArrayList<V> list = adjacencyList.get(node);
        if (list != null) {
            for (V u : list)
                dfs(u);
        }

        hierarchy.ascendVertex((Vertex) node);
        parenthesizedList.ascendVertex((Vertex) node);
        discovered.remove(node);
    }

    private V getVertex(String u) {
        return vertices.get(u);
    }

    // Class method for printing nodes/classes
    public void displayUnreachableClasses(){
        for (Map.Entry<V, ArrayList<V>> entry : adjacencyList.entrySet()) {
            if(entry.getValue().size()>0){
                if(!visited.contains(entry.getKey())){
                    System.out.println(entry.getKey() + " is unreachable");
                    visited.add(entry.getKey());
                }

                for (V vertex : entry.getValue()){

                    if(!visited.contains(vertex)){
                        System.out.println(vertex + " is unreachable");
                        visited.add(vertex);
                    }
                }
            }
        }
    }
}