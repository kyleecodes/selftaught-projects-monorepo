// Name: Kylee Fields
// Project: CMSC 350 Project 4
// Date: 12/14/2021
// Description: Parenthesized List class

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

// Constructor class for parathesized list
public class ParenthesizedList implements DFSActions<Vertex> {

    Queue<String> res = new LinkedList<>();

    @Override
    public void processVertex(Vertex vertex) {
        res.add(vertex.toString());
    }

    @Override
    public void descendVertex(Vertex vertex) {
        res.add("(");
    }

    @Override
    public void ascendVertex(Vertex vertex) {
        res.add(")");
    }

    @Override
    public void cycleDetected() {
        res.add("*");
    }

    @Override
    public String toString() {

        StringBuilder ans = new StringBuilder();
        ans.append("( ");
        while (!res.isEmpty()) {
            String c = res.peek();
            res.remove();

            if (Objects.equals(c, "(")) {
                if (Objects.equals(res.peek(), ")")) {
                    res.remove();
                    continue;
                } else if (Objects.equals(res.peek(), "*")) {
                    ans.append(res.peek()).append(" ");
                    res.remove();
                    res.remove();
                    continue;
                }
            }
            ans.append(c).append(" ");
        }
        ans.append(")\n");
        return ans.toString();
    }
}