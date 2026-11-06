/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package macz.delivery;

import javax.swing.*;

public class HomepageFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HomepageFrame.class.getName());
    
    public HomepageFrame() {
        initComponents();
        
        getContentPane().setLayout(new java.awt.GridBagLayout());
        getContentPane().add(pnlHome, new java.awt.GridBagConstraints());

        setResizable(true);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        configureRoleAccess();
    }
private void configureRoleAccess() {

    String role = UserSession.getRole();

    if (role == null) {
        btnBooking.setEnabled(false);
        btnVehicles.setEnabled(false);
        btnDrivers.setEnabled(false);
        return;
    }

    switch (role) {
        case "CUSTOMER":
        case "STAFF":
        case "ADMIN":
            btnBooking.setEnabled(true);
            btnVehicles.setEnabled(true);
            btnDrivers.setEnabled(true);
            break;

        default:
            btnBooking.setEnabled(false);
            btnVehicles.setEnabled(false);
            btnDrivers.setEnabled(false);
            break;
    }
    String currentRole = UserSession.getRole();

boolean canManageRates =
    "ADMIN".equals(currentRole) || "STAFF".equals(currentRole);

btnManageRates.setEnabled(canManageRates);
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHome = new javax.swing.JPanel();
        lblWelcome = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnBooking = new javax.swing.JButton();
        btnVehicles = new javax.swing.JButton();
        btnDrivers = new javax.swing.JButton();
        btnAbout = new javax.swing.JButton();
        btnMyProfile = new javax.swing.JButton();
        btnManageRates = new javax.swing.JButton();
        btnDashboard = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblWelcome.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        lblWelcome.setText("Macz Damilag Delivery Services");
        pnlHome.add(lblWelcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        btnLogout.setText("Log Out");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);
        pnlHome.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 410, -1, -1));

        btnBooking.setText("Create a Booking");
        btnBooking.addActionListener(this::btnBookingActionPerformed);
        pnlHome.add(btnBooking, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 150, 50));

        btnVehicles.setText("Vehicle Information");
        btnVehicles.addActionListener(this::btnVehiclesActionPerformed);
        pnlHome.add(btnVehicles, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, 150, 40));

        btnDrivers.setText("Driver Information");
        btnDrivers.addActionListener(this::btnDriversActionPerformed);
        pnlHome.add(btnDrivers, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, 150, 40));

        btnAbout.setText("About Us");
        btnAbout.addActionListener(this::btnAboutActionPerformed);
        pnlHome.add(btnAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 150, 40));

        btnMyProfile.setText("My Profile");
        btnMyProfile.addActionListener(this::btnMyProfileActionPerformed);
        pnlHome.add(btnMyProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 150, 40));

        btnManageRates.setText("Manage Delivery Rates");
        btnManageRates.addActionListener(this::btnManageRatesActionPerformed);
        pnlHome.add(btnManageRates, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 300, 40));

        btnDashboard.setText("Dashboard");
        btnDashboard.addActionListener(this::btnDashboardActionPerformed);
        pnlHome.add(btnDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 150, 50));

        getContentPane().add(pnlHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 0, 750, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed

        UserSession.logout();
        new LoginFrame().setVisible(true);
    this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnVehiclesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVehiclesActionPerformed
        new VehicleInfoFrame().setVisible(true);
    this.dispose();
    }//GEN-LAST:event_btnVehiclesActionPerformed

    private void btnDriversActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDriversActionPerformed
        new DriverInfoFrame().setVisible(true);
    this.dispose();
    }//GEN-LAST:event_btnDriversActionPerformed

    private void btnAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAboutActionPerformed
        new AboutUsFrame().setVisible(true);
    this.dispose();
    }//GEN-LAST:event_btnAboutActionPerformed

    private void btnBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookingActionPerformed
        new BookingFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBookingActionPerformed

    private void btnMyProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMyProfileActionPerformed
        new ProfileFrame().setVisible(true);
this.dispose(); 
    }//GEN-LAST:event_btnMyProfileActionPerformed

    private void btnManageRatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageRatesActionPerformed
        String role = UserSession.getRole();

if (!"ADMIN".equals(role) && !"STAFF".equals(role)) {
    javax.swing.JOptionPane.showMessageDialog(this,
        "Access denied. Only ADMIN and STAFF can manage delivery rates.");
    return;
}

ManageDeliveryRatesFrame ratesFrame = new ManageDeliveryRatesFrame();
ratesFrame.setVisible(true);
this.dispose();
    }//GEN-LAST:event_btnManageRatesActionPerformed

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        new DashboardFrame().setVisible(true);
this.dispose();
    }//GEN-LAST:event_btnDashboardActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new HomepageFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbout;
    private javax.swing.JButton btnBooking;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnDrivers;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnManageRates;
    private javax.swing.JButton btnMyProfile;
    private javax.swing.JButton btnVehicles;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JPanel pnlHome;
    // End of variables declaration//GEN-END:variables
}