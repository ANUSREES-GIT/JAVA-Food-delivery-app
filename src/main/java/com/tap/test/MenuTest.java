package com.tap.test;

import java.util.List;
import java.util.Scanner;
import com.tap.dao.MenuDao;
import com.tap.daoimpl.MenuDaoImpl;
import com.tap.model.Menu;

public class MenuTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuDao menuDao = new MenuDaoImpl();

        while (true) {
            System.out.println("\n1. Add Menu Item");
            System.out.println("2. Get Menu Item by ID");
            System.out.println("3. Get Menu by Restaurant ID");
            System.out.println("4. Update Menu Item");
            System.out.println("5. Delete Menu Item");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    Menu newMenu = new Menu();
                    System.out.print("Enter item name: ");
                    newMenu.setItemName(scanner.nextLine());

                    System.out.print("Enter price: ");
                    newMenu.setPrice(Double.parseDouble(scanner.nextLine()));

                    System.out.print("Enter restaurant ID: ");
                    newMenu.setRestaurantId(Integer.parseInt(scanner.nextLine()));

                    menuDao.addMenu(newMenu);
                    System.out.println("Menu item added successfully.");
                    break;

                case 2:
                    System.out.print("Enter menu ID: ");
                    int menuId = Integer.parseInt(scanner.nextLine());
                    Menu menu = menuDao.getMenuById(menuId);
                    if (menu != null) {
                        System.out.println(menu);
                    } else {
                        System.out.println("Menu not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter restaurant ID: ");
                    int restId = Integer.parseInt(scanner.nextLine());
                    List<Menu> menus = menuDao.getMenuByRestaurantId(restId);
                    if (!menus.isEmpty()) {
                        for (Menu m : menus) {
                            System.out.println(m);
                        }
                    } else {
                        System.out.println("No menu items found for this restaurant.");
                    }
                    break;

                case 4:
                    System.out.print("Enter menu ID to update: ");
                    int updateId = Integer.parseInt(scanner.nextLine());

                    Menu updateMenu = menuDao.getMenuById(updateId);
                    if (updateMenu != null) {
                        System.out.print("Enter new item name: ");
                        updateMenu.setItemName(scanner.nextLine());

                        System.out.print("Enter new price: ");
                        updateMenu.setPrice(Double.parseDouble(scanner.nextLine()));

                        System.out.print("Enter restaurant ID: ");
                        updateMenu.setRestaurantId(Integer.parseInt(scanner.nextLine()));

                        menuDao.updateMenu(updateMenu);
                        System.out.println("Menu item updated.");
                    } else {
                        System.out.println("Menu item not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter menu ID to delete: ");
                    int deleteId = Integer.parseInt(scanner.nextLine());
                    menuDao.deleteMenu(deleteId);
                    System.out.println("Menu item deleted.");
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
