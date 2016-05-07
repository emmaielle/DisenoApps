/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import modelo.Jugador;
import modelo.JugadorRuleta;

import modelo.Mesa;
import modelo.Modelo;

/**
 *
 * @author Euge
 */
public class ControladorListaMesas implements Observer {
    private Modelo modelo = Modelo.getInstancia();
    private Jugador jugador;
    private VistaListaMesas vista;

    public ControladorListaMesas(Jugador j, VistaListaMesas vista) {
        modelo.addObserver(this);
        this.jugador= j;
        this.vista = vista;
        listarMesas();
    }

    
    
    @Override
    public void update(Observable o, Object evento) {
        if (evento.equals(Modelo.EVENTO_NUEVA_MESA) || evento.equals(Modelo.EVENTO_NUEVO_JUGADOR_MESA_RULETA)){
            listarMesas();
        }

    }
    
    public void crearMesa(String nom){
        Mesa m = new Mesa(nom);
        if (modelo.agregarMesaRuleta(m, jugador, asignarColor(m))) vista.abrirMesa(m,jugador);
        // else
    }
    
     public void unirseAmesa(String nom){
        // change to something more robust. Si el nombre tiene una coma, ya no sirve.
        String nameMesa = nom.split(",")[0];
        Mesa m = modelo.buscarMesaRuleta(nameMesa);
                JugadorRuleta jr = new JugadorRuleta(asignarColor(m), null, jugador);
        if (m != null) {
            modelo.unirJugadorAMesaRuleta(jugador, m, asignarColor(m));
            vista.abrirMesa(m, jugador);
        }
        //else
    }
    
    public void listarMesas(){
        vista.mostrar(modelo.listarMesasRuleta());
    }
    
    private Color asignarColor(Mesa m){
        return modelo.asignarColorRuleta(m);
    }

    public void salirDeJuego() {
        modelo.salirDeJuego(jugador);
    }
   
}
