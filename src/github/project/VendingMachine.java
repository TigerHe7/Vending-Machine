package github.project;

/**
 *
 * @author Jeff Niu, Tiger He
 */
public interface VendingMachine {

    /**
     * Get the reference {@code Snack} object located at a particular
     * coordinate in the {@code VendingMachine}.
     *
     * @param c the coordinate of the snack
     * @return the snack
     * @author Jeff Niu
     */
    public Snack getSnack(Coordinate c);

    /**
     * Get the reference to the {@code Coins} object with a particular
     * value in cents.
     *
     * @param value the value of the coin in cents
     * @return the coins class
     */
    public Coins getCoins(int value);

    /**
     *
     * @param value
     * @author Tiger He
     */
    public void addCoin(int value);

    /**
     *
     * @param change
     * @return
     * @author Tiger He
     */
    public int[] getChange(int change);

}
