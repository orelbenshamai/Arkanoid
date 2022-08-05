package geometryprimitives;
import java.util.List;

/**
 * this class implements a line represented by two points, one for the start and another for the end.
 * this class has the following methods:
 * constructors, setters, getters, get the slope of the line, check if and where two lines are intersecting
 * get the length of a line, get the start and end of a line and check if two lines are equal
 *
 * @author Orel Ben Shamay 318869658
 */
public class Line {
    // starting point of the line
    private Point start;
    // ending point of the line
    private Point end;
    // a constant used for comparing doubles
    private static final double EPSILON = 0.00000001;

    /**
     * this method implements a line with a starting and ending points.
     *
     * @param start the starting point of the line
     * @param end   the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * this method implements a line with the x and y coordinate for the starting and ending points.
     *
     * @param x1 x coordinate of the starting point
     * @param y1 y coordinate of the starting point
     * @param x2 x coordinate of the ending point
     * @param y2 y coordinate of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * this method returns the length of a line.
     *
     * @return double the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * this method returns the middle point of a line.
     *
     * @return Point the middle point of the line
     */
    public Point middle() {
        double newX = (this.start.getX() + this.end.getX()) / 2;
        double newY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(newX, newY);
    }

    /**
     * this method returns the start point of a line.
     *
     * @return Point the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * this method returns the end point of a line.
     *
     * @return Point the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * this method returns the slope of a line.
     *
     * @return double the slope of the line
     */
    public double getSlope() {
        if (this.start.getX() == this.end.getX()) {
            return Double.POSITIVE_INFINITY;
        }
        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * this method checks if two lines can intersect by checking their slopes.
     *
     * @param other another line to check intersection with
     * @return boolean true if the lines can intersect, otherwise false
     */
    public boolean isIntersecting(Line other) {
        if (this.equals(other)) {
           return false;
        }
        // if one of the lines is a point
        if (this.start.equals(this.end)) {
            return other.isPointOnLine(this.start);
        }
        if (other.start().equals(other.end())) {
            return this.isPointOnLine(other.start());
        }
        return this.intersectionWith(other) != null;
    }

    /**
     * this method returns the intersection point of two lines, if no intersection was found
     * the method returns null.
     *
     * @param other Line another line to check the intersection with
     * @return Point the intersection point if found, otherwise null
     */
    public Point intersectionWith(Line other) {
        double m1 = this.getSlope();
        double m2 = other.getSlope();
        Point intersection;

        // if one of the lines is a point and it is is on the other line
        if (this.pointAndLineIntersection(other) != null) {
            return this.pointAndLineIntersection(other);
        }
        // if the lines has the same slope
        if (m1 == m2) {
            return this.sameSlopeIntersection(other);
        }
        // if one of the lines is parallel to the axis y
        if (Double.isInfinite(m1)) {
            double b2 = other.start.getY() - (m2 * (other.start.getX()));
            intersection = new Point(this.start.getX(), (m2 * this.start.getX()) + b2);
        } else if (Double.isInfinite(m2)) {
            double b1 = this.start.getY() - (m1 * (this.start.getX()));
            intersection = new Point(other.start.getX(), (m1 * other.start.getX()) + b1);
        // else, calculate the intersection with the line's equation
        } else {
            double b1 = this.start.getY() - (m1 * (this.start.getX()));
            double b2 = other.start.getY() - (m2 * (other.start.getX()));
            double newX = (b2 - b1) / (m1 - m2);
            double newY = (m1 * newX) + b1;
            intersection = new Point(newX, newY);
        }
        // check if the intersection point is on the lines
        if (this.linesIntersectionValidator(other, intersection)) {
            return intersection;
        }
        return null;
    }

    /**
     * this method checks for intersection points between two lines with the same slope.
     *
     * @param other another line with the same slope to check intersection with
     * @return Point the intersection point if was found, otherwise null
     */
    public Point sameSlopeIntersection(Line other) {
        Point intersection = null;
        // if one of the lines is contained within the other, then return null
        if (this.isPointOnLine(other.start()) && this.isPointOnLine(other.end())
                || other.isPointOnLine(this.start()) && other.isPointOnLine(this.end())) {
            return null;
        }

        if (this.start.equals(other.end())) {
            intersection = new Point(this.start.getX(), this.start.getY());
        } else if (this.end.equals(other.start())) {
            intersection = new Point(this.end().getX(), this.end().getY());
        } else if (this.start.equals(other.start())) {
            intersection = new Point(this.start.getX(), this.start.getY());
        } else if (this.end.equals(other.end())) {
            intersection = new Point(this.end.getX(), this.end().getY());
        }
        return intersection;
    }

    /**
     * this method checks if the intersection point between two lines are on the lines.
     *
     * @param line         another line to check the intersection with
     * @param intersection Point the intersection point to validate
     * @return boolean true if the lines really intersect, otherwise false
     */
    public boolean linesIntersectionValidator(Line line, Point intersection) {
        return this.isPointOnLine(intersection) && line.isPointOnLine(intersection);
    }

    /**
     * this method checks if a point is on a line.
     *
     * @param point Point to check if it is on the line
     * @return boolean true if the point is on the line, otherwise false
     */
    public boolean isPointOnLine(Point point) {
        double startToIntersection = point.distance(this.start);
        double endToIntersection = point.distance(this.end);
        double checkedLength = startToIntersection + endToIntersection;
        return Math.abs(checkedLength - this.length()) < EPSILON;
    }

    /**
     * this method checks if a line is actually a point and returns the intersection with another line.
     * if no intersection was found, the method returns null
     *
     * @param other Line another line to check the intersection with
     * @return Point intersection
     */
    public Point pointAndLineIntersection(Line other) {
        if (this.start.equals(this.end)) {
            if (other.isPointOnLine(this.start)) {
                return this.start;
            }
        }
        if (other.start().equals(other.end())) {
            if (this.isPointOnLine(other.start())) {
                return other.start();
            }
        }
        return null;
    }

    /**
     * this method checks if two lines are equal.
     *
     * @param other another line to compare with
     * @return boolean true if the lines are equal, otherwise false
     */
    public boolean equals(Line other) {
        boolean condition1 = this.start.equals(other.start()) && this.end.equals(other.end());
        boolean condition2 = this.start.equals(other.end()) && this.end.equals(other.start());
        return condition1 || condition2;
    }

    /**
     * this method returns the closest intersection point to the start of a line and a given rectangle.
     * if the line doesn't intersect with the rectangle, the method returns null.
     *
     * @param rect Rectangle to check the intersection with
     * @return Point the closest intersection point, if none exist then null
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints;
        Line ourLine = new Line(this.start, this.end);
        intersectionPoints = rect.intersectionPoints(ourLine);
        // if the line does not intersect with the rectangle, return null
        if (intersectionPoints == null) {
            return null;
        }
        // find the closest intersection to the start of the line
        double minimumDistance = this.start.distance(intersectionPoints.get(0));
        Point returnedPoint = intersectionPoints.get(0);
        for (int i = 1; i < intersectionPoints.size(); i++) {
            if (this.start.distance(intersectionPoints.get(i)) < minimumDistance) {
                minimumDistance = this.start.distance(intersectionPoints.get(i));
                returnedPoint = intersectionPoints.get(i);
            }
        }
        return returnedPoint;
    }
}