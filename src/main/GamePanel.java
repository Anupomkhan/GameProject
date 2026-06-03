package main;
import entity.Player;
import object.GameObject;
import tile.TileManager;

import java.awt.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    //Screen settings
    public static final int SOUND_MUSIC = 0;
    public static final int SOUND_COIN = 1;
    public static final int SOUND_POWER_UP = 2;
    public static final int SOUND_UNLOCK = 3;
    public static final int SOUND_FANFARE = 4;

    private final int originalTileSize=16; //16*16 tile
    private final int scale=3;

    private final int tileSize=originalTileSize*scale; //48*48 tile
    private final int maxScreenCol=16;
    private final int maxScreenRow=12;
    private final int screenWidth=tileSize*maxScreenCol;// 768 pixels
    private final int screenHeight=tileSize*maxScreenRow;// 576 pixels

    //World Settings
    private final int maxWorldCol = 100;
    private final int maxWorldRow = 100;

    private final int FPS=60;

    //System
    private final TileManager tileM = new TileManager(this);
    private final KeyHandler keyH = new KeyHandler();
    private final Sound sound = new Sound();
    private final CollisionChecker cChecker = new CollisionChecker(this);
    private final UI ui = new UI(this);
    private final AssetSetter aSetter = new AssetSetter(this);
    private Thread gameThread;

    //Entity and Object
    private final Player player=new Player(this,keyH);
    private final GameObject obj[] = new GameObject[10];

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true );
    }

    public void setupGame (){
        aSetter.setObject();
        playMusic(SOUND_MUSIC);
    }

    public void startGameThread() {
        gameThread=new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval=1000000000/FPS;
        double delta=0;
        long lastTime = System.nanoTime();
        long currentTime ;

        while (gameThread!=null) {

            currentTime=System.nanoTime();
            delta+=(currentTime-lastTime)/drawInterval;

            lastTime=currentTime;
            if(delta>=1) {
                update();
                repaint();
                delta--;
            }
        }
    }
    public void update() {
        player.update();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D) g;

        //tile
        tileM.draw(g2);//layer-1

        //object
        for (int i = 0; i <obj.length; i++) { //layer-2
            if (obj[i]!=null){
                obj[i].draw(g2,this);
            }
        }

        //player
        player.draw(g2);//layer-3

        //UI
        ui.draw(g2);//layer-4
        g2.dispose();

    }
    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic() {
        sound.stop();
    }
    public void playSE (int i) {
        sound.setFile(i);
        sound.play();
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public TileManager getTileManager() {
        return tileM;
    }

    public CollisionChecker getCollisionChecker() {
        return cChecker;
    }

    public UI getUi() {
        return ui;
    }

    public Player getPlayer() {
        return player;
    }

    public int getObjectCount() {
        return obj.length;
    }

    public GameObject getObject(int index) {
        return obj[index];
    }

    public void setObject(int index, GameObject gameObject, int worldCol, int worldRow) {
        obj[index] = gameObject;
        obj[index].setWorldPosition(worldCol * tileSize, worldRow * tileSize);
    }

    public void removeObject(int index) {
        obj[index] = null;
    }

    public void finishGame() {
        ui.setGameFinished(true);
        stopMusic();
    }

    public void stopGameThread() {
        gameThread = null;
    }
}
