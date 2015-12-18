/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Data.Math.AngAcc;
import Data.Math.AngVeloc;
import Data.Math.*;
import Data.Math.Point;
import Data.shipparts.*;
import java.util.ArrayList;

/**
 *
 * @author Magus
 */
public class Battleship extends Dynamic{
   
    public static final double W = 20;
    public static final double H = 5;
    public static final double M = 10000;
    
    public AngAcc angA;
    public AngVeloc angV;
    public AmmoDepot ammo;
    public FuelDepot fuel;
    public FighterDepot fHangar;
    public MEDepot mHangar;
    public RawDepot raw;
    public WepEnergyDepot wepEgy;
    public Shield shield;
    public Engine engine;
    public MaterialConverter MC;
    public Thrusters thrusters;
    
    public int rightCD;
    public int leftCD;
    public int HVWCD;
    
    public double inM;
    
    public Battleship(double n, double m){
        angA = new AngAcc();
        angA.mag = 0;
        angV = new AngVeloc();
        angV.mag = 0;
        ammo = new AmmoDepot();
        ammo.addAmmo( ammo.MAX_AMMO );
        fuel = new FuelDepot();
        fuel.addFuel( fuel.MAX_FUEL );
        fHangar = new FighterDepot();
        mHangar = new MEDepot();
        raw = new RawDepot();
        raw.addRaw( raw.MAX_RAW );
        wepEgy = new WepEnergyDepot();
        shield = new Shield();
        engine = new Engine( fuel );
        MC = new MaterialConverter( raw );
        thrusters = new Thrusters( fuel );
        
        mass = M; 
        acc = new Acc();
        acc.vector = new Vector();
        angA = new AngAcc();
        angV = new AngVeloc();
        dir = new Vector();
        dir.x = 1;
        dir.y = 0;
        
        
        this.loc = new Point( n,m);
        Point a = new Point(loc.x - W/2 , loc.y - H/2 );
        Point b = new Point(loc.x + W/2 , loc.y - H/2 );
        Point c = new Point(loc.x + W/2 , loc.y + H/2 );
        Point d = new Point(loc.x - W/2 , loc.y + H/2 );
        Point[] list = new Point[]{ a, b, c, d};
        edges = new Edges( 4, list);
        
        double w =  edges.list[0].leng;
        double h =  edges.list[0].leng;
        inM = mass/12*( w*w + h*h);
        
        veloc = new Veloc();
        veloc.vector = new Vector();
        

    }
    
    
    public void work(){

        engine.work();
        shield.addPower(engine.generateShield());
        wepEgy.addEnergy(engine.generateWep());
        MC.powerIn = engine.generateMC();
        
        MC.work();
        ammo.addAmmo( MC.generateAmmo());
        fuel.addFuel( MC.generateFuel());
        fHangar.addCraft( MC.generateFighter());
        mHangar.addCraft(MC.generateME());
        
        thrusters.work();
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //+++++++++++++++++++ A C C E L E R A T I O N +++++++++++++++++++++++++++++
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //nose thruster calculation
        double torque = edges.list[0].leng / 2 *thrusters.nose_out;
        angA.mag = angA.mag + torque/inM;
        
        //a = F/m;
        double x = - dir.y;
        double y = dir.x;
        
        double leng = Math.sqrt( x * x + y * y );
        x = x/leng*thrusters.nose_out/mass;
        y = y/leng*thrusters.nose_out/mass;
        
        acc.vector.x = acc.vector.x + x;
        acc.vector.y = acc.vector.y + y;
        
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //belly thruster calculation
        torque = - edges.list[0].leng / 2 *thrusters.belly_out;
        angA.mag = angA.mag + torque/inM;
        
        //a = F/m
        x = - dir.x;
        y = dir.y;
        
        leng = Math.sqrt( x * x + y * y );
        x = x/leng*thrusters.belly_out/mass;
        y = y/leng*thrusters.belly_out/mass;
        
        acc.vector.x = acc.vector.x + x;
        acc.vector.y = acc.vector.y + y;
        
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //main thruster calculation
        //a = F/m
        x = dir.y;
        y = - dir.x;
        
        leng = Math.sqrt( x * x + y * y );
        x = x/leng*thrusters.main_out/mass;
        y = y/leng*thrusters.main_out/mass;
        
        acc.vector.x = acc.vector.x + x;
        acc.vector.y = acc.vector.y + y;
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //+++++++++++++++++++ A C C E L E R A T I O N  ENDS ++++++++++++++++++++++++++
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    }
    public void move(){
        //+++++++++++++++++++++ DIRECTION ++++++++++++++++++++++++++++++++++++++++++
        double x = dir.x;
        double y = dir.y;
        double leng = Math.sqrt( x * x + y * y );
        double sina = y / leng;
        double a = Math.asin(sina);
        a = a + angV.mag*Math.PI*2;
        dir.x = Math.cos(a)*leng;
        dir.y = Math.sin(a)*leng;
        //+++++++++++++++++++++ DIRECTION ENDS ++++++++++++++++++++++++++++++++++++++
        //+++++++++++++++++++++ DISPLACEMENT ++++++++++++++++++++++++++++++++++++++++
        loc.x = loc.x + veloc.vector.x;
        loc.y = loc.y + veloc.vector.y;
        veloc.vector.x = veloc.vector.x + acc.vector.x;
        veloc.vector.y = veloc.vector.y + acc.vector.y;
        //edges below
        Point[] list = new Point[]{
            edges.list[0].start,
            edges.list[1].start,
            edges.list[2].start,
            edges.list[3].start
        };
        for( int i = 0; i < 4; i++){
            double x0 = list[i].x - loc.x;
            double y0 = list[i].y - loc.y;
            leng = Math.sqrt( x0 * x0 + y0 * y0 );
            double sinb = y0 / leng;
            double b = Math.asin(sinb);
            b = b + angV.mag*Math.PI*2;
            x0 = Math.cos(b)*leng;
            y0 = Math.sin(b)*leng;
            list[i].x = loc.x + x0;
            list[i].y = loc.y + y0;  
        }
        edges.list[0].mutate( list[0], list[1]);
        edges.list[1].mutate( list[1], list[2]);
        edges.list[2].mutate( list[2], list[3]);
        edges.list[3].mutate( list[3], list[0]);
        //+++++++++++++++++++++ DISPLACEMENT ENDS ++++++++++++++++++++++++++++++++++++
        angV.mag = angV.mag + angA.mag;
        veloc.vector.x = veloc.vector.x + acc.vector.x;
        veloc.vector.y = veloc.vector.y + acc.vector.y;    
    }
    
