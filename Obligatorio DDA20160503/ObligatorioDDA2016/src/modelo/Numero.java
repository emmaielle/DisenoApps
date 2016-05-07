/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.Color;

/**
 *
 * @author Euge
 */
public class Numero {
    private int valor;
    private Color color;
    private Color fijo;
    private JugadorRuleta jugador;
    private int apostado=0;

    public Numero(int valor, Color color) {
        this.valor = valor;
        this.color = color;
        this.fijo = color;
    }

    public JugadorRuleta getJugador() {
        return jugador;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getApostado() {
        return apostado;
    }

    public void apostar(JugadorRuleta j, int v) {
        if(jugador==null){
            apostado=v;
            jugador = j;
            color=jugador.getColor();
            Modelo.getInstancia().avisar(Modelo.EVENTO_TABLERO);
        }
        else if(j==jugador){
            jugador = null;
            apostado=0;
            color=fijo;
            Modelo.getInstancia().avisar(Modelo.EVENTO_TABLERO);

        }
    }

    @Override
    public String toString() {
        String j = "Sin marcar";
        if(jugador!=null) j=jugador.getJugador().getNombreCompleto();
        return "Numero{" + "valor=" + valor + ", usuario=" + j + '}';
    }
    
    
}
