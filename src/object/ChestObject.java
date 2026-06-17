package object;

import entity.Player;
import main.GamePanel;

public class ChestObject extends GameObject implements Interactable {
    private static final int REQUIRED_CHEST_COUNT = 3;

    public ChestObject() {
        setName("Chest");
        loadImage("/objects/chest.png");
    }

    @Override
    public void interact(Player player, GamePanel gp, int objectIndex) {
        player.addChest();
        gp.removeObject(objectIndex);
        if (player.getChestCount() >= REQUIRED_CHEST_COUNT) {
            gp.finishGame();
            gp.playSE(GamePanel.SOUND_FANFARE);
        } else {
            gp.playSE(GamePanel.SOUND_COIN);
            gp.getUi().showMessage("You found a treasure box!!!");
        }
    }
}
