package com.company.servlets.repositories;

import com.company.servlets.dto.UserDto;
import com.company.servlets.models.User;

public interface CrudRepo<T>{
    boolean save(User user);
    UserDto readUser (int id);
    boolean update(UserDto user, int id);
    boolean delete(User user);
}
