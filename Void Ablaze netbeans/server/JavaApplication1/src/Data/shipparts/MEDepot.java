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
public class MEDepot {
    public static final double MAX = 40;
    
    private double ready;
    private double used;
    private double existing;
    
    public void increaseReady(){
        existing --;
        if( existing < 0 ){
            existing = 0;
        }
        else{
            ready ++;
        }
    }
    public double release(){
        double a = ready;
        used = used + ready;
        ready = 0;
        return a;
    }
    
    public void receive( double a ){
        used = used - a;
    }
    
    public void addCraft( double a){
        existing = existing + a;
        if( existing > MAX){
            existing = MAX;
        }
    }

    public double getReady() {
        return ready;
    }

    public double getUsed() {
        return used;
    }

    public double getExisting() {
        return existing;
    }
    
}
