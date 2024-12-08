import processing.core.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class App extends PApplet{
    BoardControl boardMaker = new BoardControl(this);
    Pacman pacman;
    public static void main(String[] args)  {
        PApplet.main("App");
    }

    public void setup(){
        background(0);
        int numOfShapes = (int)random(6,20);
        boardMaker.makeBoard();
        pacman = new Pacman(this, 2f);
    }

    public void settings(){
        size(GD.width,GD.height);//divide by 35 to get background segments.
    }

    public void draw(){
        background(0);
        boardMaker.showBoard();
        pacman.pacDisplay();
    }

    public void keyPressed(){
        pacman.handleKeyPressed(keyCode);
    }
}