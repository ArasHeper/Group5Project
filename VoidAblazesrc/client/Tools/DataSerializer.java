/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Data.ClientData;
import Data.SquadWrapper;

/**
 *
 * @author Magus
 */
public class DataSerializer {
    
    public String serializeWepFire( int type ){
        return "TODO";
    }
    public String propState( int forw, int nose, int tail ){
        return "TODO";
    }
    public String squCreate( boolean type, int size ){
        return "TODO";
    }
    public String squOrder( SquadWrapper squ, double x, double y ){
        return "TODO";
    }
    
    public String squRecall( SquadWrapper squ){
        return "TODO";
    }
    
    public String MCAmmoOut( double amount){
        return "TODO";
    }
    public String MCFighterOut( double amount){
        return "TODO";
    }
    public String MCExtOut( double amount){
        return "TODO";
    }
    public String MCFuelOut( double amount){
        return "TODO";
    }
    public String MCIdleOut( double amount){
        return "TODO";
    }
    public String EngShieldOut( double amount){
        return "TODO";
    }
    public String EngWepOut( double amount){
        return "TODO";
    }
    public String EngMCOut( double amount){
        return "TODO";
    }

        
    public ClientData deSerialize( String data){
        return new ClientData(); //TODO
    }
}
