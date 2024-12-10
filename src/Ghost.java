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
    private Pathfinding pathFind;
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

        this.pathFind = new Pathfinding();

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
        ArrayList<String> path = pathFind.bfs(GD.board, xy,GD.pacXY);
        String direction = path.get(0);
        System.out.println(direction);
        if(direction.equals("right")){
            x+=speed;
        }else if(direction.equals("left")){
            x-=speed;
        }else if(direction.equals("up")){
            y-=speed;
        }else if(direction.equals("down")){
            y+=speed;
        }
    }

    private int roundToNearest(float number, int target) {
        return Math.round(number / target) * target;
    }
}
