// Name: Kylee Fields
// Description: Hierarchy class

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

// Constructor class for Hierarchy
public class Hierarchy implements DFSActions<Vertex> {

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

        int sz = 0;

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

            if(Objects.equals(c, "("))
                sz++;
            else if(Objects.equals(c, ")"))
                --sz;

            if("(".equals(c) || Objects.equals(c, ")"))
                continue;

            if(!"*".equals(c))
                ans.append("\n");

            ans.append("\t".repeat(Math.max(0, sz)));


            ans.append(c).append(" ");

        }
        ans.append("\n");
        return ans.toString();
    }

    private boolean isAlpha(String s) {
        return !Objects.equals(s, "(") && !Objects.equals(s, ")");
    }
}