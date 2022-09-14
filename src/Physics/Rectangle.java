package Physics;

public class Rectangle extends Collider{
    public double w;
    public double h;

    public Rectangle(double x, double y, double w, double h)
    {
        super(new Vector2D(x, y));
        this.w = w;
        this.h = h;
    }

    public double l() // left
    {
        return center.x - w / 2;
    }
    public double r() // right
    {
        return center.x + w / 2;
    }
    public double t() // top
    {
        return center.y + h / 2;
    }
    public double b() // bottom
    {
        return center.y - h / 2;
    }

    public double w()
    {
        return w;
    }

    public double h()
    {
        return h;
    }
}
