/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;
import Data.math.*;

/**
 *
 * @author Magus
 */
public class CannonProjectile extends Dynamic{
    static final double SPEED = 10;


    public CannonProjectile( Point loc, Vector dir){
        this.loc = loc;
        this.dir = dir;
        double x = dir.x;
        double y = dir.y;
        double leng = Math.sqrt( x * x + y * y );
        double sina = y / leng;
        double cosa = x / leng;
        veloc.vector.x = SPEED*cosa;
        veloc.vector.y = SPEED*sina;
    }
    public void move(){
        loc.x = loc.x + veloc.vector.x;
        loc.y = loc.y + veloc.vector.y;
    }
}
