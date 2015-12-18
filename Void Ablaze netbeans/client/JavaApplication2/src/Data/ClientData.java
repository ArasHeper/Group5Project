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
    
    public ArrayList<SquadWrapper> squads = new ArrayList<SquadWrapper>();
    public ArrayList<SquadWrapper> rivalSquads = new ArrayList<SquadWrapper>();
    public ArrayList<EnviroWrapper> environmentals = new ArrayList<EnviroWrapper>();
    public ArrayList<CannonWrapper> cannonPs = new ArrayList<CannonWrapper>();
    public ArrayList<CannonWrapper> rivalCannonPs = new ArrayList<CannonWrapper>();
    public double battleshipX;
    public double battleshipY;
    public double direction;
    public double rival_battleshipX;
    public double rival_battleshipY;
    public double rival_direction;
    public HYVW playersHYVW;
    public HYVW rivalsHYVW;
    public double RawMaterial;
    public double Shield;
    public double Ammo;
    public double FighterCount;
    public double MaterialExtractorCount;
    public double UsedFighterCount;
    public double UsedMaterialExtractorCount;
    public double MaxFighterCount;
    public double MaxMaterialExtractorCount;
    
    private double MaterialConverterIdle;
    private double MaterialConverterOutputAmmo;
    private double MaterialConverterOutputFuel;
    private double MaterialConverterOutputFighter;
    private double MaterialConverterOutputME;
    
    public double HYVWCd;
    public double broadsideLeftCd;
    public double broadsideRightCd;
    
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