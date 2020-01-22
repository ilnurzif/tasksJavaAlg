import java.util.Arrays;

public class MainClass {
    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0,1);
        graph.addEdge(0,4);
        graph.addEdge(1,3);
        graph.addEdge(1,5);
        graph.addEdge(1,6);
        graph.addEdge(0,1);
        graph.addEdge(4,7);
        graph.addEdge(6,7);

        int startPoint=0;
        int finPoint=7;
//        MyDepthPath myDepthPath=new MyDepthPath(graph,startPoint);
//        System.out.println("" );
//        System.out.println(myDepthPath.pathTo(finPoint));

        BreadthPath breadthPath=new BreadthPath(graph,startPoint);
        System.out.println("" );
        System.out.println("Кратчайший путь от "+
                           startPoint+" до "+finPoint+
                           " "+breadthPath.pathTo(finPoint));
        System.out.println("Минимальная длина пути равна: "+breadthPath.getMinPathLength()+" ребер");

    }
}
