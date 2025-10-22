//package com.tap.daoimpl;
//
//import java.sql.*;
//import com.tap.dao.UserDao;
//import com.tap.model.User;
//import com.tap.connection.DbConnection;
//
//public class UserDaoImpl implements UserDao {
//    private static final String INSERT_USER = "INSERT INTO `user` (`username`, `email`, `password`, `phone`, `address`, `role`) VALUES (?, ?, ?, ?, ?, ?)";
//    private static final String GET_USER_BY_ID = "SELECT * FROM `user` WHERE `userid` = ?";
//    private static final String GET_USER_BY_EMAIL = "SELECT * FROM `user` WHERE `email` = ?";
//    private static final String UPDATE_USER = "UPDATE `user` SET `username` = ?, `password` = ?, `phone` = ?, `address` = ?, `role` = ? WHERE `userId` = ?";
//    private static final String DELETE_USER = "DELETE FROM `user` WHERE `userId` = ?";
//    private static final String AUTHENTICATE_USER = "SELECT * FROM `user` WHERE `email` = ? AND `password` = ?";
//
//    @Override
//    public boolean addUser(User user) {
//        try (Connection connection = DbConnection.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
//
//            preparedStatement.setString(1, user.getUsername());
//            preparedStatement.setString(2, user.getEmail());
//            preparedStatement.setString(3, user.getPassword());
//            preparedStatement.setLong(4, user.getPhone());
//            preparedStatement.setString(5, user.getAddress());
//            preparedStatement.setString(6, user.getRole());
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//		return false;
//    }
//
//    @Override
//    public User getUserById(int userId) {
//        User user = null;
//        try (Connection connection = DbConnection.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)) {
//
//            preparedStatement.setInt(1, userId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                user = new User(
//                        resultSet.getInt("userId"),
//                        resultSet.getString("username"),
//                        resultSet.getString("email"),
//                        resultSet.getString("password"),
//                        resultSet.getLong("phone"),
//                        resultSet.getString("address"),
//                        resultSet.getString("role")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return user;
//    }
//
//    @Override
//    public User getUserByEmail(String email) {
//        User user = null;
//        try (Connection connection = DbConnection.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL)) {
//
//            preparedStatement.setString(1, email);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                user = new User(
//                        resultSet.getInt("userId"),
//                        resultSet.getString("name"),
//                        resultSet.getString("email"),
//                        resultSet.getString("password"),
//                        resultSet.getLong("phone"),
//                        resultSet.getString("address"),
//                        resultSet.getString("role")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return user;
//    }
//
//    @Override
//    public void updateUser(User user) {
//        try (Connection connection = DbConnection.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
//
//            preparedStatement.setString(1, user.getUsername());
//            preparedStatement.setString(2, user.getPassword());
//            preparedStatement.setLong(3, user.getPhone());
//            preparedStatement.setString(4, user.getAddress());
//            preparedStatement.setString(5, user.getRole());
//            preparedStatement.setInt(6, user.getUserId());
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void deleteUser(int userId) {
//        try (Connection connection = DbConnection.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
//
//            preparedStatement.setInt(1, userId);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public boolean authenticateUser(String email, String password) {
//        try (Connection connection = DbConnection.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(AUTHENTICATE_USER)) {
//
//            preparedStatement.setString(1, email);
//            preparedStatement.setString(2, password);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            return resultSet.next();  // Returns true if a matching user is found
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//}



package com.tap.daoimpl;

import java.sql.*;
import com.tap.dao.UserDao;
import com.tap.model.User;
import com.tap.connection.DbConnection;

import java.util.Date;

public class UserDaoImpl implements UserDao {

    private static final String INSERT_USER = "INSERT INTO `user` (`username`, `email`, `password`, `phone`, `address`, `role`, `createdLoginDate`, `lastLoginDate`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_USER_BY_ID = "SELECT * FROM `user` WHERE `userId` = ?";
    private static final String GET_USER_BY_EMAIL = "SELECT * FROM `user` WHERE `email` = ?";
    private static final String UPDATE_USER = "UPDATE `user` SET `username` = ?, `email` = ?, `password` = ?, `phone` = ?, `address` = ?, `role` = ?, `lastLoginDate` = ? WHERE `userId` = ?";
    private static final String DELETE_USER = "DELETE FROM `user` WHERE `userId` = ?";
    private static final String AUTHENTICATE_USER = "SELECT * FROM `user` WHERE `email` = ? AND `password` = ?";

    @Override
    public boolean addUser(User user) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_USER)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setLong(4, user.getPhone());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getRole());
            ps.setTimestamp(7, new Timestamp(user.getCreatedLoginDate().getTime()));
            ps.setTimestamp(8, new Timestamp(user.getLastLoginDate().getTime()));

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserById(int userId) {
        User user = null;
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_USER_BY_ID)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = mapResultSetToUser(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_USER_BY_EMAIL)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = mapResultSetToUser(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_USER)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setLong(4, user.getPhone());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getRole());
            ps.setTimestamp(7, new Timestamp(user.getLastLoginDate().getTime()));
            ps.setInt(8, user.getUserId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int userId) {
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_USER)) {

            ps.setInt(1, userId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean authenticateUser(String email, String password) {
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(AUTHENTICATE_USER)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            return rs.next(); // true if a record is found

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("userId"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("email"),
                rs.getLong("phone"),
                rs.getString("address"),
                rs.getString("role"),
                rs.getTimestamp("createdLoginDate"),
                rs.getTimestamp("lastLoginDate")
        );
    }
}

