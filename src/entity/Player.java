package entity;

import main.GamePanel;
import main.KeyHandler;
import object.Interactable;
import object.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    private final GamePanel gp;
    private final KeyHandler keyH;
    private final int screenX;
    private final int screenY;
    private int hasKey = 0;
    private int hasChest = 0;

    public Player (GamePanel gp,KeyHandler keyH) {
        this.gp=gp;
        this.keyH=keyH;

        screenX=gp.getScreenWidth()/2-(gp.getTileSize()/2);
        screenY=gp.getScreenHeight()/2-(gp.getTileSize()/2);

        solidArea = new Rectangle(12, 16, 24, 32);
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        worldX=gp.getTileSize()*40;
        worldY=gp.getTileSize()*40;
        speed=4;
        direction="down";
    }
    public void getPlayerImage(){
        try {
            up1= ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2= ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1= ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2= ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1= ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(keyH.isUpPressed() || keyH.isDownPressed() || keyH.isLeftPressed() || keyH.isRightPressed()){
            if(keyH.isUpPressed()) {
                direction = "up";
            }
            else if(keyH.isDownPressed()) {
                direction = "down";
            }
            else if(keyH.isLeftPressed()) {
                direction = "left";
            }
            else if(keyH.isRightPressed()) {
                direction = "right";
            }

            //Check tile collision
            collisionOn = false;
            gp.getCollisionChecker().checkTile(this);

            //Check object's collision
            int objIndex = gp.getCollisionChecker().checkObject(this,true);
            pickUpObject(objIndex);

            //If collision is false, player can move
            if (collisionOn == false){
                switch (direction){
                    case "up": worldY-=speed;
                        break;
                    case "down": worldY+=speed;
                        break;
                    case "left": worldX-=speed;
                        break;
                    case "right": worldX+=speed;
                        break;
                }
            }

            spriteCounter++;
            if(spriteCounter>10){
                if(spriteNum==1){
                    spriteNum=2;
                } else if (spriteNum==2) {
                    spriteNum=1;
                }
                spriteCounter=0;
            }
        }

    }
    public void pickUpObject( int i){
        if (i!=999){
            GameObject gameObject = gp.getObject(i);
            if (gameObject instanceof Interactable) {
                ((Interactable) gameObject).interact(this, gp, i);
            }
        }

    }
    public void draw(Graphics2D g2){
        BufferedImage image= null;
        switch (direction){
            case "up":
                if(spriteNum == 1){
                    image=up1;
                }
                if (spriteNum == 2){
                    image=up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image=down1;
                }
                if (spriteNum == 2){
                    image=down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image=left1;
                }
                if (spriteNum == 2){
                    image=left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image=right1;
                }
                if (spriteNum == 2){
                    image=right2;
                }
                break;
        }
        g2.drawImage(image,screenX,screenY ,gp.getTileSize(),gp.getTileSize(),null);

    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public int getKeyCount() {
        return hasKey;
    }

    public int getChestCount() {
        return hasChest;
    }

    public void addKey() {
        hasKey++;
    }

    public boolean hasKey() {
        return hasKey > 0;
    }

    public void useKey() {
        hasKey--;
    }

    public void addChest() {
        hasChest++;
    }

    public void increaseSpeed(int amount) {
        speed += amount;
    }
}
