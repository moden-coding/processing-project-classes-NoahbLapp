import java.util.ArrayList;
import java.util.Arrays;
import processing.core.*;
import java.util.Random;

public class BoardControl{
    public static int board[][] = new int[20][30];//0 is open, 1 is wall, 2 is orb

    private PApplet p;

    public int blueColor;

    private int radius;

    private int segDiv;

    private final int middlex1 = 12, middley1 = 8, middlex2 = 18, middley2=12;

    private ArrayList<String> sideShapeOptions;
    private ArrayList<String> midShapeOptions;

    private int y;

    private int orbDia;
    private int orbColor;

    BoardControl(PApplet p){
        this.p = p;
        
        this.orbDia = GD.orbDiameter;
        this.orbColor = this.p.color(163, 114, 33);
        GD.colorOfOrb = this.orbColor;

        this.blueColor = p.color(66,122,225);
        GD.wallColor = this.blueColor;

        this.radius = 0;

        this.segDiv = GD.segmentDivision;

        this.sideShapeOptions = new ArrayList<>();
        this.sideShapeOptions.addAll(Arrays.asList("rectangle", "T", "L"));
        this.midShapeOptions = new ArrayList<>();
        this.midShapeOptions.addAll(Arrays.asList("rectangle","T", "_|_"));
    }

    public void makeBoard(){
        makeMiddle();
        midShapes();
        sideShapes();
        findOrbNum();
        GD.board = this.board;
        showBoard();
    }

    private void makeMiddle(){
        //MAKE middle square
        for(int y = this.middley1; y<this.middley2;y++){
            for(int x = this.middlex1; x<this.middlex2;x++){
                this.board[y][x] = 1;
            }
        }
    }

    private void midShapes(){//A little bit of this method used chatgpt(but not a lot it was written by me and then I had to fix a error)
        p.fill(0);
        p.stroke(this.blueColor);
        p.strokeWeight(5);
        this.y = this.board.length-1;
        boolean shape1 = false;
        while(this.y >= 0) { // Loop while y is greater than 0
            if(this.y >= 7 && this.y <= 12){
                this.y--; // Avoid an infinite loop due to `continue`
                continue;
            }
            int shapeChosen = (int)p.random(-1, 3);
            // int shapeChosen = 1;
            String shape = midShapeOptions.get(shapeChosen);
            if(shape.equals("rectangle")) { // Use .equals for string comparison in Java
                int limit = this.y - 2; // Define the limit to avoid confusion
                if(!(limit >= 7 && limit <= 10) && limit>= 0){
                    if(this.y==19 || this.y==2){
                        System.out.println(shape);
                        while(this.y >= limit && this.y >= 0) { // Ensure y doesn't go below 0
                            this.board[y][13] = 1;
                            this.board[y][14] =1;
                            this.board[y][15] = 1;
                            this.board[y][16] =1;
                            this.y--;
                        }
                        midShapes2(1);
                        this.y--;
                    }
                }
                this.y--;
            } else if(shape.equals("T")){
                if(this.y == 19){
                    this.y--;
                    for(int i = 0;  i<2; i++){
                        this.board[y][13] = 1;
                        this.board[y][14] =1;
                        this.board[y][15] = 1;
                        this.board[y][16] =1;
                        this.y--;
                    }
                    for(int x = this.middlex1; x < this.middlex2; x++){
                        board[this.y][x] = 1;
                    }
                    this.y--;
                }else{
                    for(int x = this.middlex1; x < this.middlex2; x++){
                        board[1][x] = 1;
                    }
                    for(int x = this.middlex1+1; x < this.middlex2-1; x++){
                        board[2][x] = 1;
                    }
                    this.y = -1;
                }
                midShapes2(2);
                this.y--;
            }else if(shape.equals("_|_")){
                if (this.y == 19){
                    System.out.println(shape);
                    this.y--;
                    for(int x = this.middlex1; x < this.middlex2; x++){
                        board[this.y][x] = 1;
                    }
                    this.y--;
                    for(int i = 0;  i<2; i++){
                        this.board[y][13] = 1;
                        this.board[y][14] =1;
                        this.board[y][15] = 1;
                        this.board[y][16] =1;
                        this.y--;
                    }
                    this.y--;
                }else{
                    for(int x = this.middlex1+1; x < this.middlex2-1; x++){
                        board[1][x] = 1;
                        board[2][x] = 1;
                    }
                    for(int x = this.middlex1; x < this.middlex2; x++){
                        board[3][x] = 1;
                    }
                    this.y = -1;
                }
                midShapes2(2);
                this.y--;
            }
        }
    }

