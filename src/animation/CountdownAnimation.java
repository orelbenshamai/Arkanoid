package animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import others.Counter;
import others.SpriteCollection;
import java.awt.Color;

/**
 * this class implements a count down animation, which receives the number of seconds to count, and the number to
 * count from.
 *
 * @author Orel Ben Shamay 318869658
 */
public class CountdownAnimation implements Animation {

    private SpriteCollection collection;
    private double numOfSeconds;
    private int countFrom;
    private boolean stop;
    private Counter counter;
    private GUI gui;
    private String description;

    /**
     * constructor.
     *
     * @param sprites SpriteCollection
     * @param seconds double
     * @param newCountFrom int
     * @param gui GUI
     * @param name String
     */
    public CountdownAnimation(SpriteCollection sprites, double seconds, int newCountFrom, GUI gui, String name) {
        this.collection = sprites;
        this.numOfSeconds = seconds;
        this.countFrom = newCountFrom;
        this.stop = false;
        this.counter = new Counter();
        this.counter.increase(countFrom);
        this.gui = gui;
        this.description = name;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        collection.drawAllOn(d);
        d.drawText(600, 15, "Level Name:" + this.description, 15);
        if (this.counter.getValue() == 1) {
            this.stop = true;
        }
        d.setColor(Color.WHITE);
        d.drawText(200, 200, "Game starts in " + this.counter.getValue() + "...", 50);
        this.gui.show(d);
        this.counter.decrease(1);
        sleeper.sleepFor((long) (this.numOfSeconds / this.countFrom) * 1000);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
