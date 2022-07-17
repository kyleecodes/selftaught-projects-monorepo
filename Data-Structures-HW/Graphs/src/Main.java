// Name: Kylee Fields
// Project: CMSC 350 Project 4
// Date: 12/14/2021

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Main constructor class
public class Main {

    static DirectedGraph graph = new DirectedGraph();

    public static void main(String[] args) {

        new Main().readGraph();

        // Starting Depth First Search Utility to complete the DFS
        graph.depthFirstSearch();

        // Display Parenthesized List after processing the vertices
        System.out.println(graph.parenthesizedList.toString());

        // Display Hierarchy after processing the vertices
        System.out.println(graph.hierarchy.toString());

        // Display all the nodes that remained unreachable in the searching process
        graph.displayUnreachableClasses();
    }

    // Class method for reading graph via JFileChooser and building directed graph
    public void readGraph() {
        JFileChooser choice = new JFileChooser(new File("."));
        int option = choice.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                Scanner input = new Scanner(choice.getSelectedFile());
                while (input.hasNextLine()) {
                    String edgeString = input.nextLine();
                    String[] edge = edgeString.split(" ");
                    // DFS starts from this node
                    if (graph.startingNode == null)
                        graph.startingNode = graph.getVertex(edge[0]);
                    for (int i = 1; i < edge.length; i++) {
                        graph.addEdge(edge[0], edge[i]);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}