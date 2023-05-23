package org.example;

import org.entity.Entity;

public class CollisionChecker {

    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp=gp;

    }

    /**
     * checks tile in direction of movement for features.
     * solid: returns -1.
     * warp: returns 1;
     * event trigger: returns 2
     * damage: returns 3
     * nothing: returns 0;
     * */
    public int checkTile(Entity entity){
        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCOl = entityLeftX/gp.tileSize;
        int entityRightCOl = entityRightX/gp.tileSize;
        int entityTopROW = entityTopY/gp.tileSize;
        int entityBottomROW = entityBottomY/gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up" -> {
                entityTopROW = (entityTopY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTile[entityLeftCOl][entityTopROW];
                tileNum2 = gp.tileM.mapTile[entityRightCOl][entityTopROW];
                if (gp.tileM.tiles[tileNum2].isSolid() || gp.tileM.tiles[tileNum1].isSolid()) {
                    return -1;
                }
                if (gp.tileM.tiles[tileNum2].isWarp() || gp.tileM.tiles[tileNum1].isWarp()) {
                    gp.tileM.warp();
                    return 1;
                }
                if (gp.tileM.tiles[tileNum2].isEventTrigger() || gp.tileM.tiles[tileNum1].isEventTrigger()) {
                    return 2;
                }
                if (gp.tileM.tiles[tileNum2].fireLevel()==2 || gp.tileM.tiles[tileNum1].fireLevel()==2) {
                    return 3;
                }
            }
            case "down" -> {
                entityBottomROW = (entityBottomY + 4*entity.speed - 4) / gp.tileSize;
                tileNum1 = gp.tileM.mapTile[entityLeftCOl][entityBottomROW];
                tileNum2 = gp.tileM.mapTile[entityRightCOl][entityBottomROW];
                if (gp.tileM.tiles[tileNum2].isSolid() || gp.tileM.tiles[tileNum1].isSolid()) {
                    return -1;
                }
                if (gp.tileM.tiles[tileNum2].isWarp() || gp.tileM.tiles[tileNum1].isWarp()) {
                    gp.tileM.warp();
                    return 1;
                }
                if (gp.tileM.tiles[tileNum2].isEventTrigger() || gp.tileM.tiles[tileNum1].isEventTrigger()) {
                    return 2;
                }
                if (gp.tileM.tiles[tileNum2].fireLevel()==2 || gp.tileM.tiles[tileNum1].fireLevel()==2) {
                    return 3;
                }
            }
            case "right" -> {
                entityRightCOl = (entityRightX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTile[entityRightCOl][entityTopROW];
                tileNum2 = gp.tileM.mapTile[entityRightCOl][entityBottomROW];
                if (gp.tileM.tiles[tileNum2].isSolid() || gp.tileM.tiles[tileNum1].isSolid()) {
                    return -1;
                }
                if (gp.tileM.tiles[tileNum2].isWarp() || gp.tileM.tiles[tileNum1].isWarp()) {
                    gp.tileM.warp();
                    return 1;
                }
                if (gp.tileM.tiles[tileNum2].isEventTrigger() || gp.tileM.tiles[tileNum1].isEventTrigger()) {
                    return 2;
                }
                if (gp.tileM.tiles[tileNum2].fireLevel()==2 || gp.tileM.tiles[tileNum1].fireLevel()==2) {
                    return 3;
                }
            }
            case "left" -> {
                entityLeftCOl = (entityLeftX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTile[entityLeftCOl][entityTopROW];
                tileNum2 = gp.tileM.mapTile[entityLeftCOl][entityBottomROW];
                if (gp.tileM.tiles[tileNum2].isSolid() || gp.tileM.tiles[tileNum1].isSolid()) {
                    return -1;
                }
                if (gp.tileM.tiles[tileNum2].isWarp() || gp.tileM.tiles[tileNum1].isWarp()) {
                    gp.tileM.warp();
                    return 1;
                }
                if (gp.tileM.tiles[tileNum2].isEventTrigger() || gp.tileM.tiles[tileNum1].isEventTrigger()) {
                    return 2;
                }
                if (gp.tileM.tiles[tileNum2].fireLevel()==2 || gp.tileM.tiles[tileNum1].fireLevel()==2) {
                    return 3;
                }
            }
        }
        return 0;
    }
    public void checkEntities(){
        /* to be implemented later*/
    }
}
