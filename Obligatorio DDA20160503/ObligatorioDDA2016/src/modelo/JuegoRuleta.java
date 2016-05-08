/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import exceptions.InvalidUserActionException;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Euge
 */
public class JuegoRuleta {

    private static JuegoRuleta instancia = new JuegoRuleta();
    private ArrayList<Mesa> listadoMesas = new ArrayList();
    private JuegoRuleta() {
        
    }

    public static JuegoRuleta getInstancia() {
        return instancia;
    }

    public ArrayList<Mesa> getListadoMesas() {
        return listadoMesas;
    }

    public void setListadoMesas(ArrayList<Mesa> listadoMesas) {
        this.listadoMesas = listadoMesas;
    }
    public void agregar(Mesa m) throws InvalidUserActionException{
        if(listadoMesas.contains(m))throw new InvalidUserActionException("La mesa ya existe");
        listadoMesas.add(m);
        Modelo.getInstancia().avisar(Modelo.EVENTO_NUEVA_MESA);
    }
    public void cerrarMesa(Mesa m){
        listadoMesas.remove(m);
    }
    @Override
    public String toString() {
        return "Ruleta";
    }
    
    public Mesa buscarMesa(String nom) {
        for (Mesa m: this.listadoMesas){
            if (m.getNombre().equals(nom)) return m;
        }
        // return null or throw exception
        return null;
    }

    public boolean unirJugadorAMesaRuleta(Jugador j, Mesa m, Color c) {
        m.agregarJugador(c, j);
        return true;
    }

    boolean agregarMesaRuleta(Mesa m, Jugador j, Color c) throws InvalidUserActionException {
        if (m.validar()){
            m.agregarJugador(c, j); // lista de jugadores en mesa
            agregar(m); // lista de mesas en ruleta
            return true;
        }
        else return false;
    }
    
    
    
}
