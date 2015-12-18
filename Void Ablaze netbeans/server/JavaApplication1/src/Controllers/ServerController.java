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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * TODO
 */
public class ServerController implements Runnable {
    
    public static final int DEF_PORT = 1234;
    //threads for game calculators:
    private Thread[] calcThreads = new Thread[1]; //sample int
    private int[] calcFlag = new int[1]; // 0 = empty game, 1 = 1 spot free, 2 = 2 spots free
    int csize = 0;
    GameCalculator calculator;
    //threads for connections
    private Thread[] connectionThreads = new Thread[3];
    int nsize;
    private Connection[] conns= new Connection[3]; // sample int, 1st always is the main socket
    private int[] calcMap = new int[3]; // keeps the track of Game Calculators bound to Connections
    // -2 unreserved, -1 available, 0 , 1, 2, 3, etc. bound to 
    
    private GameList gameList;
    private GameData gameD; //TODO remove and tie to gameList
    
    
    
    
    public ServerController(){
        calcFlag[0] = 0;
        nsize = 0;
        System.out.println("cons nsize:" + nsize);
        conns[nsize] = new Connection( 6145 , this);
        connectionThreads[nsize] = new Thread( conns[nsize]);

        calcMap[0] = -2;
        calcMap[1] = -1;
        calcMap[2] = -1;
        nsize++;
        System.out.println("cons nsize:" + nsize);
    }
    public void start(){
        connectionThreads[0].start();
        System.out.println("STARTED" + nsize);
        System.out.println(calcMap[2] + "" + calcMap[1] + "" + calcMap[0]);
    }
    
    private void couplePairs( int c1, int c2){
        GameCalculator calc = new GameCalculator();
        gameD = new GameData();
        
        calc.init(conns[c1], conns[c2], gameD, this);
        conns[c1].addGameCalc( calc );
        conns[c1].player1 = true;
        conns[c2].player1 = false;
        conns[c2].addGameCalc( calc );
        calcMap[c1] = 0;
        calcMap[c2] = 0;
        DataSerializer ser = new DataSerializer();
        conns[c1].sr = ser;
        conns[c2].sr = ser;
        
        calcThreads[0] = new Thread(calc);
        calcThreads[0].start();
        calculator = calc;
    }
    
    public void aGameEnds( GameCalculator gc){
        //purges the GameCalculator and any other connection bound to it
        //purge the thread that calculator resides in
    }
    
    public int generateAReservedConnection( String clientIp ){
        if ( nsize < 3){
            nsize++;
            Connection c = new Connection( 0, this, clientIp);
            int port = 0;
            System.out.println(c);
            System.out.println(" generate: "+ port + "c.portNo:" + c.portNo);
            port = c.portNo;
            System.out.println(" generate: "+ port + "c.portNo:" + c.portNo);
            connectionThreads[ nsize - 1] = new Thread( c);
            connectionThreads[ nsize - 1].start();
            conns[ nsize - 1 ] = c;
            System.out.println(" nsize: "+ nsize);
            calcMap[ nsize -1 ] = -1;
            System.out.println(" genereta: "+ port);
            return port;
        }  
        return -1;
    }

    @Override
    public void run() {
        while(true){
            System.out.println("size: " + nsize);
            System.out.println(calcMap[2] + "" + calcMap[1] + "" + calcMap[0]);
            if( nsize == 3){
                if( calcMap[1] == -1 && calcMap[2] == -1){
                    couplePairs( 1, 2);
                    System.out.println("COUPLED" + Thread.currentThread());
                    
                }
                else{
                    if(calculator.gameEnded ){
                        calcThreads[0].interrupt();
                        conns[1].endYourself();
                        conns[2].endYourself();
                    }
                }
            }
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("cant sleep");
            }
        }
        
    }
    
    
    
    
    
    
    
}
