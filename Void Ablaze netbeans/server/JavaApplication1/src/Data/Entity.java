/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Data.Math.*;

/**
 *
 * @author Magus
 */
public abstract class Entity {
    
    public int ID;
    
    //location:
    public Point loc;
    
    //direction:
    public Vector dir; // dirrection
    public boolean isDynamic = false;
    
    
    
}
