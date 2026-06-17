package object;

import entity.Player;
import main.GamePanel;

public class DoorObject extends GameObject implements Interactable {
    public DoorObject() {
        setName("Door");
        loadImage("/objects/door.png");
        setCollision(true);
    }

    @Override
    public void interact(Player player, GamePanel gp, int objectIndex) {
        gp.playSE(GamePanel.SOUND_UNLOCK);
        if (player.hasKey()) {
            gp.removeObject(objectIndex);
            gp.getUi().showMessage("You opened a door!!!");
            player.useKey();
        } else {
            gp.getUi().showMessage("You need a key to open!!!");
        }
    }
}
