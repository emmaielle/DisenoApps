/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Moi
 */
public class ControladorMesa implements Observer {

    private VistaMesa vista;
    
    public ControladorMesa(VistaMesa vista){
        this.vista = vista;
        
    }
    
    
    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
