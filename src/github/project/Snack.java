/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github.project;

/**
 * This class will handle all snacks in the {@code VendingMachine}.
 *
 * @author Jeff Niu
 */
public abstract class Snack {

    /**
     * The name of the snack.
     */
    private final String name;

    /**
     * The coordinate of the snack in the {@code VendingMachine¨.
     */
    private final Coordinate c;

    /**
     * A nutritional statement regarding the snack.
     */
    private final String nutritionalStatement;
    /**
     * Another nutritional statement specifically regarding the sugar
     * content of the snack.
     */
    private final String sugarComment;

    /**
     * The price of the snack.
     */
    private final int price;
    /**
     * The amount remaining of this particular snack.
     */
    private int amount;

    /**
     * Create a new {@code Snack} with all of its parameters.
     *
     * @param name the name of the snack
     * @param c the coordinate
     * @param nutritionalStatement the nutritional statement
     * @param sugarComment the sugar amount comment
     * @param price the price of the snack
     * @param amount the number of snacks initially stocked
     */
    public Snack(String name, Coordinate c, String nutritionalStatement, String sugarComment, int price, int amount) {
        this.name = name;
        this.c = c;
        this.nutritionalStatement = nutritionalStatement;
        this.sugarComment = sugarComment;
        this.price = price;
        this.amount = amount;
    }

    /**
     * @return the coordinate of the snack
     */
    public Coordinate getCoord() {
        return c;
    }

    /**
     * @return the name of the snack
     */
    public String getName() {
        return name;
    }

    /**
     * @return the nutritional statement of the snack
     */
    public String getNutrition() {
        return nutritionalStatement;
    }

    /**
     * @return the sugar comment of the snack
     */
    public String getSugar() {
        return sugarComment;
    }

    /**
     * @return the price of the snack
     */
    public int getPrice() {
        return price;
    }

    /**
     * Remove some snacks from the vending machine.
     *
     * @param amount the number of snacks to remove
     * @author Tiger He
     */
    public void removeSnacks(int amount) {
        this.amount -= amount;
    }

}
