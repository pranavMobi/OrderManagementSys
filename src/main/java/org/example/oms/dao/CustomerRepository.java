package org.example.oms.dao;


import org.example.oms.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/orders";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Pranavroot123";
    public Customer getCustomerById(int customerId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customers WHERE customerId = ?")) {

            preparedStatement.setInt(1, customerId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCustomer(resultSet);
                } else {
                    System.out.println("Customer not found for ID: " + customerId);
                }
            }

        } catch (SQLException e) {
            if (e instanceof SQLNonTransientConnectionException) {
                // Handle connection issues
                System.err.println("Unable to establish a connection to the database.");
            } else {
                // Handle other SQL exceptions
                e.printStackTrace();
            }
        }

        return null; // Return null if customer is not found or if there was a connection issue
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM customers")) {

            while (resultSet.next()) {
                customers.add(mapResultSetToCustomer(resultSet));
            }
        } catch (SQLException e) {
            // Log or handle the exception based on your application's needs
            e.printStackTrace();
        }

        return customers;
    }

    public void addCustomer(Customer customer) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customers VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            // Set values for the prepared statement based on the Customer object
            preparedStatement.setInt(1, customer.getCustomerId());
            preparedStatement.setString(2, customer.getCustomerFirstName());
            preparedStatement.setString(3, customer.getCustomerLastName());
            preparedStatement.setString(4, customer.getCustomerEmail());
            preparedStatement.setLong(5, customer.getCustomerPhone());
            preparedStatement.setInt(6, customer.getAddressId());
            preparedStatement.setDate(7, new java.sql.Date(customer.getCustomerCreationDate().getTime()));
            preparedStatement.setString(8, customer.getCustomerUsername());
            preparedStatement.setString(9, String.valueOf(customer.getCustomerGender()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Log or handle the exception based on your application's needs
            e.printStackTrace();
        }
    }

    public void updateCustomer(Customer customer) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customers SET " +
                     "customerFirstName=?, customerLastName=?, customerEmail=?, customerPhone=?, " +
                     "addressId=?, customerCreationDate=?, customerUsername=?, customerGender=? " +
                     "WHERE customerId=?")) {

            // Set values for the prepared statement based on the Customer object
            preparedStatement.setString(1, customer.getCustomerFirstName());
            preparedStatement.setString(2, customer.getCustomerLastName());
            preparedStatement.setString(3, customer.getCustomerEmail());
            preparedStatement.setLong(4, customer.getCustomerPhone());
            preparedStatement.setInt(5, customer.getAddressId());
            preparedStatement.setDate(6, new java.sql.Date(customer.getCustomerCreationDate().getTime()));
            preparedStatement.setString(7, customer.getCustomerUsername());
            preparedStatement.setString(8, String.valueOf(customer.getCustomerGender()));
            preparedStatement.setInt(9, customer.getCustomerId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Log or handle the exception based on your application's needs
            e.printStackTrace();
        }
    }

    private Customer mapResultSetToCustomer(ResultSet resultSet) throws SQLException {
        // Map the result set to a Customer object
        return new Customer(
                resultSet.getInt("customerId"),
                resultSet.getString("customerFirstName"),
                resultSet.getString("customerLastName"),
                resultSet.getString("customerEmail"),
                resultSet.getLong("customerPhone"),
                resultSet.getInt("addressId"),
                resultSet.getDate("customerCreationDate"),
                resultSet.getString("customerUsername"),
                resultSet.getString("customerGender").charAt(0)
        );
    }
}

