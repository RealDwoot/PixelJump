package Physics;

public class Collider {

    Vector2D center;

    public Collider(Vector2D center) {
        this.center = center;
    }
    public double l() // left
    {
        return center.x;
    }
    public double r() // right
    {
        return center.x;
    }
    public double t() // top
    {
        return center.y;
    }
    public double b() // bottom
    {
        return center.y;
    }
    public double w()
    {
        return 0;
    }

    public double h()
    {
        return 0;
    }
}
