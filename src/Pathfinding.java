import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Pathfinding {//ChatGPT helped a lot with this for how I would do it and the libraries
    Pathfinding(){}

    private static boolean isValidMove(int[][] grid, boolean[][] visited, int x, int y) {
        return x >= 0 && x < grid.length &&
               y >= 0 && y < grid[0].length &&
               grid[x][y] == 0 && !visited[x][y];
    }

    public static ArrayList<String> bfs(int[][] grid, int[] start, int[] goal) {
        int rows = grid.length+1;
        int cols = grid[0].length;
        ArrayList<int[]> possibleDirectionsInt = new ArrayList<>();
        ArrayList<String> directionsList = new ArrayList<>();

        // Directions: Right, Down, Left, Up
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Queue to store positions and distance (x, y, distance)-chatGPT helped me with this 
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start[0], start[1], 0});

        boolean[][] visited = new boolean[rows][cols];
        visited[start[0]][start[1]] = true;
        int distance = -1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int dist = current[2];

            if (x == goal[0] && y == goal[1]) {
                distance = dist;
            }

            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (isValidMove(grid, visited, nx, ny)) {
                    queue.add(new int[]{nx, ny, dist + 1});
                    visited[nx][ny] = true;
                    possibleDirectionsInt.add(dir);
                }
            }
        }

        for(int[] dir:possibleDirectionsInt){
            int[] right = directions[0];
            int[] down = directions[1];
            int[] left = directions[2];
            int[] up = directions[3];
            if(Arrays.equals(dir,right)){//check if right
                directionsList.add("right");
            }else if(Arrays.equals(dir,down)){
                directionsList.add("down");
            }else if(Arrays.equals(dir,left)){
                directionsList.add("left");
            }else if(Arrays.equals(dir,up)){
                directionsList.add("up");
            }
        }
        return directionsList;
    }
}