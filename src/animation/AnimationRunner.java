package animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * this class implements the animation runner, which is in charge of running different animations.
 *
 * @author Orel Ben Shamay
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;

    /**
     * constructor.
     *
     * @param fps int
     * @param newGui GUI
     */
    public AnimationRunner(int fps, GUI newGui) {
        this.framesPerSecond = fps;
        this.gui = newGui;
    }

    /**
     * this methods runs a given animation.
     *
     * @param animation Animation to run
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}