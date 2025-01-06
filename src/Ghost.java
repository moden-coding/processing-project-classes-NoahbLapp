import processing.core.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Ghost {
    private PApplet p;
    private float x, y;  // Ghost's position
    private float speed;  // Ghost's movement speed
    private int segDiv;
    private String direction;
    private PImage ghostImage;  // Ghost sprite
    private int[] startPosition;
    private boolean started;
    private int boardx;
    private int boardy;
    private boolean gotHeight;

    private int moveIntervals;

    private ArrayList<String> path;

    Ghost(PApplet p, float speed, int[] startPos, String imagePath) {
        this.p = p;
        this.speed = speed;
        this.segDiv = GD.segmentDivision;
        this.direction = "right";  // Default direction

        this.started = false;

        this.gotHeight = false;

        this.startPosition = startPos;
        this.boardx = this.startPosition[0];
        this.boardy = this.startPosition[1];

        // Initialize the ghost's position
        this.x = p.width / 2-(this.segDiv/2);
        this.y = p.height / 2-(this.segDiv*1.5f);

        // Load the ghost image
        this.ghostImage = p.loadImage(imagePath);

        this.moveIntervals = Math.round(this.segDiv/this.speed);
    }

    private void startMove(){//moves the ghost to a starting position
        reinitializePath();
        if(roundToNearest(y,segDiv)/segDiv != startPosition[1]){
            y -= speed ;
            if(roundToNearest(y, segDiv)/segDiv == startPosition[1]){
                y = roundToNearest(y, segDiv);
                gotHeight = true;
            }
        }else if(roundToNearest(x,segDiv)/segDiv != startPosition[0]){
            if(startPosition[0]<15){
                x-=speed;
            }else if(startPosition[0] > 15){
                x += speed ;
            }
            if(roundToNearest(x, segDiv)/segDiv == startPosition[0]){
                x = roundToNearest(x, segDiv);
                started = true;
            }
        }
    }

    public void displayGhost() {
        // Draw the ghost image
        if(!started){
            startMove();
        }else{
            move();
        }

        p.image(ghostImage, x, y, segDiv, segDiv);
    }

    private void move() {
        // Check if the path is empty or needs recalculation
        if (path == null || path.isEmpty()) {
            reinitializePath();
            if (path == null || path.isEmpty()) {
                // If path is still invalid, do nothing this frame
                return;
            }
        }
    
        // Continue following the path
        boardx = roundToNearest(x, segDiv) / segDiv;
        boardy = roundToNearest(y, segDiv) / segDiv;
        if(!(moveIntervals <= 0)){
            moveIntervals--;
        }else{
            this.direction = path.remove(0); // Safely remove direction
            x = roundToNearest(x, segDiv);
            y = roundToNearest(y, segDiv);
            moveIntervals = Math.round(this.segDiv/this.speed);
        }

        switch (direction) {
            case "RIGHT":
                x += speed;
                break;
            case "LEFT":
                x -= speed;
                break;
            case "UP":
                y -= speed;
                break;
            case "DOWN":
                y += speed;
                break;
        }
    }

    public boolean touchingPacman(){//checks if it hits pacman
        int pacCol = p.color(255,255,0);
        int behindCol = p.get((int)x-(segDiv/2-15),(int)y+(segDiv/2));
        int frontCol = p.get((int)x+(segDiv/2+15),(int)y+(segDiv/2));
        int topCol = p.get((int)x+(segDiv/2),(int)y-(segDiv/2-15));
        int bottCol = p.get((int)x+(segDiv/2),(int)y+(segDiv/2-15));
        if(behindCol == pacCol || frontCol == pacCol || topCol == pacCol || pacCol ==bottCol){
            return true;
        }else{
            return false;
        }
    }

    private void reinitializePath() {//finds a new path
        Pathfinding pathfind = new Pathfinding(GD.board);
        int[] xy = {boardx, boardy};
        path = pathfind.solveMaze(xy[1], xy[0], GD.pacXY[1], GD.pacXY[0]);
        System.out.println(path);
    
        if (path == null || path.isEmpty()) {
            System.out.println("No valid path found! Ghost will remain stationary.");
        } else {
            System.out.println("Path recalculated: " + path);
        }
    }

    private int roundToNearest(float number, int target) {
        return Math.round(number / target) * target;
    }
}
