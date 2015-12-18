/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;


import java.util.ArrayList;

/**
 *
 * @author Magus
 */
public class ClientData extends java.util.Observable{
    public static final double MAPX = 1000000;
    public static final double MAPY = 1000000;
    
    public boolean player1;
    //public ArrayList<SquadWrapper> squads = new ArrayList<SquadWrapper>();
    //public ArrayList<SquadWrapper> rivalSquads = new ArrayList<SquadWrapper>();
    //public ArrayList<EnviroWrapper> environmentals = new ArrayList<EnviroWrapper>();
    public ArrayList<CannonWrapper> p1Ps = new ArrayList<CannonWrapper>();
    public ArrayList<CannonWrapper> p2Ps = new ArrayList<CannonWrapper>();
    public double b1X;
    public double b1Y;
    public double d1X;
    public double d1Y;
    public double b2X;
    public double b2Y;
    public double d2X;
    public double d2Y;
    //public HYVW playersHYVW;
    //public HYVW rivalsHYVW;
    public double RawMaterial;
    public double Shield;
    public double Ammo;
    //public double FighterCount;
    //public double MaterialExtractorCount;
    //public double UsedFighterCount;
    //public double UsedMaterialExtractorCount;
    //public double MaxFighterCount;
    //public double MaxMaterialExtractorCount;
    
    private double MaterialConverterIdle;
    private double MaterialConverterOutputAmmo;
    private double MaterialConverterOutputFuel;
    private double MaterialConverterOutputFighter;
    private double MaterialConverterOutputME;
    
    //public double HYVWCd;
    //public double broadsideLeftCd;
    //public double broadsideRightCd;
    
    private double EngConverterOutputIdle;
    private double EngConverterOutputShield;
    private double EngConverterOutputWep;
    private double EngConverterOutputMC;
    
    
    //TODO
    public void synchPerfect(ClientData gd){
        //replace dynamics with those coming from the server
    }
    public void synch(ClientData gd){
        //only synch shield value, and dynamic inagme objects, Outputs etc. will 
        //not be synched
    }
    
    
    
    
    
    
}
