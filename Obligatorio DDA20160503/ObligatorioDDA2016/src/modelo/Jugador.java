/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


/**
 *
 * @author Euge
 */
public class Jugador {
    private String nombre;
    private String password;
    private String nombreCompleto;
    private long saldo;
    private long totalCobrado;
    private long totalApostado;
    private boolean enJuego;
    private boolean enMesa;
    private boolean statsOn;
    private boolean ganoUltimaRonda = false;
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">   
    
    public Jugador(String nombre, String password, String nombreCompleto, int saldo) {
        this.nombre = nombre;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.saldo = saldo;
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Getters y setters"> 
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isStatsOn() {
        return statsOn;
    }

    public void setStatsOn(boolean statsOn) {
        this.statsOn = statsOn;
        Modelo.getInstancia().avisar(Modelo.EVENTO_STATSWINDOW);
    }
    
    public boolean isEnMesa() {
        return enMesa;
    }

    public void setEnMesa(boolean enMesa) {
        this.enMesa = enMesa;
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

    public long getSaldo() {
        return saldo;
    }

    public long getTotalCobrado() {
        return totalCobrado;
    }

    public void setTotalCobrado(long totalCobrado) {
        this.totalCobrado = totalCobrado;
    }

    public void setTotalApostado(long totalApostado) {
        this.totalApostado = totalApostado;
    }
    
    public long getTotalApostado() {
        return totalApostado;
    }
    
    public boolean isGanoUltimaRonda() {
        return ganoUltimaRonda;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Metodos"> 
    @Override
    public String toString(){
        return getNombreCompleto();
    }

    public void modificarSaldo(boolean agregar, int monto) {
        // a esta altura, ya se verifico que la cantidad restada no llegue a ser menor que lo 
        // que actualmente tiene, porque sino no se haria la apuesta
        if (agregar){
            saldo += monto;
            ganoUltimaRonda = true;
        }
        else {
            saldo -= monto;
            ganoUltimaRonda = false;
        }
    }
    
    // </editor-fold>



}
