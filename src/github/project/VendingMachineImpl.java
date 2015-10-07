/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github.project;

public class VendingMachineImpl implements VendingMachine {

    String[][] snackName;
    int[][] snackAmount;
    int[][] snackPrice;
        
    int [] coinValue;
    int [] coinAmount;

    public VendingMachineImpl() {
        snackName = new String[5][10];
        snackAmount = new int[5][10];
        snackPrice = new int[5][10];
    }

    public VendingMachineImpl(int rowSize, int columnSize) {
        snackName = new String[rowSize][columnSize];
        snackAmount = new int[rowSize][columnSize];
        snackPrice = new int[rowSize][columnSize];
    }
    
    
    @Override
    public void removeSnack(int xIndex, int yIndex, int amtSnack) {
        snackAmount[xIndex][yIndex] -= amtSnack;
        if (amtSnack == 1) {
            System.out.println("A " + snackName[xIndex][yIndex] + " falls down into the pick-up tray");
        } else {
            System.out.println(amtSnack + snackName[xIndex][yIndex] + "s fall down into the pick-up tray - Yowza!");
        }
    }

    @Override
    public void addCoin(int value) {

    }

    @Override
    public int[] getChange(int change) {
    }

    @Override
    public String getSnackName(int xIndex, int yIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSnackAmount(int xIndex, int yIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSnackPrice(int xIndex, int yIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
