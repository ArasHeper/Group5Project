/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.shipparts;

import Data.shipparts.FuelDepot;

/**
 *
 * @author Magus
 */
public class Engine {
    static public enum OutType{ FREE, SHIELD, MATC, WEP};
    static double FUEL_BASE_CONSUMPTION = 100;
    static double CENT = 100;
    static double SHIELD_RATE = 4;
    static double WEP_RATE = 4;
    static double MC_RATE = 4;
    // max fuel consumption = BASE * CENT 
    // max shield output = CENT * SHIELD_RATE. etc.
    public FuelDepot fuel;
    
    //below values are percentage
    private double free;
    private double shield;
    private double MC;
    private double wep;
    //ends here
    
    //below values are rewritten for each work
    //if caller doesnt collect them after the work one
    //work cycle will be sacrificed for naught 
    private double shield_out;
    private double free_out;
    private double MC_out;
    private double wep_out;
    
    private boolean isConsumedShield;
    private boolean isConsumedMC;
    private boolean isConsumedWep;
    
    public Engine( FuelDepot dep){
        isConsumedShield = false;
        isConsumedMC = false;
        isConsumedWep = false;
        free = CENT;
        shield = MC = wep = 0;
        fuel = dep;
    }
    public void work(){
        double fuelNeed = (CENT - free)*FUEL_BASE_CONSUMPTION;
        double receivedFuel = fuel.pull(fuelNeed);
        double n = shield + MC + wep;
        shield_out = (shield/n)*receivedFuel*SHIELD_RATE;
        MC_out = (MC/n)*receivedFuel*MC_RATE;
        wep_out = ( wep/n)*receivedFuel*WEP_RATE;
    }
    
    public boolean changeOutPercent( OutType type, double amount){
        if( type != OutType.FREE ){
            double realAmount;
            free = free - amount;
            if( free < 0){
                realAmount =  amount + free;
                free = 0;
            }
            else
                realAmount = amount;
            if( null != type )switch (type) {
                case SHIELD:
                    shield = shield + realAmount;
                    break;
                case MATC:
                    MC = MC + realAmount;
                    break;
                case WEP:
                    wep = wep + realAmount;
                    break;
                default:
                    break;
            }
            return true;
        }
        return false;
    }

    public double generateShield(){
        if( isConsumedShield == true)
            return 0;
        isConsumedShield = true;
        return shield_out;
    }
    public double generateWep(){
        if( isConsumedWep == true)
            return 0;
        isConsumedWep = true;
        return wep_out;
    }
    public double generateMC(){
        if( isConsumedMC == true)
            return 0;
        isConsumedMC = true;
        return MC_out;
    }
    
}
