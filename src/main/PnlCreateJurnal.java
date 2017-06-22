package main;

import injection.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import util.SUtility;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author USER
 */
public class PnlCreateJurnal extends javax.swing.JPanel {

    /**
     * Creates new form PanelCreateJurnal
     */
    private Connection myConn = null;
    private PreparedStatement myStmt = null;
    private ResultSet myRs = null;
    
    Inject inject;
    
    FrmMain main;
    
    public PnlCreateJurnal(Connection conn,Inject inject) {
        initComponents();
        myConn = conn;
        this.main=main;
        this.inject=inject;
    }
    
    public String getJurnalNumber() {
        return "J-" + txtCreateJurnalJurnalNo.getText();
    }
    
    public void generateTable() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) tblCreateJurnal.getModel();
            for (int i = tblCreateJurnal.getRowCount() - 1; i >= 0; i--) {
                tableModel.removeRow(i);
            }
            
            myStmt = myConn.prepareStatement("select * from jurnal_detail;");
            // Execute SQL query
            myRs = myStmt.executeQuery();
            // Process result set
            if (myRs.isBeforeFirst()) {
                while (myRs.next()) {
                    Object data[] = {myRs.getString("chart_no"), myRs.getString("chart_name"), myRs.getDouble("debit"), myRs.getDouble("kredit")};
                    tableModel.addRow(data);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DlgLogin.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCreateJurnalJurnalNo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaCreateJurnalDescription = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCreateJurnal = new javax.swing.JTable();
        btnCreateJurnalAddTransaction = new javax.swing.JButton();
        btnCreateJurnalSave = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Date :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Jurnal :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Description :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("J-");

        txtCreateJurnalJurnalNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCreateJurnalJurnalNoActionPerformed(evt);
            }
        });

        txaCreateJurnalDescription.setColumns(20);
        txaCreateJurnalDescription.setRows(5);
        jScrollPane1.setViewportView(txaCreateJurnalDescription);

        tblCreateJurnal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chart No", "Chart Name", "Debit", "Kredit"
            }
        ));
        jScrollPane2.setViewportView(tblCreateJurnal);
        if (tblCreateJurnal.getColumnModel().getColumnCount() > 0) {
            tblCreateJurnal.getColumnModel().getColumn(0).setResizable(false);
            tblCreateJurnal.getColumnModel().getColumn(1).setResizable(false);
            tblCreateJurnal.getColumnModel().getColumn(2).setResizable(false);
            tblCreateJurnal.getColumnModel().getColumn(3).setResizable(false);
        }

        btnCreateJurnalAddTransaction.setText("Add Transaction");
        btnCreateJurnalAddTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateJurnalAddTransactionActionPerformed(evt);
            }
        });

        btnCreateJurnalSave.setText("Save");
        btnCreateJurnalSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateJurnalSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCreateJurnalAddTransaction)
                        .addGap(135, 135, 135))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCreateJurnalJurnalNo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(400, 400, 400)
                        .addComponent(btnCreateJurnalSave, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(txtCreateJurnalJurnalNo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addComponent(btnCreateJurnalAddTransaction)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCreateJurnalSave)
                .addGap(26, 26, 26))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtCreateJurnalJurnalNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCreateJurnalJurnalNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCreateJurnalJurnalNoActionPerformed

    private void btnCreateJurnalAddTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateJurnalAddTransactionActionPerformed
        if (!txtCreateJurnalJurnalNo.getText().trim().isEmpty()) {
            DlgCreateJurnalAddJurnalTransaction addTransaction = new DlgCreateJurnalAddJurnalTransaction(this, true, myConn);
            addTransaction.setVisible(true);
        } else {
            SUtility.msg(this, "Jurnal number must not be empty");
        }
    }//GEN-LAST:event_btnCreateJurnalAddTransactionActionPerformed

    private void btnCreateJurnalSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateJurnalSaveActionPerformed
//         int x = SUtility.msq(this, "Are you sure?");
//        if (x == 0) {
//            try {
//                for (int i = 0; i < tblCreateJurnal.getRowCount(); i++) {
//                    // Prepare statement
//
//                    myStmt = myConn.prepareStatement("INSERT INTO `akuntansi`.`jurnal_detail` ( `jurnal_no`, `chart_no`, `chart_name`, `debit`, `kredit`) VALUES "
//                            + "(?,?,?,?,?)");
//                    myStmt.setString(1,"j-"+txtCreateJurnalJurnalNo.getText());
//                    myStmt.setString(2, tblCreateJurnal.getValueAt(i, 0).toString());
//                    myStmt.setString(3, tblCreateJurnal.getValueAt(i, 1).toString());
//                    myStmt.setDouble(4, Double.valueOf(tblCreateJurnal.getValueAt(i, 2).toString()));
//                    myStmt.setDouble(5, Double.valueOf(tblCreateJurnal.getValueAt(i, 3).toString()));
//
//                    // Execute SQL query
//                    myStmt.executeUpdate();
//                    System.out.println("add");
//                    
//                }SUtility.msg(this, "Update Saved!");
//            } catch (SQLException ex) {
//                Logger.getLogger(PnlAccountChart.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        inject.main.changeLayout(inject.viewJurnal);
    }//GEN-LAST:event_btnCreateJurnalSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateJurnalAddTransaction;
    private javax.swing.JButton btnCreateJurnalSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCreateJurnal;
    private javax.swing.JTextArea txaCreateJurnalDescription;
    private javax.swing.JTextField txtCreateJurnalJurnalNo;
    // End of variables declaration//GEN-END:variables
}
