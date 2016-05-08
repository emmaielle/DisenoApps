/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Euge
 */
public class Mesa {
    private String nombre;
    private ArrayList<JugadorRuleta> jugadoresMesa = new ArrayList();
    private ArrayList<Numero> numeros = new ArrayList();
    private ArrayList<Ronda> rondas = new ArrayList();
    private ArrayList<Color> coloresDisp;
    private ArrayList<JugadorRuleta> jugadorEspera = new ArrayList();

    public Mesa(String nombre) {
        this.nombre = nombre;
        initMesa();
    }

    public ArrayList<Color> getColoresDisp() {
        if (coloresDisp == null){
            coloresDisp = new ArrayList<>();
            coloresDisp.add(Color.BLUE);
            coloresDisp.add(Color.YELLOW);
            coloresDisp.add(Color.ORANGE);
            coloresDisp.add(Color.PINK);
        }
        return coloresDisp;
    }

    public void setColoresDisp(ArrayList<Color> coloresDisp) {
        this.coloresDisp = coloresDisp;
    }
    
    public String getNombre() {
        return nombre;
    }

    public ArrayList<JugadorRuleta> getJugadoresMesa() {
        return jugadoresMesa;
    }

    public ArrayList<Numero> getNumeros() {
        return numeros;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // crea y agrega el jugadorRuleta en la mesa actual y lo guarda en su lista de JR
    public boolean agregarJugador(Color c, Jugador j){
        //ver q cuando entre quede en una lista temporal hasta q termine la ronda
        if(jugadoresMesa.size()== 0){
            //Ronda primera = new Ronda(); // esto se puede hacer aca pero cuidado que hay que borrar el que ya esta hecho en otra parte
            //rondas.add(primera);
        }    
        if(jugadoresMesa.size()<4){
            JugadorRuleta jr = new JugadorRuleta(c, this, j);
            jr.setMesa(this); // mesa en jugador
            jugadoresMesa.add(jr);
                //ver si esto queda aca o donde?
            Modelo.getInstancia().avisar(Modelo.EVENTO_NUEVO_JUGADOR_MESA_RULETA);
            return true;
        }
        else{
            return false;
        }
    }
    public void quitarJugador(JugadorRuleta j){
        jugadoresMesa.remove(j);
    }

    // to DO
    public boolean validar() {
        return true;
    }

    // no non o
    @Override
    public String toString() {
        if (jugadoresMesa.size() == 1) return nombre + ", " + jugadoresMesa.size() + " jugador";
        else return nombre + ", " + jugadoresMesa.size() + " jugadores";
    }
    
    public JugadorRuleta buscarJugador(Jugador j){
        for(JugadorRuleta jr:jugadoresMesa){
            if(jr.getJugador()==j) return jr;     
        }
        return null;
    }

    public void initMesa(){
        if(numeros!=null)numeros.clear();
        numeros.add(new Numero(0, Color.green));
        numeros.add(new Numero(1, Color.red));
        numeros.add(new Numero(2, Color.black));
        numeros.add(new Numero(3, Color.red));
        numeros.add(new Numero(4, Color.black));
        numeros.add(new Numero(5, Color.red));
        numeros.add(new Numero(6, Color.black));
        numeros.add(new Numero(7, Color.red));
        numeros.add(new Numero(8, Color.black));
        numeros.add(new Numero(9, Color.red));
        numeros.add(new Numero(10, Color.black));
        numeros.add(new Numero(11, Color.black));
        numeros.add(new Numero(12, Color.red));
        numeros.add(new Numero(13, Color.black));
        numeros.add(new Numero(14, Color.red));
        numeros.add(new Numero(15, Color.black));
        numeros.add(new Numero(16, Color.red));
        numeros.add(new Numero(17, Color.black));
        numeros.add(new Numero(18, Color.red));
        numeros.add(new Numero(19, Color.red));
        numeros.add(new Numero(20, Color.black));
        numeros.add(new Numero(21, Color.red));
        numeros.add(new Numero(22, Color.black));
        numeros.add(new Numero(23, Color.red));
        numeros.add(new Numero(24, Color.black));
        numeros.add(new Numero(25, Color.red));
        numeros.add(new Numero(26, Color.black));
        numeros.add(new Numero(27, Color.red));
        numeros.add(new Numero(28, Color.black));
        numeros.add(new Numero(29, Color.black));
        numeros.add(new Numero(30, Color.red));
        numeros.add(new Numero(31, Color.black));
        numeros.add(new Numero(32, Color.red));
        numeros.add(new Numero(33, Color.black));
        numeros.add(new Numero(34, Color.red));
        numeros.add(new Numero(35, Color.black));
        numeros.add(new Numero(36, Color.red));
        Ronda ronda = new Ronda(getUltimaRonda() + 1);
        rondas.add(ronda);
    }


    public Color getUnusedColour() {
        Color sirve = Color.YELLOW;
        ArrayList<Color> temp = new ArrayList<>();
        for (JugadorRuleta jr : jugadoresMesa){
            temp.add(jr.getColor());
        }
        for (Color c : this.getColoresDisp()){
            if (!temp.contains(c)) {
                sirve = c;
                break;
            }
        }
        return sirve;

    }
    
    public int getUltimaRonda(){
        int ultRonda = 0;
        for (Ronda r : rondas){
            if (r.getNroRonda() > ultRonda) ultRonda = r.getNroRonda();
        }
        return ultRonda;
    }
    
    public Ronda buscarRonda(int id){
        for (Ronda r: rondas){
            if (r.getNroRonda() == id) return r;
        }
        return null;
    }

//    public void finalizarApuesta(JugadorRuleta jugador){
//        for(Numero n:numeros){
//            if(n.getJugador()==jugador)
//            {
//                crearApuestas(n);
//            }
//            
//        }
//    }

    private void crearApuestas(Numero n) {
        Apuesta a = new Apuesta(n);
 
    }

    public int sortearNumeroGanador() {
        this.rondas.add(new Ronda(getUltimaRonda() + 1)); // +1 xq es nueva
        return (buscarRonda(getUltimaRonda() - 1)).sortearNroGanador(); // -1 porque ya hay otra mas nueva
    }

    public int getNumeroGanador() {
        if (this.getUltimaRonda() == 1) return -1;
        return (this.buscarRonda(this.getUltimaRonda() - 1)).getNroGanador();
    }

    public void apostar(Numero n, int v, JugadorRuleta jugador) {
        (buscarRonda(getUltimaRonda())).apostar(n, v, jugador);
    }
    
     
}
