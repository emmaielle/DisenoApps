/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.ControladorMesa;
import controlador.VistaMesa;
import modelo.Jugador;
import modelo.Mesa;

/**
 *
 * @author Euge
 */
public class VistaMesaV2 extends javax.swing.JDialog implements VistaMesa{
    
    private ControladorMesa controlador;
    private Mesa mesa;
    private Jugador jugador;
    /**
     * Creates new form VistaMesaV2
     */
    public VistaMesaV2(java.awt.Frame parent, boolean modal,Mesa m, Jugador j) {
        super(parent, modal);
        initComponents();
        controlador = new ControladorMesa(this);
        mesa = m;
        jugador = j;        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}