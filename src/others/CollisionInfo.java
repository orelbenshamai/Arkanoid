package others;
import geometryprimitives.Point;

/**
 * this class implements an object of collision info which holds the collision point and the object involved
 * in the collision.
 *
 * @author Orel Ben Shamay 318869658
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * create a new collision info object.
     *
     * @param newCollisionPoint Point the new collision point
     * @param newObject Collidable the object involved in the collision
     */
    public CollisionInfo(Point newCollisionPoint, Collidable newObject) {
        this.collisionPoint = newCollisionPoint;
        this.collisionObject = newObject;
    }
    /**
     *
     * @return Point the point at which the collision occurs
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     *
     * @return Collidable the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
