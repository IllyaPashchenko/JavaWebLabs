package com.example.javalab2.controller.receivers;

import com.example.javalab2.controller.UserVеrifyingUtil;
import com.example.javalab2.dao.*;
import com.example.javalab2.domain.RoleEntity;
import com.example.javalab2.domain.User;
import com.example.javalab2.domain.Wallet;
import com.example.javalab2.domain.enums.Role;
import com.example.javalab2.exception.EmailAlreadyExistException;
import com.example.javalab2.exception.UserNotFoundException;

import java.util.List;
import java.util.stream.Collectors;


public class UserReceiver {
    private static UserReceiver userReceiver;
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final WalletDAO walletDAO;

    private UserReceiver() {
        DaoFactory factory = DaoFactory.getInstance();
        this.userDAO = (UserDAO) factory.getDAO(DaoType.USER_DAO);
        this.roleDAO = (RoleDAO) factory.getDAO(DaoType.ROLE_DAO);
        this.walletDAO = (WalletDAO) factory.getDAO(DaoType.WALLET_DAO);
    }

    public static UserReceiver getInstance() {
        if (userReceiver == null) {
            userReceiver = new UserReceiver();
        }
        return userReceiver;
    }

    public User register(String email, String password, String username) {
        User selectedUser = userDAO.findByEmail(email);
        if (selectedUser != null) {
            throw new EmailAlreadyExistException();
        }
        RoleEntity role = roleDAO.findByName(Role.CLIENT.toString());
        Wallet wallet = walletDAO.create(new Wallet());
        System.out.println("Wallet " + wallet);
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setWallet(wallet);
        user.setPassword(password);
        user.setRole(role);
        user.setWallet_id(wallet.getId());
        return userDAO.create(user);
    }

    public User login(String email, String password) {
        User user = userDAO.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException();
        }

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Пароль не правильний");
        }

        return user;
    }

    public List<User> getAllUsersForManager(Integer adminId) {
        verifyUserAccessByRole(adminId, Role.MANAGER);
        List<User> users = userDAO.findAll();

        return users.stream()
                .filter(user -> !user.getId().equals(adminId))
                .collect(Collectors.toList());
    }

    public List<User> getAllMasters() {
        List<User> users = userDAO.findAll();

        return users.stream()
                .filter(user -> user.getRole().getRole().equals(Role.MASTER))
                .collect(Collectors.toList());
    }

    public void verifyUserAccessByRole(Integer userId, Role role) {
        User user = userDAO.findById(userId);

        UserVеrifyingUtil.verifyUserNotNull(user);
        UserVеrifyingUtil.verifyUserHasRole(user, role);
    }


}
