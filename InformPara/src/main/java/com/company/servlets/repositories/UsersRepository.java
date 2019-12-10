package com.company.servlets.repositories;

import com.company.servlets.models.User;

import java.util.List;

public interface UsersRepository extends CrudRepo<User>{

    boolean insertUserRoles(Integer userId, Integer roleId);

    Integer getIdRole(String roleName);

    Integer getUserId(User user);

    User authoriseUser(User user);

    List<String> getUserRoles(Integer id);

    boolean authentificateUser(User user);

    List<User> getUsersOrderByLogin();

    List<User> getUsersOrderByCountry();
    List<User> getAllUsers();
}
