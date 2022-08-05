package game;
import biuoop.DrawSurface;
import geometryprimitives.Rectangle;
import java.awt.Color;
import others.Sprite;
import others.Counter;

/**
 * this class implements a score indicator which is also a sprite.
 *
 * @author Orel Ben Shamay 318869658
 */
public class ScoreIndicator implements Sprite {

    private Rectangle rectangle;
    private Color color;
    private Counter score;

    /**
     * constructor.
     *
     * @param newRectangle Rectangle
     * @param newColor Color
     * @param newCounter Counter
     */
    public ScoreIndicator(Rectangle newRectangle, Color newColor, Counter newCounter) {
        this.rectangle = newRectangle;
        this.color = newColor;
        this.score = newCounter;
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d, this.color);
        d.drawText(375, 15, "Score:" + this.score.getValue(), 15);
    }

    @Override
    public void timePassed() {

    }
}
