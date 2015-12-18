/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.shipparts;

/**
 *
 * @author Magus
 */
public class FuelDepot {
    public static final double MAX_FUEL = 200000;
    private double fuel;

    public double depot() {
        return fuel;
    }
    public double pull( double d){
        fuel = fuel - d;
        if( fuel < 0){
            d = d + fuel;
            fuel = 0;
            return d;
        }
        return d;
    }
    
    public void addFuel( double a ){
        fuel = fuel + a;
    }
}
