package introduction;

/**
 * 2D Vector
 */
public class Vector2D {
    public Integer x, y;

    /**
     * Creates a 2D vector given two integers
     *
     * @param x first integer
     * @param y second integer
     */
    public Vector2D(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a 2D vector from two points
     *
     * @param p1 first point
     * @param p2 second point
     */
    Vector2D(Point p1, Point p2) {
        this.x = p2.x - p1.x;
        this.y = p2.y - p1.y;
    }

    /**
     * Calculates the dot product of two vectors
     *
     * @param v incoming 2D vector
     * @return the dot product ()
     */
    public int dotProduct(Vector2D v) {
        return (x * v.x) + (y * v.y);
    }

    /**
     * Checks if two vectors are orthogonal to each other
     *
     * @param v incoming vector
     * @return true if yes, false otherwise
     */
    public boolean isOrthogonalTo(Vector2D v) {
        return (dotProduct(v) == 0);
    }
}
