package com.company.servlets.services;

import com.company.servlets.ConnectionDB;
import com.company.servlets.models.User;
import com.company.servlets.repositories.UsersRepoIMLP;
import com.company.servlets.repositories.UsersRepository;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class UserRepoService {
    ConnectionDB connectionDB = new ConnectionDB();

    public List<User> getUsrsOrderedBCountry() {
        try {
            UsersRepository usersRepository = new UsersRepoIMLP(connectionDB.getConnetion());
            List<User> list = usersRepository.getUsersOrderByCountry();
            return list;
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            throw new IllegalArgumentException("cannotConnect or not Found");
        }
    }

    public List<User> getUsrsOrderedBLogin() {
        try {
            UsersRepository usersRepository = new UsersRepoIMLP(connectionDB.getConnetion());
            List<User> list = usersRepository.getUsersOrderByLogin();
            return list;
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            throw new IllegalStateException("");
        }
    }

    public User getUser(User user) throws SQLException {
        try {
            UsersRepository usersRepository = new UsersRepoIMLP(connectionDB.getConnetion());
            boolean checker = usersRepository.authentificateUser(user);
            if (checker) {
                User autoriseduser = usersRepository.authoriseUser(user);
                return autoriseduser;
            }else {
                return null;
            }
        } catch (NamingException | ClassNotFoundException e) {
            throw new IllegalAccessError("Cannot connect to Db");
        }
    }
}
