package main;

import injection.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author USER
 */
public class PnlGeneralLedgerMonth extends javax.swing.JPanel {

    /**
     * Creates new form PnlGeneralLedgerMonth
     */
    private Connection myConn = null;
    private PreparedStatement myStmt = null;
    private ResultSet myRs = null;
    private Inject inject;
    
    private String chartName;
    private String accChartNumber;
    private int month;
    private int year;

    public PnlGeneralLedgerMonth(Connection conn, Inject inject) {
        initComponents();
        myConn = conn;
        this.inject = inject;
    }

    public String getAccChartNumber() {
        return accChartNumber;
    }

    public int getMonth() {
        return month;
    }

    public String getChartName() {
        return chartName;
    }

    public int getYear() {
        return year;
    }

    public void generateComboBoxChartName() {
        try {
            cboChartNumber.removeAllItems();
            myStmt = myConn.prepareStatement("select * from account_chart");
            // Execute SQL query
            myRs = myStmt.executeQuery();
            ArrayList<String> chartName = new ArrayList<>();
            // Process result set
            while (myRs.next()) {
                chartName.add(myRs.getString("chart_name"));
            }
            for (String a : chartName) {
                cboChartNumber.addItem(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DlgCreateJurnalAddJurnalTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void generateChartNumber() {
        try {
            ;
            myStmt = myConn.prepareStatement("select * from account_chart where chart_name=?;");
            myStmt.setString(1, cboChartNumber.getSelectedItem().toString());
            // Execute SQL query
            myRs = myStmt.executeQuery();

            // Process result set
            while (myRs.next()) {
                txtGLMChartNo.setText(myRs.getString("chart_no"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DlgCreateJurnalAddJurnalTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }catch(NullPointerException ex){
            
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
        cboChartNumber = new javax.swing.JComboBox<>();
        btnGLNext = new javax.swing.JButton();
        mtcGL = new com.toedter.calendar.JMonthChooser();
        jLabel3 = new javax.swing.JLabel();
        txtGLMChartNo = new javax.swing.JTextField();
        ytcGL = new com.toedter.calendar.JYearChooser();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Select Month :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Chart Name :");

        cboChartNumber.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Bank" }));
        cboChartNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChartNumberActionPerformed(evt);
            }
        });

        btnGLNext.setText("Next");
        btnGLNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGLNextActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Chart No :");

        txtGLMChartNo.setEditable(false);
        txtGLMChartNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGLMChartNoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(372, 372, 372)
                        .addComponent(btnGLNext, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(283, 283, 283)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboChartNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(mtcGL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ytcGL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtGLMChartNo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(340, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mtcGL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ytcGL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboChartNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGLMChartNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(btnGLNext)
                .addContainerGap(175, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboChartNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChartNumberActionPerformed
        generateChartNumber();
    }//GEN-LAST:event_cboChartNumberActionPerformed

    private void btnGLNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGLNextActionPerformed
        month = mtcGL.getMonth() + 1;
        year=ytcGL.getYear();
        chartName=cboChartNumber.getSelectedItem().toString();
        accChartNumber = txtGLMChartNo.getText();
        inject.getMain().changeLayout(inject.getGeneralLedger());
        inject.getGeneralLedger().generateData();
        inject.getGeneralLedger().generateTable(accChartNumber,month,year);
        inject.getGeneralLedger().generateTotal();
    }//GEN-LAST:event_btnGLNextActionPerformed

    private void txtGLMChartNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGLMChartNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGLMChartNoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGLNext;
    private javax.swing.JComboBox<String> cboChartNumber;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private com.toedter.calendar.JMonthChooser mtcGL;
    private javax.swing.JTextField txtGLMChartNo;
    private com.toedter.calendar.JYearChooser ytcGL;
    // End of variables declaration//GEN-END:variables
}
