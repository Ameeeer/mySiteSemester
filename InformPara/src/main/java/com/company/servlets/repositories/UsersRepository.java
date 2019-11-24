package com.company.servlets.repositories;

import com.company.servlets.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UsersRepository extends CrudRepo<User>{

    User authoriseUser(User user) throws SQLException;

    boolean authentificateUser(User user) throws SQLException;

    List<User> getUsersOrderByLogin();

    List<User> getUsersOrderByCountry();
}
