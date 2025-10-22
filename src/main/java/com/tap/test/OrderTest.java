package com.tap.test;

import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.tap.dao.OrderDao;
import com.tap.daoimpl.OrderDaoImpl;
import com.tap.model.Order;

public class OrderTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OrderDao orderDao = new OrderDaoImpl();

        while (true) {
            System.out.println("\n1. Add Order");
            System.out.println("2. Get Order by ID");
            System.out.println("3. Update Order");
            System.out.println("4. Delete Order");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter user ID: ");
                        int userId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter restaurant ID: ");
                        int restaurantId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter order date (yyyy-MM-dd): ");
                        String dateStr = sc.nextLine();
                        Date orderDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);

                        System.out.print("Enter total amount: ");
                        double totalAmount = sc.nextDouble();
                        sc.nextLine();

                        System.out.print("Enter status (Pending/Completed/Cancelled): ");
                        String status = sc.nextLine();

                        System.out.print("Enter payment mode (Cash/Card/UPI/etc): ");
                        String paymentMode = sc.nextLine();

                        Order newOrder = new Order(0, userId, restaurantId, orderDate, totalAmount, status, paymentMode);
                        orderDao.addOrder(newOrder);
                        System.out.println("Order added successfully.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter order ID: ");
                    int id = sc.nextInt();
                    Order order = orderDao.getOrderById(id);
                    if (order != null) {
                        System.out.println(order);
                    } else {
                        System.out.println("Order not found.");
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Enter order ID to update: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter new user ID: ");
                        int updateUserId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter new restaurant ID: ");
                        int updateRestaurantId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter new order date (yyyy-MM-dd): ");
                        String updateDateStr = sc.nextLine();
                        Date updateOrderDate = new SimpleDateFormat("yyyy-MM-dd").parse(updateDateStr);

                        System.out.print("Enter new total amount: ");
                        double updateAmount = sc.nextDouble();
                        sc.nextLine();

                        System.out.print("Enter new status (Pending/Completed/Cancelled): ");
                        String updateStatus = sc.nextLine();

                        System.out.print("Enter new payment mode: ");
                        String updatePaymentMode = sc.nextLine();

                        Order updateOrder = new Order(updateId, updateUserId, updateRestaurantId, updateOrderDate, updateAmount, updateStatus, updatePaymentMode);
                        orderDao.updateOrder(updateOrder);
                        System.out.println("Order updated successfully.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Enter order ID to delete: ");
                    int deleteId = sc.nextInt();
                    orderDao.deleteOrder(deleteId);
                    System.out.println("Order deleted successfully.");
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
