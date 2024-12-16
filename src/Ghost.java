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

    Ghost(PApplet p, float speed, String imagePath) {
        this.p = p;
        this.speed = speed;
        this.segDiv = GD.segmentDivision;
        this.direction = "right";  // Default direction

        this.started = false;

        this.startPosition = new int[] {16, 7};
        this.boardx = this.startPosition[0];
        this.boardy = this.startPosition[1];

        // Initialize the ghost's position
        this.x = p.width / 2-(this.segDiv/2);
        this.y = p.height / 2-(this.segDiv*1.5f);

        // Load the ghost image
        this.ghostImage = p.loadImage(imagePath);
    }

    private void startMove(){
        if(roundToNearest(y,segDiv)/segDiv != startPosition[1]){
            y -= speed ;
            if(roundToNearest(y, segDiv)/segDiv == startPosition[1]){
                y = roundToNearest(y, segDiv);
            }
        }else if(roundToNearest(x,segDiv)/segDiv != startPosition[0]){
            x += speed ;
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

    private void move(){
        boardx = roundToNearest(x, segDiv) / segDiv;
        boardy = roundToNearest(y, segDiv) / segDiv;
        int xy[] = {boardx,boardy};
        Pathfinding pathfind = new Pathfinding(GD.board);
        ArrayList<String> path = pathfind.solveMaze(xy[1],xy[0],GD.pacXY[1],GD.pacXY[0]);
        String dir = path.get(0);
        System.out.println(path);
        if(direction.equals("RIGHT")){
            x+=speed;
        }else if(direction.equals(":LEFT")){
            x-=speed;
        }else if(direction.equals("UP")){
            y-=speed;
        }else if(direction.equals("DOWN")){
            y+=speed;
        }
        xy = new int[]{roundToNearest(x, segDiv)/segDiv,roundToNearest(y, segDiv)/segDiv};
    }

    private int roundToNearest(float number, int target) {
        return Math.round(number / target) * target;
    }
}
