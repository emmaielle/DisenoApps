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
public class SistemaJugador {
    private ArrayList<Jugador> jugadores = new ArrayList();
    private ArrayList<Jugador> logueados = new ArrayList();
    private boolean habilitado = true;

    private static SistemaJugador instancia = new SistemaJugador();
    private SistemaJugador() {
    }

    public static SistemaJugador getInstancia() {
        return instancia;
    }

    public void agregar(Jugador j){
        jugadores.add(j);
    }

    public ArrayList<Jugador> getLogueados() {
        return logueados;
    }
    
    // metodo cambiado para poder reusar una parte
    public Jugador login(String nom, String psw) {
        if(!habilitado) return null;
        Jugador j = buscarJugador(nom);
        if (j != null && j.getPassword().equals(psw) && !logueados.contains(j)){
            logueados.add(j);
            Modelo.getInstancia().avisar(Modelo.EVENTO_LOGUEADOS);
            return j;
        }
        return null;
    }

    public Jugador buscarJugador(String nom){
        for(Jugador j:jugadores){
            if(j.getNombre().equals(nom)){
                return j;
            }
        }
        return null;
    }
    
    public boolean isLogged(String usr) {
        Jugador j = buscarJugador(usr);
        if (j != null) return logueados.contains(j);
        return false;
    }
    
    public void logout(Jugador j) {
        logueados.remove(j);
        Modelo.getInstancia().avisar(Modelo.EVENTO_LOGUEADOS);
    }

    public boolean isHabilitado() {
        return habilitado;
    }
    
}
