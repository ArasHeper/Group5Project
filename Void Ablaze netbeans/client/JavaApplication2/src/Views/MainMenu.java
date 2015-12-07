/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;
import Controllers.ViewListener;
import Data.ClientData;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
/**
 *
 * @author Magus
 */
public class MainMenu extends JPanel implements Observer{
    ViewListener al;
    ClientData data;
    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void addListenerTo(Component c, ActionListener a){
        //TODO
    }
    
}

