package object;

import entity.Player;
import main.GamePanel;

public class KeyObject extends GameObject implements Interactable {
    public KeyObject() {
        setName("Key");
        loadImage("/objects/key.png");
    }

    @Override
    public void interact(Player player, GamePanel gp, int objectIndex) {
        gp.playSE(GamePanel.SOUND_COIN);
        player.addKey();
        gp.removeObject(objectIndex);
        gp.getUi().showMessage("You got a key!!!");
    }
}
