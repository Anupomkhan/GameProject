package object;

import entity.Player;
import main.GamePanel;

public interface Interactable {
    void interact(Player player, GamePanel gp, int objectIndex);
}
