package com.company.servlets.repositories;

import com.company.servlets.models.User;

import java.sql.SQLException;

public interface CrudRepo<T>{
    boolean save(User user) throws SQLException;
    boolean readUser (User user);
}
