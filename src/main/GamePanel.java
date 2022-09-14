package main;

import Entity.Player;
import Physics.*;
import main.tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tiles
    final int scale = 3;
    public String mode = null;

    public final int tileSize = originalTileSize * scale; //48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels
    public final int tileHeight = tileSize * 2;

    //fps
    int FPS = 60;

    TileManager tileM;
    KeyHandler keyH;
    Thread gameThread;
    Player player;

    public String sourceDir = "/Users/dennislosett/Desktop/CS 1/PixelJump/res/";

    public GamePanel() throws IOException {
        this.keyH = new KeyHandler();
        //this.tileM = new TileManager(this);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        createWorld();
    }

    private void createWorld() throws IOException {

        player = new Player(new Body(
                BodyType.KINEMATIC,
                new Rectangle(100, 100, 4*tileSize/5, 4*tileHeight/5),
                new Vector2D(0, 10)
        ), this, keyH);
        World.getInstance().addBody(player.body);

        World.getInstance().addActor(
                new Actor(
                        new Body(BodyType.STATIC, new Rectangle(screenWidth / 2, 0, screenWidth * 2, 50)),
                        this,
                        ImageIO.read(new File(sourceDir + "textures/green_platform.png")),
                        "ground")
        );

        tileM = new TileManager(this);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; // 0.01666
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        World.getInstance().update(1);
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        World.getInstance().draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}