/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github.project;

public class VendingMachineImpl implements VendingMachine {

    private String[][] snackNames;
    private int[][] snackAmounts;
    private int[][] snackPrices;

    private int[] coinValues;
    private int[] coinAmounts;

    public VendingMachineImpl() {
        snackNames = new String[5][10];
        snackAmounts = new int[5][10];
        snackPrices = new int[5][10];
    }

    public VendingMachineImpl(int rowSize, int columnSize) {
        snackNames = new String[rowSize][columnSize];
        snackAmounts = new int[rowSize][columnSize];
        snackPrices = new int[rowSize][columnSize];
    }

    @Override
    public void removeSnack(int xIndex, int yIndex, int amtSnack) {
        snackAmounts[xIndex][yIndex] -= amtSnack;
        if (amtSnack == 1) {
            System.out.println("A " + snackNames[xIndex][yIndex] + " falls down into the pick-up tray");
        } else {
            System.out.println(amtSnack + snackNames[xIndex][yIndex] + "s fall down into the pick-up tray - Yowza!");
        }
    }

    @Override
    public void addCoin(int value) {
        for (int count = 0; count <= 5; count++) {
            if (coinValues[count] == value) {
                coinAmounts[count]++;
                break;
            }
        }
    }

    @Override
    public int[] getChange(int change) {
        int[] returnedChange = new int[6];
        for (int count = 5; count >= 0; count--) {
            if (coinValues[count] <= change) {
                coinAmounts[count] -= change / coinValues[count];
                returnedChange[count] = change / coinValues[count];
                change -= returnedChange[count];
            }
        }
        return returnedChange;
    }

    @Override
    public String getSnackName(int xIndex, int yIndex
    ) {
        return snackNames[xIndex][yIndex];
    }

    @Override
    public int getSnackAmount(int xIndex, int yIndex
    ) {
        return snackAmounts[xIndex][yIndex];
    }

    @Override
    public int getSnackPrice(int xIndex, int yIndex
    ) {
        return snackPrices[xIndex][yIndex];
    }

}
