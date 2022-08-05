package geometryprimitives;
/**
 * This class implements a point containing coordinates (x and y).
 * this class has the following methods:
 * constructors, setters, getters, calculating distance between 2 points and check if 2 points are equal
 *
 * @author Orel Ben Shamay 318869658
 */
public class Point {
    // x coordinate of the point
    private double x;
    // y coordinate of the point
    private double y;
    private static final double EPSILON = 0.00000001;

    /**
     * this method implements a point with x and y coordinates.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * this method sets a new x coordinate of a point.
     *
     * @param newX the new x coordinate
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * this method sets a new y coordinate of a point.
     *
     * @param newY the new y coordinate
     */
    public void setY(double newY) {
        this.y = newY;
    }

    /**
     * @return double x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return double y coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * this method calculates and returns the distance between two points.
     *
     * @param other another point from which the distance is being calculated
     * @return double the distance between two points
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow((other.x - this.x), 2) + (Math.pow(other.y - this.y, 2)));
    }

    /**
     * this methods checks if two points are equal.
     *
     * @param other another point to be compared with
     * @return boolean true if the points are equal, otherwise false
     */
    public boolean equals(Point other) {

        return (Math.abs(other.x - this.x) < EPSILON) && (Math.abs(other.y - this.y) < EPSILON);
    }
}