/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
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
        //se puede hacer esto? Llamar directo al getNumeros??
        vista.mostrar(mesa.getNumeros());
        modelo.addObserver(this);
    }
    
    public void apostar(Numero n, int v){
        // si el jugador que apuesta tiene saldo mayor o igual que el monto a apostar
        if(jugador.getJugador().getSaldo() >= v){ /// mensaje de que no tiene saldo suf
            //si el monto a aportar es mayor que 0
            if(v!=0){
                modelo.apostar(mesa, n, v, jugador);
                //n.apostar(jugador, v);
                vista.exitoApuesta();
            }
            // si es igual a 0 pero ese numero ya tiene apuesta. Desapuesta
            if(v == 0 && n.getApuesta() != null){
                modelo.apostar(mesa, n, v, jugador);
                vista.exitoApuesta();
            }
        } 
        else vista.errorApuesta("No tiene saldo suficiente para realizar esta apuesta");
    }
    @Override
    public void update(Observable o, Object arg) {
        if(arg.equals(Modelo.EVENTO_TABLERO)){
            vista.mostrar(mesa.getNumeros());
        }
        else if(arg.equals(Modelo.EVENTO_SORTEARNUMERO)){            
            buscarNumeroActual();
            // mostrar nuevos saldos. Hacer un display de saldo, actualizar paneltablero
            mostrarSaldo();
            vista.habilitar(true);
        }
        else if (arg.equals(Modelo.EVENTO_NUEVO_JUGADOR_MESA_RULETA)){
            vista.mostrarJugadores(modelo.getJugadoresPorMesa(mesa));
        }
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

    public void finalizarApuesta() {
        modelo.finalizarApuesta(mesa);
       // vista.habilitar(false);
    } 
}
