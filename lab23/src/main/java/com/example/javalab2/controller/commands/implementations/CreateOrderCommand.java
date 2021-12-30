package com.example.javalab2.controller.commands.implementations;

import com.example.javalab2.controller.commands.CommandSimple;
import com.example.javalab2.controller.receivers.OrderReceiver;
import com.example.javalab2.domain.Order;
import com.example.javalab2.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateOrderCommand implements CommandSimple {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String description = request.getParameter("description");
        User user = (User) request.getSession().getAttribute("user");
        Order order = new Order();

        order.setClient(user);
        order.setDescription(description);

        OrderReceiver.getInstance().createOrder(order);
        new ShowOrdersCommand().execute(request, response);
    }
}
