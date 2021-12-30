package com.example.javalab2.controller.commands.implementations;

import com.example.javalab2.controller.commands.CommandSimple;
import com.example.javalab2.controller.receivers.OrderReceiver;
import com.example.javalab2.domain.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowOrdersCommand implements CommandSimple {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        List<Order> orders = OrderReceiver.getInstance().getAllOrders();
        request.getSession().setAttribute("orders", orders);
    }
}