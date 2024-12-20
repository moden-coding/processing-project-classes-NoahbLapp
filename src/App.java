import processing.core.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.PrintWriter;

public class App extends PApplet{
    BoardControl boardMaker = new BoardControl(this);
    Pacman pacman;
    Ghost redGhost;
    Ghost pinkGhost;
    Ghost yellowGhost;
    Ghost blueGhost;
    boolean gameRunning;
    public static void main(String[] args)  {
        PApplet.main("App");
    }

    public void setup(){
        background(0);
        gameRunning = false;
        int numOfShapes = (int)random(6,20);
        boardMaker.makeBoard();
        int[] redGhostStart = {16, 7};
        int[] pinkGhostStart = {21, 7};
        int[] yellowGhostStart = {18,7};
        int[] blueGhostStart = {10,7};
        redGhost = new Ghost(this,2f, redGhostStart,"src\\pixilart-drawing.png");
        pinkGhost = new Ghost(this,2f, pinkGhostStart,"src\\pink ghost.png");
        yellowGhost = new Ghost(this,2f, yellowGhostStart,"src\\yellow Ghost.png");
        blueGhost = new Ghost(this,2f, blueGhostStart,"src\\blue Ghost.png");
        pacman = new Pacman(this, 2f);
    }

    public void settings(){
        size(GD.width,GD.height);//divide by 35 to get background segments.
    }

    public void draw(){
        if(gameRunning){
            if(GD.numsOfOrbs != 0){
                background(0);
                boardMaker.showBoard();
                pacman.pacDisplay();
                redGhost.displayGhost();
                pinkGhost.displayGhost();
                yellowGhost.displayGhost();
                blueGhost.displayGhost();
                if(redGhost.touchingPacman() || pinkGhost.touchingPacman() || yellowGhost.touchingPacman() || blueGhost.touchingPacman()){
                    setLevelZero();
                    gameRunning = false;
                    reset();
                }
            }else{
                addLevel();
                gameRunning = false;
                reset();
            }
        }else{
            fill(255);  // Set the text color (black)
            textSize(45);  // Set the text size
            textAlign(CENTER, CENTER);  // Align text to the center
            text("Level: "+getLevel(), width / 2, height / 2);  // Display the text
        }
    }

    public void reset(){
        boardMaker.clearBoard();
        boardMaker.makeBoard();
        int[] redGhostStart = {16, 7};
        int[] pinkGhostStart = {14, 7};
        pacman = new Pacman(this,2f);
        redGhost = new Ghost(this,2f,redGhostStart,"src\\pixilart-drawing.png");
    }

    public String getLevel(){
        String level = "";
        try(Scanner fileRead = new Scanner(Paths.get("src/level.txt"))){
            while (fileRead.hasNextLine()) {
                String row = fileRead.nextLine();
                level = row;
            }
        }catch(Exception e){
            System.out.println("Error: There is not file for the level :( Idk how this happened :( Don't be sad it's okay just make a new one and be happy :)");
        }
        return level;
    }
    public void setLevelZero(){
        try {
            Files.newBufferedWriter(Paths.get("src/level.txt")).close();
            System.out.println("File has been cleared.");
        } catch (Exception e) {
            System.out.println("Error while clearing the file: " + e.getMessage());
        }
        try (PrintWriter writer = new PrintWriter("src/level.txt")){
            writer.println(1); 
            writer.close(); 
            System.out.println("Integer saved to file successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
    public void addLevel(){
        int level = Integer.valueOf(getLevel()) + 1;
        try {
            Files.newBufferedWriter(Paths.get("src/level.txt")).close();
            System.out.println("File has been cleared.");
        } catch (Exception e) {
            System.out.println("Error while clearing the file: " + e.getMessage());
        }
        try (PrintWriter writer = new PrintWriter("src/level.txt")){
            writer.println(level); 
            writer.close(); 
            System.out.println("Integer saved to file successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
    public void keyPressed(){
        if(keyCode == ENTER){
            gameRunning = true;
        }
        pacman.handleKeyPressed(keyCode);
    }
}