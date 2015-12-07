/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Controllers.ClientController;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import Tools.*;

/**
 *
 * @author Magus
 */
public class Connection {
    static String SERVER_IP;
    static int SERVER_PORTNO;
    Socket skt;
    boolean active;
    String IP = SERVER_IP;
    int portNo = SERVER_PORTNO;
    ClientController ctrl;
    DataSerializer ser;

    public boolean isReserved = false;
    String reserverIP;

    
    Connection( ClientController ctrl){
        this.ctrl = ctrl;
        try {
            skt = new Socket(IP, portNo);
            // TODO
        }
        catch(IOException e){
            //TODO
        }
    }
    public void ConnectionRerouted( String IP, int portNo ){
        //TODO
    }
   
    private void endMe( ClientController c){
        //if connection drops   
    }
    
    private void sendToServer( String s){
        
    }
    
}

