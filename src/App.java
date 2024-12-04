import processing.core.*;
import java.util.ArrayList;
import java.util.Random;

public class App extends PApplet{
    public static void main(String[] args)  {
        PApplet.main("App");
    }

    public void setup(){
        background(0);
        int numOfShapes = (int)random(6,20);
        Shapes makeShapes = new Shapes(this, numOfShapes);
        makeShapes.makeBoard();
    }

    public void settings(){
        size(GD.width,GD.height);//divide by 35 to get background segments.
    }

    public void draw(){

    }
}
