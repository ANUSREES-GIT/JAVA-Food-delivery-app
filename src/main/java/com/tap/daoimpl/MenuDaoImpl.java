package com.tap.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.tap.dao.MenuDao;
import com.tap.model.Menu;
import com.tap.connection.DbConnection;

public class MenuDaoImpl implements MenuDao {
    private static final String INSERT_MENU = "INSERT INTO menu (itemName, price, restaurantId) VALUES (?, ?, ?)";
    private static final String GET_MENU_BY_RESTAURANT_ID = "SELECT * FROM menu WHERE restaurantId = ?";
    private static final String GET_MENU_BY_ID = "SELECT * FROM menu WHERE menuId = ?";
    private static final String UPDATE_MENU = "UPDATE Menu SET itemName = ?, price = ?, restaurantId = ? WHERE menuId = ?";
    private static final String DELETE_MENU = "DELETE FROM menu WHERE menuId = ?";

    @Override
    public void addMenu(Menu menu) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MENU)) {

            preparedStatement.setString(1, menu.getItemName());
            preparedStatement.setDouble(2, menu.getPrice());
            preparedStatement.setInt(3, menu.getRestaurantId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Menu> getMenuByRestaurantId(int restaurantId) {
        List<Menu> menuList = new ArrayList<>();
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_MENU_BY_RESTAURANT_ID)) {

            preparedStatement.setInt(1, restaurantId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Menu menu = new Menu(
                        resultSet.getInt("menuId"),
                        resultSet.getString("itemName"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("restaurantId")
                );
                menuList.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

    @Override
    public Menu getMenuById(int menuId) {
        Menu menu = null;
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_MENU_BY_ID)) {

            preparedStatement.setInt(1, menuId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                menu = new Menu(
                        resultSet.getInt("menuId"),
                        resultSet.getString("itemName"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("restaurantId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    @Override
    public void updateMenu(Menu menu) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MENU)) {

            preparedStatement.setString(1, menu.getItemName());
            preparedStatement.setDouble(2, menu.getPrice());
            preparedStatement.setInt(3, menu.getRestaurantId());
            preparedStatement.setInt(4, menu.getMenuId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMenu(int menuId) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MENU)) {

            preparedStatement.setInt(1, menuId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
