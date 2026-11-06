/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package macz.delivery;

import java.sql.*;
import javax.swing.*;

public class AboutUsFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AboutUsFrame.class.getName());

    /**
     * Creates new form AboutUsFrame
     */
    public AboutUsFrame() {
    initComponents();

    // Center the original form
    java.awt.Container originalContent = getContentPane();
    java.awt.Dimension originalSize = originalContent.getPreferredSize();

    javax.swing.JPanel wrapper = new javax.swing.JPanel(
        new java.awt.GridBagLayout()
    );

    originalContent.setPreferredSize(originalSize);
    wrapper.add(originalContent);
    setContentPane(wrapper);

    // Fullscreen
    setResizable(true);
    setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);

    // Keep existing company information
    fetchCompanyDetails();
}

    private void fetchCompanyDetails() {
        try (Connection con = DBConnection.connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM company_info LIMIT 1")) {
            if (rs.next()) {
                lblCompany.setText("Company: " + rs.getString("company_name"));
                lblPhone.setText("Phone: " + rs.getString("phone"));
                lblEmail.setText("Email: " + rs.getString("email"));
                lblAddress.setText("Address: " + rs.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAddress = new javax.swing.JLabel();
        lblCompany = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAddress.setText("Address:");
        getContentPane().add(lblAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, -1, -1));

        lblCompany.setText("Company:");
        getContentPane().add(lblCompany, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, -1, -1));

        lblPhone.setText("Phone:");
        getContentPane().add(lblPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, -1, -1));

        lblEmail.setText("Email:");
        getContentPane().add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, -1, -1));

        btnBack.setText("Back to Home");
        btnBack.addActionListener(this::btnBackActionPerformed);
        getContentPane().add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, -1, -1));

        jLabel5.setText("Macz Damilag Delivery Services");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

        jLabel6.setText("(+63) 991 8912 838");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, -1, -1));

        jLabel7.setText("MarkGuanzon0909@gmail.com");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, -1, -1));

        jLabel8.setText("Purok 12 Damilag, Manolo Fortich, Bukindon");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, -1, -1));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setText("About Us");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new HomepageFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new AboutUsFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblCompany;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblPhone;
    // End of variables declaration//GEN-END:variables
}
