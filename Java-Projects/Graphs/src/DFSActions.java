// Name: Kylee Fields
// Description: DFSActions class, performs actions on graph data structures

// DFSActions constructor interface class
public interface DFSActions<V> {
    public void processVertex(V vertex);

    public void descendVertex(V vertex);

    public void ascendVertex(V vertex);

    public void cycleDetected();
}