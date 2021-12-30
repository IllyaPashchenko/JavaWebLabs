package com.example.javalab2.controller.commands.implementations;

import com.example.javalab2.controller.commands.CommandSimple;
import com.example.javalab2.controller.receivers.UserReceiver;
import com.example.javalab2.domain.User;
import com.example.javalab2.exception.IncorrectAuthDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements CommandSimple {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null) throw new IncorrectAuthDataException();

        User user = UserReceiver.getInstance().login(email, password);
        request.getSession().setAttribute("user", user);
    }
}
