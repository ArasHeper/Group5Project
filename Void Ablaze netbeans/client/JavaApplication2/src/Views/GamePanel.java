/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;
import Controllers.ViewListener;
import Data.ClientData;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 *
 * @author Magus
 */
public class GamePanel extends JPanel implements Observer{
    ViewListener al;
    ClientData data;
    Timer timer;
    double x;
    double y;
    
    
    public GamePanel( ClientData cd){
        data = cd;
        timer = new Timer( 20, new Painter());
        setPreferredSize(new Dimension(600,600));
        x = cd.MAPX;
        y = cd.MAPY;
        
    }
    
    private class Painter implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
             repaint();
        }  
    }
    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void addListenerTo(Component c, ActionListener a){
        //TODO
    }
    
    @Override
    public void paintComponent( Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 600, 600);
        g.setColor(Color.RED);
        g.drawOval( (int)data.b1X,(int)data.b1Y, 10, 10 );
        g.setColor(Color.BLUE);
        g.drawOval( (int)data.b2X,(int)data.b2Y, 10, 10 );
        
    }
    
    
}

