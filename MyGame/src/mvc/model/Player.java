package mvc.model;

import mvc.controller.KeyHandler;
import mvc.view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();

    }
    public void setDefaultValues() {
        worldX= gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try{

            up = ImageIO.read(getClass().getResourceAsStream("/up.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/up2.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/down.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/down2.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/left.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/left2.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/right.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/right2.png"));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true){

        if(keyH.upPressed == true){
            direction = "up";
        }
        else if(keyH.downPressed == true){
            direction = "down";
        }
        else if(keyH.leftPressed == true){
            direction = "left";
        }
        else if(keyH.rightPressed == true){
            direction = "right";
        }

        //CHECK TILE COLLISION
        collisionOn = false;
        gp.cChecker.checkTile(this);

        //IF COLLISION IS FALSE PLAYER CAN MOVE
        if(collisionOn == false) {
            switch(direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case"right":
                    worldX += speed;
                    break;
            }
        }

        spriteCounter++;
        if(spriteCounter > 15){
            if(spriteNumber == 1) {
                spriteNumber = 2;
            }
            else if (spriteNumber == 2){
                spriteNumber = 1;
            }
            spriteCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2) {

        //rectangle set up for testing - changed when create sprites
//        //set colour
//        g2.setColor(Color.white);
//        //Draw a rectangle and fill it with specified colour
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize); //x and y co-ordinates and width and height

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spriteNumber == 1){
                    image = up;
                }
                if (spriteNumber == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNumber == 1){
                    image = down;
                }
                if (spriteNumber ==2 ){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNumber == 1){
                    image = left;
                }
                if (spriteNumber == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNumber == 1){
                    image = right;
                }
                if (spriteNumber == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
