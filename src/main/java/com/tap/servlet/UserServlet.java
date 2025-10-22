package com.tap.servlet;

import com.tap.dao.UserDao;
import com.tap.daoimpl.UserDaoImpl;
import com.tap.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@WebServlet("/registerUser")
public class UserServlet extends HttpServlet {
    private UserDao userDao = new UserDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        long phone = Long.parseLong(req.getParameter("phone"));
        String address = req.getParameter("address");
        String role = req.getParameter("role");

        Timestamp currentTime = Timestamp.valueOf(LocalDateTime.now());

        User user = new User();
        user.setUsername(userName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setAddress(address);
        user.setRole(role);
        user.setCreatedLoginDate(currentTime);
        user.setLastLoginDate(currentTime);
        

        boolean result = userDao.addUser(user);

        res.setContentType("text/html");
        if (result) {
            res.sendRedirect("html/registrationSuccess.html");  // redirect to success page
        } else {
            res.sendRedirect("html/registrationFailed.html");   // redirect to failure page
        }

    }
}
