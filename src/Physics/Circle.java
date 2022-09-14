package Physics;

public class Circle extends Collider {

    public double r;

    public Circle(Vector2D center, double r) {
        super(center);
        this.r = r;
    }

    public boolean containsPoint(double x, double y) {
        return Math.pow(x - center.x, 2) + Math.pow(y - center.y, 2) < Math.pow(r, 2);
    }

    public boolean containsPoint(Vector2D point) {
        return containsPoint(point.x, point.y);
    }

    public double l() // left
    {
        return center.x - r;
    }
    public double r() // right
    {
        return center.x + r;
    }
    public double t() // top
    {
        return center.y + r;
    }
    public double b() // bottom
    {
        return center.y - r;
    }
    public double w()
    {
        return 2 * r;
    }

    public double h()
    {
        return 2 * r;
    }
}
