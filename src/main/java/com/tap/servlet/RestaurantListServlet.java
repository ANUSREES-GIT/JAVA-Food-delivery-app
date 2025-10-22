package com.tap.servlet;

import com.tap.dao.RestaurantDao;
import com.tap.daoimpl.RestaurantDaoImpl;
import com.tap.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class RestaurantListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        RestaurantDao restaurantDao = new RestaurantDaoImpl();
        List<Restaurant> restaurants = restaurantDao.getAllRestaurants(); // You’ll write this method next

        request.setAttribute("restaurants", restaurants);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
