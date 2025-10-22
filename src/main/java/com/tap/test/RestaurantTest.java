package com.tap.test;

import com.tap.daoimpl.RestaurantDaoImpl;
import com.tap.model.Restaurant;

import java.sql.Time;
import java.util.Scanner;

public class RestaurantTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RestaurantDaoImpl dao = new RestaurantDaoImpl();

        System.out.println("1. Add Restaurant\n2. Get Restaurant by ID\n3. Update Restaurant\n4. Delete Restaurant");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter restaurant name: ");
                String name = scanner.nextLine();
                System.out.print("Enter cuisine type: ");
                String cuisine = scanner.nextLine();
                System.out.print("Enter delivery time (HH:MM:SS): ");
                String delivery = scanner.nextLine();
                System.out.print("Enter address: ");
                String address = scanner.nextLine();
                System.out.print("Enter admin user ID: ");
                int adminId = scanner.nextInt();
                System.out.print("Enter rating (0.0 to 9.9): ");
                double rating = scanner.nextDouble();
                System.out.print("Is the restaurant active? (true/false): ");
                boolean isActive = scanner.nextBoolean();
                scanner.nextLine(); // consume newline
                System.out.print("Enter image path: ");
                String imagePath = scanner.nextLine();

                // Convert delivery time to Time
                Time deliveryTime = Time.valueOf(delivery);

                Restaurant newRestaurant = new Restaurant();
                newRestaurant.setName(name);
                newRestaurant.setCuisineType(cuisine);
                newRestaurant.setDeliveryTime(deliveryTime);
                newRestaurant.setAddress(address);
                newRestaurant.setAdminUserId(adminId);
                newRestaurant.setRating(rating);
                newRestaurant.setActive(isActive);
                newRestaurant.setImagePath(imagePath);

                dao.addRestaurant(newRestaurant);
                break;

            case 2:
                System.out.print("Enter restaurant ID: ");
                int id = scanner.nextInt();
                Restaurant fetched = dao.getRestaurantById(id);
                if (fetched != null) {
                    System.out.println("Name: " + fetched.getName());
                    System.out.println("Cuisine: " + fetched.getCuisineType());
                    System.out.println("Delivery Time: " + fetched.getDeliveryTime());
                    System.out.println("Address: " + fetched.getAddress());
                    System.out.println("Admin ID: " + fetched.getAdminUserId());
                    System.out.println("Rating: " + fetched.getRating());
                    System.out.println("Active: " + fetched.isActive());
                    System.out.println("Image Path: " + fetched.getImagePath());
                } else {
                    System.out.println("Restaurant not found.");
                }
                break;

            case 3:
                System.out.print("Enter restaurant ID to update: ");
                int updateId = scanner.nextInt();
                scanner.nextLine();
                Restaurant toUpdate = dao.getRestaurantById(updateId);
                if (toUpdate != null) {
                    System.out.print("New name: ");
                    toUpdate.setName(scanner.nextLine());
                    System.out.print("New cuisine type: ");
                    toUpdate.setCuisineType(scanner.nextLine());
                    System.out.print("New delivery time (HH:MM:SS): ");
                    String newDelivery = scanner.nextLine();
                    toUpdate.setDeliveryTime(Time.valueOf(newDelivery));
                    System.out.print("New address: ");
                    toUpdate.setAddress(scanner.nextLine());
                    System.out.print("New admin user ID: ");
                    toUpdate.setAdminUserId(scanner.nextInt());
                    System.out.print("New rating: ");
                    toUpdate.setRating(scanner.nextDouble());
                    System.out.print("Is the restaurant active? (true/false): ");
                    toUpdate.setActive(scanner.nextBoolean());
                    scanner.nextLine(); // consume newline
                    System.out.print("New image path: ");
                    toUpdate.setImagePath(scanner.nextLine());

                    dao.updateRestaurant(toUpdate);
                } else {
                    System.out.println("Restaurant not found.");
                }
                break;

            case 4:
                System.out.print("Enter restaurant ID to delete: ");
                int deleteId = scanner.nextInt();
                dao.deleteRestaurant(deleteId);
                break;

            default:
                System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}
