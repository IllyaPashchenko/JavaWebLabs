package com.example.javalab2.controller;

import com.example.javalab2.controller.commands.implementations.LoginCommand;
import com.example.javalab2.controller.commands.implementations.LogoutCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new LogoutCommand().execute(req, resp);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
