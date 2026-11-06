/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package macz.delivery;

import java.sql.*;
import javax.swing.*;

public class SignupFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SignupFrame.class.getName());

    /**
     * Creates new form SignupFrame
     */
    public SignupFrame() {
        initComponents();
        
        getContentPane().setLayout(new java.awt.GridBagLayout());
        getContentPane().add(pnlSignup, new java.awt.GridBagConstraints());
        
        setResizable(true);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
    }

    
private void registerUser() {

    String name = txtFullName.getText().trim();
    String user = txtUsername.getText().trim();
    char[] password = txtPassword.getPassword();

    if (name.isEmpty() || user.isEmpty() || password.length == 0) {
        JOptionPane.showMessageDialog(
            this, "Please complete all fields."
        );
        java.util.Arrays.fill(password, '\0');
        return;
    }

    String hashedPassword;

    try {
        hashedPassword = PasswordUtil.hashPassword(password);
    } finally {
        java.util.Arrays.fill(password, '\0');
    }

    String sql =
        "INSERT INTO users "
        + "(full_name, username, password, role) "
        + "VALUES (?, ?, ?, 'CUSTOMER')";

    try (Connection con = DBConnection.connect();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setString(1, name);
        pst.setString(2, user);
        pst.setString(3, hashedPassword);

        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this, "Customer account created successfully!"
        );

        new LoginFrame().setVisible(true);
        this.dispose();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(
            this, "Registration failed: " + e.getMessage()
        );
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSignup = new javax.swing.JPanel();
        lblUsername = new javax.swing.JLabel();
        lblFullName = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtFullName = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnRegister = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlSignup.setPreferredSize(new java.awt.Dimension(500, 400));
        pnlSignup.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsername.setText("Username: ");
        pnlSignup.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, -1, -1));

        lblFullName.setText("Full Name: ");
        pnlSignup.add(lblFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));
        pnlSignup.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 140, -1));
        pnlSignup.add(txtFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 140, -1));

        lblPassword.setText("Password:");
        pnlSignup.add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, -1, -1));

        txtPassword.addActionListener(this::txtPasswordActionPerformed);
        pnlSignup.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, 140, -1));

        btnRegister.setText("Register");
        btnRegister.addActionListener(this::btnRegisterActionPerformed);
        pnlSignup.add(btnRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, -1, -1));

        btnBack.setText("Back to Login");
        btnBack.addActionListener(this::btnBackActionPerformed);
        pnlSignup.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 240, -1, -1));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setText("SignUp");
        pnlSignup.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        getContentPane().add(pnlSignup, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 400));
        pnlSignup.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        String name = txtFullName.getText();
    String user = txtUsername.getText();
    String pass = new String(txtPassword.getPassword());

    try (Connection con = DBConnection.connect();
         PreparedStatement pst = con.prepareStatement("INSERT INTO users (full_name, username, password) VALUES (?, ?, ?)")) {
        pst.setString(1, name);
        pst.setString(2, user);
        pst.setString(3, pass);
        pst.executeUpdate();
        JOptionPane.showMessageDialog(this, "Registration Successful!");
        new LoginFrame().setVisible(true);
        this.dispose();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error: Username might already exist.");
    }

    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new LoginFrame().setVisible(true);
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
        java.awt.EventQueue.invokeLater(() -> new SignupFrame().setVisible(true));
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblFullName;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPanel pnlSignup;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
