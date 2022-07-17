// Name: Kylee Fields
// Project: CMSC 350 Project 4
// Date: 12/14/2021
// Description: DFSActions class, performs actions on graph data structures

// DFSActions constructor interface class
public interface DFSActions<V> {
    public void processVertex(V vertex);

    public void descendVertex(V vertex);

    public void ascendVertex(V vertex);

    public void cycleDetected();
}