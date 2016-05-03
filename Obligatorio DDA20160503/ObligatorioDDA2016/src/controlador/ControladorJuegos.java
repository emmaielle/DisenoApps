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
    private VistaJuegosV1 vista;
    private Jugador jugador;
    
    public ControladorJuegos(VistaJuegosV1 v ,Jugador j){
        this.vista = v;
        modelo.addObserver(this);
        jugador = j;
        
    }
    
    @Override
    public void update(Observable o, Object arg) {

    }
    
    
    public void listarJuegos(){
        ArrayList<String> juegos = modelo.getJuegos();
        vista.mostrarJuegos(juegos);
    }
    
    public void ingresarAjuego(String j){
        Object juego = modelo.getJuego(j);
        modelo.setPlayerRole(jugador, juego);
        //if (juego != null) // supongo que esto deberia ir para controlar
            vista.abrirJuego(juego);
    }
}