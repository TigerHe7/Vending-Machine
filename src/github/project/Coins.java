package github.project;

/**
 * The {@code Coins} class represents a set of a particular type of coin.
 *
 * @author Jeff Niu
 */
public class Coins {

    /**
     * The value of the coin in cents.
     */
    private final int value;

    /**
     * The number of such coins.
     */
    private int amount;

    /**
     * The name associated with the coin.
     */
    private final String name;

    /**
     * Create a new {@code Coins} class.
     *
     * @param value the value of each coin in cents
     * @param amount the initial amount of coins
     * @param name the name of the coin (e.g. Quarter)
     */
    public Coins(int value, int amount, String name) {
        this.value = value;
        this.amount = amount;
        this.name = name;
    }

    /**
     * @return the value of each coin in cents
     */
    public int getValue() {
        return value;
    }

    /**
     * @return the name of the coin
     */
    public String getName() {
        return name;
    }

    /**
     * Add coins to the set.
     *
     * @param amount the number of coins to add
     */
    public void addCoins(int amount) {
        this.amount += amount;
    }

    /**
     * Remove coins from the set.
     *
     * @param amount the number of coins to remove
     */
    public void removeCoins(int amount) {
        this.amount -= amount;
    }

}
