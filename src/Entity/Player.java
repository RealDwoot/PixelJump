package Entity;

import Physics.*;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Actor {

    public String direction;
    public BufferedImage
            player_character_rising_right,
            player_character_rising_left;

    public BufferedImage[] falling_left;
    public BufferedImage[] falling_right;

    KeyHandler keyH;

    public Player(Body body, GamePanel gp, KeyHandler keyH) {
        super(body, gp);
        body.tag = "player";
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        direction = "down-left";
    }

    public void getPlayerImage() {
        //TODO: Fix sprite issue
        try {
            String sourceDir = "/Users/dennislosett/Desktop/CS 1/PixelJump/res/";
            falling_left = new BufferedImage[]{
                    ImageIO.read(new File(sourceDir + "player/player_character_falling_left_1.png")),
                    ImageIO.read(new File(sourceDir + "player/player_character_falling_left_2.png"))};
            falling_right = new BufferedImage[]{
                    ImageIO.read(new File(sourceDir + "player/player_character_falling_right_1.png")),
                    ImageIO.read(new File(sourceDir + "player/player_character_falling_right_2.png"))};
            player_character_rising_right = ImageIO.read(new File(sourceDir + "player/player_character_rising_right.png"));
            player_character_rising_left = ImageIO.read(new File(sourceDir + "player/player_character_rising_left.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed) {

        }

        if (keyH.downPressed) {

        }

        if (keyH.leftPressed) {
            body.velocity.x -= 1;

        }

        if (keyH.rightPressed) {
            body.velocity.x += 1;

        }

        if (!keyH.downPressed && !keyH.upPressed && !keyH.leftPressed && !keyH.rightPressed) {

        }

        body.spriteCounter += 1;
        if (body.spriteCounter > 16) {
            body.spriteNum = 1;
            body.spriteCounter = 0;
        }
        if (body.spriteCounter == 8) {
            body.spriteNum = 0;
        }

        if (body.velocity.y > 15) {
            body.velocity.y = 15;
        }
        if (body.position.y > gp.screenHeight - gp.tileHeight - 9) {
            //body.velocity.y = -java.lang.Math.abs(body.velocity.y);
        }
        if (body.position.x > gp.screenWidth) {
            body.position.x = -48;
        }

        if (body.position.x < -48) {
            body.position.x = gp.screenWidth;
        }
        if (body.velocity.x < 0 || (body.velocity.x == 0 && (direction == "up-left" || direction == "down-left"))) {
            if (body.velocity.y > -1) {
                direction = "down-left";
            }

            if (body.velocity.y < -1) {
                direction = "up-left";
            }
        }

        if (body.velocity.x > 0 || (body.velocity.x == 0 && (direction == "up-right" || direction == "down-right"))) {
            if (body.velocity.y > -1) {
                direction = "down-right";
            }

            if (body.velocity.y < -1) {
                direction = "up-right";
            }
        }

    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);
//        g2.fillRect((int) x, (int) y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "down-left":
                image = falling_left[body.spriteNum];
                break;

            case "down-right":
                image = falling_right[body.spriteNum];
                break;

            case "up-left":
                image = player_character_rising_left;
                break;

            case "up-right":
                image = player_character_rising_right;
                break;

        }
        g2.drawImage((Image) image, (int) (body.collider.l()), gp.screenHeight - (int) body.collider.t(), (int) body.collider.w(), (int) body.collider.h(), null);
        super.draw(g2);
    }
}
