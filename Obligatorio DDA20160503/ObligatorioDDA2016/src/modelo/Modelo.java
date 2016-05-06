/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Euge
 */
public class Modelo extends Observable {
        
    private SistemaJugador sj = SistemaJugador.getInstancia();
    private Casino casino = Casino.getInstancia();
    private static Modelo instancia = new Modelo();
    public static final int EVENTO_LOGIN = 1;
    public static final int EVENTO_LOGUEADOS= 2;
    public static final int EVENTO_NUEVA_MESA = 3;
    public static final int EVENTO_TABLERO = 4;
    
    
    private Modelo() {
    }

    public static Modelo getInstancia() {
        return instancia;
    }
    
    public void agregar (Jugador j){
        sj.agregar(j);
    }

    protected void avisar(Object evento){
        setChanged();
        notifyObservers(evento);
    }

    public boolean isHabilitado() {
        return sj.isHabilitado();
    }

    public Jugador login(String nom, String psw) {
        return sj.login(nom,psw);
    }
    public void logout(Jugador j){
        sj.logout(j);
    }
    
    public boolean isLogged(String usr) {
        return sj.isLogged(usr);
    }
    
    
    // make defensive???
    public ArrayList<String> getJuegos(){
        return casino.getJuegos();
    }

    public Object getJuego(String j) {
        return casino.getJuego(j);
    }

    public ArrayList<Mesa> listarMesasRuleta() {
        return casino.getRuleta().getListadoMesas();

    }

    public boolean agregarMesaRuleta(Mesa m, Jugador j) {
        if (m.validar()){
            JugadorRuleta jr = new JugadorRuleta(Color.yellow, m, j);
            jr.setMesa(m); // mesa en jugador
            m.agregarJugador(jr); // lista de jugadores en mesa

            casino.getRuleta().agregar(m); // lista de mesas en ruleta
            

            return true;
        }
        else return false;
    }
    

    public Mesa buscarMesaRuleta(String nom){
        return casino.getRuleta().buscarMesa(nom);
    }

    public boolean unirJugadorAMesaRuleta(Jugador j, Mesa m){
        JugadorRuleta jr = new JugadorRuleta(Color.yellow, m, j);
        m.agregarJugador(jr);
        jr.setMesa(m);
        // make robust
        return true;
    }

    public ArrayList<Jugador> getJugadoresPorMesa(Mesa m) {
        return null;
    }


  
    

}
