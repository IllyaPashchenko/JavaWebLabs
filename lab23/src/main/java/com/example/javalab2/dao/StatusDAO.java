package com.example.javalab2.dao;

import com.example.javalab2.domain.StatusEntity;
import com.example.javalab2.domain.enums.Role;
import com.example.javalab2.domain.enums.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StatusDAO extends AbstractDAO<Integer, StatusEntity>{

    private static StatusDAO statusDAO;
    private static final String SELECT_STATUS_BY_ID = "SELECT * FROM statuses WHERE id = ?";
    private static final String SELECT_STATUS_BY_NAME = "SELECT * FROM statuses WHERE meaning = ?";

    private StatusDAO() {}

    public static StatusDAO getInstance() {
        if (statusDAO == null) {
            statusDAO = new StatusDAO();
        }
        return statusDAO;
    }


    @Override
    public List<StatusEntity> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public StatusEntity findById(Integer id) {
        try {
            StatusEntity status = null;
            Connection connection = ConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_STATUS_BY_ID);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString(2);
                status = new StatusEntity(id, Status.valueOf(name));
            }
            connection.close();
            return status;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public StatusEntity findByName(String name) {
        try {
            StatusEntity status = null;
            Connection connection = ConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_STATUS_BY_NAME);

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String id = resultSet.getString(1);
                status = new StatusEntity(Integer.parseInt(id), Status.valueOf(name));
            }
            connection.close();
            return status;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StatusEntity create(StatusEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StatusEntity update(StatusEntity entity) {
        throw new UnsupportedOperationException();
    }
}
