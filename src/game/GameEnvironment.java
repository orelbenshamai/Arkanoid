package game;
import biuoop.DrawSurface;
import java.util.ArrayList;
import geometryprimitives.Rectangle;
import geometryprimitives.Line;
import geometryprimitives.Point;
import others.Collidable;
import others.Block;
import others.CollisionInfo;

/**
 * this class implements a game environment which holds all of the objects that a ball can collide with.
 *
 * @author Orel Ben Shamay 318869658
 */
public class GameEnvironment {

    // the collidables in the game environment
    private ArrayList<Collidable> collidables;

    /**
     * this method creates a new game environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * this method adds a collidable to the game environment.
     *
     * @param c Collidable a new collidable to be added
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * this method returns the list of collidables.
     *
     * @return java.util.List<Collidable>
     */
    public java.util.List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * this methods draws all of the collidables on a given surface.
     *
     * @param d DrawSurface a surface to draw the collidables on
     */
    public void drawCollidables(DrawSurface d) {
        for (Collidable collidable : this.collidables) {
            ((Block) collidable).drawOn(d);
        }
    }

    /**
     * this method returns the closest collision of an object with any of the collidables in the game environment.
     * if the object will not collide the method returns null.
     *
     * @param trajectory Line the trajectory of the object
     * @return CollisionInfo the collision information, collision point and the object involved in the collision
     *         if no collision the return null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        // list to store all of the collisions point
        ArrayList<Point> collisions = new ArrayList<>();
        // list to store all of the collidables that the object collided with
        ArrayList<Collidable> collidablesList = new ArrayList<>();
        // iterate on the collidables and check for collisions
       for (Object collidable : this.collidables) {
           Rectangle rectangle = ((Collidable) collidable).getCollisionRectangle();
           Point collision = trajectory.closestIntersectionToStartOfLine(rectangle);
           // if a collision has occurred, add the collision point and the object involved in the collision to a list
           if (collision != null) {
               collisions.add(collision);
               collidablesList.add((Collidable) collidable);
           }
       }
       // if no collision has occurred then return null
        if (collisions.isEmpty()) {
            return null;
        }
       // find the closest collision
       int index = 0;
       for (int i = 1; i < collisions.size(); i++) {
           double newDistance = trajectory.start().distance(collisions.get(i));
           double closestDistance = trajectory.start().distance(collisions.get(index));
           if (newDistance < closestDistance) {
               index = i;
           }
       }
       // return new collision info containing the object collided with, and the collision point
       return new CollisionInfo(collisions.get(index), collidablesList.get(index));
    }

    /**
     * this method removes a collidable.
     *
     * @param c Collidable
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
}
