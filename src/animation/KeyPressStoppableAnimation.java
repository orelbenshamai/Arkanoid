package animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * this class implements an animation which can be stopped by pressing a given key.
 *
 * @author Orel Ben Shamay 318869658
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * constructor.
     *
     * @param ks KeyBoardSensor
     * @param newKey String
     * @param newAnimation Animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor ks, String newKey, Animation newAnimation) {
        this.keyboardSensor = ks;
        this.key = newKey;
        this.animation = newAnimation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.keyboardSensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
        if (this.keyboardSensor.isPressed(this.key) && !this.isAlreadyPressed) {
            this.stop = true;
        }
        this.animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
