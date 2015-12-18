/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Data.shipparts.Engine;
import Data.shipparts.*;
import java.util.ArrayList;

/**
 *
 * @author Magus
 */
public class GameData {
    //those statics will be used till we can read from a file
    static public final double MAXX = 1000000;
    static public final double MAXY = 1000000;
    static public final double SHIP1LOCX = MAXX/7;
    static public final double SHIP1LOCY = MAXY/5;
    static public final double SHIP2LOCX = MAXX*6/7;
    static public final double SHIP2LOCY = MAXX*4/5;
    private int id;
    
    
    
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
    
    public GameData(){
        //start counting from 0
        //no static entities here
        //coz deadline q-q
        id = 0;
        p1 = new Battleship(SHIP1LOCX,SHIP1LOCY);
        p1.ID = id;
        id ++;
        p2 = new Battleship(SHIP2LOCX,SHIP2LOCY);
        p2.ID = id;
        id ++;
    }
    public GameData( String fileName){
        //TODO, reads from a file to construct static objects
    }
    
    public boolean synch( InputData id){
        //TODO
        boolean player = id.player; // 1st or 2nd player
        Battleship b;
        
        if( player == true)
            b = p1;
        else
            b = p2;
        
        if( id.pForwChange == true)
            b.thrusters.changeThrusterOutput(id.pForw, Thrusters.Type.MAIN);
        if( id.pNoseChange == true)
            b.thrusters.changeThrusterOutput(id.pNose, Thrusters.Type.NOSE);
        if( id.pTailChange == true)
            b.thrusters.changeThrusterOutput(id.pTail, Thrusters.Type.BELLY);
        if( id.broadsideLeft){
            if( player == true)
                cans1.addAll(b.leftCannons());
            else
                cans2.addAll(b.leftCannons());
        }
        if( id.broadsideRight){
            if( player == true)
                cans1.addAll(b.rightCannons());
            else
                cans2.addAll(b.rightCannons());
        }

        if( id.HYVW){
            if( player == true)
                HYVP1 = b.fireHVW();
            else
                HYVP2 = b.fireHVW();
        }    
        //ships internal components
        //Engine
        if( id.engineChange == true ){
            b.changeEngineOutput(Engine.OutType.SHIELD, id.shield);
            b.changeEngineOutput(Engine.OutType.WEP, id.wep);
            b.changeEngineOutput(Engine.OutType.MATC, id.materialConverter);
        }


        //MaterialConverter
        if( id.MCChange == true ){
            b.changeMCOutput( MaterialConverter.OutType.FIGHTER, id.fighter);
            b.changeMCOutput( MaterialConverter.OutType.MAE, id.ME);
            b.changeMCOutput( MaterialConverter.OutType.FUEL, id.fuel);
            b.changeMCOutput( MaterialConverter.OutType.AMMO, id.ammo);
        }
        return true;
    }
}
