import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Pathfinding {//ChatGPT helped a lot with this for how I would do it and the libraries
    Pathfinding(){}

    private static boolean isValidMove(int[][] grid, boolean[][] visited, int x, int y) {
        return x >= 0 && x < grid.length &&
               y >= 0 && y < grid[0].length &&
               grid[x][y] == 1 && !visited[x][y];
    }

    public static ArrayList<String> bfs(int[][] grid, int[] start, int[] goal) {
        int rows = grid.length;
        int cols = grid[0].length;
        ArrayList<String> directionsList = new ArrayList<>();
    
        // Directions: Right, Down, Left, Up
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        String[] directionNames = {"right", "down", "left", "up"};
    
        // Queue to store positions and distance (x, y)
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start[0], start[1]});
    
        boolean[][] visited = new boolean[rows][cols];
        visited[start[0]][start[1]] = true;
    
        int[][][] parent = new int[rows][cols][2];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                parent[i][j] = new int[]{-1, -1}; // Initialize parent to -1 (unvisited)
            }
        }
    
        boolean found = false;
    
        while (!queue.isEmpty() && !found) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
    
            // Check if we reached the goal
            if (x == goal[0] && y == goal[1]) {
                found = true;
                break;
            }
    
            for (int i = 0; i < directions.length; i++) {
                int nx = x + directions[i][0];
                int ny = y + directions[i][1];
                System.out.println(x);
    
                if (isValidMove(grid, visited, nx, ny)) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    parent[nx][ny] = new int[]{x, y}; // Record the parent
                }
            }
        }
    
        // Reconstruct the path from goal to start
        if (found) {
            int cx = goal[0];
            int cy = goal[1];
            while (!(cx == start[0] && cy == start[1])) {
                int px = parent[cx][cy][0];
                int py = parent[cx][cy][1];
    
                // Find the direction
                for (int i = 0; i < directions.length; i++) {
                    if (px + directions[i][0] == cx && py + directions[i][1] == cy) {
                        directionsList.add(0, directionNames[i]); // Add to the start of the list
                        break;
                    }
                }
                cx = px;
                cy = py;
            }
        }
    
        return directionsList;
    }
}