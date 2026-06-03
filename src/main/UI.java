package main;

import object.KeyObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    private final GamePanel gp;
    private final Font font,font2 ;
    private final BufferedImage keyImage;
    private boolean messageOn = false;
    private String message = "";
    private int messageCounter = 0;
    private boolean gameFinished = false;


    public UI(GamePanel gp){
        this.gp = gp;
        font = new Font("Arial", Font.BOLD,23);
        font2 = new Font("Arial",Font.BOLD,46);
        KeyObject key = new KeyObject();
        keyImage = key.getImage();
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
            x = gp.getScreenWidth()/2 - textLength/2;
            y = gp.getScreenHeight()/2 - g2.getFontMetrics().getHeight()/2;
            g2.drawString(text,x,y);

            g2.setFont(font2);
            g2.setColor(Color.yellow);
            text = "Congratulations!!!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.getScreenWidth()/2 - textLength/2;
            y = gp.getScreenHeight()/2 + g2.getFontMetrics().getHeight()/2;
            g2.drawString(text,x,y);

            gp.stopGameThread();
        }
        else {
            g2.setFont(font);
            g2.setColor(Color.yellow);
            g2.drawImage(keyImage,gp.getTileSize()/2,gp.getTileSize()/2 , gp.getTileSize()/2 , gp.getTileSize()/2,null);
            g2.drawString("x " + gp.getPlayer().getKeyCount(),gp.getTileSize(),gp.getTileSize());
            if(messageOn){
                g2.drawString(message,gp.getTileSize()/2,gp.getTileSize()/2*5);
                messageCounter++;
                if (messageCounter>120){
                    messageOn = false;
                    messageCounter=0;
                }
            }
        }

    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
}
