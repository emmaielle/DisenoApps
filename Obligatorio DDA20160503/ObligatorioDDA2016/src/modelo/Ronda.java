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
    private final int nroRonda;
    private Apuesta apuestaGanadora;
    private int nroGanador = -1;
    private ArrayList<Apuesta> apuestas = new ArrayList<>();

    public Ronda(int numRonda) {
        nroRonda = numRonda;
    }

    // <editor-fold defaultstate="collapsed" desc="Getters y setters">
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Metodos">
    
    // sortea si no existe, sino devuelve nomas
    public int sortearNroGanador() {
        int nro = 0;
        // significa que todavia no se sorteo
        if (nroGanador == -1){
            int randomOut = (int)Math.floor(Math.random()*37);
            nroGanador = randomOut; // hacer logica
            lookForWinner();
            return randomOut;
        }
        // nunca se deberia llegar aca
        return nroGanador;
    }
    
    public Apuesta buscarApuestaPorNumero(Numero n){
        Apuesta yaApostada = null;
        for (Apuesta a: apuestas){
            if (a.getNumero() == n) yaApostada = a;
        }
        return yaApostada;
    }
    
    // funciona en ambos sentidos si se hace click de nuevo
    public void apostar(Numero n, int v, JugadorRuleta jugador) {
        Apuesta yaApostada = buscarApuestaPorNumero(n);
        // si llega aca es porque ese numero no fue elegido antes
        if (yaApostada == null){
            Apuesta a = new Apuesta(v, jugador, n);
            if (a.validar()){
                agregarApuesta(a);
                jugador.getJugador().modificarSaldo(false, v);
            }
        }
        else {
            // solo quita la apuesta si el monto apostado es 0 y ya tiene apuesta hecha por él mismo
            // sino queda la anterior
            desapostar(jugador, n);
        }
    }
    
    public void desapostar(JugadorRuleta j, Numero n){
        Apuesta yaApostada = buscarApuestaPorNumero(n);
        if (yaApostada.getJugador().equals(j)) 
        { // && v == 0
            quitarApuesta(yaApostada);
        }
    }
    
    public void quitarApuesta(Apuesta a){
        a.getJugador().getJugador().modificarSaldo(true,a.getMonto());
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
    
    public void modificarSaldos() {
        for (Apuesta a: apuestas){
            Jugador j = a.getJugador().getJugador();
            if (apuestaGanadora != null && apuestaGanadora.equals(a)){ // si hubo un ganador
                j.modificarSaldo(true, a.getMonto()* 35);
                //j.modificarSaldo(false, a.getMonto());
                j.setTotalCobrado(j.getTotalCobrado() + a.getMonto() * 35);
                j.setTotalApostado(j.getTotalApostado() + a.getMonto());
            }
            else {
                //j.modificarSaldo(false, a.getMonto());
                j.setTotalApostado(j.getTotalApostado() + a.getMonto());
            }
        }
        Modelo.getInstancia().avisar(Modelo.EVENTO_ACTUALIZA_SALDOS);
    }
    
    public void eliminarApuestas(JugadorRuleta j){
        for (int i = 0; i < apuestas.size(); i++){
            if(apuestas.get(i).getJugador() == j){
                quitarApuesta(apuestas.get(i));
                i--;
            }
        }
    }
    public long totalApostadoRonda(){
        long total=0;
        for(Apuesta a:apuestas)
            total+=a.getMonto();
        return total;
    }
    // </editor-fold>


}
