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
    
    public boolean collide( Entity e1, Entity e2) {
       if ( e1.isPhysical && e2.isPhysical ){
           
           if( e1.isDynamic && e2.isDynamic ){
              
          }
           if( !e1.isDynamic && e2.isDynamic ){
              
          }
           if( e1.isDynamic && !e2.isDynamic ){
              
          }
           if( !e1.isDynamic && !e2.isDynamic ){
              
          }
       }
       else if ( !e1.isPhysical && e2.isPhysical ){
           
       }
       else if ( e1.isPhysical && !e2.isPhysical ){
           
       }
       else if ( !e1.isPhysical && !e2.isPhysical ){
           
       }
   
       return true; 
    }
    
    public boolean pDpD( Physical e1, Physical e2){  
        return true;
    }
}
