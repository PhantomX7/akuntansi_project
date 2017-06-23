/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UPHM
 */
public class DlgCreateJurnalAddJurnalTransaction extends javax.swing.JDialog {

    /**
     * Creates new form DlgCreateJurnalAddJurnalTransaction
     */
    private Connection myConn = null;
    private PreparedStatement myStmt = null;
    private ResultSet myRs = null;
    PnlCreateJurnal parent;

    public DlgCreateJurnalAddJurnalTransaction(PnlCreateJurnal parent, boolean modal, Connection conn) {
        initComponents();
        setLocationRelativeTo(null);
        myConn = conn;
        generateComboBoxChartName();
        this.parent = parent;
    }

    private void executeAddTransaction() {
        try {
            myStmt = myConn
                    .prepareStatement("INSERT INTO jurnal_detail "
                            + "(`jurnal_no`, `chart_no`, `chart_name`, `debit`, `kredit`)"
                            + " VALUES (?,?,?,?,?);");
            myStmt.setString(1, parent.getJurnalNumber());
            myStmt.setString(2, txtCreateJurnalChartNo.getText());
            myStmt.setString(3, cboCreateJurnalChartName.getSelectedItem().toString());
            myStmt.setDouble(4, Double.valueOf(txtCreateJurnalDebit.getText()));
            myStmt.setDouble(5, Double.valueOf(txtCreateJurnalKredit.getText()));

            // Execute SQL query
            myStmt.executeUpdate();
            parent.generateTable();
        } catch (SQLException ex) {
            Logger.getLogger(DlgCreateJurnalAddJurnalTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generateComboBoxChartName() {
        try {
            cboCreateJurnalChartName.removeAllItems();
            myStmt = myConn.prepareStatement("select * from account_chart");
            // Execute SQL query
            myRs = myStmt.executeQuery();
            ArrayList<String> chartName = new ArrayList<>();
            // Process result set
            while (myRs.next()) {
                chartName.add(myRs.getString("chart_name"));
            }
            for (String a : chartName) {
                cboCreateJurnalChartName.addItem(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DlgCreateJurnalAddJurnalTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generateChartNumber() {
        try {
            ;
            myStmt = myConn.prepareStatement("select * from account_chart where chart_name=?;");
            myStmt.setString(1, cboCreateJurnalChartName.getSelectedItem().toString());
            // Execute SQL query
            myRs = myStmt.executeQuery();

            // Process result set
            while (myRs.next()) {
                txtCreateJurnalChartNo.setText(myRs.getString("chart_no"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DlgCreateJurnalAddJurnalTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCreateJurnalChartNo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        cboCreateJurnalChartName = new javax.swing.JComboBox<>();
        txtCreateJurnalDebit = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCreateJurnalKredit = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtCreateJurnalChartNo.setEditable(false);
        txtCreateJurnalChartNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCreateJurnalChartNoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Chart No :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Chart Name :");

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        cboCreateJurnalChartName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCreateJurnalChartNameActionPerformed(evt);
            }
        });

        txtCreateJurnalDebit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCreateJurnalDebitActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Debit :");

        txtCreateJurnalKredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCreateJurnalKreditActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Kredit :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtCreateJurnalKredit, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtCreateJurnalDebit, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdd)
                            .addComponent(txtCreateJurnalChartNo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboCreateJurnalChartName, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(199, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCreateJurnalChartNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboCreateJurnalChartName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCreateJurnalDebit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCreateJurnalKredit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(btnAdd)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCreateJurnalChartNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCreateJurnalChartNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCreateJurnalChartNoActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
//        parent.generateTable();
        executeAddTransaction();
    }//GEN-LAST:event_btnAddActionPerformed

    private void cboCreateJurnalChartNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCreateJurnalChartNameActionPerformed
        generateChartNumber();
    }//GEN-LAST:event_cboCreateJurnalChartNameActionPerformed

    private void txtCreateJurnalDebitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCreateJurnalDebitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCreateJurnalDebitActionPerformed

    private void txtCreateJurnalKreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCreateJurnalKreditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCreateJurnalKreditActionPerformed

    /**
     * @param args the command line arguments
     */
//  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JComboBox<String> cboCreateJurnalChartName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtCreateJurnalChartNo;
    private javax.swing.JTextField txtCreateJurnalDebit;
    private javax.swing.JTextField txtCreateJurnalKredit;
    // End of variables declaration//GEN-END:variables
}