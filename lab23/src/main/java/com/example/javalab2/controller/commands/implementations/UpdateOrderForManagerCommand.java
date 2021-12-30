package com.example.javalab2.controller.commands.implementations;

import com.example.javalab2.controller.commands.CommandSimple;
import com.example.javalab2.controller.receivers.OrderReceiver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateOrderForManagerCommand implements CommandSimple {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String orderId = request.getParameter("orderId");
        String masterId = request.getParameter("masterId");
        String completionStatusId = request.getParameter("completionStatus");
        String paymentStatusId = request.getParameter("paymentStatus");
        String cost = request.getParameter("cost");

        OrderReceiver.getInstance().updateOrderForManager(Long.parseLong(orderId),
                                                Long.parseLong(masterId),
                                                Long.parseLong(completionStatusId),
                                                Long.parseLong(paymentStatusId),
                                                Integer.parseInt(cost));
        new ShowOrdersCommand().execute(request, response);
    }
}
