package com.example.javalab2.controller;

import com.example.javalab2.controller.commands.implementations.ShowUsersCommand;
import com.example.javalab2.controller.commands.implementations.UpdateWalletCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/manager/users")
public class ManagerUsersController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new ShowUsersCommand().execute(req, resp);
        req.getRequestDispatcher("/manager-users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new UpdateWalletCommand().execute(req, resp);
        resp.sendRedirect(req.getContextPath() + "/manager/users");
    }
}
