/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import exceptions.InvalidUserActionException;
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
    public static final int EVENTO_SORTEARNUMERO = 5;
    public static final int EVENTO_NUEVO_JUGADOR_MESA_RULETA = 6;
    public static final int EVENTO_JUEGO_CERRADO = 7;
    
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

    public boolean agregarMesaRuleta(Mesa m, Jugador j, Color c) throws InvalidUserActionException {
        return casino.getRuleta().agregarMesaRuleta(m, j, c);
    }
    

    public Mesa buscarMesaRuleta(String nom){
        return casino.getRuleta().buscarMesa(nom);
    }

    public boolean unirJugadorAMesaRuleta(Jugador j, Mesa m, Color c){
        return casino.getRuleta().unirJugadorAMesaRuleta(j, m, c);
    }

    public ArrayList<JugadorRuleta> getJugadoresPorMesa(Mesa m) {
        return m.getJugadoresMesa();
    }

    public Color asignarColorRuleta(Mesa m) {
        return m.getUnusedColour();
    }

    public int sortearNumero(Mesa m) {
        return m.sortearNumeroGanador();
    }

    public void salirDeJuego(Jugador jugador) {
        jugador.setEnJuego(false);
    }

    public int ultNumeroSorteado(Mesa m) {
        return m.getNumeroGanador();
    }

    public void apostar(Mesa mesa, Numero n, int v, JugadorRuleta jugador) {
        mesa.apostar(n, v, jugador);
    }  

    public int finalizarApuesta(Mesa mesa) {
        return mesa.finalizarApuesta();
    }
}
