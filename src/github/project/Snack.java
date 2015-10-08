/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github.project;

/**
 *
 * @author Jeff Niu
 */
public abstract class Snack {

    private final String name;

    private final Coordinate c;

    private final String nutritionalStatement;
    private final String sugarComment;

    public Snack(String name, Coordinate c, String nutritionalStatement, String sugarComment) {
        this.name = name;
        this.c = c;
        this.nutritionalStatement = nutritionalStatement;
        this.sugarComment = sugarComment;
    }

    public Coordinate getCoord() {
        return c;
    }

    public String getName() {
        return name;
    }
    
    public String getNutrition() {
        return nutritionalStatement;
    }
    
    public String getSugar() {
        return sugarComment;
    }
    
}
