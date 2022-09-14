package Physics;
import Physics.Body;

import main.GamePanel;
import main.tile.Tile;
import main.tile.TileManager;

import java.awt.*;
import java.util.ArrayList;

public class World {

    final double pivotV = 10;
    Vector2D pivot;
    Vector2D pivotDest;
    ArrayList<Body> bodies;
    ArrayList<Actor> actors;

    TileManager tileManager;

    public Vector2D g;
    public Vector2D f;
    private static final World instance = new World();
    public static World getInstance()
    {
        return instance;
    }
    private World()
    {
        pivot = new Vector2D(0, 0);
        pivotDest = new Vector2D(0, 0);
        g = new Vector2D(0, -0.5);
        f = new Vector2D(0.94, 1);
        bodies = new ArrayList<>();
        actors = new ArrayList<>();
    }

    public void setTileManager(TileManager manager)
    {
        this.tileManager = manager;
    }

    public void addBody(Body body)
    {
        bodies.add(body);
    }

    public void addActor(Actor actor)
    {
        actors.add(actor);
        bodies.add(actor.body);
    }

    public void update(float delta)
    {
        pivot.y += (pivotDest.y - pivot.y) / pivotV;
        for(Body b: bodies)
        {
            b.velocity.x += g.x;
            b.velocity.y += g.y;
            b.velocity.x *= f.x;
            b.velocity.y *= f.y;
            if (Math.abs(b.velocity.x) < 0.5) {
                b.velocity.x = 0;
            }
            b.update(delta);

        }


        Body toDelete = null;
        for (Body body: bodies) {
            if (body.type == BodyType.STATIC)
            {
                continue;
            }

            //TODO: Make the below function work
            if (body.tag != "ground" && body.tag != "player") {
                if (body.position.y < pivot.y + 576) {
                    toDelete = body;
                    System.out.println("a");
                }
            }

            //TODO: Make it so that a death screen and restart button appear and are placed at the bottom of the world, once the bottom is offscreen

            for (Body with: bodies) {

                if (body == with)
                {
                    continue;
                }
                if (CollideProcessor.collides(body.collider, with.collider))
                {
                    //add if statement to check type of collided tile (Actor[] tile may have to be public) and vary effect accordingly

                    if (body.tag == "player" && body.velocity.y < 0)
                    {

                        //TODO: Add special effects for red and blue colored tiles
                        if (with.tag != "red")
                        {
                            body.velocity.y = 20;
                            this.pivotDest.y = with.position.y;

                        } else {
                            //TODO:Add code to change red's sprite and make red fall when stepped on (may have to resize the red tile for the differently sized sprite)

                        }
                        if (with.tag == "white")
                        {
                            toDelete = with;
                        }

                        //TODO: Make the below function when uncommented (it just makes the player slides and vibrates right now), possibly add another object like soccer ball to demonstrate physics in-class
                        //body.velocity = CollideProcessor.normalToCollision((Rectangle) body.collider, (Rectangle) with.collider);


                    }

                }
            }
        }


        if (toDelete != null)
        {
            deleteBody(toDelete);
            tileManager.createNewTileRandom();
        }
    }

    public void draw(Graphics2D g2)
    {
        g2.translate(pivot.x, pivot.y);
        for (Actor actor: actors) {
            actor.draw(g2);
        }
    }

    public void deleteActor(Actor actor)
    {
        int indA = actors.indexOf(actor);
        int indB = bodies.indexOf(actor.body);
        actors.remove(indA);
        bodies.remove(indB);
    }

    public void deleteBody(Body body)
    {
        int i = 0;
        for (Actor actor: actors) {
            if (actor.body == body)
            {
                break;
            }
            ++i;
        }
        tileManager.deleteTile((Tile) actors.get(i));
        actors.remove(i);
        bodies.remove(body);
    }
}
