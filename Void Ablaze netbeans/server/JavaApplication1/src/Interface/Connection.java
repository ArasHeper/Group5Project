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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Magus
 */
public class Connection implements Runnable {
    ServerSocket skt;
    boolean active;
    public boolean player1;
    public int portNo = 100;
    public DataSerializer sr;
    public boolean isReserved = false;
    String reserverIP;
    ServerController ctrl;
    GameCalculator calc;
    DataInputStream in;
    DataOutputStream out;
    GameData data;
    private boolean shouldDie = false;
    
   
    //TODO: catchs
    
    Thread in_l = new Thread("input listener"){
        @Override
        public void run() {
            while(true){
                System.out.println(" it runs ");
                if( data == null)
                    System.out.println(" no data for connection, we have a problem");
                else{
                    try {
                       calc.addToInputQueue( sr.deSerialize( "%" + player1 + "%" + in.readUTF()));
                    } 
                    catch (IOException ex) {
                       Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                       in.reset();
                    } 
                    catch (IOException ex) {
                       Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
                    }  
                }
            }
        }
    };  
    
    public Connection( int localPort, ServerController sc){
        ctrl = sc;
        isReserved = false;
        try {
            skt = new ServerSocket(localPort);
            // 
            
        }
        catch(IOException e){
            System.out.println(" socketfailed");
        }
        portNo = skt.getLocalPort();
    }
    
    public Connection( int localPort, ServerController sc, String reserverIP){
        ctrl = sc;
        this.reserverIP = reserverIP;
        isReserved = true;
        try {
            skt = new ServerSocket(localPort);
        }
        catch(IOException e){
            System.out.println(" socketfailed constructor 2");
        }
        portNo = skt.getLocalPort();
        System.out.println(" new cons port"+ portNo);
        System.out.println(" local port: "+ localPort + "/" + skt.getLocalPort());
        System.out.println(this);
        System.out.println(reserverIP);
        
    }
    
    private int requestReroute( String IP){
        // this requests creation of a new connection for a client,
        // later sends port no. of the created connection to that client
        return ctrl.generateAReservedConnection(IP);
    }
    
    public void postGameState( GameData data ){
        //only posts dynamic values
        try {
            //sends eveything upon game initialization
            out.writeUTF( "im outputing" );
            out.writeUTF( "%" + player1 + "%" + sr.serialize(false, data) );
            out.flush();
            System.out.println("Game state :" + sr.serialize(false, data));
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void postGameInit( GameData data ){
        try {
            //sends eveything upon game initialization
            out.writeUTF( "%" + player1 + "%" + sr.serialize(false, data) );
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        data = calc.data;
    }
    
    private void endMe( GameCalculator c){
        //if connection drops
     
    }
    private void endMe( ServerController c){
        //if connection drops
    }
    
    public void endYourself(){
        shouldDie = true;
    }
    

    @Override
    public void run() {
        boolean run = true;
        while( true){
            System.out.println(" looped" + isReserved +  Thread.currentThread());
            try
            {
               System.out.println(" ME FOLLOWED BY" );
               Socket server = skt.accept();// BUG: when reserved vlient doesnt reach here
               System.out.println(" OTHER ME" );
               if(isReserved){
                   System.out.println(" RESERVED" );
                   if( !reserverIP.equals(server.getInetAddress().getHostAddress())){
                       server.close();
                       System.out.println(" closed" + isReserved);
                       run = true;
                   }
                   else{
                       System.out.println(" why am i not here?MEEEEE" );
                       in = new DataInputStream(server.getInputStream());
                       out = new DataOutputStream(server.getOutputStream());
                       System.out.println(" why am i not here?" );
                       if(!in_l.isAlive()) 
                           in_l.start();
                       run = true;
                   }
               }
               else{
                   System.out.println(" me shouldbe" );
                   int newPort = requestReroute(server.getInetAddress().getHostAddress());
                   String msg = "NEW:" + newPort + "\n";
                   out = new DataOutputStream(server.getOutputStream());
                   System.out.println(" followed by him" + newPort );
                   out.writeUTF(msg);
                   server.close();
                   run = true;
               }
            }
            catch(IOException e)
            {
               System.out.println( "something had gone terribly wrong");
            }
            if( shouldDie)
                Thread.currentThread().interrupt();
            
        }
    }
}
