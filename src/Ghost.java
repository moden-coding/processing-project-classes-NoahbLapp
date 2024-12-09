import processing.core.*;

public class Ghost {
    private PApplet p;
    private float x, y;  // Ghost's position
    private float speed;  // Ghost's movement speed
    private int segDiv;
    private String direction;
    private PImage ghostImage;  // Ghost sprite
    private int[] startPosition;
    private boolean started;
    private Pathfinding;

    Ghost(PApplet p, float speed, String imagePath) {
        this.pa

        this.p = p;
        this.speed = speed;
        this.segDiv = GD.segmentDivision;
        this.direction = "right";  // Default direction

        this.started = false;

        this.startPosition = new int[] {16, 7};

        // Initialize the ghost's position
        this.x = p.width / 2-(this.segDiv/2);
        this.y = p.height / 2-(this.segDiv*1.5f);

        // Load the ghost image
        this.ghostImage = p.loadImage(imagePath);
    }

    private void startMove(){
        if(!started){
            if(roundToNearest(y,segDiv)/segDiv != startPosition[1]){
                y -= speed ;
                if(roundToNearest(y, segDiv)/segDiv == startPosition[1]){
                    y = roundToNearest(y, segDiv);
                }
            }else if(roundToNearest(x,segDiv)/segDiv != startPosition[0]){
                x += speed ;
                if(roundToNearest(x, segDiv)/segDiv == startPosition[0]){
                    x = roundToNearest(x, segDiv);
                    started = false;
                }
            }
        }
    }

    public void displayGhost() {
        // Draw the ghost image
        startMove();
        p.image(ghostImage, x, y, segDiv, segDiv);
    }

    private int roundToNearest(float number, int target) {
        return Math.round(number / target) * target;
    }
}
