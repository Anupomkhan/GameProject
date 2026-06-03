package main;

import entity.Entity;
import object.GameObject;

public class CollisionChecker {
    private final GamePanel gp;
    CollisionChecker(GamePanel gp){
        this.gp=gp;
    }
    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x;
        int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width -1;
        int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y;
        int entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height -1;

        int entityLeftCol = entityLeftWorldX/gp.getTileSize();
        int entityRightCol = entityRightWorldX/gp.getTileSize();
        int entityTopRow = entityTopWorldY/gp.getTileSize();
        int entityBottomRow = entityBottomWorldY/gp.getTileSize();

        switch (entity.getDirection()) {

            case "up":

                entityTopRow = (entityTopWorldY - entity.getSpeed()) / gp.getTileSize();

                if(gp.getTileManager().isTileCollision(entityLeftCol, entityTopRow) ||
                        gp.getTileManager().isTileCollision(entityRightCol, entityTopRow)){
                    entity.setCollisionOn(true);
                }

                break;

            case "down":

                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / gp.getTileSize();

                if(gp.getTileManager().isTileCollision(entityLeftCol, entityBottomRow) ||
                        gp.getTileManager().isTileCollision(entityRightCol, entityBottomRow)){
                    entity.setCollisionOn(true);
                }

                break;

            case "left":

                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / gp.getTileSize();

                if(gp.getTileManager().isTileCollision(entityLeftCol, entityTopRow) ||
                        gp.getTileManager().isTileCollision(entityLeftCol, entityBottomRow)){
                    entity.setCollisionOn(true);
                }

                break;

            case "right":

                entityRightCol = (entityRightWorldX + entity.getSpeed()) / gp.getTileSize();

                if(gp.getTileManager().isTileCollision(entityRightCol, entityTopRow) ||
                        gp.getTileManager().isTileCollision(entityRightCol, entityBottomRow)){
                    entity.setCollisionOn(true);
                }

                break;
        }
    }
    public int checkObject(Entity entity,boolean player){
        int index=999;
        for (int i = 0; i < gp.getObjectCount(); i++) {
            GameObject gameObject = gp.getObject(i);
            if (gameObject!=null){
                //get entity's solid area position:
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
                //get the object's solid area positon:
                gameObject.getSolidArea().x = gameObject.getWorldX() + gameObject.getSolidArea().x;
                gameObject.getSolidArea().y = gameObject.getWorldY() + gameObject.getSolidArea().y;

                switch(entity.getDirection()) {
                    case "up":
                        entity.getSolidArea().y -= entity.getSpeed();
                        if(entity.getSolidArea().intersects(gameObject.getSolidArea())) {
                            if(gameObject.isCollision()){
                                entity.setCollisionOn(true);
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.getSolidArea().y += entity.getSpeed();
                        if(entity.getSolidArea().intersects(gameObject.getSolidArea())) {
                            if(gameObject.isCollision()){
                                entity.setCollisionOn(true);
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.getSolidArea().x -= entity.getSpeed();
                        if(entity.getSolidArea().intersects(gameObject.getSolidArea())) {
                            if(gameObject.isCollision()){
                                entity.setCollisionOn(true);
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.getSolidArea().x += entity.getSpeed();
                        if(entity.getSolidArea().intersects(gameObject.getSolidArea())) {
                            if(gameObject.isCollision()){
                                entity.setCollisionOn(true);
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                }
                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                gameObject.getSolidArea().x = gameObject.getSolidAreaDefaultX();
                gameObject.getSolidArea().y = gameObject.getSolidAreaDefaultY();
            }
        }
        return index;
    }
}
