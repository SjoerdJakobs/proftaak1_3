package OOFramework.Maths;

public class Vector2
{
    public double x;
    public double y;

    public Vector2() {
        this.x = 0.0d;
        this.y = 0.0d;
    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean Equals(Vector2 other) {
        return (this.x == other.x && this.y == other.y);
    }

    public static double Distance(Vector2 vec1, Vector2 vec2) {
        double xDist = vec2.x - vec1.x;
        double yDist = vec2.y - vec1.y;
        return Math.sqrt(xDist*xDist + yDist*yDist);
    }
}
