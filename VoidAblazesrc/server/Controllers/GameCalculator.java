/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Interface.*;
import Data.*;
import Tools.*;
import java.lang.Runnable;
import java.lang.Thread;
import java.util.*;
import java.lang.System;
import java.util.LinkedList;

/**
 *
 * @author Magus
 */
public class GameCalculator implements Runnable{
    public int ID;
    public Connection pc1;
    public Connection pc2;
    public GameData data;
    private Queue<InputData> input1 = new LinkedList<InputData>();
    private Queue<InputData> input2 = new LinkedList<InputData>();
    public ServerController serverControl;
    private CollisionHandler ch;
    
    public boolean init(Connection c1, Connection c2, GameData gd){ //TODO
        setConnections( c1, c2);
        data = gd;
        /*
        .
        .  TODO
        .
        */
        pc1.postGameInit( data);
        pc2.postGameInit( data);
        /*
        .
        .  TODO
        .
        */
        return true;
    }
    
    private boolean process(){ //TODO
        return true;
    }

    @Override
    public void run(){ 
        long a = System.nanoTime();
        process(); //calculation
        long b =  System.nanoTime();
        try{ Thread.sleep(10/*change this*/ - (b - a) ); } // to normalize the speed
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println( " Error: Calculator process interrupted!");
        }
    }
    
    private void gameEnds(){
     //TODO
    }
    
    private void notifyEnding(){
        serverControl.aGameEnds( this );
    }
    
    public void setConnections( Connection c1, Connection c2){
        pc1 = c1;
        pc2 = c2; 
    }
    
    private void postGameState( Connection c){
        c.postGameState( data);
    }
    
    private void processInput(){
        //TODO
    }
    
    public void addToInputQueue( InputData id){
        //TODO
    }
    
    
}
