import java.util.LinkedList;

public class BreadthPath extends GraphPathBase {
    private int pathLength=0;

    public BreadthPath(Graph g, int source) {
        super(g, source);
        bfs(g, source);
    }

    public int getMinPathLength() {
       return pathLength;
    }

    public void bfs(Graph g, int v) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(source);
        marked[source] = true;
        while (!queue.isEmpty()) {
            int vertex = queue.removeFirst();
            for (int w : g.getAdgeList(vertex)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = vertex;
                    queue.addLast(w);
                }
            }
        }
    }

    public LinkedList<Integer> pathTo(int dist) {
        if (!marked[dist])
            return null;
        pathLength=0;
        int vertex = dist;
        LinkedList<Integer> stack = new LinkedList<>();
        while (vertex != source) {
            stack.push(vertex);
            vertex = edgeTo[vertex];
            pathLength++;
        }
        return stack;
    }

}
