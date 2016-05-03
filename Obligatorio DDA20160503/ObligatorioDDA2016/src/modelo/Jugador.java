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
public class Jugador {
    private String nombre;
    private String password;
    private String nombreCompleto;
    private TipoJugador rol;
    
    public Jugador(String nombre, String password, String nombreCompleto) {
        this.nombre = nombre;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
    }

    public void setRol(TipoJugador rol) {
        this.rol = rol;
    }

    public TipoJugador getRol() {
        return rol;
    }
    
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    @Override
    public String toString(){
        return getNombreCompleto();
    }


}
