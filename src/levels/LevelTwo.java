package levels;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import others.Block;
import others.Sprite;
import others.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * this class implements level two in our game.
 *
 * @author Orel Ben Shamay
 */
public class LevelTwo implements LevelInformation {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int DEPTH = 20;
    private static final Point PADDLE_START = new Point(200, HEIGHT - 2 * DEPTH + 5);
    private static final int PADDLE_WIDTH = 400;
    private static final int PADDLE_SPEED = 10;
    private static final int NUM_OF_BALLS = 10;
    private static final int NUM_OF_BLOCKS = 15;
    private static final int SPEED = 4;
    private static final int ANGLE = -25;
    private static final int BALL_DISTANCE_X = 50;
    private static final int BALL_DISTANCE_Y = 30;
    private static final int BLOCK_HEIGHT = 20;
    private static final int BLOCK_WIDTH = 50;
    private static final String LEVEL_NAME = "Wide Easy";
    private static final int FIRST_HALF_START_X = 175;
    private static final int FIRST_HALF_START_Y = 400;
    private static final int SECOND_HALF_START_X = 425;
    private static final int SECOND_HALF_START_Y = 280;

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        for (int i = 0; i < NUM_OF_BALLS; i++) {
            // adjust the ball's velocity
            Velocity v = Velocity.fromAngleAndSpeed(ANGLE + (i * (NUM_OF_BALLS / 2)), SPEED);
            list.add(v);
        }
        return list;
    }

    @Override
    public List<Point> ballsPositions() {
        List<Point> positions = new ArrayList<>();
        // first half of the balls
        for (int i = 0; i < NUM_OF_BALLS / 2; i++) {
            Point point = new Point(FIRST_HALF_START_X + (i * BALL_DISTANCE_X),
                    FIRST_HALF_START_Y - (i * BALL_DISTANCE_Y));
            positions.add(point);
        }
        // second half of the balls
        for (int i = NUM_OF_BALLS / 2; i < NUM_OF_BALLS; i++) {
            Point point = new Point(SECOND_HALF_START_X + ((i % (NUM_OF_BALLS / 2)) * BALL_DISTANCE_X),
                    SECOND_HALF_START_Y + ((i % (NUM_OF_BALLS / 2) * BALL_DISTANCE_Y)));
            positions.add(point);
        }
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
        return new Block(new Rectangle(point, WIDTH - DEPTH, WIDTH - DEPTH), Color.LIGHT_GRAY);
    }

    @Override
    public List<Block> blocks() {
        Color color;
        Random rand = new Random();
        List<Block> list = new ArrayList<>();
        ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.RED, Color.RED, Color.ORANGE, Color.ORANGE,
                Color.YELLOW, Color.YELLOW, Color.GREEN, Color.GREEN, Color.GREEN, Color.blue, Color.blue,
                Color.pink, Color.pink, Color.cyan, Color.cyan));
        for (int i = 0; i < NUM_OF_BLOCKS; i++) {
            Point p = new Point(DEPTH + 5 + (i * BLOCK_WIDTH), HEIGHT / 3);
            Block block = new Block(new Rectangle(p, BLOCK_WIDTH, BLOCK_HEIGHT), colors.get(i));
            list.add(block);
        }
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
