package com.example.javalab2.controller;

import com.example.javalab2.controller.commands.implementations.ShowOrdersCommand;
import com.example.javalab2.controller.commands.implementations.UpdateOrderForMasterCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/master/orders")
public class MasterOrdersController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            new ShowOrdersCommand().execute(req, resp);
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/");
        }
        req.getRequestDispatcher("/master-orders.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new UpdateOrderForMasterCommand().execute(req, resp);
        resp.sendRedirect(req.getContextPath() + "/master/orders");
    }
}
