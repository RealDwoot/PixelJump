package Physics;

import Entity.Entity;

public class Body extends Entity {

    public BodyType type;

    public Collider collider;

    public Vector2D velocity;

    public Vector2D position;

    public Body(BodyType type, Collider collider, Vector2D velocity)
    {
        this.type = type;
        this.collider = collider;
        this.position = collider.center;
        this.velocity = velocity;
    }

    public Body(BodyType type, Collider collider)
    {
        this(type, collider, new Vector2D(0, 0));
    }

    public Body(BodyType type, Collider collider, Vector2D velocity, int Lbound, int Rbound)
    {
        this.type = type;
        this.collider = collider;
        this.position = collider.center;
        this.velocity = velocity;
    }

    public void update(double delta)
    {
        if (type == BodyType.STATIC)
        {
            return;
        }

        position.x += velocity.x * delta;
        position.y += velocity.y * delta;
    }

}
