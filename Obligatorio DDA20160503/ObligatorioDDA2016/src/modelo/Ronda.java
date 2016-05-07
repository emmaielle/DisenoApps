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
public class Ronda {
    
    // no hice getters de todos
    
    private static int ultRonda = 1;
    private final int nroRonda;
    // montoGanado estaba en el UML pero creo que lo pusimos cuando ibamos a 
    // calcular las estadisticas por el lado del casino. Hay que ver si sigue yendo aca
    private int montoGanado;
    private int nroGanador = -1;
    
    private ArrayList<Apuesta> apuestas = new ArrayList<>();

    public Ronda() {
        nroRonda = ultRonda;
        Ronda.ultRonda++;
    }

    public void setApuestas(ArrayList<Apuesta> apuestas) {
        this.apuestas = apuestas;
    }
    
    public static int getUltRonda() {
        return ultRonda;
    }

    public int getNroRonda() {
        return nroRonda;
    }

    public int getMontoGanado() {
        return montoGanado;
    }

    public ArrayList<Apuesta> getApuestas() {
        return apuestas;
    }

    // sortea si no existe, sino devuelve nomas
    public int sortearNroGanador() {
        int nro = 0;
        if (nroGanador == -1){
            int randomOut = (int)Math.floor(Math.random()*37);
            nroGanador = randomOut; // hacer logica
            Modelo.getInstancia().avisar(Modelo.EVENTO_SORTEARNUMERO);
            return randomOut;
        }
        return nroGanador;
    }
    public void agregarApuestas(Apuesta a){
        if (a!=null)apuestas.add(a);
    }
    
    
    
    
}
