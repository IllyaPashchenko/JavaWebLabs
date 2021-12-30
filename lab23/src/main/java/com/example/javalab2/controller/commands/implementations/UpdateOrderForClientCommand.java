package com.example.javalab2.controller.commands.implementations;

import com.example.javalab2.controller.commands.CommandSimple;
import com.example.javalab2.controller.receivers.OrderReceiver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateOrderForClientCommand implements CommandSimple {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Integer orderId = Integer.parseInt(request.getParameter("orderId"));
        String feedback = request.getParameter("feedback");

        OrderReceiver.getInstance().updateOrderWithFeedback(orderId, feedback);
        new ShowOrdersCommand().execute(request, response);
    }
}
