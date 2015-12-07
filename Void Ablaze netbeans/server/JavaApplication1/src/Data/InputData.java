/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author Magus
 */
public class InputData {
    //player
    //propeller power
    public boolean player; // 1st or 2nd player
    
    public int pForw; // Forward/backward propeller
    public boolean pForwChange;
    
    public int pNose; // side nose propeller
    public boolean pNoseChange; 
    
    public int pTail; // side tail propeller
    public boolean pTailChange; 
    
    //Wep
    public boolean broadsideLeft;
    public boolean broadsideRight;
    public boolean HYVW;
    
    //Squadrons
    public boolean newSqu;
    public int type; // 0 = figter, 1 = ME
    public int size;
    public int ID; 
    public boolean recall;
    public boolean newTarget;
    public int x;
    public int y;
    public int MEtargetID;
    
    //ships internal components
    //Engine
    public boolean engineChange;
    public int shield;
    public int wep;
    public int materialConverter;
    
    //MaterialConverter
    public boolean MCChange;
    public int fighter;
    public int ME;
    public int fuel;
    public int ammo;
    
    //ConnecitonLoss or PlayerLeaves
    boolean specialEnd;

}
