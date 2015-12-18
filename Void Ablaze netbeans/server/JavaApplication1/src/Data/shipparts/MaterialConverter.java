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
public class MaterialConverter {
    static public enum OutType{ FREE, FIGHTER, MAE, AMMO, FUEL};
    static double RAW_BASE_CONSUMPTION = 100;
    static double ENG_BASE_CONSUMPTION = 10;
    static double CENT = 100;
    static double FIGHTER_RATE = 0.5;
    static double ME_RATE = 0.1;
    static double AMMO_RATE = 4;
    static double FUEL_RATE = 4;
    // max fuel consumption = BASE * CENT 
    // max shield output = CENT * SHIELD_RATE. etc.
    public RawDepot raw;
    public double powerIn;
    
    //below values are percentage
    private double free;
    private double fighter;
    private double ME;
    private double ammo;
    private double fuel;
    //ends here
    
    //below values are rewritten for each work
    //if caller doesnt collect them after the work one
    //work cycle will be sacrificed for naught 
    private double fighter_out;
    private double free_out;
    private double ME_out;
    private double ammo_out;
    private double fuel_out;
    
    private boolean isConsumedFighter;
    private boolean isConsumedME;
    private boolean isConsumedAmmo;
    private boolean isConsumedFuel;
    
    public MaterialConverter( RawDepot dep){
        isConsumedFighter = false;
        isConsumedME = false;
        isConsumedAmmo = false;
        free = CENT;
        fighter = ME = ammo = fuel = 0;
        raw = dep;
    }
    
    public void work(){
        double engNeed = (CENT - free)*ENG_BASE_CONSUMPTION;
        double a = powerIn/engNeed;
        double rawNeed = (CENT - free)*RAW_BASE_CONSUMPTION;
        double receivedRaw = raw.pull(rawNeed);
        double n = fighter + ME + ammo + fuel;
        fighter_out = (fighter/n)*receivedRaw*FIGHTER_RATE*a;
        ME_out = (ME/n)*receivedRaw*ME_RATE*a;
        ammo_out = ( ammo/n)*receivedRaw*AMMO_RATE*a;
        fuel_out = ( fuel/n)*receivedRaw*FUEL_RATE*a;
        
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
                case FIGHTER:
                    fighter = fighter + realAmount;
                    break;
                case MAE:
                    ME = ME + realAmount;
                    break;
                case AMMO:
                    ammo = ammo + realAmount;
                    break;
                case FUEL:
                    fuel = fuel + realAmount;
                    break;
                default:
                    break;
            }
            return true;
        }
        return false;
    }

    public double generateFighter(){
        if( isConsumedFighter == true)
            return 0;
        isConsumedFighter = true;
        return fighter_out;
    }
    public double generateME(){
        if( isConsumedME == true)
            return 0;
        isConsumedME = true;
        return ME_out;
    }
    public double generateAmmo(){
        if( isConsumedAmmo == true)
            return 0;
        isConsumedAmmo = true;
        return ammo_out;
    }
    public double generateFuel(){
        if( isConsumedFuel == true)
            return 0;
        isConsumedFuel = true;
        return fuel_out;
    }
}
