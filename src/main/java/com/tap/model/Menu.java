package com.tap.model;

public class Menu {
    private int menuId;
    private String itemName;
    private double price;
    private int restaurantId;
    
    
    

    public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Menu(int menuId, String itemName, double price, int restaurantId) {
        this.menuId = menuId;
        this.itemName = itemName;
        this.price = price;
        this.restaurantId = restaurantId;
    }

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", itemName=" + itemName + ", price=" + price + ", restaurantId="
				+ restaurantId + "]";
	}

    // Getters and Setters
   
	
	
}
