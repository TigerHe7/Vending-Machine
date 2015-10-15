package github.project;

/**
 *
 * @author Tiger He
 */
public class VendingMachineImpl implements VendingMachine {

    private Snack[][] snacks; // reprents grid of snacks
    private Coins[] coins; // represents array of coins by value 

    /**
     * instantiate Vending Machine 3 slots wide and 3 slots tall
     */
    public VendingMachineImpl() {
        this(3, 3);
    }

    /**
     * instantiate Vending Machine with given dimensions and put in all snacks
     * and coins
     *
     * @param rowSize the length of the row
     * @param columnSize the length of the column
     */
    public VendingMachineImpl(int rowSize, int columnSize) {
        snacks = new Snack[rowSize][columnSize];
        snacks[0][0] = new MarsBar(10);
        snacks[1][0] = new Doritos(10);
        snacks[2][0] = new Starbursts(10);
        snacks[0][1] = new FuzzyPeaches(10);
        snacks[1][1] = new Apple(10);
        snacks[2][1] = new Coke(10);
        snacks[0][2] = new SwagCandy(10);
        snacks[1][2] = new CyanidePills(10);
        snacks[2][2] = new RDTongueBlasters(10);
        coins = Coins.getSet(5, 100); // put 100 coins for each of 5 types
    }

    /**
     *
     * returns a snack at a given coordinate
     *
     * @param c coordinate of snack
     * @return snacks snack at coordinate
     */
    @Override
    public Snack getSnack(Coordinate c) {
        return snacks[c.x][c.y];
    }

    /**
     * returns coins of given value
     *
     * @param value value of coin
     * @return coins[i] coins with given value
     */
    @Override
    public Coins getCoins(int value) {
        for (int i = 0; i <= 4; i++) {
            if (coins[i].getValue() == value) {
                return coins[i];
            }
        }
        return null;
    }

    /**
     * removes change from machine and returns array of change amounts
     *
     * @param change amount of change required
     * @return returnedChange array of coins amounts to match change value
     */
    @Override
    @SuppressWarnings("AssignmentToMethodParameter")
    public int[] getChange(int change) {
        int[] returnedChange = new int[5];
        for (int i = 4; i >= 0; i--) {
            // check if more than enough of coin type to pay back change
            if (coins[i].getAmount() * coins[i].getValue() >= change) {
                // remove at many coins as possible under change value
                coins[i].removeCoins(change / coins[i].getValue());
                // put number of coins removed of specific value in returned change
                returnedChange[i] = change / coins[i].getValue();
                // reduce change left to remove by value of coins removed
                change -= returnedChange[i] * coins[i].getValue();
                // check if all coins of this value are not enough
            } else {
                // for given value of coin, set returned change to all coins in the machine
                returnedChange[i] = coins[i].getAmount();
                // reduce change left to remove by value of coins removed 
                change -= returnedChange[i] * coins[i].getValue();
                // remove all coins of given value from machine
                coins[i].removeCoins(coins[i].getAmount());
            }

        }
        // check if machine did not have enough coins to return all change
        if (change > 0) {
            // put all removed change back in machine
            for (int i = 4; i >= 0; i--) {
                coins[i].addCoins(returnedChange[i]);
            }
            return null;
        }

        return returnedChange;
    }

}
