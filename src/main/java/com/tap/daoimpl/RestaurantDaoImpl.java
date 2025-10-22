package com.tap.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.RestaurantDao;
import com.tap.model.Restaurant;
import com.tap.connection.DbConnection;

public class RestaurantDaoImpl implements RestaurantDao {
    private static final String INSERT_RESTAURANT = "INSERT INTO restaurant (name, cuisineType, deliveryTime, address, adminUserId, rating, isActive, imagePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_RESTAURANT_BY_ID = "SELECT * FROM restaurant WHERE restaurantId = ?";
    private static final String UPDATE_RESTAURANT = "UPDATE restaurant SET name = ?, cuisineType = ?, deliveryTime = ?, address = ?, adminUserId = ?, rating = ?, isActive = ?, imagePath = ? WHERE restaurantId = ?";
    private static final String DELETE_RESTAURANT = "DELETE FROM restaurant WHERE restaurantId = ?";

    @Override
    public void addRestaurant(Restaurant restaurant) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RESTAURANT)) {

            preparedStatement.setString(1, restaurant.getName());
            preparedStatement.setString(2, restaurant.getCuisineType());
            preparedStatement.setTime(3, restaurant.getDeliveryTime());
            preparedStatement.setString(4, restaurant.getAddress());
            preparedStatement.setInt(5, restaurant.getAdminUserId());
            preparedStatement.setDouble(6, restaurant.getRating());
            preparedStatement.setBoolean(7, restaurant.isActive());
            preparedStatement.setString(8, restaurant.getImagePath());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId) {
        Restaurant restaurant = null;
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_RESTAURANT_BY_ID)) {

            preparedStatement.setInt(1, restaurantId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                restaurant = new Restaurant();
                restaurant.setRestaurantId(resultSet.getInt("restaurantId"));
                restaurant.setName(resultSet.getString("name"));
                restaurant.setCuisineType(resultSet.getString("cuisineType"));
                restaurant.setDeliveryTime(resultSet.getTime("deliveryTime"));
                restaurant.setAddress(resultSet.getString("address"));
                restaurant.setAdminUserId(resultSet.getInt("adminUserId"));
                restaurant.setRating(resultSet.getDouble("rating"));
                restaurant.setActive(resultSet.getBoolean("isActive"));
                restaurant.setImagePath(resultSet.getString("imagePath"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurant;
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESTAURANT)) {

            preparedStatement.setString(1, restaurant.getName());
            preparedStatement.setString(2, restaurant.getCuisineType());
            preparedStatement.setTime(3, restaurant.getDeliveryTime());
            preparedStatement.setString(4, restaurant.getAddress());
            preparedStatement.setInt(5, restaurant.getAdminUserId());
            preparedStatement.setDouble(6, restaurant.getRating());
            preparedStatement.setBoolean(7, restaurant.isActive());
            preparedStatement.setString(8, restaurant.getImagePath());
            preparedStatement.setInt(9, restaurant.getRestaurantId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurantList = new ArrayList<>();

        String query = "SELECT * FROM restaurant";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Restaurant r = new Restaurant();
                r.setRestaurantId(rs.getInt("restaurantId"));
                r.setName(rs.getString("name"));
                r.setCuisineType(rs.getString("cuisineType"));
                r.setDeliveryTime(rs.getTime("deliveryTime"));
                r.setAddress(rs.getString("address"));
                r.setAdminUserId(rs.getInt("adminUserId"));
                r.setRating(rs.getDouble("rating"));
                r.setActive(rs.getBoolean("isActive"));
                r.setImagePath(rs.getString("imagePath"));

                restaurantList.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurantList;
    }


    @Override
    public void deleteRestaurant(int restaurantId) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESTAURANT)) {

            preparedStatement.setInt(1, restaurantId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
