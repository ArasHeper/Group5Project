/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;
import Data.*;
/**
 *
 * @author Magus
 */
public class DataSerializer {
    public String serialize( boolean init, GameData data){
        // BS:loc.X,loc.y;dir.X,dir.Y-loc.X,loc.Y;dir.x,dir.Y
        // ===> BS: battleship1-battleship2
        //BS:(cont)|CP:(cont)
        // > CP:(cont);
        // (cont) = +loc.X,loc.Y;dir.X,dir.Y+(etc. for other cannonPs)-(2nd players cannon Ps) 
        String ser = "";
        ser = ser + "BS:" + String.valueOf( data.p1.loc.x) + "," + String.valueOf( data.p1.loc.y)
                + ";" + String.valueOf( data.p1.dir.x) + "," + String.valueOf( data.p1.dir.y);
        ser = ser + "-" + String.valueOf( data.p2.loc.x) + "," + String.valueOf( data.p2.loc.y)
                + ";" + String.valueOf( data.p2.dir.x) + "," + String.valueOf( data.p2.dir.y);
        ser = ser + "|CP:";
        for( CannonProjectile item: data.cans1 ){
            ser = ser + "+" + String.valueOf(item.loc.x) + "," + String.valueOf(item.loc.y)
                    + ";" + String.valueOf(item.dir.x) + "," + String.valueOf(item.dir.y);
        }
        ser = ser + "-";
        for( CannonProjectile item: data.cans2 ){
            ser = ser + "+" + String.valueOf(item.loc.x) + "," + String.valueOf(item.loc.y)
                    + ";" + String.valueOf(item.dir.x) + "," + String.valueOf(item.dir.y);
        }
        //TODO: add others if time allows  
        return ser + "/n";
    }
    public InputData deSerialize( String data){
        String[] items = data.split("%");
        InputData id = new InputData();
        String ply = items[0];
        if( ply.equals("true")){
            id.player =true;
        }
        else
            id.player = false;

        for( String item: items){
            switch (item.substring(0,3)) {
                case "FIR:":
                    {
                        String tok = item.substring(4);
                        int type = Integer.parseInt(tok);
                        if( type == 0)
                            id.broadsideRight = true;
                        else
                            id.broadsideLeft = true;
                        break;
                    }
                case "FRW:":
                    {
                        String tok = item.substring(4);
                        id.pForw = Double.parseDouble(tok);
                        id.pForwChange = true;
                        break;
                    }
                case "NSE:":
                    {
                        String tok = item.substring(4);
                        id.pNose = Double.parseDouble(tok);
                        id.pNoseChange = true;
                        break;
                    }
                case "TIL:":
                    {
                        String tok = item.substring(4);
                        id.pTail = Double.parseDouble(tok);
                        id.pTailChange = true;
                        break;
                    }
                default:
                    break;
            }

        }
        return new InputData(); //TODO
    }
    
}
