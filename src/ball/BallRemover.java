package ball;
import others.Counter;
import game.GameLevel;
import others.Block;
import others.HitListener;

/**
 * this class is in charge of removing the ball from the game if it hits the dead zone.
 *
 * @author Orel Ben Shamay
 */
public class BallRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBalls;

    /**
     * constructor.
     *
     * @param newGame Game
     * @param numOfBalls int
     */
    public BallRemover(GameLevel newGame, Counter numOfBalls) {
        this.game = newGame;
        this.remainingBalls = numOfBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeHitListener(this);
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
