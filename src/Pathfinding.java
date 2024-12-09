import java.lang.classfile.instruction.ThrowInstruction;
import java.util.LinkedList;
import java.util.Queue;

public class Pathfinding {//ChatGPT helped a lot with this for how I would do it and the libraries
    private int[] startPos;
    private int[][] board;
    Pathfinding(int[] startPos, int[][] grid){
        this.startPos = startPos;
        this.board = grid;
    }

    private static boolean isValidMove(int[][] grid, boolean[][] visited, int x, int y) {
        return x >= 0 && x < grid.length &&
               y >= 0 && y < grid[0].length &&
               grid[x][y] == 1 && !visited[x][y];
    }

    public static int bfs(int[][] grid, int[] start, int[] goal) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Directions: Right, Down, Left, Up
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Queue to store positions and distance (x, y, distance)
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start[0], start[1], 0});

        // Visited array
        boolean[][] visited = new boolean[rows][cols];
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int dist = current[2];

            // Check if goal is reached
            if (x == goal[0] && y == goal[1]) {
                return dist;
            }

        
            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (isValidMove(grid, visited, nx, ny)) {
                    queue.add(new int[]{nx, ny, dist + 1});
                    visited[nx][ny] = true;
                }
            }
        }

        return -1; 
    }
}
