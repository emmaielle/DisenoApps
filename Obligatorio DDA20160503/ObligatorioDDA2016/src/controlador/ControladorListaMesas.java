/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import exceptions.InvalidUserActionException;
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
        if (evento.equals(Modelo.EVENTO_NUEVA_MESA) || evento.equals(Modelo.EVENTO_NUEVO_JUGADOR_MESA_RULETA)
         || evento.equals(Modelo.EVENTO_SALIR_MESA)){
            listarMesas();
        }
    }
    
    public void crearMesa(String nom){
        Mesa m = new Mesa(nom);
        try{
            modelo.agregarMesaRuleta(m, jugador, asignarColor(m));
            vista.abrirMesa(m,jugador, false);
        }
        catch(InvalidUserActionException ex){
            vista.errorCrearMesa(ex.getMessage());
        }
        // else
    }
    
     public void unirseAmesa(String nom) throws InvalidUserActionException{
        String nameMesa = nom.split(",")[0];
        Mesa m = modelo.buscarMesaRuleta(nameMesa);
        JugadorRuleta jr = new JugadorRuleta(asignarColor(m), null, jugador); // check this
        if (m != null) {
            if (m.getTodosJugadoresEnMesa().size() == 4) throw new InvalidUserActionException("Esta mesa ya contiene el maxino numero de jugadores posible");
            try {
                boolean espera = modelo.unirJugadorAMesaRuleta(jugador, m, asignarColor(m));
                vista.abrirMesa(m, jugador, espera);
            }
            catch (InvalidUserActionException ex){
                vista.errorCrearMesa(ex.getMessage());
            }
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
