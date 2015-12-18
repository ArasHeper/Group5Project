/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Interface.*;
import Data.*;
import Data.Math.Edge;
import Data.Math.*;
import Data.Math.Vector;
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
    public boolean gameEnded;
    
    public boolean init(Connection c1, Connection c2, GameData gd, ServerController sc){ //TODO
        setConnections( c1, c2);
        data = gd;
        serverControl = sc;
        gameEnded =  false;
        return true;
    }
    
    private boolean process(){ //TODO
        int counter = 0;
        
        Iterator<InputData> iter = input1.iterator();
        while (iter.hasNext() && counter < 4) {
            InputData item = iter.next();
            data.synch(item);
            iter.remove();
            counter++;
        }
        
        counter = 0;
        iter = input2.iterator();
        while (iter.hasNext() && counter < 4 ) {
            InputData item = iter.next();
            data.synch(item);
            iter.remove();
        }
        
        for (CannonProjectile item: data.cans1){
            item.loc.x = item.veloc.vector.x + item.loc.x;
            item.loc.y = item.veloc.vector.y + item.loc.y;
        }
        for (CannonProjectile item: data.cans2){
            item.loc.x = item.veloc.vector.x + item.loc.x;
            item.loc.y = item.veloc.vector.y + item.loc.y;
        }
        data.p1.move();
        data.p2.move();
        //TODO: fighters, HVW, ME'S
        
        //collision, unt vector is the unit vector of functional axis, which 
        // is also the unit vector of a normal of an edge
        //battleship- cannon projectile collision detection:
        
        Iterator<CannonProjectile> it = data.cans1.iterator();
        while(it.hasNext()){
            CannonProjectile item = it.next();
            Point p = new Point( item.loc.x, item.loc.y);
            Edges el = new Edges(data.p2.edges.size, data.p2.edges.list);
            boolean sep = false;
            for( int i = 0; i > el.size && !sep; i++){
                Edge target = el.list[i]; 
                Vector normal = new Vector(target.end.y - target.start.y, -target.end.x + target.start.x);
                Vector dist = new Vector( p.x - data.p2.loc.x, p.y - data.p2.loc.y);
                double ul = Math.sqrt(normal.x * normal.x + normal.y*normal.y);
                double ux = normal.x/ul;
                double uy = normal.y/ul;
                Vector unitNormal = new Vector( ux,uy);
                double leng = dist.x*unitNormal.x + dist.y*unitNormal.y;
                int a = 0;
                if( i+1 == el.size)
                    a = 0;
                else 
                    a = i+1;
                if( el.list[a].leng/2 < leng )
                    sep = true;
            }
            if( !sep ){
                ch.collide( item, data.p2);
                it.remove();
            }
        }
        it = data.cans2.iterator();
        while(it.hasNext()){
            CannonProjectile item = it.next();
            Point p = new Point( item.loc.x, item.loc.y);
            Edges el = new Edges(data.p1.edges.size, data.p1.edges.list);
            boolean sep = false;
            for( int i = 0; i > el.size && !sep; i++){
                Edge target = el.list[i]; 
                Vector normal = new Vector(target.end.y - target.start.y, -target.end.x + target.start.x);
                Vector dist = new Vector( p.x - data.p1.loc.x, p.y - data.p1.loc.y);
                double ul = Math.sqrt(normal.x * normal.x + normal.y*normal.y);
                double ux = normal.x/ul;
                double uy = normal.y/ul;
                Vector unitNormal = new Vector( ux,uy);
                double leng = dist.x*unitNormal.x + dist.y*unitNormal.y;
                int a = 0;
                if( i+1 == el.size)
                    a = 0;
                else 
                    a = i+1;
                if( el.list[a].leng/2 < leng )
                    sep = true;
            }
            if( !sep ){
                ch.collide( item, data.p1);
                it.remove();
            }
        }
        
        // battleship - battleship collision
        boolean sep = false;
        for ( int i = 0; i < 4 && !sep; i++){
            Edges e1 = new Edges(data.p1.edges.size, data.p1.edges.list);
            Edge target = e1.list[i]; 
            Vector normal = new Vector(target.end.y - target.start.y, -target.end.x + target.start.x);
            double ul = Math.sqrt(normal.x * normal.x + normal.y*normal.y);
            double ux = normal.x/ul;
            double uy = normal.y/ul;
            Vector unitNormal = new Vector( ux,uy);
            Vector m = new Vector( data.p2.loc.x - data.p1.loc.x, data.p2.loc.y - data.p1.loc.y);
            double mainLeng =  m.x*unitNormal.x + m.y*unitNormal.y;
            
            boolean sepSub = true;
            for ( int ii = 0; ii < 4 && sepSub; ii++){
                Edges e2 = new Edges(data.p2.edges.size, data.p2.edges.list);
                Point p = e2.list[ii].start;
                Vector r = new Vector( p.x - data.p2.loc.x, p.y - data.p2.loc.y);
                double leng =  r.x*unitNormal.x + r.y*unitNormal.y;
                if( mainLeng <= leng){
                    sepSub = false;
                }
            }
            if( sepSub == true )
                sep = true;
        }
        if ( sep == false )
            ch.collide( data.p1, data.p2);
        
        if( data.p1.shield.isDead || data.p2.shield.isDead )
            gameEnds();
        return true;
    }

    @Override
    public void run(){ 
        while(true){
            long a = System.nanoTime();
            process(); //calculation
            long b =  System.nanoTime();
            System.out.println( data);
            pc1.postGameState(data);
            pc2.postGameState(data);
            System.out.println( "calculator is running");
            try{ Thread.sleep(100/*change this*/ - (b - a) ); } // to normalize the speed          
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println( " Error: Calculator process interrupted!");
            }
        }

    }
    
    public boolean addToInputQueue( InputData id){
        if( id.player)
            input1.add(id);
        else
            input2.add(id);      
        return true;
    }
    
    private void gameEnds(){
        gameEnded = true;
        //TODO: rest if needed
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
}
