package others;
import ball.Ball;
import biuoop.DrawSurface;
import game.GameLevel;
import geometryprimitives.Rectangle;
import geometryprimitives.Line;
import geometryprimitives.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * this class implements a block which is collidable and a sprite.
 * this class has the following methods:
 * constructors, getters, setters, draw on a given surface, time passed which moves the ball, adds the block to a given
 * game and the hit method which returns new velocity after an expected hit to the block
 *
 * @author Orel Ben Shamay 318869658
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle rectangle;
    private java.awt.Color color;
    private List<HitListener> hitListeners;

    /**
     * this method creates a new block which is in a shape of a rectangle and with a color.
     *
     * @param newRectangle Rectangle the shape of the block
     * @param newColor Color the color of the block
     */
    public Block(Rectangle newRectangle, java.awt.Color newColor) {
        this.rectangle = newRectangle;
        this.color = newColor;
        this.hitListeners = new ArrayList<>();
    }

    /**
     *
     * @return Rectangle returns the shape of the block
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    /**
     *
     * @return java.awt.Color returns the color of the block
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * this method returns the velocity after an object with a velocity hits the block in a given point.
     *
     * @param hitter Ball
     * @param collisionPoint Point the collision point
     * @param currentVelocity Velocity the current velocity before the hit
     * @return Velocity the velocity after the hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // array to store the rectangle edges
        Line[] rectangleBoundaries = new Line[4];
        // left edge
        rectangleBoundaries[0] = this.rectangle.leftEdge();
        // upper edge
        rectangleBoundaries[1] = this.rectangle.upperEdge();
        // right edge
        rectangleBoundaries[2] = this.rectangle.rightEdge();
        // lower edge
        rectangleBoundaries[3] = this.rectangle.lowerEdge();

        for (int i = 0; i < rectangleBoundaries.length; i++) {
            // hits a vertical edge (left or right edges) so we change horizontal direction
            if ((i == 0 || i == 2) && rectangleBoundaries[i].isPointOnLine(collisionPoint)) {
                this.notifyHit(hitter);
                return new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy());
            }
            // hits an horizontal edge (upper or lower edges) so we change the vertical direction
            if ((i == 1 || i == 3) && rectangleBoundaries[i].isPointOnLine(collisionPoint)) {
                this.notifyHit(hitter);
                return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
            }
        }
        // if the ball didn't hit a block and we don't need to change direction
        return currentVelocity;
    }
    /**
     * this method draws the block on a given surface.
     *
     * @param d DrawSurface the surface to draw the block on
     */
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d, this.getColor());
    }

    @Override
    public void timePassed() {
    }

    /**
     * this method adds the block to the game, both as a sprite and as a collidable.
     *
     * @param g Game the game to add the block in
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * this method removes the block from the game.
     *
     * @param game Game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * notifies that a block was hit.
     *
     * @param hitter Ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
