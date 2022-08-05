package game;
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import java.awt.Color;
import geometryprimitives.Rectangle;
import geometryprimitives.Line;
import geometryprimitives.Point;
import others.Velocity;
import ball.Ball;
import others.Sprite;
import others.Collidable;


/**
 * this class implements a paddle controlled by the user, which is a sprite and a collidable.
 * this class has the following methods:
 * Constructors, notify that time passed, adds the paddle to a given game, hit method, get collision rectangle
 * draws the paddle on a given surface and moves the paddle to the left and the right
 *
 * @author Orel Ben Shamay 318869658
 */
public class Paddle implements Sprite, Collidable {

    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;
    private int speed;
    private int width;
    private int height;
    private static final int LEFT_BORDER = 20;
    private static final int RIGHT_BORDER = 780;
    private static final int NUM_OF_REGIONS = 5;

    /**
     * this method constructs a new paddle.
     *
     * @param keyboardSensor KeyboardSensor
     * @param rectangle Rectangle the shape of the paddle
     * @param color Color
     * @param speed int the speed of the paddle
     * @param nWidth int width of the paddle
     * @param nHeight int height of the paddle
     */
    public Paddle(KeyboardSensor keyboardSensor, Rectangle rectangle, Color color, int speed, int nWidth, int nHeight) {
       this.keyboard = keyboardSensor;
       this.rectangle = rectangle;
       this.color = color;
       this.speed = speed;
       this.width = nWidth;
       this.height = nHeight;
    }

    /**
     * this method moves the paddle to the left.
     */
    public void moveLeft() {
        if (this.rectangle.getUpperLeft().getX() == LEFT_BORDER) {
            return;
        }
        double newX = this.rectangle.getUpperLeft().getX() - this.speed;
        double newY = this.rectangle.getUpperLeft().getY();
        this.rectangle = new Rectangle(new Point(newX, newY), this.width, this.height);
    }

    /**
     * this method moves the paddle to the right.
     */
    public void moveRight() {
        if (this.rectangle.getUpperLeft().getX() + this.rectangle.upperEdge().length() == RIGHT_BORDER) {
            return;
        }
        double newX = this.rectangle.getUpperLeft().getX() + this.speed;
        double newY = this.rectangle.getUpperLeft().getY();
        this.rectangle = new Rectangle(new Point(newX, newY), this.width, this.height);
    }

    /**
     * this method notifies the paddle that time has passed.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * this method draws the paddle on a given surface.
     *
     * @param d DrawSurface
     */
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d, this.color);
    }

    /**
     * this method returns the shape of the paddle.
     *
     * @return Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * this method returns new velocity after an hit with the paddle.
     *
     * @param hitter Ball
     * @param collisionPoint Point the collision point
     * @param currentVelocity Velocity the current velocity before the hit
     * @return Velocity new velocity according to the hit in the paddle
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line upper = this.rectangle.upperEdge();
        Line left = this.rectangle.leftEdge();
        Line right = this.rectangle.rightEdge();
        // get the region size
        double regionSize = upper.length() / NUM_OF_REGIONS;
        // convert the velocity into a speed vector
        double speedVector = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        if (upper.isPointOnLine(collisionPoint)) {
            if (upper.start().distance(collisionPoint) < (1 * regionSize)) {
                return Velocity.fromAngleAndSpeed(-60, speedVector);
            } else if (upper.start().distance(collisionPoint) < (2 * regionSize)) {
                return Velocity.fromAngleAndSpeed(-30, speedVector);
            } else if (upper.start().distance(collisionPoint) < (3 * regionSize)) {
                return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
            } else if (upper.start().distance(collisionPoint) < (4 * regionSize)) {
                return Velocity.fromAngleAndSpeed(30, speedVector);
            } else if (upper.start().distance(collisionPoint) < (5 * regionSize)) {
                return Velocity.fromAngleAndSpeed(60, speedVector);
            }
        } else if (left.isPointOnLine(collisionPoint) || right.isPointOnLine(collisionPoint)) {
            return new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy());
        }
        return currentVelocity;
    }

    /**
     * this method adds the paddle to the game, as a sprite and a collidable.
     *
     * @param g Game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
