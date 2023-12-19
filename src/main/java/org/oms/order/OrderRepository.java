package org.oms.order;
import org.oms.DatabaseConnectionManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private static OrderRepository instance;

    private static final String orderByIdQuery = "SELECT * FROM orders WHERE ORDER_ID = ?";
    private static final String allOrdersQuery = "SELECT * FROM orders";
    private static final String addOrderQuery = "INSERT INTO orders VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String updateOrderQuery = "UPDATE orders SET " +
            "CUSTOMER_ID=?, ORDER_DATE=?, ORDER_STATUS=?, PAYMENT_MODE=?, " +
            "PAYMENT_DATE=?, ORDER_SHIPMENT_DATE=?, SHIPPER_ID=? " +
            "WHERE ORDER_ID=?";
    private static final String deleteOrderQuery = "DELETE FROM orders WHERE ORDER_ID=?";

    private OrderRepository() { }

    public static OrderRepository getInstance() {
        if (instance == null) {
            instance = new OrderRepository();
        }
        return instance;
    }

    public OrderHeader getOrderById(int orderId) {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(orderByIdQuery)) {

            preparedStatement.setInt(1, orderId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToOrder(resultSet);
                } else {
                    System.out.println("Order not found for ID: " + orderId);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    public List<OrderHeader> getAllOrders() {
        List<OrderHeader> orders = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(allOrdersQuery)) {

            while (resultSet.next()) {
                orders.add(mapResultSetToOrder(resultSet));
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return orders;
    }

    public void addOrder(OrderHeader order) {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addOrderQuery)) {

            setOrderValuesInPreparedStatement(preparedStatement, order);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public void deleteOrder(int orderId) {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteOrderQuery)) {

            preparedStatement.setInt(1, orderId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public void updateOrder(OrderHeader order) {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateOrderQuery)) {

            setOrderValuesInPreparedStatement(preparedStatement, order);
            preparedStatement.setInt(8, order.getOrderId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    private void setOrderValuesInPreparedStatement(PreparedStatement preparedStatement, OrderHeader order)
            throws SQLException {
        preparedStatement.setInt(1, order.getCustomerId());
        preparedStatement.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
        preparedStatement.setString(3, order.getOrderStatus());
        preparedStatement.setString(4, order.getPaymentMode());
        preparedStatement.setDate(5, new java.sql.Date(order.getPaymentDate().getTime()));
        preparedStatement.setDate(6, new java.sql.Date(order.getOrderShipmentDate().getTime()));
        preparedStatement.setInt(7, order.getShipperId());
    }

    private OrderHeader mapResultSetToOrder(ResultSet resultSet) throws SQLException {
        // Map the result set to an OrderHeader object
        OrderHeader orderHeader = OrderHeader.getInstance();
        orderHeader.setOrderId(resultSet.getInt("orderId"));
        orderHeader.setCustomerId(resultSet.getInt("customerId"));
        orderHeader.setOrderDate(resultSet.getDate("orderDate"));
        orderHeader.setOrderStatus(resultSet.getString("orderStatus"));
        orderHeader.setPaymentMode(resultSet.getString("paymentMode"));
        orderHeader.setPaymentDate(resultSet.getDate("paymentDate"));
        orderHeader.setOrderShipmentDate(resultSet.getDate("orderShipmentDate"));
        orderHeader.setShipperId(resultSet.getInt("shipperId"));
        return orderHeader;
    }

    private void handleSQLException(SQLException e) {
        if (e instanceof SQLNonTransientConnectionException) {
            // Handle connection issues
            System.err.println("Unable to establish a connection to the database.");
        } else {
            // Log or handle other SQL exceptions
            e.printStackTrace();
        }
    }
}

