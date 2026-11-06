/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package macz.delivery;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VehicleInfoFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger =
        java.util.logging.Logger.getLogger(VehicleInfoFrame.class.getName());

    public VehicleInfoFrame() {
    initComponents();
    
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

    // Load vehicle data
    loadVehicles();

    // Keep STAFF and ADMIN permissions
    String role = UserSession.getRole();

    boolean canManage = "STAFF".equals(role) || "ADMIN".equals(role);

    btnAddVehicle.setEnabled(canManage);
}

   private void loadVehicles() {

    DefaultTableModel model = new DefaultTableModel(
        new String[]{
            "Vehicle ID",
            "Plate Number",
            "Vehicle Type",
            "Status"
        }, 0
    );

    try (Connection con = DBConnection.connect()) {

        if (con == null) {
            JOptionPane.showMessageDialog(
                this,
                "Database connection failed."
            );
            return;
        }

        String sql = "SELECT vehicle_id, plate_number, vehicle_type, status FROM vehicles";

        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                model.addRow(new Object[]{
                    rs.getInt("vehicle_id"),
                    rs.getString("plate_number"),
                    rs.getString("vehicle_type"),
                    rs.getString("status")
                });

            }
        }

        table.setModel(model);

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            this,
            "Error loading vehicles:\n" + e.getMessage()
        );

    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnAddVehicle = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBack.setText("Back to Home");
        btnBack.addActionListener(this::btnBackActionPerformed);
        getContentPane().add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, -1, -1));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 550, 210));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setText("Vehicle Info");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, -1, -1));

        btnAddVehicle.setText("Add Vehicle");
        btnAddVehicle.addActionListener(this::btnAddVehicleActionPerformed);
        getContentPane().add(btnAddVehicle, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new HomepageFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnAddVehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddVehicleActionPerformed
        String role = UserSession.getRole();

if (!"STAFF".equals(role) && !"ADMIN".equals(role)) {
    JOptionPane.showMessageDialog(
        this,
        "Access denied. Only Staff and Admin can add vehicles."
    );
    return;
}
        String plate = JOptionPane.showInputDialog(
    this, "Enter Plate Number:"
);

if (plate == null) return;

plate = plate.trim().toUpperCase();

if (plate.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Plate number is required.");
    return;
}

String type = JOptionPane.showInputDialog(
    this, "Enter Vehicle Type (Van, Truck, Motorcycle):"
);

if (type == null) return;

type = type.trim();

if (type.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Vehicle type is required.");
    return;
}

String[] statuses = {
    "AVAILABLE",
    "UNAVAILABLE",
    "MAINTENANCE"
};

String status = (String) JOptionPane.showInputDialog(
    this,
    "Select Vehicle Status:",
    "Vehicle Availability",
    JOptionPane.QUESTION_MESSAGE,
    null,
    statuses,
    statuses[0]
);

if (status == null) return;

String sql = "INSERT INTO vehicles (plate_number, vehicle_type, status) "
           + "VALUES (?, ?, ?)";

try (Connection con = DBConnection.connect()) {

    if (con == null) {
        JOptionPane.showMessageDialog(this, "Database connection failed.");
        return;
    }

    try (PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setString(1, plate);
        pst.setString(2, type);
        pst.setString(3, status);

        pst.executeUpdate();
    }

    JOptionPane.showMessageDialog(
        this,
        "Vehicle successfully added!"
    );

    loadVehicles();

} catch (SQLException e) {

    JOptionPane.showMessageDialog(
        this,
        "Error saving vehicle: " + e.getMessage()
    );
}
    }//GEN-LAST:event_btnAddVehicleActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new VehicleInfoFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddVehicle;
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}