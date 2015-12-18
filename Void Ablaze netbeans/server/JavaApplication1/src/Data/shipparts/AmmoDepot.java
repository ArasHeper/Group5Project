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
public class AmmoDepot {
    public static final double MAX_AMMO = 200000;
    private double ammo;

    public double depot() {
        return ammo;
    }
    public double pull( double d){
        ammo = ammo - d;
        if( ammo < 0){
            d = d + ammo;
            ammo = 0;
            return d;
        }
        return d;
    }
    public void addAmmo( double a ){
        ammo = ammo + a;
    }
}
