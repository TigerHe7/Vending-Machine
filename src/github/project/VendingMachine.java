package github.project;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 073257974
 */
public interface VendingMachine {
    
    public String getSnackName(int xIndex, int yIndex);
     public int getSnackAmount(int xIndex, int yIndex);
      public int getSnackPrice(int xIndex, int yIndex);
    
    public void removeSnack(int xIndex, int yIndex, int amtSnack);
    
    public void addCoin(int value);
    
    public int[] getChange(int change);
    
    
    
}
