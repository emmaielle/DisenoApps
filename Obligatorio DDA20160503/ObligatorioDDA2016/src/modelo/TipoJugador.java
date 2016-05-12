/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Euge
 */
public interface TipoJugador {
    public int totalCobrado();
    public int totalApostado();
    public void modificarSaldo(boolean agregar,int monto);
}
