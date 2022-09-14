package main.tile;

import Physics.Actor;
import Physics.Body;
import main.GamePanel;

import java.awt.image.BufferedImage;

public class Tile extends Actor {

    public Tile(Body body, GamePanel gp, BufferedImage image) {
        this(body, gp, image, "green");
    }

    public Tile(Body body, GamePanel gp, BufferedImage image, String tag) {
        super(body, gp);
        this.image = image;
        this.body.tag = tag;
    }

    public Tile(Body body2, GamePanel gp, BufferedImage image, String tag, double dx) {
        super(body2, gp);
        this.image = image;
        this.body.tag = tag;
        this.body.velocity.x = dx;
    }

    public void update() {

    }

}
