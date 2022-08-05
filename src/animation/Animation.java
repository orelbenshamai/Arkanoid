package animation;
import biuoop.DrawSurface;

/**
 * interface for animation.
 *
 * @author Orel Ben Shamay 318869658
 */
public interface Animation {
    /**
     * draws one frame of the animation.
     *
     * @param d DrawSurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * returns if the animation should stop.
     *
     * @return boolean
     */
    boolean shouldStop();
}
