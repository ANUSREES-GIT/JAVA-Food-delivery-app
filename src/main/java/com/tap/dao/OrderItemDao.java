package com.tap.dao;

import com.tap.model.OrderItem;
import java.util.List;

public interface OrderItemDao {
    void addOrderItem(OrderItem orderItem);
    List<OrderItem> getOrderItemsByOrderId(int orderId);
    void updateOrderItem(OrderItem orderItem);
    void deleteOrderItem(int orderItemId);
}
