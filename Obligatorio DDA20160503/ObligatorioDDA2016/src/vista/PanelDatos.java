/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.ControladorMesa;
import java.util.ArrayList;
import modelo.JugadorRuleta;

/**
 *
 * @author Euge
 */
public class PanelDatos extends javax.swing.JPanel {

    /**
     * Creates new form PanelDatos
     */
    ControladorMesa controlador;
    
    public PanelDatos(ControladorMesa c) {
        initComponents();
        controlador = c;
        txt_valorApuesta.setText("0");

    }
    
    public PanelDatos(ControladorMesa c, int numeroSorteado) {
        initComponents();
        controlador = c;
        mostrarNumeroSorteado(numeroSorteado);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaJugadores = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        nroSorteado = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txt_valorApuesta = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btn_finalizarA = new javax.swing.JButton();

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Numero Sorteado: ");
        add(jLabel1);
        jLabel1.setBounds(20, 20, 120, 14);

        jScrollPane1.setViewportView(listaJugadores);

        add(jScrollPane1);
        jScrollPane1.setBounds(330, 40, 220, 80);

        jLabel2.setText("Jugadores en mesa:");
        add(jLabel2);
        jLabel2.setBounds(330, 10, 140, 14);

        nroSorteado.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        add(nroSorteado);
        nroSorteado.setBounds(150, 10, 50, 30);

        jButton1.setText("SortearPrueba");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(50, 70, 120, 40);
        add(txt_valorApuesta);
        txt_valorApuesta.setBounds(140, 150, 90, 20);

        jLabel3.setText("Ingrese valor a apostar:");
        add(jLabel3);
        jLabel3.setBounds(10, 150, 117, 14);

        btn_finalizarA.setText("Finalizar Apuestas");
        btn_finalizarA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_finalizarAActionPerformed(evt);
            }
        });
        add(btn_finalizarA);
        btn_finalizarA.setBounds(440, 130, 119, 40);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        sortearNum();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_finalizarAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_finalizarAActionPerformed
        // TODO add your handling code here:
        finalizarApuesta();
    }//GEN-LAST:event_btn_finalizarAActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_finalizarA;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listaJugadores;
    private javax.swing.JLabel nroSorteado;
    private javax.swing.JTextField txt_valorApuesta;
    // End of variables declaration//GEN-END:variables
    
    public void mostrarJugadores(ArrayList<JugadorRuleta> j) {
       ArrayList<String> listado = new ArrayList<>();
       for (JugadorRuleta ju : j){
           listado.add(ju.getJugador().getNombreCompleto());
       }
       listaJugadores.setListData(listado.toArray());
    }
    
    public void mostrarNumeroSorteado(int num){
        if (num == -1) nroSorteado.setText("");
        else nroSorteado.setText(String.valueOf(num));
    }

    private void sortearNum() {
        controlador.sortearNumero();
    }
    public int obtenerApuesta(){
        int monto=0;
        //TODO Controlar que si mete letras largue una excepcion... solo sirve para numeros
        if(txt_valorApuesta.getText()!="0"){
            monto = Integer.parseInt(txt_valorApuesta.getText());
        }
        return monto;
    }

    public void exito() {
        txt_valorApuesta.setText("0");
        txt_valorApuesta.requestFocus();
    }

    private void finalizarApuesta() {
        //controlador.finalizarApuesta();
    }
}
