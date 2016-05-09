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
public class Ronda {
    
    // no hice getters de todos
    
    private final int nroRonda;
    // montoGanado estaba en el UML pero creo que lo pusimos cuando ibamos a 
    // calcular las estadisticas por el lado del casino. Hay que ver si sigue yendo aca
    private Apuesta apuestaGanadora;
    private int nroGanador = -1;
    
    private ArrayList<Apuesta> apuestas = new ArrayList<>();

    public Ronda(int numRonda) {
        nroRonda = numRonda;
    }

    public int getNroGanador() {
        return nroGanador;
    }

    public void setApuestas(ArrayList<Apuesta> apuestas) {
        this.apuestas = apuestas;
    }
    
    public int getNroRonda() {
        return nroRonda;
    }

    public Apuesta getApuestaGanadora() {
        return apuestaGanadora;
    }

    

    public ArrayList<Apuesta> getApuestas() {
        return apuestas;
    }

    // sortea si no existe, sino devuelve nomas
    public int sortearNroGanador() {
        int nro = 0;
        // significa que todavia no se sorteo
        if (nroGanador == -1){
            int randomOut = (int)Math.floor(Math.random()*37);
            nroGanador = randomOut; // hacer logica
            Modelo.getInstancia().avisar(Modelo.EVENTO_SORTEARNUMERO);
            lookForWinner();
            return randomOut;
        }
        // nunca se deberia llegar aca
        return nroGanador;
    }
    
    // funciona en ambos sentidos si se hace click de nuevo
    public void apostar(Numero n, int v, JugadorRuleta jugador) {
        Apuesta yaApostada = null;
        for (Apuesta a: apuestas){
            if (a.getNumero() == n) yaApostada = a;
        }
        // si llega aca es porque ese numero no fue elegido antes
        if (yaApostada == null){
            Apuesta a = new Apuesta(v, jugador, n);
            if (a.validar()){
                agregarApuesta(a);
            }
        }
        else {
            // solo quita la apuesta si el monto apostado es 0 y ya tiene apuesta hecha por él mismo
            // sino queda la anterior
            if (yaApostada.getJugador().equals(jugador) && v == 0) {
                quitarApuesta(yaApostada);
            }
        }
    }
    
    public void quitarApuesta(Apuesta a){
        a.getNumero().setApuesta(null);
        a.getJugador().quitarApuesta(a);
        a.setJugador(null);
        apuestas.remove(a);
        Modelo.getInstancia().avisar(Modelo.EVENTO_TABLERO);
    }
    
    public void agregarApuesta(Apuesta a){
        a.getNumero().setApuesta(a);
        a.getJugador().agregarApuesta(a);
        apuestas.add(a);
        Modelo.getInstancia().avisar(Modelo.EVENTO_TABLERO);
    }

    private void lookForWinner() {
        for (Apuesta a : apuestas){
            if (a.getNumero().getValor() == nroGanador) apuestaGanadora = a;
        }
        
    }
    
    
}
