package com.example.javalab2.controller.commands.implementations;

import com.example.javalab2.controller.commands.CommandSimple;
import com.example.javalab2.controller.receivers.WalletReceiver;
import com.example.javalab2.domain.Wallet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateWalletCommand implements CommandSimple {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String walletId = request.getParameter("wallet_id");
        String sum = request.getParameter("sum");

        Wallet wallet = WalletReceiver.getInstance().getWalletById(Integer.parseInt(walletId));
        wallet.setMoney(wallet.getMoney() + Integer.parseInt(sum));

        WalletReceiver.getInstance().updateWallet(wallet);
        new ShowUsersCommand().execute(request, response);
    }
}
