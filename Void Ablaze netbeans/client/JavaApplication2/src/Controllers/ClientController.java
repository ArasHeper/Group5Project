/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import java.awt.event.ActionListener;
import Tools.*;
import Data.*;
import Interface.Connection;
import Views.*;
/**
 *
 * TODO
 */
public class ClientController {
    
    private DataSerializer ser;
    private ViewListener listeners;
    private ClientData dt;
    private Connection cnt;
    
    
    private EnginePanel ep;
    
    private ImportantInfoPanel iip;
    private InGameMenu igm;
    private MainMenu mm;
    private MaterialConverterPanel mcp;
    private PropellersPanel pp;
    private SettingsMenu sm;
    private SquadronsPanel sp;
    private WeaponsPanel wp;
    
    //MEYBE DO THIS
    private ConnectionPanel cp;
    //DOTHIS:
    private GamePanel gp;
    
    public void connectToServer(){
        
    }
    
    public void returnToMenu(){
        
    }
    
    public void toSettings(){
        
    }
    public void exit(){
        
    }
    public void openIngameMenu(){
        
    }
    private void gameReady(){
        
    }
    public void Input( ClientData newData){
        
    }
    public void gameEnds( ){
        
    }
    
    public void changeSettings( int type){
        
    }
    
    public void wepFire( int type ){

    }
    public void propState( int forw, int nose, int tail ){

    }
    public void squCreate( boolean type, int size ){

    }
    public void squOrder( SquadWrapper squ, double x, double y ){

    }
    
    public void squRecall( SquadWrapper squ){

    }
    
    public void MCAmmoOut( double amount){

    }
    public void MCFighterOut( double amount){

    }
    public void MCExtOut( double amount){

    }
    public void MCFuelOut( double amount){

    }
    public void MCIdleOut( double amount){
 
    }
    public void EngShieldOut( double amount){

    }
    public void EngWepOut( double amount){

    }
    public void EngMCOut( double amount){

    }
}
