import java.util.ArrayList;
import java.util.Arrays;
import processing.core.*;
import java.util.Random;

public class Shapes{
    private PApplet p;

    private int radius;

    private int segDiv;

    private int numOfShapes;

    private ArrayList<String> sideShapeOptions;
    private ArrayList<String> midShapeOptions;


    Shapes(PApplet p, int amountOfShapes){
        this.p = p;

        this.radius = 20;

        this.segDiv = GD.segmentDivision;

        this.numOfShapes = amountOfShapes;

        this.sideShapeOptions = new ArrayList<>();
        this.sideShapeOptions.addAll(Arrays.asList("rectangle", "T", "L"));
    }

    public void makeBoard(){
        p.strokeWeight(4);
        p.fill(22);
        makeMiddle();
        sideShapes();
    }

    private void makeMiddle(){
        //MAKE middle square
        p.stroke(66,122,225);
        p.rect(p.width/2-(5*this.segDiv)/2, p.height/2-(3*this.segDiv)/2, 5*this.segDiv,3*this.segDiv,this.radius);

        //get the line for the ghosts to leave
        p.stroke(255);
        p.line(p.width/2-(2*this.segDiv)/2, p.height/2-(3*this.segDiv)/2, p.width/2-(8*this.segDiv)/2+5*this.segDiv, p.height/2-(3*this.segDiv)/2);
    }

    private void sideShapes(){
        int shapeSizeRound = (int) random(this.numOfShapes, 7);
        for(int shapeNum = 0; shapeNum < this.numOfShapes; shapeNum++){
            int xSize = shapeSizeRound
            for(int i = 0; i<2; i++){
            }
        }
    }

    private void combineShapes(){
    
    }

}
