/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.ControladorListaMesas;
import controlador.VistaListaMesas;
import java.util.ArrayList;
import modelo.Jugador;
import modelo.JugadorRuleta;
import modelo.Mesa;

/**
 *
 * @author Euge
 */
public class VistaListaMesasV1 extends javax.swing.JDialog implements VistaListaMesas {

    /**
     * Creates new form VistaListaMesasV2
     */
    private ControladorListaMesas controlador;
    public VistaListaMesasV1(Jugador j) {
        initComponents();
        controlador = new ControladorListaMesas(j,this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        list_mesas = new javax.swing.JList();
        btn_enterTable = new javax.swing.JButton();
        txt_newTableName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCrear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel2.setText("Mesas activas:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(121, 0, 91, 25);

        jScrollPane1.setViewportView(list_mesas);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 31, 310, 130);

        btn_enterTable.setText("Ingresar");
        btn_enterTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_enterTableActionPerformed(evt);
            }
        });
        getContentPane().add(btn_enterTable);
        btn_enterTable.setBounds(338, 71, 85, 33);
        getContentPane().add(txt_newTableName);
        txt_newTableName.setBounds(61, 217, 118, 20);

        jLabel1.setText("Crear Mesa:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(120, 179, 59, 14);

        jLabel3.setText("Nombre:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 220, 41, 14);

        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        getContentPane().add(btnCrear);
        btnCrear.setBounds(210, 220, 73, 23);

        setBounds(0, 0, 483, 339);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_enterTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_enterTableActionPerformed
        // TODO add your handling code here:
        ingresarMesa();
    }//GEN-LAST:event_btn_enterTableActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        // TODO add your handling code here:
        controlador.crearMesa(txt_newTableName.getText());
    }//GEN-LAST:event_btnCrearActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cerrarJuego();
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btn_enterTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList list_mesas;
    private javax.swing.JTextField txt_newTableName;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mostrar(ArrayList<Mesa> lista) {
        ArrayList<String> listado = new ArrayList<>();
        for (Mesa m : lista){
            listado.add(formatear(m));
        }
        list_mesas.setListData(listado.toArray());
    }


    @Override
    public void cerrarJuego() {

    }

    @Override
    public void abrirMesa(Mesa m, Jugador j) {
        //dispose();
        
        new VistaMesaV1(m, j).setVisible(true);
    }

    private void ingresarMesa() {
        if (list_mesas.getModel().getSize() > 0 && list_mesas.getSelectedValue() != null)
            controlador.unirseAmesa(list_mesas.getSelectedValue().toString());
    }

    private String formatear(Mesa m) {
        return m.getNombre() + ", " + m.getJugadoresMesa().size() + " jugador/es";
    }

    @Override
    public void salirDeJuego() {
        controlador.salirDeJuego();
    }

    @Override
    public void habilitarIrAMesas(boolean habilitar) {
        btnCrear.setEnabled(habilitar);
        btn_enterTable.setEnabled(habilitar);
    }
}
