/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import modelo.Jugador;
import modelo.Modelo;

/**
 *
 * @author Euge
 */
public class Inicio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        cargarDatos();
        new Principal().setVisible(true);
    }

    private static void cargarDatos() {
        Modelo m = Modelo.getInstancia();
        m.agregar(new Jugador("a","a","Maria Eugenia Cremona"));
        m.agregar(new Jugador("b","b","Moira Lasserre"));
        m.agregar(new Jugador("c","c","Gabriel Serrano"));
        m.agregar(new Jugador("d","d","Florencia Polcaro"));
        m.agregar(new Jugador("e","e","Profe"));

    }
    
}
