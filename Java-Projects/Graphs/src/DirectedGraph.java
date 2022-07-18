// Name: Kylee Fields
// Description: Directed Graph class

import java.util.ArrayList;

// Constructor class for DirectedGraph, extends Graph
// Created directed edge and adds it to the graph
public class DirectedGraph extends Graph<Vertex> {
    public void addEdge(String u, String v) {
        ArrayList<Vertex> list = adjacencyList.get(getVertex(u));

        if (list == null) {
            list = new ArrayList<>();
        }

        list.add(getVertex(v));

        adjacencyList.put(getVertex(u), list);
    }

    // Class method for getting vertex
    public Vertex getVertex(String u) {
        // if this node(String) showed up for the first time
        // map it to a correspond vertex
        if (!vertices.containsKey(u)) {
            vertices.put(u, new Vertex(u));
        }
        return vertices.get(u);
    }

}