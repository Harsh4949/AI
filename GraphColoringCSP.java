import java.util.ArrayList;
import java.util.Arrays;

public class GraphColoringCSP {

    // Graph Coloring using CSP
    // Technique: DFS + Backtracking + Branch and Bound

    static class Edge {
        int src;
        int dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    static int V = 5;
    static int[] bestColoring;
    static int bestColorCount = Integer.MAX_VALUE;

    // Create an undirected graph
    public static void createGraph(ArrayList<Edge> graph[]) {

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        // 0-1-2 forms a triangle, so at least 3 colors required
        addUndirectedEdge(graph, 0, 1);
        addUndirectedEdge(graph, 1, 2);
        addUndirectedEdge(graph, 2, 0);

        // extra edges
        addUndirectedEdge(graph, 1, 3);
        addUndirectedEdge(graph, 2, 4);
        addUndirectedEdge(graph, 3, 4);
    }

    public static void addUndirectedEdge(ArrayList<Edge> graph[], int u, int v) {
        graph[u].add(new Edge(u, v));
        graph[v].add(new Edge(v, u));
    }

    // Check if assigning "color" to vertex "vertex" is valid
    public static boolean isSafe(ArrayList<Edge> graph[], int[] colors, int vertex, int color) {

        for (int i = 0; i < graph[vertex].size(); i++) {
            Edge e = graph[vertex].get(i);
            int neighbor = e.dest;

            if (colors[neighbor] == color) {
                return false;
            }
        }

        return true;
    }

    // DFS + Backtracking + Branch and Bound
    public static void solve(ArrayList<Edge> graph[], int[] colors, int vertex, int usedColors) {

        // Branch and Bound: if already not better than best, stop exploring
        if (usedColors >= bestColorCount) {
            return;
        }

        // Base case: all vertices colored
        if (vertex == V) {
            bestColorCount = usedColors;
            bestColoring = colors.clone();
            return;
        }

        // Try existing colors first (good for minimizing color count)
        for (int color = 1; color <= usedColors; color++) {
            if (isSafe(graph, colors, vertex, color)) {
                colors[vertex] = color;
                solve(graph, colors, vertex + 1, usedColors);
                colors[vertex] = 0;  // backtrack
            }
        }

        // Try one new color
        int newColor = usedColors + 1;
        if (isSafe(graph, colors, vertex, newColor)) {
            colors[vertex] = newColor;
            solve(graph, colors, vertex + 1, newColor);
            colors[vertex] = 0;  // backtrack
        }
    }

    public static void printSolution() {
        System.out.println("Minimum colors needed: " + bestColorCount);
        System.out.println("Vertex -> Color");

        for (int i = 0; i < bestColoring.length; i++) {
            System.out.println(i + " -> " + bestColoring[i]);
        }
    }

    public static void main(String[] args) {

        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);

        int[] colors = new int[V];
        Arrays.fill(colors, 0);

        // Start DFS from vertex 0
        solve(graph, colors, 0, 0);

        System.out.println("=== Graph Coloring (CSP) ===");
        System.out.println("Technique: DFS + Backtracking + Branch and Bound\n");

        if (bestColoring != null) {
            printSolution();
        } else {
            System.out.println("No valid coloring found.");
        }
    }
}

/*
Detailed Explanation:

This program solves the Graph Coloring problem using the CSP approach.

1. What is Graph Coloring?
    - We must color all vertices of a graph.
    - Two adjacent vertices must not have the same color.
    - The main goal is to use the minimum number of colors.

2. Why is it a CSP problem?
    - CSP means Constraint Satisfaction Problem.
    - Here the constraint is: adjacent vertices cannot share the same color.
    - So we try different colors while checking the constraint.

3. Edge class:
    - It stores a connection between two vertices.
    - The graph is stored as an adjacency list.

4. createGraph() method:
    - It builds a sample undirected graph.
    - Undirected means if vertex u is connected to v, then v is also connected to u.

5. isSafe() method:
    - It checks whether a color can be assigned to a vertex.
    - It compares the chosen color with all adjacent vertices.
    - If any neighbor already has the same color, it returns false.

6. solve() method:
    - This is the main DFS + Backtracking method.
    - It tries to color one vertex at a time.
    - First it tries existing colors.
    - Then it tries one new color.

7. Backtracking:
    - If a color choice does not work for future vertices, the program removes that color.
    - Then it tries another possible color.

8. Branch and Bound:
    - bestColorCount stores the best minimum number of colors found so far.
    - If the current path already uses more or equal colors than the best solution, that branch is stopped early.
    - This saves time.

9. printSolution() method:
    - It prints the final color assigned to each vertex.
    - Example: vertex 0 -> color 1.

10. Final result:
     - The program finds a valid graph coloring.
     - It also tries to minimize the number of colors used.

Simple viva answer:
"Graph coloring is a CSP where we assign colors to vertices such that adjacent vertices
 do not share the same color. This program uses DFS, backtracking, and branch and bound to
  find a minimum-color solution."
*/
