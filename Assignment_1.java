import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Assignment_1 {

    static class Edge{

        int src;
        int dest;

        public Edge(int src , int dest){
            this.src = src;
            this.dest=dest;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[] ){ 

        for(int i =0; i<graph.length;i++){      
            graph [i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 3));
        graph[0].add(new Edge(0, 1));

        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 2));

        graph[2].add(new Edge(2, 1));
        graph[2].add(new Edge(2, 3));
        
        graph[3].add(new Edge(3, 0));
        graph[3].add(new Edge(3, 2));


    }

    public static void BFS(ArrayList<Edge> graph []){
        
        boolean visited[] = new boolean[graph.length];

        for (int i = 0; i <graph.length; i++) {
                BFSUtil(graph, i, visited);
        }
    }

    public static void BFSUtil(ArrayList<Edge> graph [] , int start , boolean visited[]){

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            
            int edge = q.poll();

            if (!visited[edge]) {

                visited[edge]=true;

                System.out.print(" "+edge);

                // push remainning

                for (int i = 0; i < graph[edge].size(); i++) {

                    Edge curr= graph[edge].get(i);

                    if (!visited[curr.dest]) {
                       q.add(curr.dest);
                    }
                    
                }
                
            }
        }
    }
        
    public static void DFS(ArrayList<Edge> graph []){
      
        boolean visited[] = new boolean[graph.length];

        for (int i = 0; i <graph.length; i++) {
                DFSUtil(graph, i, visited);
        }
    }

    public static void DFSUtil(ArrayList<Edge> graph [],int curr , boolean[] visited){
        if (visited[curr]) {
            return;
        }

        System.out.print(curr+" ");
        visited[curr]=true;

        // call for nabhors

        for (int i = 0; i < graph[curr].size(); i++) {

            Edge e = graph[curr].get(i);

            if (!visited[e.dest]) {
                
                DFSUtil(graph, e.dest, visited);
            }

        }
    }

    public static void main(String[] args) {

        ArrayList<Edge> graph [] = new ArrayList[4];
        createGraph(graph);
        BFS(graph);
        System.out.println();
        DFS(graph);

        
    }
}