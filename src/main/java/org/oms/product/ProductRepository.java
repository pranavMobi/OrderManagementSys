package org.oms.product;

import org.oms.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static final String ProductByIdQuery = "SELECT * FROM products WHERE PRODUCT_ID = ?";
    private static final String allProductsQuery = "SELECT * FROM products";
    private static final String addProductQuery = "INSERT INTO products VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String updateProductQuery = "UPDATE products SET " +
            "PRODUCT_DESCRIPTION=?, PRODUCT_CLASS_CODE=?, PRODUCT_PRICE=?, PRODUCT_QUANTITY_AVAILABLE=?, " +
            "LENGTH=?, WIDTH=?, HEIGHT=?, WEIGHT=? " +
            "WHERE PRODUCT_ID=?";
    private static final String deleteProductQuery = "DELETE FROM products WHERE PRODUCT_ID=?";

    public Product getProductById(int productId) {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ProductByIdQuery)) {

            preparedStatement.setInt(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToProduct(resultSet);
                } else {
                    System.out.println("Product not found for ID: " + productId);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(allProductsQuery)) {

            while (resultSet.next()) {
                products.add(mapResultSetToProduct(resultSet));
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return products;
    }

    public void addProduct(Product product) {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addProductQuery)) {

            setProductValuesInPreparedStatement(preparedStatement, product);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public void deleteProduct(int productId) {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteProductQuery)) {

            preparedStatement.setInt(1, productId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public void updateProduct(Product product) {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateProductQuery)) {

            setProductValuesInPreparedStatement(preparedStatement, product);
            preparedStatement.setInt(9, product.getProductId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    private void setProductValuesInPreparedStatement(PreparedStatement preparedStatement, Product product)
            throws SQLException {
        preparedStatement.setInt(1, product.getProductId());
        preparedStatement.setString(2, product.getProductDescription());
        preparedStatement.setInt(3, product.getProductClassCode());
        preparedStatement.setDouble(4, product.getProductPrice());
        preparedStatement.setInt(5, product.getProductQuantityAvailable());
        preparedStatement.setInt(6, product.getLength());
        preparedStatement.setInt(7, product.getWidth());
        preparedStatement.setInt(8, product.getHeight());
        preparedStatement.setDouble(9, product.getWeight());
    }

    private Product mapResultSetToProduct(ResultSet resultSet) throws SQLException {
        // Map the result set to a Product object
        return new Product(
                resultSet.getInt("productId"),
                resultSet.getString("productDescription"),
                resultSet.getInt("productClassCode"),
                resultSet.getDouble("productPrice"),
                resultSet.getInt("productQuantityAvailable"),
                resultSet.getInt("length"),
                resultSet.getInt("width"),
                resultSet.getInt("height"),
                resultSet.getDouble("weight")
        );
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
