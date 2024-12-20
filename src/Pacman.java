import processing.core.*;

public class Pacman {
    private PApplet p;
    public float x, y;  // Pac-Man's position
    private float mouthAngle;  // Current mouth angle
    public boolean opening;  // Direction of mouth movement
    private float speed;  // Pac-Man's movement speed
    private int segDiv;
    private String direction;
    private int openingCount;
    public int boardx;
    public int boardy;

    Pacman(PApplet p, float speed){
        this.p = p;
        this.openingCount = 0;
        this.opening = true;
        this.speed = speed;
        this.segDiv = GD.segmentDivision;
        this.x = this.p.width/2;
        this.y = 16*this.segDiv;
        this.direction = "right";
        this.x = 15.5f*this.segDiv;
        if(GD.board[16][15] == 1) this.y = 15*this.segDiv;
    }
    
    public void pacDisplay() {
        updatePosition();
        boardx = roundToNearest(x, segDiv) / segDiv;
        boardy = roundToNearest(y, segDiv) / segDiv;
        GD.pacXY = new int[]{boardx,boardy};
        eatOrbs();
        p.fill(255, 255, 0);
    
        // Define start and stop angles based on direction
        float startAngle = 0, stopAngle = 0;
        if (direction.equals("right")) {
            startAngle = p.radians(30);
            stopAngle = p.radians(330);
        } else if (direction.equals("left")) {
            startAngle = p.radians(210);
            stopAngle = p.radians(510);
        } else if (direction.equals("up")) {
            startAngle = p.radians(290);
            stopAngle = p.radians(590);
        } else if (direction.equals("down")) {
            startAngle = p.radians(110);
            stopAngle = p.radians(410);
        }
    
        // Draw Pac-Man
        if (opening) {
            // Mouth open
            p.arc(x + segDiv / 2, y + segDiv / 2, segDiv - 10, segDiv - 10, startAngle, stopAngle); 
        } else {
            // Mouth closed: Draw a circle
            p.ellipse(x + segDiv / 2, y + segDiv / 2, segDiv - 10, segDiv - 10);
        }
    
        // Update opening/closing state
        openingCount++;
        if (openingCount >= 15) {
            opening = !opening;
            openingCount = 0;
        }
    }
    
    private void eatOrbs(){
        int checkX = (int) x + segDiv / 2;
        int checkY = (int) y + segDiv / 2;

        int pixelColor = p.get(checkX,checkY);
        if(pixelColor == GD.colorOfOrb){
            GD.board[boardy][boardx] = 2;
            GD.numsOfOrbs--;
        }
    }

    public void updatePosition() {
        // Determine the new position to check for collisional
        int checkX = (int) x + segDiv / 2;
        int checkY = (int) y + segDiv / 2;
    
        // Check for the direction and adjust the check position accordingly
        if (direction.equals("left")) {
            checkX -= speed;
        } else if (direction.equals("right")) {
            checkX += speed;
        } else if (direction.equals("up")) {
            checkY -= speed;
        } else if (direction.equals("down")) {
            checkY += speed;
        }
    
        // Get the color of the pixel in front of Pac-Man
        int pixelColor = p.get(checkX, checkY);
    
        // Check if the pixel color is the same as the wall color
        if (pixelColor != GD.wallColor && pixelColor != p.color(255)) {
            // If it's not a wall, update the position
            if (direction.equals("left")) {
                x -= speed;
            } else if (direction.equals("right")) {
                x += speed;
            } else if (direction.equals("up")) {
                y -= speed;
            } else if (direction.equals("down")) {
                y += speed;
            }
        }
    }

    private int roundToNearest(float number, int target) {
        return Math.round(number / target) * target;
    }
    
    public void handleKeyPressed(int keyCode){ 
        x = roundToNearest(x, segDiv);
        y = roundToNearest(y, segDiv);
        
        if (keyCode == p.LEFT) direction = "left";
        if (keyCode == p.RIGHT) direction = "right";
        if (keyCode == p.UP) direction = "up";
        if (keyCode == p.DOWN) direction = "down";
    }
}
