package org.oms.customer;

import org.oms.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private static final String GetCustomerById = "SELECT * FROM online_customer WHERE  CUSTOMER_ID = ?";
    private static final String GetAllCustomers = "SELECT * FROM online_customer";
    private static final String InsertCustomer = "INSERT INTO online_customer VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UpdateCustomer = "UPDATE online_customer SET " +
            "CUSTOMER_FNAME=?, CUSTOMER_LNAME=?, CUSTOMER_EMAIL=?, CUSTOMER_PHONE=?, " +
            "ADDRESS_ID=?, CUSTOMER_CREATION_DATE=?, CUSTOMER_USERNAME=?, CUSTOMER_GENDER=? " +
            "WHERE CUSTOMER_ID=?";
    public static final String DeleteCustomer = "DELETE FROM online_customer WHERE CUSTOMER_ID=?";

    public Customer getCustomerById(int customerId) {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GetCustomerById)) {

            preparedStatement.setInt(1, customerId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCustomer(resultSet);
                } else {
                    System.out.println("Customer not found for ID: " + customerId);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GetAllCustomers)) {

            while (resultSet.next()) {
                customers.add(mapResultSetToCustomer(resultSet));
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return customers;
    }

    public void addCustomer(Customer customer) {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(InsertCustomer)) {

            setCustomerValuesInPreparedStatement(preparedStatement, customer);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void deleteCustomer(int customerId) {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DeleteCustomer)) {

            preparedStatement.setInt(1, customerId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void updateCustomer(Customer customer) {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UpdateCustomer)) {

            setCustomerValuesInPreparedStatement(preparedStatement, customer);
            preparedStatement.setInt(9, customer.getCustomerId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    private void setCustomerValuesInPreparedStatement(PreparedStatement preparedStatement, Customer customer)
            throws SQLException {
        preparedStatement.setInt(1, customer.getCustomerId());
        preparedStatement.setString(2, customer.getCustomerFirstName());
        preparedStatement.setString(3, customer.getCustomerLastName());
        preparedStatement.setString(4, customer.getCustomerEmail());
        preparedStatement.setLong(5, customer.getCustomerPhone());
        preparedStatement.setInt(6, customer.getAddressId());
        preparedStatement.setDate(7, new java.sql.Date(customer.getCustomerCreationDate().getTime()));
        preparedStatement.setString(8, customer.getCustomerUsername());
        preparedStatement.setString(9, String.valueOf(customer.getCustomerGender()));
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

    private void handleSQLException(SQLException e) {
        if (e instanceof SQLNonTransientConnectionException) {
            // Handle connection issues
            System.err.println("Unable to establish a connection to the database.");
        }
        else {
            // Log or handle other SQL exceptions
            e.printStackTrace();
        }
    }


}
