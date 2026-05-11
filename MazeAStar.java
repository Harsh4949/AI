import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MazeAStar {

    // 0 = open cell, 1 = wall
    static int[][] maze = {
            {0, 0, 0, 0, 0},
            {1, 1, 0, 1, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0}
    };

    static int startRow = 0;
    static int startCol = 0;
    static int goalRow = 4;
    static int goalCol = 4;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Node {
        int row;
        int col;
        int g;
        int h;
        int f;
        Node parent;

        public Node(int row, int col, int g, Node parent) {
            this.row = row;
            this.col = col;
            this.g = g;
            this.parent = parent;
            this.h = Math.abs(row - goalRow) + Math.abs(col - goalCol);
            this.f = g + h;
        }
    }

    public static void solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.f));
        boolean[][] visited = new boolean[maze.length][maze[0].length];

        pq.add(new Node(startRow, startCol, 0, null));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (visited[current.row][current.col]) {
                continue;
            }

            visited[current.row][current.col] = true;

            if (current.row == goalRow && current.col == goalCol) {
                printPath(current);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = current.row + dx[i];
                int nc = current.col + dy[i];

                if (isValid(nr, nc) && !visited[nr][nc]) {
                    pq.add(new Node(nr, nc, current.g + 1, current));
                }
            }
        }

        System.out.println("No path found.");
    }

    public static boolean isValid(int row, int col) {
        return row >= 0 && row < maze.length && col >= 0 && col < maze[0].length && maze[row][col] == 0;
    }

    public static void printPath(Node node) {
        List<int[]> path = new ArrayList<>();

        while (node != null) {
            path.add(new int[]{node.row, node.col});
            node = node.parent;
        }

        Collections.reverse(path);

        System.out.println("Path found using A*:");
        for (int[] cell : path) {
            System.out.println("(" + cell[0] + ", " + cell[1] + ")");
        }

        System.out.println("Total steps: " + (path.size() - 1));
    }

    public static void main(String[] args) {
        System.out.println("A* Algorithm for Maze Pathfinding");
        System.out.println("Start: (" + startRow + ", " + startCol + ")");
        System.out.println("Goal: (" + goalRow + ", " + goalCol + ")\n");

        solve();
    }
}

/*
Detailed Explanation:

This program solves a maze using the A* search algorithm.

1. Maze representation:
    - 0 means open cell.
    - 1 means wall or blocked cell.
    - The agent starts from (0, 0) and tries to reach (4, 4).

2. Node class:
    - row and col store the current position.
    - g is the actual cost from the start cell to the current cell.
    - h is the heuristic value.
    - f = g + h.
    - parent is used to store the previous node so the final path can be printed.

3. Heuristic used:
    - Manhattan distance is used:
      h = |row - goalRow| + |col - goalCol|
    - This works well for maze movement because we move only up, down, left, and right.

4. PriorityQueue:
    - The priority queue always chooses the node with the smallest f value.
    - This is the main idea of A*.

5. Visited array:
    - It prevents revisiting the same cell again and again.
    - This saves time and avoids loops.

6. Movement:
    - The program checks four directions:
      up, down, left, and right.
    - If a new cell is inside the maze and not blocked, it is added to the queue.

7. Path printing:
    - When the goal is reached, the parent links are followed backwards.
    - The path is reversed and printed from start to goal.

8. Final result:
    - The algorithm finds the shortest path in the maze using A*.
    - It is faster than plain BFS in many cases because it uses a heuristic to guide the search.

Simple viva answer:
"A* is an informed search algorithm that uses g + h to find the best path. In this maze program, g is the path cost and h is the Manhattan distance to the goal."
*/
