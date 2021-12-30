package com.example.javalab2.controller;

import com.example.javalab2.domain.User;
import com.example.javalab2.domain.enums.Role;
import com.example.javalab2.exception.NoRightsException;
import com.example.javalab2.exception.UserNotFoundException;

public class UserVеrifyingUtil {

    public static void verifyUserNotNull(User user) {
        if (user == null) {
            throw new UserNotFoundException();
        }
    }

    public static boolean areUserHasRole(User user, Role role) {
        String roleName = user.getRole().getRole().toString();
        return roleName.equals(role.toString());
    }

    public static void verifyUserHasRole(User user, Role role) {
        if (!areUserHasRole(user, role)) {
            throw new NoRightsException();
        }
    }
}
