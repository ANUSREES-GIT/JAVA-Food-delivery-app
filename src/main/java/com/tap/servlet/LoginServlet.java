package com.tap.servlet;

import com.tap.connection.DbConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.*;
import java.util.*;

public class LoginServlet extends HttpServlet {
    private Map<String, Integer> attempts = new HashMap<>();

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Integer failedAttempts = attempts.get(email);
        if (failedAttempts == null) {
            failedAttempts = 0; // treat as first attempt
        }

        if (failedAttempts >= 3) {
            out.println("<h2 style='color:red;'>Your account is blocked after 3 failed attempts.</h2>");
            return;
        }

        try {
            Connection conn = DbConnection.getConnection(); // your DB helper
            PreparedStatement stmt = conn.prepareStatement("SELECT password FROM user WHERE email = ?");
            stmt.setString(1, email);


            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");

                if (storedPassword.equals(password)) {
                    attempts.put(email, 0); // reset attempts
                    response.sendRedirect(request.getContextPath() + "/html/home.html");
                }
                else {
                    failedAttempts++;
                    attempts.put(email, failedAttempts);
                    out.println("<h3>Incorrect password. Attempt " + failedAttempts + " of 3.</h3>");
                }
            } else {
                out.println("<h3>User not found</h3>");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
