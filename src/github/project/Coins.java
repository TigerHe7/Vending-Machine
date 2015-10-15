package github.project;

/**
 * The {@code Coins} class represents a set of a particular type of coin.
 *
 * @author Jeff Niu
 */
public class Coins {

    /**
     * A shorthand method used to create a new array of coins representing
     * nickels, dimes, quarters, loonies, and toonies with zero amounts.
     *
     * @param size an extended size
     * @param amt a generic amt
     * @return a new set
     */
    public static Coins[] getSet(int size, int amt) {
        final Coins[] coins = new Coins[size >= 5 ? size : 5];
        coins[0] = new Coins(5, amt, "Nickel");
        coins[1] = new Coins(10, amt, "Dime");
        coins[2] = new Coins(25, amt, "Quarter");
        coins[3] = new Coins(100, amt, "Loonie");
        coins[4] = new Coins(200, amt, "Toonie");
        return coins;
    }

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

    /**
     * @return the number of coins in this set
     */
    public int getAmount() {
        return amount;
    }

}
