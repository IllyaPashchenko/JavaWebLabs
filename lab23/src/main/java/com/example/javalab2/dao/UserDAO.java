package com.example.javalab2.dao;

import com.example.javalab2.domain.RoleEntity;
import com.example.javalab2.domain.User;
import com.example.javalab2.domain.Wallet;
import com.example.javalab2.domain.enums.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDAO extends AbstractDAO<Integer, User>{

    private static UserDAO userDAO;
    private static final String SELECT_ALL_USERS = "SELECT u.*, r.naming, w.money FROM users u INNER JOIN roles r ON u.role_id = r.id INNER JOIN wallets w ON u.wallet_id = w.id";
    private static final String SELECT_USER_BY_ID = "SELECT u.*, r.naming, w.money FROM users u INNER JOIN roles r ON u.role_id = r.id INNER JOIN wallets w ON u.wallet_id = w.id WHERE u.id = ?";
    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
    private static final String INSERT_USER = "INSERT INTO users (email, username, pass, role_id, wallet_id) VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE users SET (email = ?, username = ?, pass = ?, role_id = ?, wallet_id ?) WHERE id = ?";
    private static final String SELECT_USER_BY_EMAIL = "SELECT u.*, r.naming, w.money FROM users u INNER JOIN roles r ON u.role_id = r.id INNER JOIN wallets w ON u.wallet_id = w.id WHERE u.email = ?";

    public UserDAO() {
    }

    public static UserDAO getInstance() {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS);
            while (resultSet.next()) {
                users.add(mapping(resultSet));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public User findById(Integer id) {
        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID);
            statement.setLong(1, id);
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

    public User findByEmail(String email) {
        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_EMAIL);
            statement.setString(1, email);
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
            PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID);
            statement.setLong(1, id);
            rowsAffected = statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rowsAffected == 1;
    }

    @Override
    public User create(User entity) {
        try {
            User user;

            Connection connection = ConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getUsername());
            statement.setString(3, entity.getPassword());
            statement.setInt(4, entity.getRole().getId());
            statement.setInt(5, entity.getWallet().getId());
            int rowAffected = statement.executeUpdate();
            ResultSet resultSet;
            Integer generatedId = null;
            if(rowAffected == 1) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    generatedId = (Integer) resultSet.getObject(1);
                }
                if (generatedId != null) {
                    user = entity;
                    user.setId(generatedId);
                    return user;
                }
            }
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public User update(User entity) {
        User user = null;
        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER);

            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getUsername());
            statement.setString(3, entity.getPassword());
            statement.setString(4, entity.getRole().getId().toString());
            statement.setString(5, entity.getWallet_id().toString());
            statement.setString(6, entity.getId().toString());

            int rowAffected = statement.executeUpdate();
            if (rowAffected == 1) {
                user = entity;
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    private User mapping(ResultSet set) {
        try {
            return new User (
                set.getInt(1), // id
                set.getString(2), // email
                set.getString(3), // username
                set.getString(4), // password
                new RoleEntity(set.getInt(5), Role.valueOf(set.getString(7))), // role name
                set.getInt(6), // wallet id
                new Wallet(set.getInt(6), set.getInt(8)) // money
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
