/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Jugador;

/**
 *
 * @author Euge
 */
public interface VistaEstadisticas {
    
    public void mostrarTotalApostado(long m);
    
    public void mostrarTotalCobrado(long m);
    
    public void mostrarTodosApostado(int m);
    
    public void mostrarTodosCobrado(int m);
}
