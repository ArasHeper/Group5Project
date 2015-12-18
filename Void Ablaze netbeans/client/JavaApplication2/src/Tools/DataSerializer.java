/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Data.CannonWrapper;
import Data.ClientData;
import Data.SquadWrapper;

/**
 *
 * @author Magus
 */
public class DataSerializer {
    
    public String serializeWepFire( int type ){  
        return "FIR:" + Integer.toString(type) + "-";
    }
    public String propState( int forw, int nose, int tail ){
        return "FRW:" + Integer.toString(forw) + "-" + "NSE" 
                + Integer.toString(nose) +  "TIL" + Integer.toString(tail) + "-";
    }
    
    //unfinished stuff below TODO
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
        // BS:loc.X,loc.y;dir.X,dir.Y-loc.X,loc.Y;dir.x,dir.Y
        // ===> BS: battleship1-battleship2
        //BS:(cont)|CP:(cont)
        // > CP:(cont);
        // (cont) = +loc.X,loc.Y;dir.X,dir.Y+(etc. for other cannonPs)-(2nd players cannon Ps) 
        ClientData c = new ClientData();
        String[] inputs = data.split("/n");
        String target = "";
        for( String s:inputs){
            target = s;
        }
        if( target.contains("%")){
            target = target.substring(1);
            int index = target.indexOf("%");
            String token = target.substring( 0, index - 1);
            if( token.equals( "true"))
                c.player1 = true;      
            else
                c.player1 = false;
            target = target.substring(1);
        }
        if( target.contains("BS:")){
            target= target.substring(3);
            
            int index = target.indexOf(",");
            String token = target.substring( 0, index-1);
            c.b1X = Double.parseDouble(token);
            target = target.substring(1);
            
            index = target.indexOf(";");
            token = target.substring( 0, index-1);
            c.b1Y = Double.parseDouble(token);
            target = target.substring(1);
            
            index = target.indexOf(",");
            token = target.substring( 0, index-1);
            c.d1X = Double.parseDouble(token);
            target = target.substring(1);
            
            index = target.indexOf("-");
            token = target.substring( 0, index-1);
            c.d1Y = Double.parseDouble(token);
            target = target.substring(1);
            
            index = target.indexOf(",");
            token = target.substring( 0, index-1);
            c.b2X = Double.parseDouble(token);
            target = target.substring(1);
            
            index = target.indexOf(";");
            token = target.substring( 0, index-1);
            c.b2Y = Double.parseDouble(token);
            target = target.substring(1);
            
            index = target.indexOf(",");
            token = target.substring( 0, index-1);
            c.d2X = Double.parseDouble(token);
            target = target.substring(1);
            
            index = target.indexOf("|");
            token = target.substring( 0, index-1);
            c.d2X = Double.parseDouble(token);
            target = target.substring(1);
        }
        
        if( target.contains( "CP:")){
            target= target.substring(3);
            String[] str = target.split("-");
            String[] ct;
            ct = str[0].split("\\+");
            for( String item: ct){
                CannonWrapper cw = new CannonWrapper();
                
                int index = item.indexOf(",");
                String token = item.substring( 0, index-1);
                cw.x = Double.parseDouble(token);
                item = item.substring(1);

                index = item.indexOf(";");
                token = item.substring( 0, index-1);
                cw.y = Double.parseDouble(token);
                item = item.substring(1);

                index = item.indexOf(",");
                item = target.substring( 0, index-1);
                cw.dx = Double.parseDouble(token);
                item = item.substring(1);
                
                token = item.substring( 0, index-1);
                cw.dy = Double.parseDouble(token);
                item = item.substring(1);
                //c.p1Ps = Integer.parseInt(token);
                
                c.p1Ps.add(cw);
            }
            ct = str[1].split("\\+");
            for( String item: ct){
                CannonWrapper cw = new CannonWrapper();
                
                int index = item.indexOf(",");
                String token = item.substring( 0, index-1);
                cw.x = Double.parseDouble(token);
                item = item.substring(1);

                index = item.indexOf(";");
                token = item.substring( 0, index-1);
                cw.y = Double.parseDouble(token);
                item = item.substring(1);

                index = item.indexOf(",");
                item = target.substring( 0, index-1);
                cw.dx = Double.parseDouble(token);
                item = item.substring(1);
                
                token = item.substring( 0, index-1);
                cw.dy = Double.parseDouble(token);
                item = item.substring(1);
                //c.p1Ps = Integer.parseInt(token);
                c.p2Ps.add(cw);
            }
        }
        
        return c; 
    }
}
