/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.shipparts;

/**
 *
 * @author Magus
 */
public class Thrusters {
    /*          
             MAIN
            IIIIIII
                -
            IIIIIII
            _______
    II-II  | NOSE   | II + II  
           |        |
           |        |
           |        |
           |        |
    II - II| BELLY  | II + II
           |________|
            IIIIIIII
                +
            IIIIIIII
               MAIN
    */
    static public final double NOSE_MAX = 10;
    static public final double BELLY_MAX = 10;
    static public final double MAIN_MAX = 50;
    static public final double FUEL_CONSUME_RATE = 1;
    static public final double IMPULSE_RATE = 10;
    static public enum Type{ NOSE, BELLY, MAIN };
    
    private double nose;
    private double belly;
    private double main;
    private FuelDepot fuel;
    
    public double nose_out;
    public double belly_out;
    public double main_out;
    
    public Thrusters( FuelDepot dep ){
        fuel = dep;
        nose = 0;
        belly = 0;
        main = 0;
    }
    
    public void changeThrusterOutput( double a, Type i ){
         if( null != i )switch (i) {
                case NOSE:
                    nose = a;
                    break;
                case BELLY:
                    belly = a;
                    break;
                case MAIN:
                    main = a;
                    break;
                default:
                    break;
            }
         if( NOSE_MAX - nose < 0 )
             nose = NOSE_MAX;
         if( NOSE_MAX + nose < 0 )
             nose = - NOSE_MAX;
         if( BELLY_MAX - belly < 0 )
             belly = BELLY_MAX;
         if( BELLY_MAX + belly < 0 )
             belly = - BELLY_MAX;
         if( MAIN_MAX - main < 0 )
             main = MAIN_MAX;
         if( MAIN_MAX + main < 0 )
             main = - MAIN_MAX;
    }
    public void work(){
        double i = main*FUEL_CONSUME_RATE;
        double a = fuel.pull(main*i);
        main_out = a/i*main*IMPULSE_RATE;
        
        i = nose*FUEL_CONSUME_RATE;
        a = fuel.pull(nose*i);
        nose_out = a/i*nose*IMPULSE_RATE;
        
        i = belly*FUEL_CONSUME_RATE;
        a = fuel.pull(belly*i);
        belly_out = a/i*belly*IMPULSE_RATE;
    }
    
}
