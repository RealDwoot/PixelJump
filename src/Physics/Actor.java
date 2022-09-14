package Physics;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Actor {

    public Body body;
    public BufferedImage image;
    public GamePanel gp;

    public Actor(Body body, GamePanel gp) {
        this(body, gp, null, "");
    }

    public Actor(Body body, GamePanel gp, BufferedImage image, String tag) {
        this.body = body;
        this.gp = gp;
        this.image = image;
        this.body.tag = tag;
    }

    public void draw(Graphics2D g2) {
        if (body.collider.getClass() == Rectangle.class) {
            Rectangle r = (Rectangle) body.collider;
            //g2.drawRect((int) r.l(), gp.screenHeight - (int) r.t(), (int) r.w(), (int) r.h());
            if (image != null) {
                g2.drawImage((Image) image, (int) r.l(), gp.screenHeight - (int) r.t(), (int) r.w(), (int) r.h(), null);
            }
        } else if (body.collider.getClass() == Circle.class) {
            Circle c = (Circle) body.collider;
            //g2.drawOval((int) (c.l()), gp.screenHeight - (int) (c.b()), (int) c.w(), (int) c.h());

        }
        else if (image != null) {
            g2.drawImage(image, (int) body.position.x, (int) body.position.y, gp.tileSize, gp.tileHeight, null);
        }
    }
}