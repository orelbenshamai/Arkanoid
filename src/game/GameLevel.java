package game;
import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import ball.Ball;
import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
import java.util.List;
import biuoop.KeyboardSensor;
import geometryprimitives.Rectangle;
import geometryprimitives.Point;
import levels.LevelInformation;
import others.HitListener;
import others.Counter;
import others.SpriteCollection;
import others.Collidable;
import ball.BallRemover;
import others.Sprite;
import others.Block;
import others.BlockRemover;

/**
 * this class implements a game
 * this class has the following methods:
 * Constructor, add collidables and sprites to the game, initialize and run a game.
 *
 * @author Orel Ben Shamay
 */
public class GameLevel implements HitListener, Animation {

    private Counter numOfBlocks;
    private Counter numOfBalls;
    private Counter score;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private int width;
    private int height;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation level;
    private KeyboardSensor keyboardSensor;
    // constants used in the class
    private static final int DEPTH = 23;
    private static final int PADDLE_HEIGHT = 15;
    private static final int BALL_RADIUS = 3;
    private static final int NEXT_LEVEL_BONUS = 100;
    private static final String PAUSE = "p";
    private static final int NUM_OF_SECONDS = 3;
    private static final int COUNT_FROM = 3;

    /**
     * this method constructs a new game level.
     *
     * @param info LevelInformation
     * @param ks KeyBoardSensor
     * @param ar AnimationRunner
     * @param newScore Counter
     * @param newGui GUI
     */
    public GameLevel(LevelInformation info, KeyboardSensor ks, AnimationRunner ar, Counter newScore, GUI newGui) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = newGui;
        this.width = gui.getDrawSurface().getWidth();
        this.height = gui.getDrawSurface().getHeight();
        this.numOfBlocks = new Counter();
        this.numOfBalls = new Counter();
        this.running = false;
        this.runner = ar;
        this.keyboardSensor = ks;
        this.level = info;
        this.score = newScore;
    }

    /**
     * this method adds a collidable to the game.
     *
     * @param c Collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * this method adds a sprite to the game.
     *
     * @param s Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * this method initialize the game.
     * it creates and adds paddle, blocks and balls to the game.
     */
    public void initialize() {
        BlockRemover blockRemover = new BlockRemover(this, this.numOfBlocks);
        BallRemover ballRemover = new BallRemover(this, this.numOfBalls);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        // create the background
        this.addSprite(level.getBackground());
        // create the paddle
        Rectangle rectangle = new Rectangle(level.paddleStart(), level.paddleWidth(), PADDLE_HEIGHT);
        Color color = Color.orange;
        Paddle paddle = new Paddle(this.keyboardSensor, rectangle, color, level.paddleSpeed(),
                level.paddleWidth(), PADDLE_HEIGHT);
        paddle.addToGame(this);
        // create the edges of the screen
        this.createScreenEdges(ballRemover);
        // add the blocks
        this.createBlocks(blockRemover, scoreTrackingListener);
        // add the balls
        this.createBalls();
        // create the score indicator
        Rectangle rect = new Rectangle(new Point(0, 0), width, DEPTH);
        Sprite scoreIndicator = new ScoreIndicator(rect, Color.white, this.score);
        this.addSprite(scoreIndicator);
    }

    /**
     * this method runs the game by starting the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(this.sprites, NUM_OF_SECONDS, COUNT_FROM, this.gui, level.levelName()));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * this methods creates the screen edges.
     *
     * @param ballRemover the ball remover listener
     */
    private void createScreenEdges(BallRemover ballRemover) {
        Block block1 = new Block(new Rectangle(new Point(0, 0), width, 2 * DEPTH), Color.gray);
        block1.addToGame(this);
        Block block2 = new Block(new Rectangle(new Point(0, DEPTH), DEPTH, height - 2 * DEPTH), Color.gray);
        block2.addToGame(this);
        Block block3 = new Block(new Rectangle(new Point(width - DEPTH, DEPTH), DEPTH, height - 2 * DEPTH), Color.gray);
        block3.addToGame(this);
        Block deathBlock = new Block(new Rectangle(new Point(0, height - DEPTH), width, DEPTH), Color.gray);
        deathBlock.addToGame(this);
        deathBlock.addHitListener(ballRemover);
    }

    /**
     * this method creates the blocks that are in the game.
     *
     * @param blockRemover the blocks listener
     * @param scoreTrackingListener score tracker
     */
    private void createBlocks(BlockRemover blockRemover, ScoreTrackingListener scoreTrackingListener) {
        List<Block> blocks = level.blocks();
        for (Block block : blocks) {
            block.addToGame(this);
            block.addHitListener(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            this.numOfBlocks.increase(1);
        }
    }

    /**
     * creates the balls that are in the game.
     */
    private void createBalls() {
        List<Point> startingPoints = level.ballsPositions();
        for (int i = 0; i < level.numberOfBalls(); i++) {
            Ball ball = new Ball(startingPoints.get(i), BALL_RADIUS, Color.WHITE, this.environment);
            ball.addToGame(this);
            ball.setVelocity(this.level.initialBallVelocities().get(i));
            this.numOfBalls.increase(1);
        }
    }

    /**
     * removes a collidable from the game.
     *
     * @param c Collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * removes a sprite from the game.
     *
     * @param s Sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboardSensor.isPressed(PAUSE)) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.gui)));
        }
        if (this.numOfBalls.getValue() == 0) {
            this.running = false;
            return;
        }
        if (this.numOfBlocks() <= this.level.blocks().size() - this.level.numberOfBlocksToRemove()) {
            score.increase(NEXT_LEVEL_BONUS);
            this.running = false;
            return;
        }
        this.sprites.drawAllOn(d);
        // draw the level name
        d.drawText(this.height, 15, "Level Name:" + this.level.levelName(), 15);
        this.sprites.notifyAllTimePassed();
        this.gui.show(d);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * returns the number of balls that are included in the level.
     *
     * @return int
     */
    public int numOfBalls() {
        return this.numOfBalls.getValue();
    }

    /**
     * returns the number of blocks that are included in the level.
     *
     * @return int
     */
    public int numOfBlocks() {
        return this.numOfBlocks.getValue();
    }
}

