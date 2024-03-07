package mvc.view;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 title
    final int scale=3;

    final int tileSize = originalTileSize * scale; //48x48 title
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn; //768 pixels
    final int screenHeight = tileSize * maxScreenRow; //576 pixels

    Thread gameThread; //thread is something you can start and stop - once started it keeps program running until you stop it
//60 frames per second this is very helpful with this. implement Runnable
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //drawing from this component will be done in offscreen painting buffer - improves games rendering performance
    }
    public void startGameThread() {

        //instantiate a thread = passes in this GamePanel to thread constructor
        gameThread = new Thread(this);
        //start thread (automatically call run method)
        gameThread.start();
    }
    //when gameThread is started it automatically calls this run method
    //game loop making core of game
    @Override
    public void run() {
        //as long as the gameThread exists, it repeats the process that is witten inside these brackets
        while(gameThread != null){
//            System.out.println("The game loop is running");
            //1. UPDATE: update information such as character positions

            //2. DRAW; draw the screen with the updated information
        }
    }
    public void update(){

    }

    public void paintComponent(Graphics g) {

    }
}
