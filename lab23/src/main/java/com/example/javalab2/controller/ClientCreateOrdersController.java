package com.example.javalab2.controller;

import com.example.javalab2.controller.commands.implementations.CreateOrderCommand;
import com.example.javalab2.controller.commands.implementations.ShowOrdersCommand;
import com.example.javalab2.controller.commands.implementations.UpdateOrderForClientCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/client/orders/create")
public class ClientCreateOrdersController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new CreateOrderCommand().execute(req, resp);
        resp.sendRedirect(req.getContextPath() + "/client/orders");
    }
}
