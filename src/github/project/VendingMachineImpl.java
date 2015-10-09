/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github.project;

/**
 *
 * @author Tiger He
 */
public class VendingMachineImpl implements VendingMachine {

    private Snack[][] snacks;
    private Coins[] coins;

    public VendingMachineImpl() {
        this(5, 10);
    }

    public VendingMachineImpl(int rowSize, int columnSize) {
        snacks = new Snack[rowSize][columnSize];
        snacks[0][0] = new MarsBar();
        snacks[1][0] = new Doritos();
        coins = Coins.getSet(5, 100);
    }

    @Override
    public Snack getSnack(Coordinate c) {
        return snacks[c.x][c.y];
    }

    @Override
    public Coins getCoins(int value) {
        for (int i = 0; i <= 4; i++) {
            if (coins[i].getValue() == value) {
                return coins[i];
            }
        }
        return null;
    }

    @Override
    @SuppressWarnings("AssignmentToMethodParameter")
    public int[] getChange(int change) {
        int[] returnedChange = new int[6];
        for (int i = 4; i >= 0; i--) {
            if (coins[i].getValue() <= change) {
                coins[i].removeCoins(change / coins[i].getValue());
                returnedChange[i] = change / coins[i].getValue();
                change -= returnedChange[i];
            }
        }
        return returnedChange;
    }

}
