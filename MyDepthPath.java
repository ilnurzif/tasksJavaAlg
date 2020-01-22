import java.util.LinkedList;

public class MyDepthPath extends GraphPathBase  {
    public MyDepthPath(Graph g, int source) {
        super(g, source);
        dfs(g, source);
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;

        for (int w : g.getAdgeList(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }



}
