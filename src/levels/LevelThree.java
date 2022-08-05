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
 * this class implements level three in our game.
 *
 * @author Orel Ben Shamay 318869658
 */
public class LevelThree implements LevelInformation {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int DEPTH = 20;
    private static final Point PADDLE_START = new Point(330, HEIGHT - 2 * DEPTH + 5);
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_SPEED = 10;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 20;
    private static final int ROWS_OF_BLOCKS = 6;
    private static final int NUM_OF_BALLS = 2;
    private static final int BALL_SPEED = 4;
    private static final int ANGLE = 45;
    private static final Point START_OF_BLOCKS = new Point(275, 120);
    private static final String LEVEL_NAME = "Green 3";
    private static final Point FIRST_BALL_POSITION = new Point(300, 400);
    private static final Point SECOND_BALL_POSITION = new Point(480, 400);

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(-ANGLE, BALL_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(ANGLE, BALL_SPEED));
        return velocities;
    }

    @Override
    public List<Point> ballsPositions() {
        List<Point> positions = new ArrayList<>();
        positions.add(FIRST_BALL_POSITION);
        positions.add(SECOND_BALL_POSITION);
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
        return new Block(new Rectangle(point, WIDTH - DEPTH, WIDTH - DEPTH), Color.green);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] rowColor = {Color.YELLOW, Color.WHITE, Color.RED, Color.PINK, Color.CYAN, Color.orange};
        double startX = START_OF_BLOCKS.getX(), startY = START_OF_BLOCKS.getY();
        for (int i = 0; i < ROWS_OF_BLOCKS; i++) {
            for (int j = 0; j < 10 - i; j++) {
                Point p = new Point((int) startX + BLOCK_WIDTH * j, (int) startY);
                Rectangle temp = new Rectangle(p, BLOCK_WIDTH, BLOCK_HEIGHT);
                blocks.add(new Block(temp, rowColor[i]));
            }
            startX += BLOCK_WIDTH;
            startY += BLOCK_HEIGHT;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }

    @Override
    public Point paddleStart() {
        return PADDLE_START;
    }
}
