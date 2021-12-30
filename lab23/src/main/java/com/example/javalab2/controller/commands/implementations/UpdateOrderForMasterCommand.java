package com.example.javalab2.controller.commands.implementations;

import com.example.javalab2.controller.commands.CommandSimple;
import com.example.javalab2.controller.receivers.OrderReceiver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateOrderForMasterCommand implements CommandSimple {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        if (action.equals("assign")) {
            System.out.println("Master " + request.getParameter("masterId"));
            int masterId = Integer.parseInt(request.getParameter("masterId"));
            OrderReceiver.getInstance().updateOrderWithMaster(orderId, masterId);
        } else {
            System.out.println("Status " + request.getParameter("completionStatus"));
            int completionStatusId = Integer.parseInt(request.getParameter("completionStatus"));
            OrderReceiver.getInstance().updateOrderWithCompletionStatusId(orderId, completionStatusId);
        }
        new ShowOrdersCommand().execute(request, response);
    }
}
