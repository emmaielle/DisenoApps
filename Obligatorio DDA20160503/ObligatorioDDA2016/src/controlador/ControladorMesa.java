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
import modelo.Numero;

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
        //se puede hacer esto? Llamar directo al getNumeros??
        vista.mostrar(mesa.getNumeros());
        modelo.addObserver(this);
        
    }
    
    public void marcar(Numero n){
        n.marcar(jugador);
    }
    @Override
    public void update(Observable o, Object arg) {
        if(arg.equals(Modelo.EVENTO_TABLERO)){
            vista.mostrar(mesa.getNumeros());
        }
        // refinar
        else if(arg.equals(Modelo.EVENTO_SORTEARNUMERO)){
            buscarNumeroActual();
        }
        else if (arg.equals(Modelo.EVENTO_NUEVO_JUGADOR_MESA_RULETA)){
            vista.mostrarJugadores(modelo.getJugadoresPorMesa(mesa));
        }
    }

    public void cargarJugadoresActivos() {
        ArrayList<JugadorRuleta> j = modelo.getJugadoresPorMesa(mesa);
        vista.mostrarJugadores(j);
    }

    public void sortearNumero() {
        mostrarNumeroSorteado(modelo.sortearNumero(mesa));
    }

    public void buscarNumeroActual() {
        mostrarNumeroSorteado(modelo.ultNumeroSorteado(mesa));
    }
    
    private void mostrarNumeroSorteado(int i){
        vista.mostrarNumeroSorteado(i);
    }
    
}
