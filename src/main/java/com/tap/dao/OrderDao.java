package com.tap.dao;

import com.tap.model.Order;

public interface OrderDao {
    void addOrder(Order order);
    Order getOrderById(int orderId);
    void updateOrder(Order order);
    void deleteOrder(int orderId);
}
