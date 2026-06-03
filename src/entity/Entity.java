package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    protected int worldX,worldY;
    protected int speed;
    protected BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    protected String direction;
    protected int spriteCounter = 0;
    protected int spriteNum = 1;
    protected Rectangle solidArea;
    protected int solidAreaDefaultX,solidAreaDefaultY;
    protected boolean collisionOn = false;

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public int getSpeed() {
        return speed;
    }

    public String getDirection() {
        return direction;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }

}
