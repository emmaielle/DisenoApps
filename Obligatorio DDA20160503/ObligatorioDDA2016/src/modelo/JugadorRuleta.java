/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Euge
 */
public class JugadorRuleta {
    private Color color;
    private Mesa mesa;
    private Jugador jugador;
    private ArrayList<Apuesta> apuestas;

    public JugadorRuleta(Color color, Mesa mesa, Jugador jugador) {
        this.color = color;
        this.mesa = mesa;
        this.jugador = jugador;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<Apuesta> getApuestas() {
        return apuestas;
    }
    public void agregarApuesta(Apuesta a){
        if(a!=null) apuestas.add(a);
    }
}