    private void midShapes2(int lineSize){
        int shapeChosen2 = (int)p.random(-1, 3);
        //int shapeChosen2 = 0;
        String shape2 = midShapeOptions.get(shapeChosen2);

        if(shape2.equals("rectangle")){
            if(this.y > 10){
                this.y--;
                for(int x = this.middlex1; x < this.middlex2; x++){
                    board[this.y][x] = 1;
                }
                this.y = (lineSize == 1) ? this.y - 2 : this.y-1;
                for(int x = this.middlex1; x < this.middlex2; x++){
                    board[this.y][x] = 1;
                }
            }else if(this.y == -1){
                this.y+=7;
                for(int x = this.middlex1; x < this.middlex2; x++){
                    board[this.y][x] = 1;
                }
                this.y -= 2;
                for(int x = this.middlex1; x < this.middlex2; x++){
                    board[this.y][x] = 1;
                }
                this.y = -1;
            }
        } else if (shape2.equals("T")){
            if(this.y > 10){
                this.y--;
                for(int x = this.middlex1; x < this.middlex2; x++){
                    board[this.y][x] = 1;
                }
                this.y --;
                for(int i = 0; i<2; i++){
                    this.board[this.y][13] = 1;
                    this.board[this.y][14] =1;
                    this.board[this.y][15] = 1;
                    this.board[this.y][16] =1;
                    this.y--;
                }
            } else if(this.y == -1){
                this.y+=7;
                for(int x = this.middlex1; x < this.middlex2; x++){
                    board[this.y][x] = 1;
                }
                this.y --;
                for(int i = 0; i<2; i++){
                    this.board[this.y][13] = 1;
                    this.board[this.y][14] =1;
                    this.board[this.y][15] = 1;
                    this.board[this.y][16] = 1;
                    this.y--;
                }
                this.y = -1;
            }
        }else if(shape2.equals("_|_")){
            if(this.y > 10){
                this.y--;
                for(int i = 0; i < 2; i++){
                    this.board[this.y][13] = 1;
                    this.board[this.y][14] =1;
                    this.board[this.y][15] = 1;
                    this.board[this.y][16] = 1;
                    this.y--;
                }
                for(int x = this.middlex1; x < this.middlex2; x++){
                    board[this.y][x] = 1;
                }
            }else if(this.y == -1){
                this.y+=7;
                for(int i = 0; i < 2; i++){
                    this.board[this.y][13] = 1;
                    this.board[this.y][14] =1;
                    this.board[this.y][15] = 1;
                    this.board[this.y][16] = 1;
                    this.y--;
                }
                for(int x = this.middlex1; x < this.middlex2; x++){
                    board[this.y][x] = 1;
                }
                System.out.println("Happy");
            }
        }
    }


