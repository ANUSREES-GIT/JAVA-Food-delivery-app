package com.tap.dao;

import com.tap.model.User;

public interface UserDao {
    boolean addUser(User user);
    User getUserById(int userId);
    User getUserByEmail(String email);
    void updateUser(User user);
    void deleteUser(int userId);
    boolean authenticateUser(String email, String password);
}
