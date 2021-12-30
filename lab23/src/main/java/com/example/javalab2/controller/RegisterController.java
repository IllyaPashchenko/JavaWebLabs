package com.example.javalab2.controller;

import com.example.javalab2.controller.commands.implementations.LoginCommand;
import com.example.javalab2.controller.commands.implementations.RegisterCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            new RegisterCommand().execute(req, resp);
            resp.sendRedirect(req.getContextPath() + "/");
        } catch (RuntimeException e) {
            resp.sendRedirect(req.getContextPath() + "/register");
        }
    }
}
