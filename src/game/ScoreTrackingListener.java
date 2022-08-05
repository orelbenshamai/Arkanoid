package game;
import others.HitListener;
import others.Counter;
import ball.Ball;
import others.Block;

/**
 * this class implements the score tracking listener.
 *
 * @author Orel Ben Shamay 318869658
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /**
     * constructor.
     *
     * @param scoreCounter Counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * this methods adds point to the counter.
     *
     * @param beingHit Block
     * @param hitter Ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}