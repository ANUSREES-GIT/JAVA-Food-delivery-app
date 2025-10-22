package com.tap.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.tap.dao.OrderItemDao;
import com.tap.model.OrderItem;
import com.tap.connection.DbConnection;

public class OrderItemDaoImpl implements OrderItemDao {

    private static final String INSERT_ORDER_ITEM = "INSERT INTO orderitem (orderId, menuId, quantity, totalAmount) VALUES (?, ?, ?, ?)";
    private static final String GET_ORDER_ITEMS_BY_ORDER_ID = "SELECT * FROM orderitem WHERE orderId = ?";
    private static final String UPDATE_ORDER_ITEM = "UPDATE orderitem SET menuId = ?, quantity = ?, totalAmount = ? WHERE orderItemId = ?";
    private static final String DELETE_ORDER_ITEM = "DELETE FROM orderitem WHERE orderItemId = ?";
    private static final String CHECK_ORDER_EXISTS = "SELECT COUNT(*) FROM `order` WHERE `orderId` = ?";

    @Override
    public void addOrderItem(OrderItem orderItem) {
        try (Connection connection = DbConnection.getConnection()) {

            // Check if the orderId exists in the 'order' table
            PreparedStatement checkStmt = connection.prepareStatement(CHECK_ORDER_EXISTS);
            checkStmt.setInt(1, orderItem.getOrderId());
            ResultSet resultSet = checkStmt.executeQuery();

            // If orderId does not exist, return an error message
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                System.out.println("Error: Order ID " + orderItem.getOrderId() + " does not exist.");
                return;
            }

            // Proceed with inserting the order item if the orderId exists
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_ITEM);
            preparedStatement.setInt(1, orderItem.getOrderId());
            preparedStatement.setInt(2, orderItem.getMenuId());
            preparedStatement.setInt(3, orderItem.getQuantity());
            double totalAmount = calculateTotalAmount(orderItem.getMenuId(), orderItem.getQuantity());
            preparedStatement.setDouble(4, totalAmount);

            preparedStatement.executeUpdate();
            System.out.println("Order Item added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private double calculateTotalAmount(int menuId, int quantity) {
        // Logic to calculate the total amount, for example, based on the menu item price.
        // In a real-world application, you would query the menu table to get the price for the given menuId
        double pricePerItem = 100.0; // Placeholder value: replace with actual logic to get price from menu.
        return pricePerItem * quantity;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        List<OrderItem> orderItems = new ArrayList<>();

        try (Connection connection = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_ITEMS_BY_ORDER_ID);
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderItemId(resultSet.getInt("orderItemId"));
                orderItem.setOrderId(resultSet.getInt("orderId"));
                orderItem.setMenuId(resultSet.getInt("menuId"));
                orderItem.setQuantity(resultSet.getInt("quantity"));
                orderItem.setTotalAmount(resultSet.getDouble("totalAmount"));
                orderItems.add(orderItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItems;  // Return the list of OrderItems
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        try (Connection connection = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_ITEM);
            preparedStatement.setInt(1, orderItem.getMenuId());
            preparedStatement.setInt(2, orderItem.getQuantity());
            preparedStatement.setDouble(3, calculateTotalAmount(orderItem.getMenuId(), orderItem.getQuantity()));
            preparedStatement.setInt(4, orderItem.getOrderItemId());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Order Item updated successfully.");
            } else {
                System.out.println("Order Item update failed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderItem(int orderItemId) {
        try (Connection connection = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_ITEM);
            preparedStatement.setInt(1, orderItemId);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Order Item deleted successfully.");
            } else {
                System.out.println("Order Item deletion failed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
