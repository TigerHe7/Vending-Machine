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
     * The ongoing tally of profits.
     */
    @SuppressWarnings("PublicField")
    public static int TOTAL_PROFIT = 0;

    /**
     * The ongoing tally of items sold.
     */
    @SuppressWarnings("PublicField")
    public static int ITEMS_SOLD = 0;

    /**
     * Whether a string is an integer.
     *
     * @return true if the string is an integer
     */
    private static boolean isParsable(final String s) {
        try {
            final int i = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    /**
     * Pluralize a noun if it is not already if we are discussing more than
     * one of such items.
     *
     * @param s the noun
     * @param amt the amount of such
     * @return the pluralized noun or maybe not
     */
    private static String pluralize(String s, int amt) {
        if (amt == 1) {
            return s;
        } else {
            final char[] chars = s.toCharArray();
            if (chars[chars.length - 1] == 's') {
                return s;
            } else {
                return s + "s";
            }
        }
    }

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
     * Start up the {@code VendingMachine} at the main menu.
     */
    public void startVendingMachine() {
        // Run the input cycle
        do {
            // Ask the user what they want to do at the vending machine
            final int usageChoice = getChoice("You are at a vending machine.\nWhat would you like to do?",
                    "Buy a snack.",
                    "Leave.",
                    "I own this vending machine.");
            if (usageChoice == 1) {
                // If they want to buy a snack, initiate the snack buying method
                buySnack();
            } else if (usageChoice == 2) {
                // If they want to leave, quit the loop
                System.out.println("You hear a quiet whimper from within.");
                usingVendingMachine = false;
            } else if (usageChoice == 3) {
                // If they want to administrate the machine, start that method
                administrate();
            }
        } while (usingVendingMachine);
    }

    private void administrate() {
        System.out.println("You attempt to access the vending machine's top-secret files.");
        System.out.println("A password screen appears:\n");
        System.out.println("What do you get when you square a compsci teacher?\n");
        final String guess = input.nextLine();
        if (!guess.toUpperCase().equals("R2D2")) {
            System.out.println("\nThe vending machine makes a worrying noise.\n");
            return;
        }
        System.out.println("\nYou've guessed the password!\n");
        int adminChoice;
        do {
            System.out.println("CLASSIFIED VENDING MACHINE PROTOCOLS:");
            adminChoice = getChoice("INITIATE ADMINISTRATION PROCEDURE?",
                    "ACCESS FINANCIAL RECORDS.",
                    "ACCESS EXPERIMENTAL ITEMS STOCK.",
                    "ADJUST FINANCIAL RESOURCES.",
                    "ADJUST EXPERIMENTAL ITEMS STOCK.",
                    "EXIT ADMINISTRATION MODE.");
            if (adminChoice == 1) {
                displayFinancialRecords();
            } else if (adminChoice == 2) {
                displayStockRecords();
            } else if (adminChoice == 3) {
                adjustCoins();
            } else if (adminChoice == 4) {
                adjustSnacks();
            }
        } while (adminChoice != 5);
    }

    /**
     * Display the total profit and the amount of coins.
     */
    private void displayFinancialRecords() {
        System.out.printf("%nMONEY HERESOFAR EXTORTED FROM TEST SUBJECTS: $%.2f%n", TOTAL_PROFIT / 100.0);
        System.out.printf("%nCURRENT INVENTORY OF FINANCIAL TRANSACTION FACILITATORS:%n");
        final Coins[] coinValues = Coins.getSet(5, 0);
        for (Coins coin : coinValues) {
            final Coins coins = vendingMachine.getCoins(coin.getValue());
            System.out.printf("%s: %d%n", pluralize(coins.getName(), coins.getAmount()), coins.getAmount());
        }
        System.out.println("\n");
    }

    /**
     * Display the total number of items sold and the remaining stock.
     */
    @SuppressWarnings("AssignmentToForLoopParameter")
    private void displayStockRecords() {
        System.out.printf("%nITEMS HERESOFAR TESTED ON SUBJECTS: %d%n", ITEMS_SOLD);
        System.out.printf("%nCURRENT INVENTORY OF EXPERIMENTAL ITEMS:%n");
        for (int y = 0; y >= 0; y++) {
            for (int x = 0; x >= 0; x++) {
                Snack s;
                try {
                    s = vendingMachine.getSnack(new Coordinate(x, y));
                } catch (final ArrayIndexOutOfBoundsException ex) {
                    s = null;
                    if (x == 0) {
                        y = -2;
                    }
                    x = -2;
                }
                if (s != null) {
                    System.out.printf("%s: %d%n", s.getName(), s.getAmount());
                }
            }
        }
        System.out.println("\n");
    }

    /**
     * Set directly the amount of each coin.
     */
    private void adjustCoins() {
        System.out.printf("%nDICTATE QUANTITY OF FINANCIAL TRANSACTION FACILITATORS:%n");
        final Coins[] coinValues = Coins.getSet(5, 0);
        for (Coins coin : coinValues) {
            final Coins coins = vendingMachine.getCoins(coin.getValue());
            System.out.printf("%ss: ", coins.getName());
            String setAmt;
            do {
                setAmt = input.nextLine();
            } while (!isParsable(setAmt) && Integer.parseInt(setAmt) >= 0);
            coins.addCoins(Integer.parseInt(setAmt) - coins.getAmount());
        }
        System.out.println("\n");
    }

    @SuppressWarnings("AssignmentToForLoopParameter")
    private void adjustSnacks() {
        System.out.printf("%nDICTATE QUANTITY OF EXPERIMENTAL ITEMS:%n");
        for (int y = 0; y >= 0; y++) {
            for (int x = 0; x >= 0; x++) {
                Snack s;
                try {
                    s = vendingMachine.getSnack(new Coordinate(x, y));
                } catch (final ArrayIndexOutOfBoundsException ex) {
                    s = null;
                    if (x == 0) {
                        y = -2;
                    }
                    x = -2;
                }
                if (s != null) {
                    System.out.printf("%s: ", s.getName());
                    String setAmt;
                    do {
                        setAmt = input.nextLine();
                    } while (!isParsable(setAmt) && Integer.parseInt(setAmt) >= 0);
                    s.removeSnacks(s.getAmount() - Integer.parseInt(setAmt));
                }
            }
        }
        System.out.println("\n");
    }

    /**
     * A general method for getting user input. This method will take a
     * user prompt and a list of choices. It will then wait for the user to
     * enter a valid choice before returning that choice.
     *
     * @param prompt the prompt for the user
     * @param choices the list of choices
     * @return a valid choice
     */
    private int getChoice(String prompt, String... choices) {
        int choice; // a natural number representing the user's choice
        do {
            // Display the prompt
            System.out.println(prompt);
            // Display the choices
            for (int i = 0; i < choices.length; i++) {
                System.out.printf("%d. %s%n", i + 1, choices[i]);
            }
            System.out.println();
            // Get the user's choice; repeat until the user enters a valid choice
            try {
                choice = Integer.valueOf(input.nextLine());
            } catch (final NumberFormatException ex) {
                choice = -1;
            }
            if (choice < 1 || choice > choices.length) {
                System.out.println("\nThat is not a valid choice.\n");
            }
        } while (choice < 1 || choice > choices.length);
        System.out.println();
        return choice;
    }

    /**
     * In this method, the {@code Display} will handle the purchase of
     * snacks.
     */
    private void buySnack() {
        // Display the snacks
        displaySnacks();
        // Get the user's input the desired snack coordinate
        final Coordinate coord = getCoordinate(); // the snack coordinate
        // Get the snack from the vending machine
        final Snack snack; // the snack that we are buying
        try {
            snack = vendingMachine.getSnack(coord);
        } catch (ArrayIndexOutOfBoundsException ex) {
            // If we try to get a snack that does not exist, return
            System.out.println("That snack does exist!");
            return;
        }
        // If there are no more snacks remaining, tell so and return
        if (snack.getAmount() <= 0) {
            System.out.println("Sorry, but there are no " + pluralize(snack.getName(), 0) + " left.");
            return;
        }
        // Get the deposited money
        final int amtPaid = payForSnack(snack); // the amount of money deposited
        // Dispense the change to the user
        final int[] changeCoins = vendingMachine.getChange(amtPaid - snack.getPrice()); // the coins that will be dispensed as change
        // getChange(int change) returns null if there is not enough change
        // In such a case, refund the person's money
        if (changeCoins == null) {
            System.out.println("Sorry, but there is not enough change in the vending machine.");
            System.out.println("We apologize for the inconvenience; here is a refund:");
            // Calling this should never return null because the user has
            // just deposited the correct amount of coins in order to
            // procure the exact refund
            dispense(vendingMachine.getChange(amtPaid));
        } else {
            // Add to the total profit
            TOTAL_PROFIT += snack.getPrice();
            // Add to the total amount sold
            ITEMS_SOLD++;
            // Remove one snack from the stock
            snack.removeSnacks(1);
            // Dispense the snack
            dispense(snack);
            // Dispense the change
            System.out.printf("$%.2f will be your change:%n", (amtPaid - snack.getPrice()) / 100.0);
            dispense(changeCoins);
            System.out.println();
        }
    }

    /**
     * Display in a grid the available {@code Snack}s. This method will
     * keep track of whether the snack next over in the same row exists or
     * whether the entire next row exists. If the next snack over does not
     * exist, this method will move down a method. If the first snack does
     * not exist in the new row, then we assume that the entire row does
     * not exist and exit the method.
     */
    private void displaySnacks() {
        boolean nextColumnExists; // if the snack next over in the x axis exists
        boolean nextRowExists; // if the next row of snacks exists
        int x; // the current x-coordinate
        int y; // the current y-coordinate
        nextRowExists = true;
        y = 0;
        do {
            nextColumnExists = true;
            x = 0;
            String prices = "\n";
            do {
                Snack s; // the snack whose name we want to display
                try {
                    s = vendingMachine.getSnack(new Coordinate(x, y));
                } catch (ArrayIndexOutOfBoundsException ex) {
                    // Since we do not know how many snacks the vending
                    // machine has, we must catch any out of bounds 
                    // exceptions and handle them appropriately
                    s = null;
                }
                if (s != null) {
                    // Print the coordinate and the snack name
                    final String coord = (char) (y + 'A') + String.valueOf(x);
                    System.out.printf("%s %-18s", coord, s.getName(), s.getPrice() / 100.0);
                    prices += String.format("$%-20.2f", s.getPrice() / 100.0);
                } else {
                    // The next snack over does not exist
                    nextColumnExists = false;
                    System.out.println(prices + "\n");
                    if (x == 0) {
                        // The next row does not exist
                        nextRowExists = false;
                    }
                }
                x++;
            } while (nextColumnExists);
            System.out.println();
            y++;
        } while (nextRowExists);
    }

    /**
     * Get the user input of the snack coordinate in the form
     * [letter][number] and return it as a {@code Coordinate}.
     *
     * @return the snack coordinate
     */
    private Coordinate getCoordinate() {
        // Get the raw input
        char[] coord; // the two entered coordinates
        boolean validCoordinate; // whether the user has entered a valid coordinate
        do {
            System.out.println("Enter the snack coordinate:\n");
            coord = input.nextLine().toUpperCase().toCharArray();
            // Make sure that they have entered a valid coordinate
            validCoordinate = isValidCoordinate(coord);
            if (!validCoordinate) {
                System.out.println("\nThat is not a valid coordinate.");
            }
            System.out.println();
        } while (!validCoordinate);
        final int x = coord[1] - '0';
        final int y = coord[0] - 'A';
        return new Coordinate(x, y);
    }

    /**
     * Determine if the coordinate entered is a letter followed by a digit.
     *
     * @param coord the coordinate String
     * @return true if it is valid
     */
    private boolean isValidCoordinate(final char[] coord) {
        if (coord.length != 2) {
            return false;
        } else if (!Character.isLetter(coord[0])) {
            return false;
        } else if (!Character.isDigit(coord[1])) {
            return false;
        }
        return true;
    }

    /**
     * A method to get the coins that a user will put in to buy a snack.
     *
     * @param snack the snack that we are buying
     * @return the total amount paid
     */
    private int payForSnack(final Snack snack) {
        final Coins[] coins = Coins.getSet(5, 0); // the set of inputted coins
        final int snackPrice = snack.getPrice(); // the cost of the snack
        int amtPaid = 0; // the total amount of money paid in cents
        int coinChoice; // the coin input choice
        do {
            System.out.printf("You need to pay $%.2f%n", (snackPrice - amtPaid) / 100.0);
            do {
                System.out.printf("You have paid $%.2f of $%.2f%n%n", amtPaid / 100.0, snackPrice / 100.0);
                coinChoice = getChoice("What coin will you deposit?",
                        "Nickel $0.05",
                        "Dime $0.10",
                        "Quarter $0.25",
                        "Loonie $1.00",
                        "Toonie $2.00",
                        "Finish paying.");
                // If the user deposits a coin, add it to the vending machine
                if (coinChoice >= 1 && coinChoice <= 5) {
                    final int coinValue = coins[coinChoice - 1].getValue(); // the added coin
                    // Add the coin value to the running amount paid sum
                    amtPaid += coinValue;
                    // Add the coin to the vending machine
                    vendingMachine.getCoins(coinValue).addCoins(1);
                }
            } while (coinChoice != 6); // exit when the user selects so
            if (amtPaid < snackPrice) {
                System.out.println("You have not paid enough!");
            }
        } while (amtPaid < snackPrice); // leave only when the user has paid
        return amtPaid;
    }

    /**
     * Dispense a snack.
     *
     * @param snack the snack to dispense
     */
    private void dispense(Snack snack) {
        System.out.printf("A %s is dispensed from the vending machine.%n%n", snack.getName());
        System.out.printf("Enjoy you snack! %s %s%n%n", snack.getNutrition(), snack.getSugar());
    }

    /**
     * Dispense some coins
     *
     * @param coins the array of coin amounts.
     */
    private void dispense(int[] coins) {
        System.out.println(coins[0] + pluralize(" Nickel", coins[0]));
        System.out.println(coins[1] + pluralize(" Dime", coins[1]));
        System.out.println(coins[2] + pluralize(" Quarter", coins[2]));
        System.out.println(coins[3] + pluralize(" Loonie", coins[3]));
        System.out.println(coins[4] + pluralize(" Toonie", coins[4]));
    }

}
