package com.tap.test;

import com.tap.daoimpl.OrderItemDaoImpl;
import com.tap.model.OrderItem;
import java.util.List;
import java.util.Scanner;

public class OrderItemTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
        
        while (true) {
            System.out.println("\n1. Add Order Item");
            System.out.println("2. Get Order Items by Order ID");
            System.out.println("3. Update Order Item");
            System.out.println("4. Delete Order Item");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    // Add Order Item
                    System.out.print("Enter Order ID: ");
                    int orderId = scanner.nextInt();
                    System.out.print("Enter Menu ID: ");
                    int menuId = scanner.nextInt();
                    System.out.print("Enter Quantity: ");
                    int quantity = scanner.nextInt();
                    
                    // Create OrderItem object
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrderId(orderId);
                    orderItem.setMenuId(menuId);
                    orderItem.setQuantity(quantity);
                    
                    // Add order item to the database
                    orderItemDao.addOrderItem(orderItem);
                    break;
                    
                case 2:
                    // Get Order Items by Order ID
                    System.out.print("Enter Order ID: ");
                    int getOrderId = scanner.nextInt();
                    
                    // Fetch order items from the database
                    List<OrderItem> orderItems = orderItemDao.getOrderItemsByOrderId(getOrderId);
                    
                    // Check if the list is null or empty
                    if (orderItems != null && !orderItems.isEmpty()) {
                        for (OrderItem item : orderItems) {
                            System.out.println("OrderItemId: " + item.getOrderItemId() +
                                    ", OrderId: " + item.getOrderId() +
                                    ", MenuId: " + item.getMenuId() +
                                    ", Quantity: " + item.getQuantity() +
                                    ", TotalAmount: " + item.getTotalAmount());
                        }
                    } else {
                        System.out.println("No order items found for the given Order ID.");
                    }
                    break;
                    
                case 3:
                    // Update Order Item
                    System.out.print("Enter Order Item ID to update: ");
                    int orderItemIdToUpdate = scanner.nextInt();
                    System.out.print("Enter new Menu ID: ");
                    int newMenuId = scanner.nextInt();
                    System.out.print("Enter new Quantity: ");
                    int newQuantity = scanner.nextInt();
                    
                    // Fetch the order item by ID
                    OrderItem orderItemToUpdate = new OrderItem();
                    orderItemToUpdate.setOrderItemId(orderItemIdToUpdate);
                    orderItemToUpdate.setMenuId(newMenuId);
                    orderItemToUpdate.setQuantity(newQuantity);
                    
                    // Update the order item in the database
                    orderItemDao.updateOrderItem(orderItemToUpdate);
                    break;
                    
                case 4:
                    // Delete Order Item
                    System.out.print("Enter Order Item ID to delete: ");
                    int orderItemIdToDelete = scanner.nextInt();
                    
                    // Delete the order item from the database
                    orderItemDao.deleteOrderItem(orderItemIdToDelete);
                    break;
                    
                case 5:
                    // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }
}
