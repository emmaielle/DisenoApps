/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

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
    public void agregar(Mesa m){
        listadoMesas.add(m);
        // no se si los avisos pueden ir en cualquier clase o en los sistemas solo, sino se cambia esto
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
    
    
    
}
