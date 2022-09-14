package Physics;

public class CollideProcessor {

    static boolean collides(Circle c, Rectangle r) {
        if (c.containsPoint(r.r(), r.t()) || c.containsPoint(r.l(), r.b()) || c.containsPoint(r.l(), r.t()) || c.containsPoint(r.r(), r.b())) {
            return true;
        }
        return (Math.abs(c.center.y - r.center.y) < (c.r + r.h / 2)) && (Math.abs(c.center.x - r.center.x) < (c.r + r.w / 2));
    }

    static boolean collides(Circle c1, Circle c2) {
        return Math.pow(c1.center.x - c2.center.x, 2) + Math.pow(c1.center.y - c2.center.y, 2) < Math.pow(c1.r + c2.r, 2);
    }

    static boolean collides(Rectangle r1, Rectangle r2) {
        return (r1.l() < r2.r() && r1.r()  > r2.l() && r1.t() > r2.b() && r1.b() < r2.t());
    }

    static boolean collides(Circle c, LineSegment l) {
        return false; //TODO: make it work
    }

    static boolean collides(Rectangle r, LineSegment l) {
        return false; //TODO: make it work
    }

    static boolean collides(LineSegment l1, LineSegment l2) {
        return false; //TODO: make it work
    }

    static Vector2D normalToCollision(Circle c1, Circle c2) {
        if (!collides(c1, c2)) {
            return new Vector2D(0, 0);
        }
        return new Vector2D(c2.center.x - c1.center.x, c2.center.y - c1.center.y);
    }

    static Vector2D normalToCollision(Rectangle r1, Rectangle r2) {
        if (!collides(r1, r2)) {
            return new Vector2D(0, 0);
        }
        if (
                (Math.abs(r1.center.x - r2.center.x) < (r1.w + r2.w) / 2) &&
                (Math.abs(r1.center.y - r2.center.y) < (r1.h + r2.h) / 2)
            )
        {
            if((r1.center.y - r2.center.y) * (r1.center.x - r2.center.x) > 0) {
                return new Vector2D(1, 1);
            }
            else {
                return new Vector2D(-1, 1);
            }
        }
        if (Math.abs(r1.center.y - r2.center.y) < (r1.h + r2.h) / 2)
        {
            return new Vector2D(1, 0);
        }
        else
        {
            return new Vector2D(0, 1);
        }
    }

    static Vector2D normalToCollision(Circle c, Rectangle r) {
        if (!collides(c, r)) {
            return new Vector2D(0, 0);
        }

        if (c.center.x < r.r() && c.center.x > r.l()) {
            if (c.center.y > r.t()) {
                return new Vector2D(0, 1);
            } else {
                return new Vector2D(0, -1);
            }
        }
        if (c.center.y < r.t() && c.center.y > r.b()) {
            if (c.center.x > r.r()) {
                return new Vector2D(1, 0);
            } else {
                return new Vector2D(-1, 0);
            }
        }
        if (c.containsPoint(r.r(), r.t())) {
            return new Vector2D(c.center.x - r.r(), c.center.y - r.t());
        }
        if (c.containsPoint(r.r(), r.b())) {
            return new Vector2D(c.center.x - r.r(), c.center.y - r.b());
        }
        if (c.containsPoint(r.l(), r.b())) {
            return new Vector2D(c.center.x - r.l(), c.center.y - r.b());
        }
        if (c.containsPoint(r.l(), r.t())) {
            return new Vector2D(c.center.x - r.l(), c.center.y - r.t());
        }
        return new Vector2D(0, 0);
    }

    static boolean collides(Collider c1, Collider c2) {
        if (c1.getClass() == Circle.class) {
            if (c2.getClass() == Circle.class) {
                return collides((Circle) c1, (Circle) c2);
            }
            if (c2.getClass() == Rectangle.class) {
                return collides((Circle) c1, (Rectangle) c2);
            }
        }
        if (c1.getClass() == Rectangle.class) {
            if (c2.getClass() == Circle.class) {
                return collides((Circle) c2, (Rectangle) c1);
            }
            if (c2.getClass() == Rectangle.class) {
                return collides((Rectangle) c1, (Rectangle) c2);
            }
        }
        return false;
    }
    static Vector2D normalToCollision(Collider c1, Collider c2) {
        if (c1.getClass() == Circle.class) {
            if (c2.getClass() == Circle.class) {
                return normalToCollision((Circle) c1, (Circle) c2);
            }
            if (c2.getClass() == Rectangle.class) {
                return normalToCollision((Circle) c1, (Rectangle) c2);
            }
        }
        if (c1.getClass() == Rectangle.class) {
            if (c2.getClass() == Circle.class) {
                return normalToCollision((Circle) c2, (Rectangle) c1);
            }
            if (c2.getClass() == Rectangle.class) {
                return normalToCollision((Rectangle) c1, (Rectangle) c2);
            }
        }
        return new Vector2D(0, 0);
    }
}
