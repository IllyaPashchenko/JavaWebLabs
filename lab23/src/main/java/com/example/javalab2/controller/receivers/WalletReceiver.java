package com.example.javalab2.controller.receivers;

import com.example.javalab2.dao.*;
import com.example.javalab2.domain.Wallet;

public class WalletReceiver {
    private static WalletReceiver walletReceiver;
    private final WalletDAO walletDAO;

    public WalletReceiver() {
        DaoFactory factory = DaoFactory.getInstance();
        this.walletDAO = (WalletDAO) factory.getDAO(DaoType.WALLET_DAO);
    }

    public static WalletReceiver getInstance() {
        if (walletReceiver == null) {
            walletReceiver = new WalletReceiver();
        }
        return walletReceiver;
    }

    public Wallet getWalletById(Integer id) {
        return walletDAO.findById(id);
    }

    public void updateWallet(Wallet wallet) {
        walletDAO.update(wallet);
    }

}
