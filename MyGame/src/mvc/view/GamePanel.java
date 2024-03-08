package mvc.view;

import mvc.controller.KeyHandler;
import org.w3c.dom.ls.LSOutput;

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

    //Frames Per Second FPS
    int FPS = 60;
    //initiate keyHandler
    KeyHandler keyH = new KeyHandler();
    Thread gameThread; //thread is something you can start and stop - once started it keeps program running until you stop it
    //60 frames per second this is very helpful with this. implement Runnable

    //Set player's default position (palyerX, Y co-ordinates to use in paintComponent - can change the player position using these variables)
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //drawing from this component will be done in offscreen painting buffer - improves games rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);//GamePanel can be "focussed" to receive key input.
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

        //variables set for sleep method:
//        double drawInterval = 1000000000/FPS; //0.01666 seconds (1billion nanoseconds divided by 60 or 1 second/60 so 60 frames per second.
//        double nextDrawTime = System.nanoTime() + drawInterval;

        //variables for delta method:
        double drawInterval = 1000000000/FPS; //as with sleep method.
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        //check if running at 60FPS - display timer (whenever update increase drawCount by 1 and increase timer
        long timer = 0;
        int drawCount = 0;

        //as long as the gameThread exists, it repeats the process that is witten inside these brackets
        while(gameThread != null){
//            System.out.println("The game loop is running"); //test to see the loop is running.
            //System.nanoTime() returns the current value of the running JVM high resolution time source in nanoseconds.
            //(Need to get the time it takes to run between update and repaint so that we know how much
            //to slow the loop down by to show the updated player position(otherwise the CPU runs so quick the position goes
            //off-screen before we see it even move) 1 billion nanoseconds = 1 second
//            long currentTime = System.nanoTime();
//            long currentTime2 = System.currentTimeMillis(); //returns current time in milliseconds - this is okay but nano is more precise
//            System.out.println("current Time: "+ currentTime + ", currentTime2: " + currentTime2);

            //method for delta method
            currentTime = System.nanoTime(); //check for current time.
            delta += (currentTime - lastTime) / drawInterval; //subtract last time from current time (how much time has passed)
            //and divide by drawInterval and add to this delta
            timer += (currentTime - lastTime); //time passed is added to timer so every loop passed time is added.
            lastTime = currentTime; //current time becomes the last time.

            if(delta >= 1) {  //if statement this 1 is equal to this drawInterval
                //every loop add past time divided by drawInterval to delta and when delta reaches
                //drawInterval update and repaint then reset delta so this is another way to update and draw
                //at every draw interval.

                //1. UPDATE: update information such as character positions
                update();

                //2. DRAW; draw the screen with the updated information
                repaint();

                delta--;
                drawCount++; //increase drawCount each frame drawn.

                //sleep method will pause the game until the remainingTime has run.
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1000000; //convert from nanoseconds to milliseconds to pass into sleep method
//
//                if(remainingTime < 0){
//                    remainingTime = 0;
//                }
//                Thread.sleep( (long) remainingTime);
//
//                nextDrawTime += drawInterval;
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }  //end of sleep method
            }//end of delta method (minus the try catch sleep method section.
            if (timer >= 1000000000 ){ //when timer reaches 1 second (1 billion nanoseconds) show FPS
                System.out.println("FPS: " + drawCount);//how many times it updated and repainted until timer hits 1 second
                drawCount = 0; //re-set drawCount
                timer = 0; //re=set timer
            }
        }
    }
    //code to handle changes to player position based on key press/release:
    public void update(){

        if(keyH.upPressed == true){
            playerY -= playerSpeed;
        }
        else if(keyH.downPressed == true){
            playerY += playerSpeed;
        }
        else if(keyH.leftPressed == true){
            playerX -= playerSpeed;
        }
        else if(keyH.rightPressed == true){
            playerX += playerSpeed;
        }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        //Graphics 2D extends the Graphics class to provide more
        //sophisticated controller geometry, co-ordinate transformations,
        //colour management, and text layout.
        Graphics2D g2 = (Graphics2D)g; //changes graphics g to graphics2D
        //set colour
        g2.setColor(Color.white);
        //Draw a rectangle and fill it with specified colour
        g2.fillRect(playerX, playerY, tileSize, tileSize); //x and y co-ordinates and width and height

        g2.dispose();//dispose() dispose of this graphics context and release any system resources that it is using.


    }
}
