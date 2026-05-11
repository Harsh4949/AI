
import java.util.*;

public class EightPuzzleAStar {

    public static int[][] goal = {
                {1,2,3},
                {8,0,4},
                {7,6,5}
        };

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};


    static class Node {

        int state[][];
        int g,h,f;
        Node parent;


        public Node(int[][] state ,int g, Node parent){

            this.state=state;
            this.g=g;
            this.parent=parent;

            this.h= misplaced_heuristic(state);
            this.f=g+h;
        }


        private int misplaced_heuristic(int[][] state) {
           
            int misplaced=0;

            for (int i = 0; i < state.length; i++) {
                for (int j = 0; j < state[0].length; j++) {
                    if (state[i][j]!=0 && state[i][j] != goal[i][j]) {
                        misplaced++;
                    }
                }
            }

            return misplaced;
        }
    }
    

    public static void solve(int[][] start) {
        
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a->a.f));
        HashSet<String> visited = new HashSet<>();

        pq.add(new Node(start, 0, null));

        while (!pq.isEmpty()) {
            
            Node current = pq.poll();

            String key = Arrays.deepToString(current.state);

            if (visited.contains(key)) 
                    continue;
            
            visited.add(key);

            if (Arrays.deepEquals(current.state, goal)){
                
                printSolution(current);
                return ;
            }
                
            int zx=0,zy=0;   // used to get the empty (0) in the state

            for (int i = 0; i < start.length; i++) {
                for (int j = 0; j < start[0].length; j++) {
                    
                    if (current.state[i][j]==0) {
                        zx=i; zy=j;
                    }
                }
            }

            for (int i = 0; i < dx.length; i++) {
                
               int nx = zx + dx[i];
               int ny = zy + dy[i];
                
               if (nx>=0 && nx<start.length && ny>=0 && ny<start[0].length) {
                
                    int newState[][] = copy(current.state);

                    newState[zx][zy]=newState[nx][ny];
                    newState[nx][ny]=0;

                    pq.add(new Node(newState, current.g+1, current));
               }
            }
        }

        System.out.println("No solution Found...");
    }


    public static int[][] copy(int[][] state) {
        
        int [][]newState = new int [state.length][state[0].length];

        for (int i = 0; i < newState.length; i++) {
            for (int j = 0; j < newState[0].length; j++) {
                newState[i][j]=state[i][j];
            }
        }

        return newState;
    }


    public static void printSolution(EightPuzzleAStar.Node node) {
        
        List<int[][]> path = new ArrayList<>();

        while (node!=null) {
            path.add(node.state);
            node=node.parent;
        }

        Collections.reverse(path);

        System.out.println("Solution Steps:\n");

        for(int[][] state : path){

            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    System.out.print(state[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    
    public static void main(String[] args) {

        int[][] start = {
                {2,8,3},
                {1,6,4},
                {7,0,5}
        };

        solve(start);
    }
}