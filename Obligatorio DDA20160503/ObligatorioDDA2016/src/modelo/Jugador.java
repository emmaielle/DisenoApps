/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.Color;
import java.util.Objects;

/**
 *
 * @author Euge
 */
public class Jugador {
    private String nombre;
    private String password;
    private String nombreCompleto;
    private int saldo;
    private long totalCobrado;
    private long totalApostado;
    private boolean enJuego;
    
    
    public Jugador(String nombre, String password, String nombreCompleto, int saldo) {
        this.nombre = nombre;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.saldo = saldo;
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

    public boolean isEnJuego() {
        return enJuego;
    }

    public void setEnJuego(boolean enJuego) {
        this.enJuego = enJuego;
        Modelo.getInstancia().avisar(Modelo.EVENTO_JUEGO_CERRADO);
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getSaldo() {
        return saldo;
    }

    public long getTotalCobrado() {
        return totalCobrado;
    }

    public long getTotalApostado() {
        return totalApostado;
    }
    
    // capaz que no?
    @Override
    public String toString(){
        return getNombreCompleto();
    }


    
    


}
