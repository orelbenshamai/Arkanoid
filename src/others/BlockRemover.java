package others;
import game.GameLevel;
import ball.Ball;

/**
 * this class "BlockRemover" is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 *
 * @author Orel Ben Shamay 318869658
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor.
     *
     * @param newGame Game
     * @param blocks Counter
     */
    public BlockRemover(GameLevel newGame, Counter blocks) {
        this.game = newGame;
        this.remainingBlocks = blocks;
    }

    /**
     * Blocks that are hit should be removed from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     *
     * @param beingHit Block
     * @param hitter Ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        this.remainingBlocks.decrease(1);
    }
}