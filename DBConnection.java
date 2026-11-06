package macz.delivery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConnection {
    private static final String URL =
            "jdbc:mysql://localhost:3306/Macz_delivery_db";

    private static final String USER = "root";

    private static final String PASSWORD = "";

    public static Connection connect() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

        } catch (ClassNotFoundException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "MySQL JDBC Driver not found."
            );

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Database Connection Failed:\n"
                    + e.getMessage()
            );
        }

        return null;
    }
}