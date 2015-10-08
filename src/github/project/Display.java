/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github.project;

import java.util.Scanner;

/**
 *
 * @author Jeff Niu
 */
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
                vendingMachine.removeSnack(snackCoord.x, snackCoord.y, 1);
                final int snackPrice = vendingMachine.getSnackPrice(snackCoord.x, snackCoord.y);
                // Get the coin inputs
                final int[] inputCoins = getCoinInput(snackPrice);
                final int amtPaid = sumCoinValue(inputCoins);
                // Add the coins to the vending machine
                for (int i = 0; i < coinValues.length; i++) {
                    for (int j = 1; j <= inputCoins[i]; j++) {
                        vendingMachine.addCoin(coinValues[i]);
                    }
                }
                // Dispense the change
                final int[] changeCoins = vendingMachine.getChange(amtPaid - snackPrice);
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
        } while (isValidCoordinate(coord));
        final int x = coord.charAt(1);
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
    private int[] getCoinInput(int snackPrice) {
        final int[] paidCoins = new int[coinValues.length];
        int amtPaid = 0;
        int choice;
        do {
            System.out.println("You need to pay $" + (snackPrice - amtPaid) / 100.0);
            do {
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
                    paidCoins[choice - 1]++;
                }
            } while (choice >= 0);
            amtPaid = sumCoinValue(paidCoins);
            if (amtPaid < snackPrice) {
                System.out.println("You have not paid enough!");
            }
        } while (amtPaid < snackPrice);
        return paidCoins;
    }

    /**
     * Sum up the values of the coins.
     *
     * @param coins
     * @return
     */
    private int sumCoinValue(int[] coins) {
        int amt = 0;
        for (int i = 0; i < coins.length; i++) {
            amt += coins[i] * coinValues[i];
        }
        return amt;
    }

    private void dispenseChange(int[] coins) {
        System.out.println("Here is your change:");
        System.out.println(coins[0] + " Nickels");
        System.out.println(coins[1] + " Dimes");
        System.out.println(coins[2] + " Quarters");
        System.out.println(coins[3] + " Loonies");
        System.out.println(coins[4] + " Toonies");
    }

}
