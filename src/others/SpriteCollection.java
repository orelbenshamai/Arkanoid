package others;
import  biuoop.DrawSurface;
import java.util.ArrayList;

/**
 * this class implements a sprite collection.
 * this class has the following methods:
 * add sprite, notify all time passed for all of the sprite collection and draws the sprites on a given surface
 *
 * @author Orel Ben Shamay 318869658
 */
public class SpriteCollection {

    private ArrayList<Sprite> sprites;

    /**
     * Creates a new sprite collection.
     *
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * adds a sprite to the collection.
     *
     * @param s Sprite a new sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * this method calls timePassed() for all sprites.
     *
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> temp = new ArrayList<>(this.sprites);
        for (Sprite sprite : temp) {
            sprite.timePassed();
        }
    }

    /**
     * this method draws all of the sprites on a given surface.
     *
     * @param d DrawSurface to draw the sprites on
     */
    public void drawAllOn(DrawSurface d) {
        ArrayList<Sprite> temp = new ArrayList<>(this.sprites);
        for (Sprite sprite : temp) {
            sprite.drawOn(d);
        }
    }

    /**
     * remove a sprite.
     *
     * @param c sprite to remove
     */
    public void removeSprite(Sprite c) {
        this.sprites.remove(c);
    }
}
