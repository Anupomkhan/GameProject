package main;

import object.BootsObject;
import object.ChestObject;
import object.DoorObject;
import object.KeyObject;

public class AssetSetter  {
    private final GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject() {
        gp.setObject(0, new KeyObject(), 50, 94);
        gp.setObject(1, new KeyObject(), 94, 54);
        gp.setObject(2, new KeyObject(), 18, 15);
        gp.setObject(3, new DoorObject(), 87, 15);
        gp.setObject(4, new ChestObject(), 87, 17);
        gp.setObject(5, new DoorObject(), 5, 3);
        gp.setObject(6, new ChestObject(), 7, 5);
        gp.setObject(7, new DoorObject(), 50, 97);
        gp.setObject(8, new ChestObject(), 61, 98);
        gp.setObject(9, new BootsObject(), 77, 23);
    }
}
