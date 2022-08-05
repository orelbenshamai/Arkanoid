package others;
import java.util.Random;

import ball.Ball;
import geometryprimitives.Point;

/**
 * this class implements velocity with a movement on the axis x and axis y.
 * this class hold the following methods:
 * getters, setters, constructors, application to a point, converts angle and speed to a velocity
 * and sets a velocity to a given ball according to his size
 *
 * @author Orel Ben Shamay 318869658
 */
public class Velocity {

    private double dx;
    private double dy;
    // a constant used for the velocityAccordingToSize method
    private static final int SPEED_FACTOR = 5;
    // a constant used for comparing doubles
    private static final double EPSILON = 0.00000001;

    /**
     * this method implements a velocity.
     *
     * @param dx double movement on the axis x
     * @param dy double movement on the axis y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * this method sets new velocity.
     *
     * @param newDx double movement on the axis x
     * @param newDy double movement on the axis y
     */
    public void setVelocity(double newDx, double newDy) {
        this.dx = newDx;
        this.dy = newDy;
    }

    /**
     * @return double dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return double dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * this method converts angle and speed to a velocity.
     *
     * @param angle double the angle of the speed vector
     * @param speed double the size of the speed vector
     * @return Velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle - 90));
        double dy = speed * Math.sin(Math.toRadians(angle - 90));
        if (Math.abs(dx) < EPSILON) {
            dx = 0;
        }
        if (Math.abs(dy) < EPSILON) {
            dy = 0;
        }
        return new Velocity(dx, dy);
    }

    /**
     * this methods applies the velocity into a given point.
     *
     * @param p Point
     * @return Point a new point after the movement
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * this method sets a velocity to a ball according to his size.
     * for balls of size greater than the max size the velocity is identical
     *
     * @param ball    a ball to which we add the velocity
     * @param maxSize int the maximum size of a ball from which the ball has the same velocity
     * @return Velocity the new velocity
     */
    public static Velocity velocityAccordingToSize(Ball ball, int maxSize) {
        // if the ball size is greater than the max size
        if (ball.getSize() >= maxSize) {
            return new Velocity(1, 1);
        }
        Random rand = new Random();
        // generate a random degree for the speed vector
        double angle = rand.nextInt(90);
        // create the speed vector according to the ball's size
        double speedVector = ((double) (maxSize / SPEED_FACTOR)) - ((double) (ball.getSize() / SPEED_FACTOR));
        return Velocity.fromAngleAndSpeed(angle, speedVector);
    }
}