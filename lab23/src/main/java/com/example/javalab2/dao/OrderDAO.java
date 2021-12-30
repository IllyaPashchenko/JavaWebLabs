package com.example.javalab2.dao;

import com.example.javalab2.domain.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends AbstractDAO<Integer, Order>{

    private static OrderDAO orderDAO;
    private static final String SELECT_ALL_ORDERS = "SELECT * FROM orders";
    private static final String SELECT_ORDER_BY_ID = "SELECT * FROM orders WHERE id = ?";
    private static final String DELETE_ORDER_BY_ID = "DELETE FROM orders WHERE id = ?";
    private static final String INSERT_ORDER = "INSERT INTO orders (user_id, description) VALUES(?, ?)";
    private static final String UPDATE_ORDER = "UPDATE orders SET master_id = ?, status_id = ?, user_id = ?, paid_status_id = ?, description = ?, feedback = ?, money = ? WHERE id = ?";
    private static final String UPDATE_ORDER_FOR_MANAGER = "UPDATE orders SET master_id = ?, status_id = ?, paid_status_id = ?, money = ? WHERE id = ?";
    private static final String UPDATE_ORDER_WITH_MASTER = "UPDATE orders SET master_id = ? WHERE id = ?";
    private static final String UPDATE_ORDER_WITH_C_STATUS = "UPDATE orders SET status_id = ? WHERE id = ?";
    private static final String UPDATE_ORDER_WITH_FEEDBACK = "UPDATE orders SET feedback = ? WHERE id = ?";
    private static final String SELECT_ALL_ORDERS_BY_USER_ID = "SELECT * FROM orders WHERE user_id = ?";

    public OrderDAO() {
    }

    public static OrderDAO getInstance() {
        if (orderDAO == null) {
            orderDAO = new OrderDAO();
        }
        return orderDAO;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_ORDERS);
            while (resultSet.next()) {
                orders.add(mapping(resultSet));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }

    public List<Order> findAllByUserId(Integer userId) {
        List<Order> orders = new ArrayList<>();
        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ORDERS_BY_USER_ID);
            statement.setString(1, userId.toString());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(mapping(resultSet));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }

    @Override
    public Order findById(Integer id) {
        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ORDER_BY_ID);
            statement.setString(1, id.toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapping(resultSet);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        int rowsAffected = 0;
        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_ORDER_BY_ID);
            statement.setString(1, id.toString());
            rowsAffected = statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rowsAffected == 1;
    }

    @Override
    public Order create(Order entity) {
        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);

            statement.setLong(1, entity.getClient().getId());
            statement.setString(2, entity.getDescription());

            int rowAffected = statement.executeUpdate();
            ResultSet resultSet;
            Integer generatedId = null;
            if(rowAffected == 1) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    generatedId = (Integer) resultSet.getObject(1);
                }
                if (generatedId != null) {
                    entity.setId(generatedId);
                    return entity;
                }
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Order update(Order entity) {
        Order order = null;
        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER);

            statement.setLong(1, entity.getMaster().getId());
            statement.setLong(2, entity.getCompletionStatus().getId());
            statement.setLong(3, entity.getClient().getId());
            statement.setLong(4, entity.getPaymentStatus().getId());
            statement.setString(5, entity.getDescription());
            statement.setString(6, entity.getFeedback());
            statement.setInt(7, entity.getCost());

            int rowAffected = statement.executeUpdate();
            if (rowAffected == 1) {
                order = entity;
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return order;
    }

    public void updateForManager(long orderId, long masterId, long cStatusId, long pStatusId, int cost) {
        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER_FOR_MANAGER);

            statement.setLong(1, masterId);
            statement.setLong(2, cStatusId);
            statement.setLong(3, pStatusId);
            statement.setLong(4, cost);
            statement.setLong(5, orderId);

            statement.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateWithMaster(long orderId, long masterId) {
        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER_WITH_MASTER);

            statement.setLong(1, masterId);
            statement.setLong(2, orderId);

            statement.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateWithCStatus(long orderId, long statusId) {
        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER_WITH_C_STATUS);

            statement.setLong(1, statusId);
            statement.setLong(2, orderId);

            statement.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateWithFeedback(long orderId, String feedback) {
        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER_WITH_FEEDBACK);

            statement.setString(1, feedback);
            statement.setLong(2, orderId);

            statement.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    private Order mapping(ResultSet set) {
        try {
            UserDAO uDAO = UserDAO.getInstance();
            StatusDAO sDAO = StatusDAO.getInstance();
            return new Order (
                    set.getInt(1), // id
                    uDAO.findById(set.getInt(2)),
                    uDAO.findById(set.getInt(4)),
                    sDAO.findById(set.getInt(3)),
                    sDAO.findById(set.getInt(5)),
                    set.getString(6),
                    set.getString(7),
                    set.getInt(8)
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
