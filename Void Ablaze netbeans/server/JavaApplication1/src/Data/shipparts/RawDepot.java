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
public class RawDepot {
    public static final double MAX_RAW = 200000;
    private double raw;
    
    
    public double depot() {
        return raw;
    }
    public double pull( double d){
        raw = raw - d;
        if( raw < 0){
            d = d + raw;
            raw = 0;
            return d;
        }
        return d;
    }
    
    public void addRaw( double a ){
        raw = raw + a;
    }
}
