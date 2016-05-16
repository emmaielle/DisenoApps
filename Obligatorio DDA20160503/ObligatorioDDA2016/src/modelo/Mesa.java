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
    private ArrayList<JugadorRuleta> jugadoresEspera = new ArrayList<>();
    private ArrayList<JugadorRuleta> jugadoresMesa = new ArrayList();
    private ArrayList<Numero> numeros = new ArrayList();
    private ArrayList<Ronda> rondas = new ArrayList();
    private ArrayList<Color> coloresDisp;
    private int cantFinalizados;
    
    public Mesa(String nombre) {
        this.nombre = nombre;
        initMesa();
    }

    // <editor-fold defaultstate="collapsed" desc="Getters y setters">   
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

    public ArrayList<JugadorRuleta> getJugadoresEspera() {
        return jugadoresEspera;
    }
    
    public ArrayList<JugadorRuleta> getJugadoresMesa() {
        return jugadoresMesa;
    }
    
    public ArrayList<JugadorRuleta> getTodosJugadoresEnMesa(){
        ArrayList<JugadorRuleta> todos = new ArrayList<>();
        for (JugadorRuleta k : jugadoresMesa){
            todos.add(k);
        }
        for (JugadorRuleta j : jugadoresEspera){
            todos.add(j);
        }
        return todos;
    }

    public ArrayList<Numero> getNumeros() {
        return numeros;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Metodos">  
    
    // crea y agrega el jugadorRuleta en la mesa actual y lo guarda en su lista de JR
    // devuelve boolean que indica si el jugador esta en espera o no
    public boolean agregarJugador(Color c, Jugador j){
        //ver q cuando entre quede en una lista temporal hasta q termine la ronda
        JugadorRuleta jr = new JugadorRuleta(c, this, j);
        if(jugadoresMesa.isEmpty()){
            jr.setMesa(this); // mesa en jugador
            jugadoresMesa.add(jr);
            Modelo.getInstancia().avisar(Modelo.EVENTO_NUEVO_JUGADOR_MESA_RULETA);
            j.setEnMesa(true);
            return false;
        }    
        else if(jugadoresMesa.size()<4 && this.buscarRonda(this.getUltimaRonda()).getNroGanador()==-1){
            jr.setMesa(this); // mesa en jugador
            jugadoresEspera.add(jr);
            j.setEnMesa(true);
            Modelo.getInstancia().avisar(Modelo.EVENTO_NUEVO_JUGADOR_MESA_RULETA);
            return true;                
        }
        else{
            return false;
        }
    }
    
    public void quitarJugador(JugadorRuleta j){
        jugadoresMesa.remove(j);
        jugadoresEspera.remove(j);
        j.getJugador().setEnMesa(false);
        if(jugadoresMesa.size()>0) buscarRonda(getUltimaRonda()).eliminarApuestas(j);
        apuestaTotal(); // al salir hace una apuesta por si todos ya finalizaron y el no. Si no estaba
        // terminado no cambia en nada porque la cant de jugadores cambio
        Modelo.getInstancia().avisar(Modelo.EVENTO_SALIR_MESA);
        // no necesito quitar mesa de j, porque se va a eliminar solo cn el garbage collector
    }

    public boolean validar() {
        return !nombre.isEmpty() && !nombre.contains(",");
    }

    public JugadorRuleta buscarJugador(Jugador j){
        for(JugadorRuleta jr:jugadoresMesa){
            if(jr.getJugador()==j) return jr;     
        }
        for(JugadorRuleta jr: jugadoresEspera){
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
        cantFinalizados=0;
    }

    public Color getUnusedColour() {
        Color sirve = Color.YELLOW;
        ArrayList<Color> temp = new ArrayList<>();
        for (JugadorRuleta jr : this.getTodosJugadoresEnMesa()){
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

    public int sortearNumeroGanador() {
        Ronda ultimaRonda = buscarRonda(getUltimaRonda());
        int nro = ultimaRonda.sortearNroGanador(); // -1 porque ya hay otra mas nueva
        // reviso resultados // aviso ganadores // reparto plata // guardo historial
        ultimaRonda.modificarSaldos();
        nuevaRonda();
        Modelo.getInstancia().avisar(Modelo.EVENTO_SORTEARNUMERO);
        return nro;
    }

    public void nuevaRonda(){
        // esto asegura que no intente pasar en espera solo porque termino la ronda
        // y no considere la max cant de jugadores por ronda
        while (jugadoresMesa.size() < 4 && !jugadoresEspera.isEmpty()){
            if (!jugadoresEspera.isEmpty()){
                JugadorRuleta temp = jugadoresEspera.get(0);
                jugadoresMesa.add(temp);
                jugadoresEspera.remove(temp);
            }
        }
        initMesa();
        Modelo.getInstancia().avisar(Modelo.EVENTO_TABLERO);
        Modelo.getInstancia().avisar(Modelo.EVENTO_NUEVO_JUGADOR_MESA_RULETA);
    }
    
    public int getNumeroGanador() {
        if (this.getUltimaRonda() == 1) return -1;
        return (this.buscarRonda(this.getUltimaRonda() - 1)).getNroGanador();
    }

    public void apostarUnNumero(Numero n, int v, JugadorRuleta jugador) {
        for(JugadorRuleta jr:jugadoresMesa){
            if(jugador==jr)
                (buscarRonda(getUltimaRonda())).apostar(n, v, jugador);
        }
        Modelo.getInstancia().avisar(Modelo.EVENTO_ACTUALIZA_SALDOS);
    }

    public int finalizarApuesta(){
        cantFinalizados++;
        return apuestaTotal();
    } 
     
    public int apuestaTotal() {
        if(cantFinalizados == jugadoresMesa.size() || cantFinalizados == jugadoresMesa.size() + 1){ 
            return sortearNumeroGanador();
        }
        else if(cantFinalizados>1 && cantFinalizados < jugadoresMesa.size())
        {
            cantFinalizados++;
            return -1;
        }
        return -1;
    }

    public boolean estaEnEspera(JugadorRuleta jugador) {
        return jugadoresEspera.contains(jugador);
    }

    @Override
    public String toString() {
        int activos = jugadoresMesa.size();
        int espera = jugadoresEspera.size();
        String temp;
        if (activos == 1) temp = nombre + ", " + jugadoresMesa.size() + " jugador activo";
        else temp = nombre + ", " + jugadoresMesa.size() + " jugadores activos";
        
        if (espera != 0){

            temp += " || " + espera +" en espera";
        }
        return temp;
    }
        
    @Override
     public boolean equals(Object o){
        Mesa m = (Mesa)o;
        return nombre.equalsIgnoreCase(m.getNombre());
    }
     // </editor-fold>
}
