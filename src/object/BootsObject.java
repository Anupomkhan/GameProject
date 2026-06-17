package object;

import entity.Player;
import main.GamePanel;

public class BootsObject extends GameObject implements Interactable {
    public BootsObject() {
        setName("Boots");
        loadImage("/objects/boots.png");
    }

    @Override
    public void interact(Player player, GamePanel gp, int objectIndex) {
        gp.playSE(GamePanel.SOUND_POWER_UP);
        player.increaseSpeed(2);
        gp.removeObject(objectIndex);
        gp.getUi().showMessage("You got speed boots!!!");
    }
}
