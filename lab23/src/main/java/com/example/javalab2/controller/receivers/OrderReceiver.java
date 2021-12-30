package com.example.javalab2.controller.receivers;

import com.example.javalab2.dao.*;
import com.example.javalab2.domain.Order;

import java.util.List;

public class OrderReceiver {
    private static OrderReceiver orderReceiver;
    private final OrderDAO orderDAO;
    private final UserDAO userDAO;

    private OrderReceiver() {
        DaoFactory factory = DaoFactory.getInstance();
        this.userDAO = (UserDAO) factory.getDAO(DaoType.USER_DAO);
        this.orderDAO = (OrderDAO) factory.getDAO(DaoType.ORDER_DAO);
    }

    public static OrderReceiver getInstance() {
        if (orderReceiver == null) {
            orderReceiver = new OrderReceiver();
        }
        return orderReceiver;
    }

    public List<Order> getAllOrdersByUserID(Integer userId) {
        return orderDAO.findAllByUserId(userId);
    }


    public List<Order> getAllOrders() {
        return orderDAO.findAll();
    }

    public void updateOrderForManager(long orderId, long masterId, long cStatusId, long pStatusId, int cost) {
        orderDAO.updateForManager(orderId, masterId, cStatusId, pStatusId, cost);
    }

    public void updateOrderWithMaster(Integer orderId, Integer masterId) {
        orderDAO.updateWithMaster(orderId, masterId);
    }

    public void updateOrderWithCompletionStatusId(Integer orderId, Integer cStatusId) {
        orderDAO.updateWithCStatus(orderId, cStatusId);
    }

    public void updateOrderWithFeedback(Integer orderId, String feedback) {
        orderDAO.updateWithFeedback(orderId, feedback);
    }

    public void createOrder(Order order) {
        orderDAO.create(order);
    }


}