    private void sideShapes(){
        ArrayList<String> leftShapes = new ArrayList<>();
        ArrayList<String> rightShapes = new ArrayList<>();//bigger
        for (int i = 0; i < 4; i++){
            int leftShapeRan = (int)p.random(-1,3);
            int rightShapeRan = (int)p.random(-1,3);
            leftShapes.add(this.sideShapeOptions.get(leftShapeRan));
            rightShapes.add(this.sideShapeOptions.get(rightShapeRan));
        }
        int x = 1;
        int y = 1;
        for(String shape: leftShapes){
            System.out.println(shape);
            if(shape.equals("rectangle")){
                for(int i = 0; i<4; i++){
                    try {
                        putSideShape(x, y);
                    } catch (Exception e) {
                    }
                    try {
                        putSideShape(x, y+1);
                        putSideShape(x, y+2);
                    } catch (Exception e) {
                    }
                    x++;
                }
                y += 4;
                x = 1;
            }else if(shape.equals("T")){
                for(int xOff = 0; xOff<6; xOff++){
                    try {
                        putSideShape(x+xOff, y);
                    } catch (Exception e) {
                    }
                }
                x++;
                for(int i = 0; i<4; i++){
                    try {
                        putSideShape(x,y+i);
                    } catch (Exception e) {
                    }
                }
                
                x+=2; y+=2;
                for(int xOff = 0; xOff<3; xOff++){
                    try {
                        putSideShape(x+xOff, y);
                    } catch (Exception e) {
                    }
                    try {
                        putSideShape(x+xOff, y+1);
                    } catch (Exception e) {
                    }
                }

                x = 1;
                y+=3;
            }else if(shape.equals("L")){
                for(int i = 0; i<4; i++){
                    try {
                        putSideShape(x,y);
                    } catch (Exception e) {
                    }
                    y++;
                }
                for(int xOff = 0; xOff<6; xOff++){
                    try {
                        putSideShape(x+xOff, y);
                    } catch (Exception e) {
                    }
                }
                
                y-=4;
                x+=2;
                for(int xOff = 0; xOff<4; xOff++){
                    try {
                        putSideShape(x+xOff,y);
                    } catch (Exception e) {
                    }
                    try {
                        putSideShape(x+xOff,y+1);
                    } catch (Exception e) {
                    }
                    try {
                        putSideShape(x+xOff,y+2);
                    } catch (Exception e) {
                    }
                }

                x = 1;
                y+=6;
            }
        }
        y = 1;
        x = 8;
        for(String shape: rightShapes){
            if(shape.equals("rectangle")){
                x = (this.board[y][2] == 1) ? 6 : 8;
                int runTime = (x==6) ? 5: 3;
                for(int xOff = 0; xOff<runTime; xOff++){
                    putSideShape(x+xOff,y);
                    putSideShape(x+xOff,y+1);
                    putSideShape(x+xOff,y+2);
                }
            }
        }
        
    }

    private void findOrbNum(){
        for(int y = 0; y < this.board.length;y++){
            for(int x = 0; x <this.board[0].length;x++){
                if(board[y][x] == 0){
                    GD.numsOfOrbs++;
                }
            }
        }
    }

    private void putSideShape(int x,int y){
        int boardXOffset = this.board[0].length-1;
        board[y][x] = 1;
        board[y][boardXOffset-x] = 1;
    }

    public void showBoard(){
        GD.board = this.board;
        //make board outline
        p.strokeWeight(5);
        p.stroke(this.blueColor);
        p.fill(0);
        p.rect(0,0,p.width,p.height,this.radius);

        p.strokeWeight(5);
        //make middle
        p.stroke(this.blueColor);
        p.rect(this.middlex1*this.segDiv, this.middley1*this.segDiv, (this.middlex2-this.middlex1)*this.segDiv,(this.middley2-this.middley1)*this.segDiv,this.radius);

        //get the line for the ghosts to leave
        p.stroke(255);
        p.line((this.middlex1+2)*this.segDiv, this.middley1*this.segDiv, (this.middlex2-2)*this.segDiv,this.middley1*this.segDiv);

        p.stroke(this.blueColor);
        for(int y = 0; y < this.board.length; y++){
            for(int x = 0; x < this.board[0].length; x++){
                if(x >= this.middlex1 && x <= this.middlex2-1 && y >= this.middley1 && y <= this.middley2-1){
                    continue;
                }
                if(this.board[y][x]==1){
                    p.stroke(this.blueColor);
                    p.fill(0);
                    p.rect(x*this.segDiv, y*this.segDiv,this.segDiv,this.segDiv,this.radius);
                }else if(this.board[y][x] == 0){
                    p.noStroke();
                    p.fill(this.orbColor);
                    p.ellipse(x*this.segDiv+this.segDiv/2,y*this.segDiv+this.segDiv/2,this.orbDia,this.orbDia);
                }
            }
        }
    }
}
