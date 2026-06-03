package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Font font,font2 ;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;


    public UI(GamePanel gp){
        this.gp = gp;
        font = new Font("Arial", Font.BOLD,23);
        font2 = new Font("Arial",Font.BOLD,46);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text){
        message =  text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        if (gameFinished){
            g2.setFont(font);
            g2.setColor(Color.yellow);

            String text ;
            int textLength ;
            int x;
            int y;
            text = "You found the treasuere!!!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - g2.getFontMetrics().getHeight()/2;
            g2.drawString(text,x,y);

            g2.setFont(font2);
            g2.setColor(Color.yellow);
            text = "Congratulations!!!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + g2.getFontMetrics().getHeight()/2;
            g2.drawString(text,x,y);

            gp.gameThread = null;
        }
        else {
            g2.setFont(font);
            g2.setColor(Color.yellow);
            g2.drawImage(keyImage,gp.tileSize/2,gp.tileSize/2 , gp.tileSize/2 , gp.tileSize/2,null);
            g2.drawString("x " + gp.player.hasKey,gp.tileSize,gp.tileSize);
            if(messageOn){
                g2.drawString(message,gp.tileSize/2,gp.tileSize/2*5);
                messageCounter++;
                if (messageCounter>120){
                    messageOn = false;
                    messageCounter=0;
                }
            }
        }

    }
}
