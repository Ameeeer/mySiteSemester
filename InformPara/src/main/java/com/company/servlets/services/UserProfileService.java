package com.company.servlets.services;

import com.company.servlets.ConnectionDB;
import com.company.servlets.dto.UserDto;
import com.company.servlets.repositories.UsersRepoIMLP;
import com.company.servlets.repositories.UsersRepository;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class UserProfileService {
    private ConnectionDB connectionDB = new ConnectionDB();
    private UsersRepository usersRepository;

    public List<UserDto> getAllUsers() {
        return null;
    }

    public UserDto getUser(int id) {
        try {
            usersRepository = new UsersRepoIMLP(connectionDB.getConnetion());
            UserDto dto = usersRepository.readUser(id);
            return dto;
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            throw new IllegalArgumentException("User does'n exist err");
        }
    }
    public boolean updateUser(UserDto userDto, int id){// TODO: 09.12.2019 add tokens
        try {
            usersRepository = new UsersRepoIMLP(connectionDB.getConnetion());
            boolean checkUpdate = usersRepository.update(userDto,id);
            return checkUpdate;
        } catch (SQLException | ClassNotFoundException | NamingException e) {
            throw new IllegalArgumentException("User not found");
        }
    }
}
