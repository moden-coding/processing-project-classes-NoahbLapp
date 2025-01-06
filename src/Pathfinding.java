import java.util.ArrayList;

public class Pathfinding {
    public static int[][] board;
    private static final int[] ROW_MOVES = {-1, 1, 0, 0}; // Up, Down, Left, Right
    private static final int[] COL_MOVES = {0, 0, -1, 1};
    private static final String[] DIRECTIONS = {"UP", "DOWN", "LEFT", "RIGHT"};
    Pathfinding(int[][] board){
        this.board = board;
    }

    public static ArrayList<String> solveMaze(int startRow, int startCol, int endRow, int endCol) {//I used chatgpt and the internet to help me with this maze solver pathfinder
        int[][] maze = board;
        int rows = maze.length;
        int cols = maze[0].length;
        boolean[][] visited = new boolean[rows][cols];
        ArrayList<String> path = new ArrayList<>();

        if (dfs(maze, startRow, startCol, endRow, endCol, visited, path)) {
            return path;
        }
        return null;
    }
    private static boolean dfs(int[][] maze, int row, int col, int endRow, int endCol, boolean[][] visited, ArrayList<String> directions){//chatgpt did most of this (but not all)
        if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length || visited[row][col] || maze[row][col] == 1) {
            return false;
        }

        visited[row][col] = true;

        // Check if we've reached the endpoint
        if (row == endRow && col == endCol) {
            return true;
        }

        // Explore all possible directions
        for (int i = 0; i < 4; i++) {
            int newRow = row + ROW_MOVES[i];
            int newCol = col + COL_MOVES[i];

            // Add direction to the list
            directions.add(DIRECTIONS[i]);

            if (dfs(maze, newRow, newCol, endRow, endCol, visited, directions)) {
                return true;
            }

            // Backtrack
            directions.remove(directions.size() - 1);
        }

        return false;
    }
}