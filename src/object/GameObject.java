package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class GameObject {
    private BufferedImage image;
    private String name;
    private boolean collision = false;
    private int worldX;
    private int worldY;
    private final Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    private final int solidAreaDefaultX = 0;
    private final int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
        int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

        if (worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX()
                && worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX()
                && worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY()
                && worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {
            g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public boolean isCollision() {
        return collision;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
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

    public void setWorldPosition(int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setCollision(boolean collision) {
        this.collision = collision;
    }

    protected void loadImage(String resourcePath) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(resourcePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
