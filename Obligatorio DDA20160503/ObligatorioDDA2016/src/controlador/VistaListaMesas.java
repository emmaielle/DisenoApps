/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import modelo.Jugador;
import modelo.Mesa;

/**
 *
 * @author Euge
 */
public interface VistaListaMesas {
    public void mostrar(ArrayList<Mesa> lista);
    public void cerrarJuego();
    public void abrirMesa(Mesa m,Jugador jr);

}
