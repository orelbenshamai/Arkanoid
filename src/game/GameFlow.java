package game;
import animation.Animation;
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import others.Counter;
import java.util.List;

/**
 * this class implements the game flow, which is in charge of moving between different levels.
 *
 * @author Orel Ben Shamay 318869658
 */
public class GameFlow {

    private AnimationRunner runner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private GUI gui;

    /**
     * constructor.
     *
     * @param ar AnimationRunner
     * @param ks KeyBoardSensor
     * @param newGui GUI
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI newGui) {
        this.runner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter();
        this.gui = newGui;

    }

    /**
     * this method runs all of the levels which are received in the list of levels.
     * if the player loses, then a message is printed.
     * if the player wins, then a message is printed.
     *
     * @param levels List<LevelInformation> the levels in the game
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean lost = false;
        for (int i = 0; i < levels.size(); i++) {
            LevelInformation levelInfo = levels.get(i);
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.runner, this.score, this.gui);
            level.initialize();
            while (level.numOfBalls() != 0
                    && level.numOfBlocks() > levelInfo.blocks().size() - levelInfo.numberOfBlocksToRemove()) {
                level.run();
            }
            if (level.numOfBalls() == 0) {
                String string = "Game Over. Your score is ";
                Animation end = new EndScreen(this.gui, string, this.score.getValue());
                KeyPressStoppableAnimation stoppableAnimation = new KeyPressStoppableAnimation(this.keyboardSensor,
                        KeyboardSensor.SPACE_KEY, end);
                runner.run(stoppableAnimation);
                lost = true;
                break;
            }
        }
        if (!lost) {
            String string = "You Win! Your score is ";
            Animation end = new EndScreen(this.gui, string, this.score.getValue());
            KeyPressStoppableAnimation stoppableAnimation = new KeyPressStoppableAnimation(this.keyboardSensor,
                    KeyboardSensor.SPACE_KEY, end);
            runner.run(stoppableAnimation);
        }
        gui.close();
    }
}
