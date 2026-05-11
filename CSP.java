import java.util.ArrayList;

public class CSP {

    // N-Queens Problem - CSP (Constraint Satisfaction Problem)
    // Using DFS + Backtracking to find all valid solutions
    // Time Complexity: O(N!) in worst case

    static int N = 4;
    static int solutionCount = 0;
    static ArrayList<String> allSolutions = new ArrayList<>();

    static class Queen {
        int row;
        int col;

        public Queen(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    // Initialize the board
    public static void createBoard(int board[][]) {
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = 0;  // 0 = empty cell
            }
        }
    }

    // Check if queen placement is safe (no conflicts with existing queens)
    public static boolean isSafe(int board[][], int row, int col) {
        
        // Check column above - same column in all rows above current row
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;  // Queen already exists in this column
            }
        }

        // Check left-upper diagonal
        int i = row;
        int j = col;
        while (i >= 0 && j >= 0) {
            if (board[i][j] == 1) {
                return false;  // Queen found in left diagonal
            }
            i--;
            j--;
        }

        // Check right-upper diagonal
        i = row;
        j = col;
        while (i >= 0 && j < N) {
            if (board[i][j] == 1) {
                return false;  // Queen found in right diagonal
            }
            i--;
            j++;
        }

        return true;  // Safe to place queen
    }

    // DFS + Backtracking - Try to place queens row by row
    public static void solveNQueens(int board[][], int row) {
        
        // Base Case: All queens placed successfully
        if (row == N) {
            solutionCount++;
            printSolution(board);
            return;
        }

        // Try placing queen in each column of current row
        for (int col = 0; col < N; col++) {
            
            // Check if current position is safe
            if (isSafe(board, row, col)) {
                
                // STEP 1: Place queen at this position
                board[row][col] = 1;

                // STEP 2: Recursively try to place queens in next row (DFS)
                solveNQueens(board, row + 1);

                // STEP 3: Backtrack - remove queen if no solution found in next rows
                board[row][col] = 0;
            }
        }
    }

    // Display the solution board
    public static void printSolution(int board[][]) {
        
        System.out.println("Solution " + solutionCount + ":");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        
        int board[][] = new int[N][N];
        createBoard(board);

        System.out.println("=== CSP: N-Queens Problem ===");
        System.out.println("Algorithm: DFS + Backtracking");
        System.out.println("Problem Size: " + N + " x " + N + "\n");

        // Solve N-Queens using DFS + Backtracking
        solveNQueens(board, 0);

        System.out.println("Total solutions found: " + solutionCount);
    }
}

/*
Detailed Explanation:

This program solves the N-Queens problem using the CSP approach.

1. What is the N-Queens problem?
    - We must place N queens on an N x N chessboard.
    - No two queens should attack each other.
    - That means no two queens can be in the same row, column, or diagonal.

2. Why is it a CSP problem?
    - CSP means Constraint Satisfaction Problem.
    - Here the variable is the position of each queen.
    - The constraint is that queens must not attack each other.

3. createBoard() method:
    - It initializes the board with 0.
    - 0 means empty cell.
    - 1 means queen is placed.

4. isSafe() method:
    - It checks whether a queen can be placed at a given row and column.
    - It checks the column above the current cell.
    - It checks the left diagonal.
    - It checks the right diagonal.
    - If no queen is found, the position is safe.

5. solveNQueens() method:
    - This is the main DFS + Backtracking function.
    - It tries to place one queen in each row.
    - For each row, it tries all columns.
    - If the position is safe, the queen is placed and the function moves to the next row.

6. Backtracking:
    - If a queen placement does not lead to a solution, the queen is removed.
    - Then the program tries another column.
    - This is called backtracking.

7. Base case:
    - When row == N, all queens are placed successfully.
    - Then the board is printed as one solution.

8. printSolution() method:
    - It prints the board.
    - 1 shows the queen position.
    - 0 shows empty cell.

9. Final result:
    - The program finds all valid ways to place queens.
    - It uses DFS to explore choices.
    - It uses backtracking to undo wrong choices.

Simple viva answer:
"N-Queens is a CSP where queens are placed on a chessboard so that no two queens attack each other. The program uses DFS and backtracking to try placements row by row and remove invalid choices."
*/
