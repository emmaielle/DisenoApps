/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Euge
 */
public class Mesa {
    private String nombre;
    private ArrayList<JugadorRuleta> jugadoresMesa = new ArrayList();
    private ArrayList<Numero> numeros = new ArrayList();

    public Mesa(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<JugadorRuleta> getJugadoresMesa() {
        return jugadoresMesa;
    }

    public ArrayList<Numero> getNumeros() {
        return numeros;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarJugador(JugadorRuleta j){
        jugadoresMesa.add(j);
        //ver si esto queda aca o donde?
        Modelo.getInstancia().avisar(Modelo.EVENTO_NUEVA_MESA);

    }
    public void quitarJugador(JugadorRuleta j){
        jugadoresMesa.remove(j);
    }

    // to DO
    public boolean validar() {
        return true;
    }

    @Override
    public String toString() {
        if (jugadoresMesa.size() == 1) return nombre + ", " + jugadoresMesa.size() + " jugador";
        else return nombre + ", " + jugadoresMesa.size() + " jugadores";
    }
    
    


    
}
