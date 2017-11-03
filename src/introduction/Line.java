package introduction;

/**
 * Given two points, get the length, vector
 * and check if the two lines are the same length
 */
public class Line {
    private Point p1, p2;

    Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     * Get the vector of two points.
     *
     * @return Vector2D
     */
    public Vector2D getVector() {
        return new Vector2D(p1, p2);
    }

    /**
     * Get the length of two points
     *
     * @return length
     */
    public Double getLength() {
        return Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
    }

    /**
     * Check if the two lines have the same length
     *
     * @param l line to compare
     * @return true if same, false otherwise
     */
    public Boolean isSameLengthAs(Line l) {
        return (Math.abs(getLength() - l.getLength()) < 0.00001);
    }
}
