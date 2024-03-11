package mvc.controller;

import mvc.model.Entity;
import mvc.view.GamePanel;

public class CollisionChecker {

    GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftColumn = entityLeftWorldX/gp.tileSize;
        int entityRightColumn = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNumber1, tileNumber2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNumber1 = gp.tileM.mapTileNumber[entityLeftColumn][entityTopRow];
                tileNumber2 = gp.tileM.mapTileNumber[entityRightColumn][entityTopRow];
                if(gp.tileM.tile[tileNumber1].collision == true || gp.tileM.tile[tileNumber2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNumber1 = gp.tileM.mapTileNumber[entityLeftColumn][entityBottomRow];
                tileNumber2 = gp.tileM.mapTileNumber[entityRightColumn][entityBottomRow];
                if(gp.tileM.tile[tileNumber1].collision == true || gp.tileM.tile[tileNumber2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftColumn = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNumber1 = gp.tileM.mapTileNumber[entityLeftColumn][entityTopRow];
                tileNumber2 = gp.tileM.mapTileNumber[entityLeftColumn][entityBottomRow];
                if(gp.tileM.tile[tileNumber1].collision == true || gp.tileM.tile[tileNumber2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightColumn = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNumber1 = gp.tileM.mapTileNumber[entityRightColumn][entityTopRow];
                tileNumber2 = gp.tileM.mapTileNumber[entityRightColumn][entityBottomRow];
                if(gp.tileM.tile[tileNumber1].collision == true || gp.tileM.tile[tileNumber2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }

    }
}
