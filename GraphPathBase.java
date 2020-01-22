import java.util.LinkedList;

public class GraphPathBase {
    protected boolean[] marked;
    protected int[] edgeTo;
    protected int source;

    public GraphPathBase(Graph g, int source) {
        this.source = source;
        marked = new boolean[g.getVertexCount()];
        edgeTo = new int[g.getVertexCount()];
    }

    public boolean hasPathTo(int dist) {
        return marked[dist];
    }

    public LinkedList<Integer> pathTo(int dist) {
        if (!marked[dist])
            return null;
        int vertex = dist;
        LinkedList<Integer> stack = new LinkedList<>();
        while (vertex != source) {
            stack.push(vertex);
            vertex = edgeTo[vertex];
        }
        return stack;
    }
}
