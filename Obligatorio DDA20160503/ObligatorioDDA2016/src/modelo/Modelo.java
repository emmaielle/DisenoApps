/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

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
            ((JugadorRuleta)j.getRol()).setMesa(m); // mesa en jugador
            m.agregarJugador((JugadorRuleta)j.getRol()); // lista de jugadores en mesa

            casino.getRuleta().agregar(m); // lista de mesas en ruleta
            

            return true;
        }
        else return false;
    }
    
    //otro metodo que no esta bueno
    public void setPlayerRole(Jugador jugador, Object juego) {
        switch(juego.toString()){
            case "Ruleta": 
                jugador.setRol(new JugadorRuleta("Ruleta"));
                break;
            default:
                jugador.setRol(null);
        }
        
    }
    public Mesa buscarMesaRuleta(String nom){
        return casino.getRuleta().buscarMesa(nom);
    }

    public boolean unirJugadorAMesaRuleta(Jugador j, Mesa m){
        m.agregarJugador((JugadorRuleta)j.getRol());
        ((JugadorRuleta)j.getRol()).setMesa(m);
        // make robust
        return true;
    }


  
    

}
