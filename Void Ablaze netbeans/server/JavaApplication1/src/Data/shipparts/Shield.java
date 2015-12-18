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
public class Shield {
    public static final double MAX = 20000;
    private double shield;
    
    public boolean isDead;
    
    public Shield(){
        shield = MAX;
        isDead = false;
    }
    
    public void addPower( double p){
        shield = shield + p;
        if( shield > MAX)
            shield = MAX;
    }
    
    public void losePower( double p){
        shield = shield - p;
        if( shield <= 0)
            isDead = true;
    }

    public double getShield() {
        return shield;
    }
}
