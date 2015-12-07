/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Math;
import java.lang.Math.*;

/**
 *
 * @author Magus
 */
public class Edges {
    public Edge[] list;
    public int size;
    public boolean exists = true;
    
    public Edges( boolean x ){
        exists = x;
    }
    public Edges( int sz, Edge[] lst ){
        list = lst;
        size = sz;
    }

    public Edges( int psz, Point[] lst ){
        Edge[] newList = new Edge[psz];
        Point start;
        Point end;
        for( int i = psz - 1; psz >= 0; i--){
            if( i > 0){
                end = lst[i];
                start = lst[ i+ 1];
            }
            else{
                end = lst[i];
                start = lst[ psz - 1];
            }
            newList[i] = new Edge( start, end );
        }
        list = newList;
        size = psz;
    }
    
    
}
