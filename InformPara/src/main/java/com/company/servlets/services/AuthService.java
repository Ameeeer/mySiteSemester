package com.company.servlets.services;

import com.company.servlets.models.User;

import java.sql.SQLException;
import java.util.Optional;

public class AuthService {
    private AuthCheck authCheck;

    private User authentificate(User user) {
        UserRepoService userRepoService = new UserRepoService();
        try {
            User newUser = userRepoService.getUser(user);
            if (newUser != null) {
                return newUser;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect To db");
        }
    }


    public User authoriseUserSetOnline(User user) {
        User onlineUser = authentificate(user);
        if (onlineUser != null) {
            return onlineUser;
        } else {
            return null;
        }
    }
}
