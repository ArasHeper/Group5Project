/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;
import Data.Math.*;
import java.util.*;

/**
 *
 * @author Magus
 */
public class Physical extends Entity{
    public Edges edges = new Edges( false ); // false means circle
    public double rad = -1; // radius. If <0, not a circle
    public boolean isPhysical = true;
    
    
}
