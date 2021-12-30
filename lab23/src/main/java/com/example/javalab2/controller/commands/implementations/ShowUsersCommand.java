package com.example.javalab2.controller.commands.implementations;

import com.example.javalab2.controller.commands.CommandSimple;
import com.example.javalab2.controller.receivers.UserReceiver;
import com.example.javalab2.domain.User;
import com.example.javalab2.exception.NoUserInSessionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowUsersCommand implements CommandSimple {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new NoUserInSessionException();
        }

        List<User> users = UserReceiver.getInstance().getAllUsersForManager(user.getId());
        request.getSession().setAttribute("users", users);
    }
}
