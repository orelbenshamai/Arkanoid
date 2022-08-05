package geometryprimitives;

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this class implements a rectangle with a point to represent its upper left edge, width and height.
 * this class has the following methods:
 * constructors, getters, setters, draw on a given surface and returns intersections points between the rectangles
 * and a given line.
 *
 * @author Orel Ben Shamay 318869658
 */
public class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;

    /**
     * creates a new rectangle.
     *
     * @param upperLeft Point the upper left point
     * @param width double the width of the rectangle
     * @param height double the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * this method checks if the rectangle intersects with a given line, if yes it returns a list of intersection
     * points, otherwise it returns null.
     *
     * @param line Line to check the intersection points with
     * @return java.util.List<Point> list of intersections points if any, otherwise null
     */
    public List<Point> intersectionPoints(Line line) {
        // array to store the rectangle edges
        Line[] rectangleBoundaries = new Line[4];
        // left edge
        rectangleBoundaries[0] = this.leftEdge();
        // upper edge
        rectangleBoundaries[1] = this.upperEdge();
        // right edge
        rectangleBoundaries[2] = this.rightEdge();
        // lower edge
        rectangleBoundaries[3] = this.lowerEdge();
        // a list for the intersection points
        List<Point> intersections = new ArrayList<>();
        for (Line edge : rectangleBoundaries) {
            if (edge.isIntersecting(line)) {
                intersections.add(edge.intersectionWith(line));
            }
        }
        if (intersections.isEmpty()) {
            return null;
        }
        return intersections;
    }

    /**
     * this method returns the left edge of the rectangle.
     *
     * @return Line the left edge
     */
    public Line leftEdge() {
        Point lowerLeftCorner = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        return new Line(this.upperLeft, lowerLeftCorner);
    }
    /**
     * this method returns the right edge of the rectangle.
     *
     * @return Line the right edge
     */
    public Line rightEdge() {
        double x = this.upperLeft.getX();
        double y = this.upperLeft.getY();
        Point upperRightCorner = new Point(x + this.width, y);
        Point lowerRightCorner = new Point(x + this.width, y + this.height);
        return new Line(upperRightCorner, lowerRightCorner);
    }
    /**
     * this method returns the upper edge of the rectangle.
     *
     * @return Line the upper edge
     */
    public Line upperEdge() {
        Point upperRightCorner = new Point(this.upperLeft.getX() + this.width, upperLeft.getY());
        return new Line(this.upperLeft, upperRightCorner);
    }
    /**
     * this method returns the lower edge of the rectangle.
     *
     * @return Line the lower edge
     */
    public Line lowerEdge() {
        double x = this.upperLeft.getX();
        double y = this.upperLeft.getY();
        Point lowerLeftCorner = new Point(x, y + this.height);
        Point lowerRightCorner = new Point(x + this.width, y + this.height);
        return new Line(lowerLeftCorner, lowerRightCorner);
    }

    /**
     * this method draws the rectangle on a given surface.
     *
     * @param d DrawSurface the surface to draw the rectangle on
     * @param color Color the of the rectangle
     */
    public void drawOn(DrawSurface d, Color color) {
        int x = (int) this.getUpperLeft().getX();
        int y = (int) this.getUpperLeft().getY();
        int rectangleWidth = (int) this.getWidth();
        int rectangleHeight = (int) this.getHeight();
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, rectangleWidth, rectangleHeight);
        d.setColor(color);
        d.fillRectangle(x, y, rectangleWidth, rectangleHeight);

        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, rectangleWidth + 1, rectangleHeight + 1);
    }

    /**
     * this method returns the width of the rectangle.
     *
     * @return double the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * this method returns the height of the rectangle.
     *
     * @return double the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * this method returns the upper left point of the rectangle.
     *
     * @return Point the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}
