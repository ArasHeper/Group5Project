/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;
import java.lang.*;
import java.io.*;
import java.net.*;
import Controllers.*;
import Tools.*;
import Data.*;
/**
 *
 * @author Magus
 */
public class Connection {
    ServerSocket skt;
    boolean active;
    int portNo;
    DataSerializer sr;
    public boolean isReserved = false;
    String reserverIP;
    ServerController ctrl;
    GameCalculator calc;
    
    Connection( int localPort, ServerController sc){
        ctrl = sc;
        try {
            skt = new ServerSocket(localPort);
            // TODO
        }
        catch(IOException e){
            //TODO
        }
    }
    
    Connection( int localPort, ServerController sc, String reserverIP){
        ctrl = sc;
        this.reserverIP = reserverIP;
        try {
            skt = new ServerSocket(localPort);
            // TODO
        }
        catch(IOException e){
            //TODO
        }
    }
    
    private void requestReroute( String IP){
        // this requests creation of a new connection for a client,
        // later sends port no. of the created connection to that client
        ctrl.generateAReservedConnection(IP);
    }
    
    public void postGameState( GameData data ){
        //only posts dynamic values
    }
    public void postGameInit( GameData data ){
        //sends eveything upon game initialization
    }
    
    private void inputData( String input){
        InputData id = new InputData();
        /*
        .
        .TODO
        .
        */
        calc.addToInputQueue(id);
    }
    
    public void addGameCalc( GameCalculator c){
        calc = c;
    }
    
    private void endMe( GameCalculator c){
        //if connection drops
     
    }
    private void endMe( ServerController c){
        //if connection drops
    }
    
}
