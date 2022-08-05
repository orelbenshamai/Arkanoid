package levels;
import geometryprimitives.Point;
import others.Block;
import others.Sprite;
import others.Velocity;
import java.util.List;

/**
 * this is the level information interface, used to implement a level in our game.
 *
 * @author Orel Ben Shamay 318869658
 */
public interface LevelInformation {

    /**
     * get the number of balls in the game.
     *
     * @return int
     */
    int numberOfBalls();

    /**
     * return the initial velocity of each ball.
     *
     * @return List<Velocity>
     */
    List<Velocity> initialBallVelocities();

    /**
     * return the starting points of the balls.
     *
     * @return List<Point>
     */
    List<Point> ballsPositions();

    /**
     * returns the paddle's speed.
     *
     * @return int
     */
    int paddleSpeed();

    /**
     * return the paddle's width.
     *
     * @return int
     */
    int paddleWidth();

    /**
     * return the level name will be displayed at the top of the screen.
     *
     * @return String
     */
    String levelName();

    /**
     * returns a sprite with the background of the level.
     *
     * @return Sprite
     */
    Sprite getBackground();

    /**
     * return the blocks that make up this level, each block contains its size, color and location.
     *
     * @return List<Block>
     */
    List<Block> blocks();

    /**
     * return the number of blocks that should be removed before the level is considered to be "cleared".
     *
     * @return int
     */
    int numberOfBlocksToRemove();

    /**
     * return the paddle's start point.
     *
     * @return Point
     */
    Point paddleStart();
}
