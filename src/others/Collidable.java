package others;
import geometryprimitives.Rectangle;
import geometryprimitives.Point;
import ball.Ball;

/**
 * this is a collidable interface which will be used by objects that can be collided with.
 *
 * @author Orel Ben Shamay 318869658
 */
public interface Collidable {
    /**
     *
     * @return Rectangle
     */
    Rectangle getCollisionRectangle();
    /**
     * this method receives a collision point and the current velocity of an object, and returns the velocity after
     * the hit.
     *
     * @param hitter Ball
     * @param collisionPoint Point the collision point
     * @param currentVelocity Velocity the current velocity before the hit
     * @return Velocity the new velocity after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
