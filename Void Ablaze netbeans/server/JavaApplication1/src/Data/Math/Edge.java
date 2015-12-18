/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Math;

/**
 *
 * @author Magus
 */
public class Edge {
    public Point start;
    public Point end;
    public double leng;
    
    public Edge( Point s, Point e){
        start = s;
        end = e;
        leng = Math.sqrt(Math.pow(end.x - start.x , 2) + 
        Math.pow(end.y - start.y , 2));
    }
    public void mutate( Point s, Point e){
                start = s;
        end = e;
        leng = Math.sqrt(Math.pow(end.x - start.x , 2) + 
        Math.pow(end.y - start.y , 2));
    }
}
