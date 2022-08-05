package game;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * this class implements the end screen animation.
 * this class implements both the winner and looser ending screens.
 *
 * @author Orel Ben Shamay 318869658
 */
public class EndScreen implements Animation {

    private GUI gui;
    private String string;
    private int finalScore;
    // constants used in the class
    private static final int FONT_SIZE = 40;
    private static final int TEXT_X_COORDINATES = 100;
    private static final int TEXT_Y_COORDINATES = 300;
    /**
     * constructor.
     *
     * @param newGui GUI
     * @param newString String the string that will be printed (winner or looser)
     * @param newFinalScore int
     */
    public EndScreen(GUI newGui, String newString, int newFinalScore) {
        this.gui = newGui;
        this.string = newString;
        this.finalScore = newFinalScore;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(TEXT_X_COORDINATES, TEXT_Y_COORDINATES, this.string + this.finalScore, FONT_SIZE);
        gui.show(d);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
