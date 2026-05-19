package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Font font ;
    BufferedImage keyImage;

    public UI(GamePanel gp){
        this.gp = gp;
        font = new Font("Arial", Font.BOLD,23);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void draw(Graphics2D g2){
        g2.setFont(font);
        g2.setColor(Color.yellow);
        g2.drawImage(keyImage,gp.tileSize/2,gp.tileSize/2 , gp.tileSize/2 , gp.tileSize/2,null);
        g2.drawString("x:" + gp.player.hasKey,45,45);
    }
}
