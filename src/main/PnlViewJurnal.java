package main;

import injection.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author USER
 */
public class PnlViewJurnal extends javax.swing.JPanel {

    /**
     * Creates new form PnlViewJurnal
     */
    private Connection myConn = null;
    private PreparedStatement myStmt = null;
    private ResultSet myRs = null;

    Inject inject;

    public PnlViewJurnal(Connection conn, Inject inject) {
        initComponents();
        myConn = conn;
        this.inject = inject;
//        generateTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void generateTable() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) tblViewJurnal.getModel();
            for (int i = tblViewJurnal.getRowCount() - 1; i >= 0; i--) {
                tableModel.removeRow(i);
            }

            myStmt = myConn.prepareStatement("select * from jurnal_fulldata where extract(month from date)=? && "
                    + "extract(year from date)=? group by jurnal_no ;");
            // Execute SQL query
            myStmt.setInt(1, inject.getMonth());
            myStmt.setInt(2, inject.getYear());
            myRs = myStmt.executeQuery();
            ArrayList<String> jurnalNo = new ArrayList<>();
            // Process result set
            while (myRs.next()) {
                jurnalNo.add(myRs.getString("jurnal_no"));
            }

            for (String jnum : jurnalNo) {
                double debit = 0;
                double kredit = 0;
                String date = "";
                String desc = "";

                myStmt = myConn.prepareStatement("select * from jurnal_fulldata where jurnal_no=? && extract(month from date)=? && "
                        + "extract(year from date)=?;");
                // Execute SQL query
                myStmt.setString(1, jnum);
                myStmt.setInt(2, inject.getMonth());
                myStmt.setInt(3, inject.getYear());
                myRs = myStmt.executeQuery();

                if (myRs.isBeforeFirst()) {
                    while (myRs.next()) {
                        date = myRs.getString("date");
                        desc = myRs.getString("description");
                        debit += myRs.getDouble("debit");
                        kredit += myRs.getDouble("kredit");
                    }

                }
                Object data[] = {jnum, date, desc, debit, kredit};
                tableModel.addRow(data);

            }
            generateTotal();
            
        } catch (SQLException ex) {
            Logger.getLogger(PnlViewJurnal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generateTotal(){
        double tDebit=0;
        double tKredit=0;
        for(int row=0;row<tblViewJurnal.getRowCount();row++){
            tDebit+=Double.valueOf(tblViewJurnal.getValueAt(row,3).toString());
            tKredit+=Double.valueOf(tblViewJurnal.getValueAt(row,4).toString());
        }
        txtViewJurnalTotalDebit.setText(String.valueOf(tDebit));
        txtViewJurnalTotalKredit.setText(String.valueOf(tKredit));
    }
    
    private void executeDeleteRow() {
        int row = tblViewJurnal.getSelectedRow();
        try {
            myStmt = myConn.prepareStatement("delete from jurnal_detail where jurnal_no=? && extract(month from date)=? && "
                    + "extract(year from date)=?;");
            myStmt.setString(1,tblViewJurnal.getValueAt(row, 0).toString());
            myStmt.setInt(2, inject.getMonth());
            myStmt.setInt(3, inject.getYear());

            // Execute SQL query
            myStmt.executeUpdate();
            
            myStmt = myConn.prepareStatement("delete from jurnal_master where jurnal_no=? && extract(month from date)=? && "
                    + "extract(year from date)=?;");
            myStmt.setString(1, tblViewJurnal.getValueAt(row, 0).toString());
            myStmt.setInt(2, inject.getMonth());
            myStmt.setInt(3, inject.getYear());

            // Execute SQL query
            myStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PnlViewJurnal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnViewJurnalDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblViewJurnal = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtViewJurnalTotalDebit = new javax.swing.JTextField();
        txtViewJurnalTotalKredit = new javax.swing.JTextField();

        btnViewJurnalDelete.setText("Delete");
        btnViewJurnalDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewJurnalDeleteActionPerformed(evt);
            }
        });

        tblViewJurnal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Jurnal No", "Date", "Description", "Total Debit", "Total Kredit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblViewJurnal);
        if (tblViewJurnal.getColumnModel().getColumnCount() > 0) {
            tblViewJurnal.getColumnModel().getColumn(0).setResizable(false);
            tblViewJurnal.getColumnModel().getColumn(1).setResizable(false);
            tblViewJurnal.getColumnModel().getColumn(2).setResizable(false);
            tblViewJurnal.getColumnModel().getColumn(3).setResizable(false);
            tblViewJurnal.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Total Kredit :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Total Debit :");

        txtViewJurnalTotalDebit.setEditable(false);

        txtViewJurnalTotalKredit.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(18, 18, 18)
                            .addComponent(txtViewJurnalTotalKredit, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtViewJurnalTotalDebit, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(btnViewJurnalDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(168, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnViewJurnalDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtViewJurnalTotalDebit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtViewJurnalTotalKredit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewJurnalDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewJurnalDeleteActionPerformed
        int x = JOptionPane.showConfirmDialog(this, "Are You sure?", "warning", JOptionPane.YES_NO_OPTION);
        if (x == 0) {
            executeDeleteRow();
            generateTable();
        }

    }//GEN-LAST:event_btnViewJurnalDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnViewJurnalDelete;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblViewJurnal;
    private javax.swing.JTextField txtViewJurnalTotalDebit;
    private javax.swing.JTextField txtViewJurnalTotalKredit;
    // End of variables declaration//GEN-END:variables
}
