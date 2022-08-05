package others;
import biuoop.DrawSurface;

/**
 * this interface is for a sprite.
 *
 * @author Orel Ben Shamay 318869658
 */
public interface Sprite {

    /**
     * this method draws the sprite on a given surface.
     *
     * @param d DrawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * this method notifies the sprite that time has passed.
     */
    void timePassed();

}
