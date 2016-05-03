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
public class JugadorRuleta extends TipoJugador{
    private Color color;
    private Mesa mesa;
    public JugadorRuleta(String nombre) {
        super(nombre);
        
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
    
}
