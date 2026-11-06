# Macz Delivery System

A Java Swing-based Delivery Booking and Management System developed using Apache NetBeans and MySQL.

## Features
- User Login and Registration
- Delivery Booking
- Booking History
- Booking Assignment
- Driver Management
- Vehicle Management
- Delivery Rate Management
- Dashboard
- User Profile Management

## Technologies Used
- Java (Swing)
- Apache NetBeans
- MySQL / MariaDB
- XAMPP
- JDBC

## How to Run

1. Install Java JDK and Apache NetBeans.
2. Install XAMPP and start MySQL.
3. Open phpMyAdmin.
4. Create a database named `Macz_delivery_db`.
5. Import the included `Macz_delivery_db.sql` file.
6. Open the project in Apache NetBeans.
7. Configure the MySQL connection in `DBConnection.java` for your local database.
8. Ensure the MySQL Connector/J JDBC driver is available in the project libraries.
9. Build and run the application.

## Database

The system uses six tables:
- `users`
- `bookings`
- `drivers`
- `vehicles`
- `delivery_rates`
- `company_info`

## Project Type

Desktop-based Delivery Booking and Management System.
