/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package macz.delivery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LoginFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LoginFrame.class.getName());

    /**
     * Creates new form LoginFrame
     */
    public LoginFrame() {
        initComponents();
        
        getContentPane().setLayout(new java.awt.GridBagLayout());
        getContentPane().add(pnlLogin, new java.awt.GridBagConstraints());
        
        setResizable(true);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlLogin = new javax.swing.JPanel();
        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnSignup = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        chkShowPassword = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlLogin.setPreferredSize(new java.awt.Dimension(450, 350));
        pnlLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsername.setText("Username:");
        pnlLogin.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, 20));

        lblPassword.setText("Password:");
        pnlLogin.add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, -1));

        txtUsername.addActionListener(this::txtUsernameActionPerformed);
        pnlLogin.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 180, -1));
        pnlLogin.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 180, -1));

        btnLogin.setText("Log In");
        btnLogin.addActionListener(this::btnLoginActionPerformed);
        pnlLogin.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 70, -1));

        btnSignup.setText("Sign Up");
        btnSignup.addActionListener(this::btnSignupActionPerformed);
        pnlLogin.add(btnSignup, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, -1, -1));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setText("LogIn");
        pnlLogin.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, -1, -1));

        chkShowPassword.setText("Show Password");
        chkShowPassword.addActionListener(this::chkShowPasswordActionPerformed);
        pnlLogin.add(chkShowPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, -1, -1));

        getContentPane().add(pnlLogin, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed

    String user = txtUsername.getText().trim();
    char[] password = txtPassword.getPassword();

    if (user.isEmpty() || password.length == 0) {
        JOptionPane.showMessageDialog(
            this, "Please enter username and password."
        );
        java.util.Arrays.fill(password, '\0');
        return;
    }

    String sql =
        "SELECT user_id, username, password, full_name, role "
        + "FROM users WHERE username = ?";

    try (Connection con = DBConnection.connect();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setString(1, user);

        try (ResultSet rs = pst.executeQuery()) {

            if (!rs.next()) {
                JOptionPane.showMessageDialog(
                    this, "Invalid Username or Password!"
                );
                return;
            }

            String storedPassword = rs.getString("password");

            boolean valid = PasswordUtil.verifyPassword(
                password, storedPassword
            );

            // Temporary compatibility for older plain-text accounts.
            // This will be removed after migration.
            boolean oldPassword =
                !storedPassword.startsWith("pbkdf2_sha256$")
                && java.security.MessageDigest.isEqual(
                    new String(password).getBytes(
                        java.nio.charset.StandardCharsets.UTF_8),
                    storedPassword.getBytes(
                        java.nio.charset.StandardCharsets.UTF_8)
                );

            if (!valid && !oldPassword) {
                JOptionPane.showMessageDialog(
                    this, "Invalid Username or Password!"
                );
                return;
            }

            int userId = rs.getInt("user_id");
            String fullName = rs.getString("full_name");
            String role = rs.getString("role");

            // Upgrade an old password after successful login.
            if (oldPassword) {
                String newHash = PasswordUtil.hashPassword(password);

                try (PreparedStatement update = con.prepareStatement(
                    "UPDATE users SET password = ? "
                    + "WHERE user_id = ? AND password = ?")) {

                    update.setString(1, newHash);
                    update.setInt(2, userId);
                    update.setString(3, storedPassword);
                    update.executeUpdate();
                }
            }

            UserSession.login(userId, user, fullName, role);

            JOptionPane.showMessageDialog(
                this, "Welcome, " + fullName + "!"
            );

            new HomepageFrame().setVisible(true);
            this.dispose();
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(
            this, "Database Error:\n" + e.getMessage()
        );
    } finally {
        java.util.Arrays.fill(password, '\0');
    }
    
    }//GEN-LAST:event_btnLoginActionPerformed
    
    private void btnSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignupActionPerformed
                                         
    new SignupFrame().setVisible(true);
    this.dispose();
    
    }//GEN-LAST:event_btnSignupActionPerformed

    private void chkShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkShowPasswordActionPerformed
        if (chkShowPassword.isSelected()) {
    txtPassword.setEchoChar((char) 0);
} else {
    txtPassword.setEchoChar('\u2022');
}
    }//GEN-LAST:event_chkShowPasswordActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> {
            new LoginFrame().setVisible(true);
                });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSignup;
    private javax.swing.JCheckBox chkShowPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
