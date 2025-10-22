package com.tap.dao;

import com.tap.model.Menu;
import java.util.List;

public interface MenuDao {
    void addMenu(Menu menu);
    List<Menu> getMenuByRestaurantId(int restaurantId);
    Menu getMenuById(int menuId);
    void updateMenu(Menu menu);
    void deleteMenu(int menuId);
}
