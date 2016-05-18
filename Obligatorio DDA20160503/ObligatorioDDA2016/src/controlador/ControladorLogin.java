/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Observable;
import java.util.Observer;
import modelo.Jugador;
import modelo.Modelo;

/**
 *
 * @author Euge
 */
public class ControladorLogin implements Observer{
    
    private Modelo modelo = Modelo.getInstancia();
    private VistaLogin vista;
    
    public ControladorLogin(VistaLogin vista) {
        this.vista = vista;
        modelo.addObserver(this);
        vista.habilitar(modelo.isHabilitado());

    }
    public void login(String usr,String pass){ // hay que cambiar
        if (!modelo.isLogged(usr)){
            Jugador j = modelo.login(usr, pass);
            if(j == null){
                vista.errorLogin();
            }
            else{
                vista.ingresarJugador(j);
            }
        }
        else vista.errorYaLogueado();
    }
    
    @Override
    public void update(Observable o, Object evento) {
        if(evento.equals(Modelo.EVENTO_LOGIN)){
            vista.habilitar(modelo.isHabilitado());
        }
    } 
}
