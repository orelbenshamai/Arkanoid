package ball;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import game.GameLevel;
import game.GameEnvironment;
import geometryprimitives.Point;
import geometryprimitives.Line;
import geometryprimitives.Rectangle;
import others.Sprite;
import others.HitListener;
import others.HitNotifier;
import others.Velocity;
import others.CollisionInfo;
import others.Collidable;

/**
 * this class implements a ball with a center point, radius, color, velocity and the game in which the ball is in.
 * this class has the following methods:
 * constructors, getters, setters, draws the ball on a surface, moves the ball and adds the ball to a given game.
 *
 * @author Orel Ben Shamay 318869658
 */
public class Ball implements Sprite, HitNotifier {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment environment;
    private List<HitListener> hitListeners;
    // constants used in the class
    private static final double PADDLE_SPEED = 10;
    private static final int LEFT_BORDER = 20;
    private static final int RIGHT_BORDER = 780;

    /**
     * creates a new ball.
     *
     * @param center Point the center of the ball
     * @param r      int the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = null;
    }
    /**
     * creates a new ball.
     *
     * @param x int x coordinate of the center of the ball
     * @param y int y coordinate of the center of the ball
     * @param r      int the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(int x, int y, int r, Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.velocity = null;
    }
    /**
     * creates a new ball.
     *
     * @param center Point the center of the ball
     * @param r      int the radius of the ball
     * @param color  the color of the ball
     * @param newEnvironment GameEnvironment a new environment for the ball
     */
    public Ball(Point center, int r, Color color, GameEnvironment newEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = null;
        this.environment = newEnvironment;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * @return int the x coordinate of the center of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return int the y coordinate of the center of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return Ball the center point of the ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * @return the radius(size) of the ball
     */
    public int getSize() {
        return radius;
    }

    /**
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * @return Velocity the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * this method sets a velocity to the ball.
     *
     * @param v Velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * this method sets a game environment to ball.
     *
     * @param newEnvironment gameEnvironment
     */
    public void setEnvironment(GameEnvironment newEnvironment) {
        this.environment = newEnvironment;
    }

    /**
     * this method sets a random color for a ball.
     */
    public void setRandomColor() {
        Random rand = new Random();
        int g = rand.nextInt(255);
        int r = rand.nextInt(255);
        int b = rand.nextInt(255);
        this.color = new Color(r, g, b);
    }

    /**
     * this method sets a new radius for the ball.
     *
     * @param newRadius int new radius for the ball
     */
    public void setRadius(int newRadius) {
        this.radius = newRadius;
    }

    /**
     * this method sets a velocity to the ball with dx and dy.
     *
     * @param dx double
     * @param dy double
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * this method draws a ball on a given surface.
     *
     * @param surface DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), radius);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * this method moves the ball one step according to the ball's velocity and the collidables in the game environment.
     */
    public void moveOneStep() {
        if (this.velocity == null) {
            return;
        }
        double newX = this.getX() + this.velocity.getDx();
        double newY = this.getY() + this.velocity.getDy();
        Line trajectory = new Line(this.getX(), this.getY(), newX, newY);
        CollisionInfo newCollision = this.environment.getClosestCollision(trajectory);
        // if it did collide
        if (newCollision != null) {
            double newPointX = this.getX(), newPointY = this.getY();
            if (this.velocity.getDx() < 0) {
                newPointX = newCollision.collisionPoint().getX() + this.radius;
            } else if (this.velocity.getDx() > 0) {
                newPointX = newCollision.collisionPoint().getX() - this.radius;
            }
            if (this.velocity.getDy() < 0) {
                newPointY = newCollision.collisionPoint().getY() + this.radius;
            } else if (this.velocity.getDy() > 0) {
                newPointY = newCollision.collisionPoint().getY() - this.radius;
            }
            // place the ball a little before the collision point
            this.center = new Point(newPointX, newPointY);
            Collidable newCollisionObject = newCollision.collisionObject();
            // change the velocity according to the collision
            this.velocity = newCollisionObject.hit(this, newCollision.collisionPoint(), this.velocity);
            // check if the ball is inside the paddle, if yes then move it out
            while (this.isBallInPaddle()) {
                this.moveBallOutOfPaddle();
            }
        } else {
            this.center = this.velocity.applyToPoint(this.center);
        }
    }
    /**
     * this methods adds the ball to a given game as a sprite.
     *
     * @param g Game the game to add to ball to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * this method checks if the ball is inside the paddle.
     *
     * @return boolean true if the ball is in a paddle, otherwise false
     */
    public boolean isBallInPaddle() {
        // the paddle in saved in the 0th location in the collidables
        Collidable paddle = this.environment.getCollidables().get(0);
        Rectangle rectangle = paddle.getCollisionRectangle();
        boolean condition1 = this.getY() >= rectangle.getUpperLeft().getY();
        boolean condition2 = this.getY() <= rectangle.getUpperLeft().getY() + rectangle.getHeight();
        boolean condition3 = this.getX() >= rectangle.getUpperLeft().getX();
        boolean condition4 = this.getX() <= rectangle.getUpperLeft().getX() + rectangle.getWidth();
        return condition1 && condition2 && condition3 && condition4;

    }

    /**
     * this method moves the ball out of the paddle.
     */
    public void moveBallOutOfPaddle() {
        Collidable paddle = this.environment.getCollidables().get(0);
        Rectangle rectangle = paddle.getCollisionRectangle();
        double newX = rectangle.getUpperLeft().getX() + rectangle.getWidth();
        double newY = rectangle.getUpperLeft().getY();
        Point upperRight = new Point(newX, newY);
        double height = rectangle.getUpperLeft().getY();
        /*
        if the ball is closer to the right side of the paddle, move it to the right out of the paddle
        else move it to the left out of the paddle
         */
        if (this.center.distance(rectangle.getUpperLeft()) > this.center.distance(upperRight)) {
            this.center = new Point(this.getX() + PADDLE_SPEED + 1, height);
        } else {
           this.center = new Point(this.getX() - PADDLE_SPEED + 1, height);
        }
        /*
         if the ball has passed the left or right boundaries of the screen, move it up so it will stop
         colliding with the paddle
         */
        if (this.center.getX() + this.radius >= RIGHT_BORDER) {
            this.center = new Point(this.getX() - PADDLE_SPEED, height -  this.radius);
        } else if (this.center.getX() - this.radius <= LEFT_BORDER) {
            this.center = new Point(this.getX() + PADDLE_SPEED, height -  this.radius);
        }
    }

    /**
     * this methods removes the ball from the game.
     *
     * @param game Game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
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