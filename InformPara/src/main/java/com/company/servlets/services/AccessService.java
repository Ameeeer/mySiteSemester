package com.company.servlets.services;

import com.company.servlets.ConnectionDB;
import com.company.servlets.models.User;
import com.company.servlets.repositories.UsersRepoIMLP;
import com.company.servlets.repositories.UsersRepository;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class AccessService {
    private ConnectionDB connectionDB = new ConnectionDB();
    private UsersRepository usersRepository;

    public List<String> getAllUserRoles(Integer userID) {
        try {
            usersRepository= new UsersRepoIMLP(connectionDB.getConnetion());
            List<String> roles = usersRepository.getUserRoles(userID);
            return roles;
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            throw new IllegalArgumentException();
        }
    }
}
