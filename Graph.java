import java.util.LinkedList;

public class Graph {
    private final String vertexCountErrMsg = "Некорректное количество вершин";
    private int vertexCount;
    private int edgeCount;
    private LinkedList<Integer>[] adgLst;

    public Graph(int vertexCount) {
        if (vertexCount<=0)
            throw new IllegalArgumentException(vertexCountErrMsg);
        this.vertexCount=vertexCount;
        adgLst=new LinkedList[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            adgLst[i]=new LinkedList<>();
        }
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public LinkedList<Integer> getAdgeList(int vertex) {
        return (LinkedList<Integer>) adgLst[vertex].clone();
    }

    public void addEdge(int v1, int v2) {
        adgLst[v1].add(v2);
        edgeCount++;
        if (v1==v2) return;
        adgLst[v2].add(v1);
    }

}
