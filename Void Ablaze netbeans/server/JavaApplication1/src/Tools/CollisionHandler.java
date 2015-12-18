/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;
import Data.*;
import Data.Math.*;

/**
 * TO DO
 * 
 */
public class CollisionHandler {
    static public final double CANNON_DMG = 1000;//PLACE HOLDER, rather use their velocity etc.
    
    public boolean collide( Battleship b1, Battleship b2) {
        double pow1 = b1.shield.getShield();
        double pow2 = b2.shield.getShield();
        b1.shield.losePower(pow2);
        b2.shield.losePower(pow1);
        return true;
    }
    
    public boolean collide( CannonProjectile cp, Battleship b){
        b.shield.losePower(CANNON_DMG);
        return true;
    }
    
    //TODO REST
}
