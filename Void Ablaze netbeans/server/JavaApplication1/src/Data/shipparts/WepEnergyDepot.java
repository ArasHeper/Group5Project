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
public class WepEnergyDepot {
    public static final double MAX = 20000;
    private double energy;
    
    
    public WepEnergyDepot(){
        energy = MAX;
    }
    
    public void addEnergy( double p){
        energy = energy + p;
        if( energy > MAX)
            energy = MAX;
    }
    
    public void loseEnergy( double p){
        energy = energy - p;
    }

    public double getEnergy() {
        return energy;
    }
}
