/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import modelo.Jugador;
import modelo.Mesa;
import modelo.Modelo;

/**
 *
 * @author Euge
 */
public class ControladorListaMesas implements Observer{
    
    private Modelo modelo = Modelo.getInstancia();
    //private Mesa mesa;
    private VistaListaMesas vista;

    public ControladorListaMesas(VistaListaMesas vista) {
        modelo.addObserver(this);
        //this.mesa = mesa;
        this.vista = vista;
        listarMesas();
    }
    
    @Override
    public void update(Observable o, Object evento) {
        if (evento.equals(Modelo.EVENTO_NUEVA_MESA)){
            listarMesas();
        }
    }
    
    public void crearMesa(String nom, Jugador j){
        Mesa m = new Mesa(nom);
        if (modelo.agregarMesaRuleta(m, j)) vista.abrirMesa(m,j);
        // else
    }
    
    public void unirseAmesa(String nom, Jugador j){
        
    }
    
    public void listarMesas(){
        ArrayList<Mesa> lista = modelo.listarMesasRuleta();
        ArrayList<String> listaString = new ArrayList();
        for (Mesa m: lista){
            listaString.add(m.toString());
        }
        //vista.mostrar(listaString);
    }
    
}
