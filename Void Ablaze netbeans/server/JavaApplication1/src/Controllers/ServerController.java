/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import java.lang.Runnable;
import java.lang.Thread;
import Data.*;
import Tools.*;
import Interface.*;
/**
 *
 * TODO
 */
public class ServerController implements Runnable {
    //threads for game calculators:
    private Thread[] calcThreads = new Thread[3]; //sample int
    private int[] calcFlag = new int[3]; // 0 = empty game, 1 = 1 spot free, 2 = 2 spots free
    //threads for connections
    private Connection[] conns= new Connection[7]; // sample int, 1st always is the main socket
    private int[] calcMap = new int[6]; // keeps the track of Game Calculators bound to Connections
    
    private GameList gameList;
    
    private void couplePairs( Connection c1, Connection c2){
        //create a game Calc, initialize everything related to it
        //start the thread created Calc resides in
    }
    
    public void aGameEnds( GameCalculator gc){
        //purges the GameCalculator and any other connection boun to it
        //purge the thread that calculator resides in
    }
    
    public String generateAReservedConnection( String clientIp ){
        return "TODO";
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("TODO"); 
    }
    
    
    
    
    
    
    
}