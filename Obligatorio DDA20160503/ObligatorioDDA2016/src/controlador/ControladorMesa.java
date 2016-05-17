/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import exceptions.InvalidUserActionException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import modelo.Apuesta;
import modelo.JugadorRuleta;
import modelo.Mesa;
import modelo.Modelo;
import modelo.Numero;

/**
 *
 * @author Moi
 */
public class ControladorMesa implements Observer {

    private Modelo modelo = Modelo.getInstancia();
    private VistaMesa vista;
    private JugadorRuleta jugador;
    private Mesa mesa;
    private ArrayList<Apuesta> apuestas = new ArrayList<>();
    
    public ControladorMesa(VistaMesa vista,Mesa m, JugadorRuleta jr){
        this.vista = vista;
        this.jugador = jr;
        this.mesa= m;
        vista.mostrar(mesa.getNumeros());
        modelo.addObserver(this);
    }
    
    public void desapostar(Numero n) throws InvalidUserActionException{
        if(jugador.isApostado()) throw new InvalidUserActionException("Ya ha finalizado su apuesta");
        modelo.desapostar(mesa, n, jugador);
    }
    
    public void apostar(Numero n, int v) throws InvalidUserActionException {
        // si el jugador que apuesta tiene saldo mayor o igual que el monto a apostar
        if (mesa.getJugadoresEspera().contains(jugador)) throw new InvalidUserActionException("Debe esperar a que finalice la ronda actual");
        if(jugador.isApostado()) throw new InvalidUserActionException("Ya ha finalizado su apuesta");
        if(jugador.getJugador().getSaldo() < v) throw new InvalidUserActionException("No tiene saldo suficiente para realizar esta apuesta");
        if(v == 0) throw new InvalidUserActionException("Ingrese un monto mayor que 0");
        //si el monto a aportar es mayor que 0
        if(v!=0){
            modelo.apostar(mesa, n, v, jugador);
            vista.exitoApuesta();
        }
         
    }
    @Override
    public void update(Observable o, Object arg) {
        if(arg.equals(Modelo.EVENTO_TABLERO)){
            vista.mostrar(mesa.getNumeros());
            vista.mostrarTotalApostado(mesa.buscarRonda(mesa.getUltimaRonda()).totalApostadoRonda(jugador));
        }
        else if(arg.equals(Modelo.EVENTO_SORTEARNUMERO)){            
            buscarNumeroActual();
            if (!modelo.estaEnEspera(jugador, mesa))
            {
                vista.habilitar(true);
                //jugador.setApostado(true);
            }
            mensajeRonda();
            mostrarSaldo();
        }
        else if (arg.equals(Modelo.EVENTO_NUEVO_JUGADOR_MESA_RULETA) ||
                arg.equals(Modelo.EVENTO_SALIR_MESA)){
            vista.mostrarJugadores(modelo.getJugadoresPorMesa(mesa));
        }
        else if(arg.equals(Modelo.EVENTO_ACTUALIZA_SALDOS))
            vista.mostrarSaldo(jugador.getJugador().getSaldo());
    }

    public void cargarJugadoresActivos() {
        ArrayList<JugadorRuleta> j = modelo.getJugadoresPorMesa(mesa);
        vista.mostrarJugadores(j);
    }

    public void sortearNumero() {
        mostrarNumeroSorteado(modelo.sortearNumero(mesa));
        // llamar a metodo de ver quien gano
        vista.mostrar(mesa.getNumeros());
    }

    public void buscarNumeroActual() {
        mostrarNumeroSorteado(modelo.ultNumeroSorteado(mesa));
    }
    
    private void mostrarNumeroSorteado(int i){
        vista.mostrarNumeroSorteado(i);
    }

    public void mostrarSaldo() {
        vista.mostrarSaldo(jugador.getJugador().getSaldo());
    }

    public void finalizarApuesta()  {
        int sorteado = modelo.finalizarApuesta(mesa);
        if(sorteado!=-1){
            vista.habilitar(true);
//            try{
            mesa.consultarYQuitar();
//vista.salirDeMesa();
//            }
//            catch (InvalidUserActionException ex){
//                JOptionPane.showMessageDialog(this, ex.getMessage());
//            }
        }
        else{
            vista.habilitar(false);
            jugador.setApostado(true);
        }
    } 

    public void salirDeMesa() {
        modelo.salirDeMesaRuleta(jugador, mesa);
        if (mesa.getTodosJugadoresEnMesa().isEmpty()) modelo.cerrarMesaRuleta(mesa);
    }
    
    public void mensajeRonda(){
        String msj = (mesa.estaEnEspera(jugador)) ? "Espera..." : "Apostar";
        vista.mensajeRonda(msj);
    }
}
