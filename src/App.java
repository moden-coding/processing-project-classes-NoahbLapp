import processing.core.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class App extends PApplet{
    BoardControl boardMaker = new BoardControl(this);
    Pacman pacman;
    Ghost redGhost;
    public static void main(String[] args)  {
        PApplet.main("App");
    }

    public void setup(){
        background(0);
        int numOfShapes = (int)random(6,20);
        boardMaker.makeBoard();
        redGhost = new Ghost(this,1.5f, "src\\pixilart-drawing.png");
        pacman = new Pacman(this, 2f);
    }

    public void settings(){
        size(GD.width,GD.height);//divide by 35 to get background segments.
    }

    public void draw(){
        if(GD.numsOfOrbs != 0){
            background(0);
            boardMaker.showBoard();
            pacman.pacDisplay();
            redGhost.displayGhost();
        }
    }

    public void keyPressed(){
        pacman.handleKeyPressed(keyCode);
    }
}