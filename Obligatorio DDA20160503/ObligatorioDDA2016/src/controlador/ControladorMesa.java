/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import modelo.Jugador;
import modelo.JugadorRuleta;
import modelo.Mesa;
import modelo.Modelo;

/**
 *
 * @author Moi
 */
public class ControladorMesa implements Observer {

    private Modelo modelo = Modelo.getInstancia();
    private VistaMesa vista;
    private JugadorRuleta jugador;
    private Mesa mesa;
    
    public ControladorMesa(VistaMesa vista,Mesa m, JugadorRuleta jr){
        this.vista = vista;
        this.jugador = jr;
        this.mesa= m;
    }
    
    
    @Override
    public void update(Observable o, Object arg) {

    }

    public void cargarJugadoresActivos() {
        ArrayList<Jugador> j = modelo.getJugadoresPorMesa(mesa);
        vista.mostrarJugadores(j);
    }
    
}
