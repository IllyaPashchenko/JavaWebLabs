package com.example.javalab2.dao;

import com.example.javalab2.domain.RoleEntity;
import com.example.javalab2.domain.enums.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoleDAO extends AbstractDAO<Integer, RoleEntity>{

    private static RoleDAO roleDAO;
    private static final String SELECT_ROLE_BY_ID = "SELECT * FROM roles WHERE id = ?";
    private static final String SELECT_ROLE_BY_NAME = "SELECT * FROM roles WHERE naming = ?";

    private RoleDAO() {

    }

    public static RoleDAO getInstance() {
        if (roleDAO == null) {
            roleDAO = new RoleDAO();
        }
        return roleDAO;
    }


    @Override
    public List<RoleEntity> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public RoleEntity findById(Integer id) {
        try {
            RoleEntity role = null;
            Connection connection = ConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ROLE_BY_ID);

            statement.setString(1, id.toString());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString(2);
                role = new RoleEntity(id, Role.valueOf(name));
            }
            connection.close();
            return role;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public RoleEntity findByName(String name) {
        try {
            RoleEntity role = null;
            Connection connection = ConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ROLE_BY_NAME);

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String id = resultSet.getString(1);
                role = new RoleEntity(Integer.parseInt(id), Role.valueOf(name));
            }
            connection.close();
            return role;
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
    public RoleEntity create(RoleEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RoleEntity update(RoleEntity entity) {
        throw new UnsupportedOperationException();
    }
}
