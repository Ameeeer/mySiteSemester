package com.company.servlets.services;

import com.company.servlets.ConnectionDB;
import com.company.servlets.models.User;
import com.company.servlets.repositories.UsersRepoIMLP;
import com.company.servlets.repositories.UsersRepository;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RegistrateService {
    ConnectionDB connectionDB = new ConnectionDB();
    private UsersRepository usersRepository;

    public boolean registrateUser(User newuser) {
        try {
            usersRepository = new UsersRepoIMLP(connectionDB.getConnetion());
            Integer checkRegistratedUser = usersRepository.getUserId(newuser);
            if (checkRegistratedUser == null) {
                boolean reg = usersRepository.save(newuser);
                Integer newUserID = usersRepository.getUserId(newuser);
                Integer idRole;
                for (int i = 0; i < newuser.getRoles().size(); i++) {
                    idRole = usersRepository.getIdRole(newuser.getRoles().get(i));
                    if (idRole == null) {
                        return false;
                    } else {
                        usersRepository.insertUserRoles(newUserID, idRole);
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (SQLException | ClassNotFoundException |
                NamingException e) {
            throw new IllegalArgumentException("Cannot registrate user");
        }

    }

    public boolean checkFields(List<String> list) {
        for (String str : list) {
            if (str == null) return false;
        }
        return true;
    }
}
