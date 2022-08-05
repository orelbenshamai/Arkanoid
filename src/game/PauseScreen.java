package game;
import animation.Animation;
import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * this class implements the pause screen animation.
 *
 * @author Orel Ben Shamay 318869658
 */
public class PauseScreen implements Animation {

    private GUI gui;
    private static final int TEXT_X = 20;
    private static final int TEXT_Y = 300;
    private static final int TEXT_SIZE = 50;

    /**
     * constructor.
     *
     * @param newGui GUI
     */
    public PauseScreen(GUI newGui) {
        this.gui = newGui;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(TEXT_X, TEXT_Y, "paused -- press space to continue", TEXT_SIZE);
        this.gui.show(d);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}