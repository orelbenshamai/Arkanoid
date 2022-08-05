package others;
import ball.Ball;

/**
 * interface for hit listeners.
 *
 * @author Orel Ben Shamay
 */
public interface HitListener {
    /**
     *  This method is called whenever the beingHit object is hit.
     *  The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit Block
     * @param hitter Ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}
