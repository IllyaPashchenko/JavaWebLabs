package com.example.javalab2.dao;

import com.example.javalab2.domain.Entity;

import java.util.UUID;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory() {

    }

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public AbstractDAO<Integer, ? extends Entity> getDAO(DaoType daoType) {
        switch (daoType) {
            case ORDER_DAO: return OrderDAO.getInstance();
            case ROLE_DAO: return RoleDAO.getInstance();
            case STATUS_DAO: return StatusDAO.getInstance();
            case USER_DAO: return UserDAO.getInstance();
            case WALLET_DAO:return WalletDAO.getInstance();
            default:
                throw new EnumConstantNotPresentException(DaoType.class, daoType.name());
        }
    }
}
