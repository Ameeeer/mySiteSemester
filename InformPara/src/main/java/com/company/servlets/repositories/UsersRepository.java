package com.company.servlets.repositories;

import com.company.servlets.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UsersRepository extends CrudRepo<User>{

    User authoriseUser(User user);

    boolean authentificateUser(User user);

    List<User> getUsersOrderByLogin();

    List<User> getUsersOrderByCountry();
}
