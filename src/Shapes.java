import java.util.ArrayList;
import java.util.Arrays;
import processing.core.*;
import java.util.Random;

public class Shapes{
    private PApplet p;

    public int blueColor;

    private int radius;

    private int segDiv;

    private int numOfShapes;

    private ArrayList<String> sideShapeOptions;
    private ArrayList<String> midShapeOptions;


    Shapes(PApplet p, int amountOfShapes){
        this.p = p;
        
        this.blueColor = p.color(66,122,225);

        this.radius = 20;

        this.segDiv = GD.segmentDivision;

        this.numOfShapes = amountOfShapes;

        this.sideShapeOptions = new ArrayList<>();
        this.sideShapeOptions.addAll(Arrays.asList("rectangle", "T", "L"));
    }

    public void makeBoard(){
        //make board outline
        p.strokeWeight(5);
        p.stroke(this.blueColor);
        p.fill(0);
        p.rect(0,0,p.width,p.height,this.radius);

        p.fill(22);
        makeMiddle();
        sideShapes();
    }

    private void makeMiddle(){
        //MAKE middle square
        p.stroke(this.blueColor);
        p.rect(p.width/2-(5*this.segDiv)/2, p.height/2-(3*this.segDiv)/2, 5*this.segDiv,3*this.segDiv,this.radius);

        //get the line for the ghosts to leave
        p.stroke(255);
        p.line(p.width/2-(2*this.segDiv)/2, p.height/2-(3*this.segDiv)/2, p.width/2-(8*this.segDiv)/2+5*this.segDiv, p.height/2-(3*this.segDiv)/2);
    }

    private void sideShapes(){
        ArrayList<String> chosenShapes = new ArrayList();
        p.strokeWeight(5);
        p.stroke(this.blueColor);
        for(int i = 0; i < this.numOfShapes; i++){
            int shape = (int)p.random(0,this.sideShapeOptions.size());
            chosenShapes.add(this.sideShapeOptions.get(shape));
        }

        int shapeSizeRound = (this.numOfShapes > 1) 
            ? (int) p.random((float)this.numOfShapes, 7.0f) 
            : (int) p.random((float)(this.numOfShapes + 1), 7.0f);
        for(int y = 1; y < GD.board.length; y++){
            for(int x = 1; x< GD.board[0].length/2-1;x++){
                if(shapeCount>0){
                    p.rect(x*this.segDiv,y*this.segDiv,this.segDiv,this.segDiv,this.radius);
                }
            }
        }
    }

    private void combineShapes(){
    
    }

}
