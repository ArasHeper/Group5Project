/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.ArrayList;

/**
 *
 * @author Magus
 */
public class GameData {
    //TODO
    public Battleship p1;
    public Battleship p2;
    
    public HyperVelocityProjectile HYVP1;
    public HyperVelocityProjectile HYVP2;
    
    
    public ArrayList<CannonProjectile> cans1 = new ArrayList<CannonProjectile>();
    public ArrayList<CannonProjectile> cans2 = new ArrayList<CannonProjectile>();
    
    public ArrayList<FighterSqu> fighters1 = new ArrayList<FighterSqu>();
    public ArrayList<FighterSqu> fighters2 = new ArrayList<FighterSqu>();
    
    public ArrayList<MatExt> MEs1 = new ArrayList<MatExt>();
    public ArrayList<MatExt> MEs2 = new ArrayList<MatExt>();
    
    public ArrayList<Static> stacs = new ArrayList<Static>();
    
    
    
    
    
    
    public GameData( String fileName){
        //TODO, reads from a file to construct static objects
    }
    
    public boolean synch( InputData id){
        //TODO
        return true;
    }
}
