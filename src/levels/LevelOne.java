package levels;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import others.Block;
import others.Sprite;
import others.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this class implements level one in our game.
 *
 * @author Orel Ben Shamay 318869658.
 */
public class LevelOne implements LevelInformation {

    private static final int BLOCK_SIZE = 40;
    private static final Point BLOCK_START = new Point(360, 100);
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int DEPTH = 20;
    private static final Point PADDLE_START = new Point(330, HEIGHT - 2 * DEPTH + 5);
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_SPEED = 10;
    private static final Point START_OF_BALL = new Point(380, 550);
    private static final int NUM_OF_BALLS = 1;
    private static final int ANGLE = 0;
    private static final int SPEED = 4;
    private static final String LEVEL_NAME = "Direct Hit";

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity velocity = Velocity.fromAngleAndSpeed(ANGLE, SPEED);
        List<Velocity> list = new ArrayList<>();
        list.add(velocity);
        return list;
    }

    @Override
    public List<Point> ballsPositions() {
        List<Point> positions = new ArrayList<>();
        positions.add(START_OF_BALL);
        return positions;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return LEVEL_NAME;
    }

    @Override
    public Sprite getBackground() {
        Point point = new Point(DEPTH, DEPTH);
        return new Block(new Rectangle(point, WIDTH - DEPTH, WIDTH - DEPTH), Color.BLACK);
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        list.add(new Block(new Rectangle(BLOCK_START, BLOCK_SIZE, BLOCK_SIZE), Color.RED));
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

    @Override
    public Point paddleStart() {
        return PADDLE_START;
    }
}
