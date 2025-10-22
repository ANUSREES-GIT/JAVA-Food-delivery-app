package com.tap.daoimpl;

import java.sql.*;
import com.tap.dao.OrderDao;
import com.tap.model.Order;
import com.tap.connection.DbConnection;

public class OrderDaoImpl implements OrderDao {

	private static final String INSERT_ORDER = 
		    "INSERT INTO `order` (userId, restaurantId, orderDate, totalAmount, status, paymentMode) VALUES (?, ?, ?, ?, ?, ?)";

		private static final String GET_ORDER_BY_ID = 
		    "SELECT * FROM `order` WHERE orderId = ?";

		private static final String UPDATE_ORDER = 
		    "UPDATE `order` SET userId = ?, restaurantId = ?, orderDate = ?, totalAmount = ?, status = ?, paymentMode = ? WHERE orderId = ?";

		private static final String DELETE_ORDER = 
		    "DELETE FROM `order` WHERE orderId = ?";

    @Override
    public void addOrder(Order order) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_ORDER)) {

            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getRestaurantId());
            ps.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            ps.setDouble(4, order.getTotalAmount());
            ps.setString(5, order.getStatus());
            ps.setString(6, order.getPaymentMode());

            ps.executeUpdate();
            System.out.println("Order added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order getOrderById(int orderId) {
        Order order = null;
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ORDER_BY_ID)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                order = new Order(
                    rs.getInt("orderId"),
                    rs.getInt("userId"),
                    rs.getInt("restaurantId"),
                    rs.getDate("orderDate"),
                    rs.getDouble("totalAmount"),
                    rs.getString("status"),
                    rs.getString("paymentMode")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_ORDER)) {

            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getRestaurantId());
            ps.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            ps.setDouble(4, order.getTotalAmount());
            ps.setString(5, order.getStatus());
            ps.setString(6, order.getPaymentMode());
            ps.setInt(7, order.getOrderId());

            ps.executeUpdate();
            System.out.println("Order updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_ORDER)) {

            ps.setInt(1, orderId);
            ps.executeUpdate();
            System.out.println("Order deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
