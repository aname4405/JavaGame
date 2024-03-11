package mvc.model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY;
    public int speed;

    public BufferedImage up, up2, down, down2, left, left2, right, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNumber = 1;

    public Rectangle solidArea;

    public boolean collisionOn = false;

}
