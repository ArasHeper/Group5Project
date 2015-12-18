/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import Controllers.ClientController;
import Interface.Connection;

/**
 *
 * @author Magus
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        ClientController ctrl = new ClientController();
        Connection c = new Connection(ctrl);
        Thread thread = new Thread(c);
        thread.start();
        int i = 0;
        while ( i < 200000000 )
            i++;
        System.out.println("sdasdasdasd");
        
        
    }
    
}
