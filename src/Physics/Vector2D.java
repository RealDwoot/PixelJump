package Physics;

public class Vector2D {

    public double x;

    public double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double l() {
        return Math.sqrt(x * x + y * y);
    }

    public double a() {
        return Math.atan2(y, x);
    }

    public double length() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public void normalize()
    {
        double l = length();
        this.x /= l;
        this.y /= l;
    }
}