    public void changeEngineOutput( Engine.OutType type, double percent){
        engine.changeOutPercent(type, percent);
    }
    
    public void changeMCOutput( MaterialConverter.OutType type, double percent){
        MC.changeOutPercent( type, percent);
    }
    
    public ArrayList<CannonProjectile> rightCannons(){
        double metaX = Math.abs(edges.list[0].start.x - edges.list[0].end.x);
        double metaY = Math.abs(edges.list[0].start.y - edges.list[0].end.y);
        double sina = metaY / edges.list[0].leng;
        double a = Math.asin(sina);
        //        SHIPS          RIGHT         SIDE
        //_____________________________________________________________________
        //-------|1/5|--------|2/5|------------|3/5|------------|4/5|----------
        //         O            O                O                O
        Vector cannonDir;
        cannonDir = new Vector( dir.y , -dir.x);
        
        ArrayList<CannonProjectile> list = new ArrayList<CannonProjectile>();
        double x = edges.list[0].start.x + edges.list[0].leng * 1/5 *Math.cos(a);
        double y = edges.list[0].start.y + edges.list[0].leng * 1/5 *Math.sin(a);
        Point cloc = new Point( x,y );
        list.add(new CannonProjectile( cloc, cannonDir));
        
        x = edges.list[0].start.x + edges.list[0].leng * 2/5 *Math.cos(a);
        y = edges.list[0].start.y + edges.list[0].leng * 2/5 *Math.sin(a);
        cloc = new Point( x,y );
        list.add(new CannonProjectile( cloc, cannonDir));
        
        x = edges.list[0].start.x + edges.list[0].leng * 3/5 *Math.cos(a);
        y = edges.list[0].start.y + edges.list[0].leng * 3/5 *Math.sin(a);
        cloc = new Point( x,y );
        list.add(new CannonProjectile( cloc, cannonDir));
        
        x = edges.list[0].start.x + edges.list[0].leng * 4/5 *Math.cos(a);
        y = edges.list[0].start.y + edges.list[0].leng * 4/5 *Math.sin(a);
        cloc = new Point( x,y );
        list.add(new CannonProjectile( cloc, cannonDir));
        
        return list;   
    }
    
    public ArrayList<CannonProjectile> leftCannons(){
        double metaX = Math.abs(edges.list[2].start.x - edges.list[2].end.x);
        double metaY = Math.abs(edges.list[2].start.y - edges.list[2].end.y);
        double sina = metaY / edges.list[2].leng;
        double a = Math.asin(sina);
        //        SHIPS          LEFT         SIDE
        //_____________________________________________________________________
        //-------|1/5|--------|2/5|------------|3/5|------------|4/5|----------
        //         O            O                O                O
        Vector cannonDir;
        cannonDir = new Vector( -dir.y , dir.x);
        
        ArrayList<CannonProjectile> list = new ArrayList<CannonProjectile>();
        double x = edges.list[2].start.x + edges.list[2].leng * 1/5 *Math.cos(a);
        double y = edges.list[2].start.y + edges.list[2].leng * 1/5 *Math.sin(a);
        Point cloc = new Point( x,y );
        list.add(new CannonProjectile( cloc, cannonDir));
        
        x = edges.list[2].start.x + edges.list[2].leng * 2/5 *Math.cos(a);
        y = edges.list[2].start.y + edges.list[2].leng * 2/5 *Math.sin(a);
        cloc = new Point( x,y );
        list.add(new CannonProjectile( cloc, cannonDir));
        
        x = edges.list[2].start.x + edges.list[2].leng * 3/5 *Math.cos(a);
        y = edges.list[2].start.y + edges.list[2].leng * 3/5 *Math.sin(a);
        cloc = new Point( x,y );
        list.add(new CannonProjectile( cloc, cannonDir));
        
        x = edges.list[2].start.x + edges.list[2].leng * 4/5 *Math.cos(a);
        y = edges.list[2].start.y + edges.list[2].leng * 4/5 *Math.sin(a);
        cloc = new Point( x,y );
        list.add(new CannonProjectile( cloc, cannonDir));
        return list;   
    }
    
    public HyperVelocityProjectile fireHVW(){
        HyperVelocityProjectile hvw = new HyperVelocityProjectile();
        hvw.loc = this.loc;
        hvw.dir = this.dir;
        return hvw;
    } 
}
