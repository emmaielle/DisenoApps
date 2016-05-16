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
public class Casino {
    
    private ArrayList<IJuego> juegos = new ArrayList(); // llenarlo
    private JuegoRuleta ruleta = JuegoRuleta.getInstancia();
    
    private static Casino instancia = new Casino();
    private Casino() {
        // no me gusta como esta hecho esto. Parece harcodeado para cada juego
        juegos.add(ruleta);
    }

    public static Casino getInstancia() {
        return instancia;
    }
    
    
    public ArrayList<String> getJuegos(){
        ArrayList<String> juegosString = new ArrayList();
        for(Object o: juegos){
            // seguro que no anda, necesito castearlo pero por ahora queda asi
            juegosString.add(o.toString());
        }
        
        return juegosString;
    }

    public IJuego getJuego(String j) {
        for (IJuego o : juegos){
            if (o.toString().equals(j)) return o;
        }
        return null;
    }

    public JuegoRuleta getRuleta() {
        return ruleta;
    }
    
    
}
