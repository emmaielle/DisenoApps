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
import modelo.Modelo;
import vista.VistaJuegosV1;

/**
 *
 * @author Moi
 */
public class ControladorJuegos implements Observer {

    private Modelo modelo = Modelo.getInstancia();
    private VistaJuegos vista;
    private Jugador jugador;
    
    public ControladorJuegos(VistaJuegos v ,Jugador j){
        this.vista = v;
        modelo.addObserver(this);
        jugador = j;
        
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals(Modelo.EVENTO_JUEGO_CERRADO)){
            if(!jugador.isEnJuego()) vista.habilitarIrAJuego(true);
        }
    }
    
    
    public void listarJuegos(){
        ArrayList<String> juegos = modelo.getJuegos();
        vista.mostrarJuegos(juegos);
    }
    
    public void ingresarAjuego(String j){
        Object juego = modelo.getJuego(j);
        //modelo.setPlayerRole(jugador, juego);
        //if (juego != null) // supongo que esto deberia ir para controlar
            vista.abrirJuego(juego);
            jugador.setEnJuego(true);
            vista.habilitarIrAJuego(false);
    }
    
    public void logout(){
        modelo.logout(jugador);
        
    }
}
