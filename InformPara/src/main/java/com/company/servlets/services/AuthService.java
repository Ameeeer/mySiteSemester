package com.company.servlets.services;

import com.company.servlets.models.User;

import java.sql.SQLException;
import java.util.Optional;

public class AuthService {
    private String email;
    private String password;
    private AuthCheck authCheck;

    private User authentificate(User user) throws SQLException {
        try {
            User checkUser = identificate(user);
            if (checkUser != null) {
                return checkUser;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException("not found this user");
        }
    }

    private User identificate(User authUserInfo) throws SQLException {
        UserRepoService userRepoService = new UserRepoService();
        try {
            User user = userRepoService.getUser(authUserInfo);
            if (user != null) {
                return user;
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException("cannot connect to db");
        }
    }

    public User authoriseUserSetOnline(User user) throws SQLException {
        try {
            User nullable = null;
            User onlineUser = authentificate(user);
            if (onlineUser != null) {
                onlineUser.setStatus("online");
                return onlineUser;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException("this user does not exist");
        }
    }
}
