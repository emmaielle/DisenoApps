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
    
    public void apostar(Numero n, int v){
        if(jugador.getJugador().getSaldo()>=v){
            if(v!=0){
                n.apostar(jugador, v);
                vista.exitoApuesta();
            }
            if(v==0&&n.getJugador()!=null){
                n.apostar(jugador, v);
                vista.exitoApuesta();
            }
        }
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
