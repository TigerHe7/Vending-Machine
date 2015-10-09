package github.project;

import java.util.Scanner;

/**
 * The {@code VendingMachine} {@code Display} is the class that handles all
 * interaction with the {@code VendingMachine} user. This class will handle
 * input from the user and output from the {@code VendingMachine}.
 *
 * @author Jeff Niu
 */
@SuppressWarnings("UseOfSystemOutOrSystemErr")
public class Display {

    /**
     * A reference array for the value of a coin.
     */
    private static final int[] coinValues = {5, 10, 25, 100, 200};

    /**
     * Whether or not the user is using the {@code VendingMachine}.
     */
    private boolean usingVendingMachine;

    /**
     * The {@code VendingMachine} for which this {@code Display} is meant.
     */
    private final VendingMachine vendingMachine;

    /**
     * The primary input channel.
     */
    private final Scanner input;

    /**
     * Create a new {@code Display} for a {@code VendingMachine}.
     *
     * @param vendingMachine the machine for which to create the display
     */
    public Display(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        usingVendingMachine = true;
        input = new Scanner(System.in);
    }

    /**
     * This method will start the display and have it take input from the
     * user.
     */
    public void startDisplay() {
        // The input-output cycle
        do {
            // Display the snack options in a grid
            displaySnacks();
            // Get the user option of the snack
            final Coordinate snackCoord = getSnackInput();
            // If the user wants to quit, leave the loop
            if (snackCoord == null) {
                usingVendingMachine = false;
            } else {
                // Get the snack price
                final Snack snack = vendingMachine.getSnack(snackCoord);
                // Get the coin inputs
                final Coins[] inputCoins = getCoinInput(snack.getPrice());
                // Put them in the machine
                for (int i = 0; i < 5; i++) {
                    final Coins coin = inputCoins[i];
                    vendingMachine.getCoins(coin.getValue()).addCoins(coin.getAmount());
                }
                int amtPaid = inputCoins[5].getAmount();
                // Dispense the change
                final int[] changeCoins = vendingMachine.getChange(amtPaid - snack.getPrice());
                dispenseChange(changeCoins);
            }
        } while (usingVendingMachine);
    }

    /**
     * Display in a grid the available snacks.
     */
    private void displaySnacks() {
        // TO DO
    }

    /**
     * Get the user input of the snack coordinate in the form
     * [letter][number] and return it as a {@code Coordinate}. If it
     * returns null, the user has decided not to buy a snack.
     *
     * @return the snack coordinate or null
     */
    private Coordinate getSnackInput() {
        // Decide whether the user wants to buy a snack
        int choice;
        do {
            System.out.println("Would you like to buy a snack?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            try {
                choice = Integer.valueOf(input.nextLine());
            } catch (NumberFormatException ex) {
                choice = -1;
                System.out.println("That is not a valid choice.");
            }
        } while (choice < 0);
        if (choice == 2) {
            return null;
        }
        // Get the raw input
        String coord;
        do {
            System.out.println("Enter the snack coordinate: ");
            coord = input.nextLine().toUpperCase();
            // Make sure that they have entered a valid coordinate
        } while (!isValidCoordinate(coord));
        final int x = Integer.valueOf(coord.substring(1));
        final int y = coord.charAt(0) - 'A';
        return new Coordinate(x, y);
    }

    /**
     * Determine if the coordinate entered is a letter followed by a digit.
     *
     * @param coord the coordinate String
     * @return true if it is valid
     */
    private boolean isValidCoordinate(String coord) {
        if (coord.length() != 2) {
            return false;
        } else if (!Character.isLetter(coord.charAt(0))) {
            return false;
        } else if (!Character.isDigit(coord.charAt(1))) {
            return false;
        }
        return true;
    }

    /**
     * A method to get the coins that a user will put in to buy a snack.
     *
     * @param snackPrice the cost of the snack
     * @return an array representing the inputted coins
     */
    private Coins[] getCoinInput(int snackPrice) {
        final Coins[] coins = Coins.getSet(6, 0);
        int amtPaid = 0;
        int choice;
        do {
            System.out.printf("You need to pay $%.2f%n", (snackPrice - amtPaid) / 100.0);
            do {
                System.out.printf("You have paid $%.2f of $%.2f%n", amtPaid / 100.0, snackPrice / 100.0);
                System.out.println("What coin will you deposit?");
                System.out.println("Enter a negative number to finish.");
                System.out.println("1. Nickel %0.05");
                System.out.println("2. Dime $0.10");
                System.out.println("3. Quarter $0.25");
                System.out.println("4. Loonie $1.00");
                System.out.println("5. Toonie $2.00\n");
                try {
                    choice = Integer.valueOf(input.nextLine());
                } catch (NumberFormatException ex) {
                    System.out.println("That is not a valid choice.\n");
                    choice = 100;
                }
                if (choice >= 1 && choice <= 5) {
                    coins[choice - 1].addCoins(1);
                    amtPaid += coins[choice - 1].getValue();
                }
            } while (choice >= 0);
            if (amtPaid < snackPrice) {
                System.out.println("You have not paid enough!");
            }
        } while (amtPaid < snackPrice);
        coins[5] = new Coins(1, amtPaid, "Change"); // here we explot the coin system to return total amount paid
        System.out.printf("Your change will be $%.2f%n", (amtPaid - snackPrice) / 100.0);
        return coins;
    }

    /**
     * Display the change.
     *
     * @param coins the array of coin amounts.
     */
    private void dispenseChange(int[] coins) {
        System.out.println("Here is your change:");
        System.out.println(coins[0] + " Nickels");
        System.out.println(coins[1] + " Dimes");
        System.out.println(coins[2] + " Quarters");
        System.out.println(coins[3] + " Loonies");
        System.out.println(coins[4] + " Toonies");
    }

}
