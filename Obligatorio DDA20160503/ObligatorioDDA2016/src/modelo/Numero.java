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
    private JugadorRuleta jugador;

    public Numero(int valor, Color color) {
        this.valor = valor;
        this.color = color;
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

    public void marcar(JugadorRuleta j) {
        Color c = color;
        if(jugador==null){
            jugador = j;
            color=jugador.getColor();
            Modelo.getInstancia().avisar(Modelo.EVENTO_TABLERO);
        }
        else if(j==jugador){
            jugador = null;
            color = c;
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
