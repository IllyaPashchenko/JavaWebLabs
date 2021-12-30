package com.example.javalab2.controller;

import com.example.javalab2.controller.commands.implementations.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/manager/orders")
public class ManagerOrdersController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            new ShowOrdersCommand().execute(req, resp);
            new ShowMastersCommand().execute(req, resp);
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/");
        }
        req.getRequestDispatcher("/manager-orders.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new UpdateOrderForManagerCommand().execute(req, resp);
        resp.sendRedirect(req.getContextPath() + "/manager/orders");
    }
}
