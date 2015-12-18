/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Controllers.ClientController;
import Data.ClientData;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import Tools.*;
import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Magus
 */
public class Connection implements Runnable{
    static final String SERVER_IP = "127.0.0.1" ;
    static final int SERVER_PORTNO = 6145;
    Socket skt;
    boolean active;
    String IP = SERVER_IP;
    public int portNo = SERVER_PORTNO;
    ClientController ctrl;
    DataSerializer ser;
    DataInputStream in;
    DataOutputStream out;

    public boolean isReserved = false;
    String reserverIP;

    
    public Connection  (ClientController ctrl){
        this.ctrl = ctrl;
        try {
            skt = new Socket(SERVER_IP , SERVER_PORTNO);
            // TODO
        }
        catch(IOException e){
            //TODO
            System.out.println("failed to connect");
        }
    }


    public void connectionRerouted( String IP, int portNo ){
        try {
            skt.close();
            skt = new Socket(IP, 6147);
            System.out.println("MY PORT NO: " + portNo);
            in = new DataInputStream(skt.getInputStream());
            out = new DataOutputStream(skt.getOutputStream());
        } catch (IOException ex) {
            System.out.println("me failed to reroute :( " + portNo);//but why???
        }
    }
   
    private void endMe( ClientController c){
        //if connection drops, TODO obv.  
    }
    
    private void sendToServer( String s){
        try {
            out.writeUTF( s );
            out.flush();
        } catch (IOException ex) {
            System.out.println( "cant write to output stream");
        }
        
    }

    @Override
    public void run() {  
        try {
            System.out.println(skt);
            System.out.println(skt.getInputStream());
            System.out.println(skt.getOutputStream());
            in = new DataInputStream(skt.getInputStream());
            out = new DataOutputStream(skt.getOutputStream());
        } catch (IOException ex) {
            System.out.println("I/O stream creation failure");
        }
        while(true){
            System.out.println("invalue : " + in);

            try {
                //System.out.println("invalue : " + in.readUTF());
                String a = in.readUTF();// BUG: i am not halting the thread, but why?
                System.out.println(a);
                if( !a.equals("")){
                    if( a.contains("NEW:")){
                        int ind = a.indexOf("NEW:");
                        ind = ind + 4;
                        int ind2 = a.indexOf("\n");
                        String b =  a.substring(ind, ind2);
                        System.out.println(Integer.parseInt(b));
                        connectionRerouted( SERVER_IP, Integer.parseInt(b));
                        
                    }
                    else{
                        ClientData newData = ser.deSerialize(a);
                        ctrl.Input(newData);
                    }
                    in.reset();
                }
               
            } catch (IOException ex) {
                System.out.println("Input stream is empty?");
            }
        }
    }
    
}

