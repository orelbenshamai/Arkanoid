import animation.AnimationRunner;
import biuoop.GUI;
import game.GameFlow;
import levels.LevelInformation;
import levels.LevelOne;
import levels.LevelTwo;
import levels.LevelThree;
import levels.LevelFour;
import java.util.ArrayList;
import java.util.List;


/**
 * this class creates a new game, initializes and runs it.
 *
 * @author Orel Ben Shamay 318869658
 */
public class Ass6Game {

    /**
     * main method.
     *
     * @param args void
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
         if (args.length >= 1) {
            for (String arg : args) {
                try {
                    if (Integer.parseInt(arg) == 1) {
                        levels.add(new LevelOne());
                    } else if (Integer.parseInt(arg) == 2) {
                        levels.add(new LevelTwo());
                    } else if (Integer.parseInt(arg) == 3) {
                        levels.add(new LevelThree());
                    } else if (Integer.parseInt(arg) == 4) {
                        levels.add(new LevelFour());
                    }
                } catch (Exception ignored) { }
            }
        }
         if (levels.size() == 0) {
             levels.add(new LevelOne());
             levels.add(new LevelTwo());
             levels.add(new LevelThree());
             levels.add(new LevelFour());
         }
        GUI gui = new GUI("Game", 800, 600);
        GameFlow g = new GameFlow(new AnimationRunner(60, gui), gui.getKeyboardSensor(), gui);
        g.runLevels(levels);
        gui.close();
    }
}