package github.project;

/**
 *
 * @author Tiger He
 */
public class MarsBar extends Snack {

    /**
     *
     * @param amt
     */
    public MarsBar(final int amt) {
        super("Mars Bar", new Coordinate(0, 0), "Chocolate-y.", "Don't eat too many!", 150, amt);
    }

}
