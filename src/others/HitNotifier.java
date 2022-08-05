package others;

/**
 * interface for hit notifiers.
 *
 * @author Orel Ben Shamay
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl new listener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl listener to remove
     */
    void removeHitListener(HitListener hl);
}
