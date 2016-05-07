/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import modelo.Jugador;
import modelo.JugadorRuleta;
import modelo.Numero;

/**
 *
 * @author Moi
 */
public interface VistaMesa {
    public void mostrar(ArrayList<Numero> numeros);
    public void mostrarJugadores(ArrayList<JugadorRuleta> jugadores);
    public void mostrarNumeroSorteado(int num);
    public void salirDeMesa();
}
