package com.example.javalab2.dao;

import com.example.javalab2.domain.User;
import com.example.javalab2.domain.Wallet;

import java.sql.*;
import java.util.List;

public class WalletDAO extends AbstractDAO<Integer, Wallet>{

    private static WalletDAO walletDAO;
    private static final String SELECT_WALLET_BY_ID = "SELECT * FROM wallets WHERE id = ?";
    private static final String UPDATE_WALLET = "UPDATE wallets SET money = ? WHERE id = ?";
    private static final String INSERT_NEW_WALLET = "INSERT INTO wallets (money) VALUES(0)";

    private WalletDAO() {}

    public static WalletDAO getInstance() {
        if (walletDAO == null) {
            walletDAO = new WalletDAO();
        }
        return walletDAO;
    }


    @Override
    public List<Wallet> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Wallet findById(Integer id) {
        try {
            Wallet wallet = null;
            Connection connection = ConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_WALLET_BY_ID);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int money = resultSet.getInt(2);
                wallet = new Wallet(id, money);
            }
            connection.close();
            return wallet;
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
    public Wallet create(Wallet entity) {
        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();

            PreparedStatement statement = connection.prepareStatement(INSERT_NEW_WALLET, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();

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
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Wallet update(Wallet entity) {
        Wallet wallet = null;
        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_WALLET);

            statement.setInt(1, entity.getMoney());
            statement.setLong(2, entity.getId());

            int rowAffected = statement.executeUpdate();
            connection.commit();

            if (rowAffected == 1) {
                wallet = entity;
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return wallet;
    }
}
