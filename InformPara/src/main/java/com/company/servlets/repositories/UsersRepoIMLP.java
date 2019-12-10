package com.company.servlets.repositories;

import com.company.servlets.dto.UserDto;
import com.company.servlets.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersRepoIMLP implements UsersRepository {
    private Connection connection;
    private List<User> list = new ArrayList<>();

    public UsersRepoIMLP(Connection connection) {
        this.connection = connection;
    }

    private RowMapper<UserDto> userRowMapper =
            resultSet -> UserDto.builder().email(resultSet.getString("email")).login(resultSet.getString("login")).country(resultSet.getString("country")).infoAboutUser(resultSet.getString("infoaboutuser")).build();

    //            resultSet.getString("email"),
//            resultSet.getString("login"),
//            resultSet.getString("password")
//    );
    private RowMapper<User> userEmailUniqueCheck = resultSet -> User.builder().email(resultSet.getString("email")).build();
    //            resultSet.getString("email")
//    );
    private RowMapper<User> userAuthCheck = resultSet -> User.builder().email(resultSet.getString("email")).password(resultSet.getString("password")).build();
    //            resultSet.getString("email"),
//            resultSet.getString("password")
//    );
    private RowMapper<User> rowMapperWithAllInfo = resultSet -> User.builder().id(resultSet.getInt("id")).email(resultSet.getString("email")).login(resultSet.getString("login")).password(resultSet.getString("password")).country(resultSet.getString("country")).info(resultSet.getString("infoaboutuser")).build();
    private RowMapper<User> minInfoAboutUserRowMapper = resultSet -> User.builder().login(resultSet.getString("login")).country(resultSet.getString("country")).info(resultSet.getString("infoaboutuser")).build();
//            resultSet.getString("email"),
//            resultSet.getString("login"),
//            resultSet.getString("password"),
//            resultSet.getString("country"),
//            resultSet.getString("infoaboutuser")
//    );
//    private RowMapper<User> userRole = resultSet -> new User(
//            resultSet.getString("email"),
//            resultSet.getString("email"),
//            resultSet.getString("email"),
//            resultSet.getString("email"),
//            resultSet.getString("email")
//    );

    @Override
    public boolean save(User user) {
        String sqlQueryAddUser = "INSERT INTO mysiteusers(email,login,password,country,infoaboutuser) VALUES(?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryAddUser)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getCountry());
            preparedStatement.setString(5, user.getInfo());
            int checking = preparedStatement.executeUpdate();
            if (checking < 5) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Cannot add user");
            // TODO: 24.11.2019 add logger to insert user on db
        }
    }

    @Override
    public UserDto readUser(int id) {

        String sqlReadUser = "select email,login,country,infoaboutuser from mysiteusers where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlReadUser)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                UserDto userDto = userRowMapper.mapRow(resultSet);
                return userDto;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();// TODO: 08.12.2019 Add logger
        }
    }

    @Override
    public boolean insertUserRoles(Integer userId, Integer roleId) {
        String sql = "insert into usrs_with_roles(userid, roleid) VALUES (?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, roleId);
            int checkLine = preparedStatement.executeUpdate();
            if (checkLine < 2) {
                return false;
            } else return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException("Insert Error");
        }
    }

    @Override
    public Integer getIdRole(String roleName) {
        String sqlQuery = "select id from roles where rolename = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, roleName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int x = resultSet.getInt(1);
                System.out.println(x);
                return x;
            } else return null;
        } catch (SQLException e) {
            throw new IllegalArgumentException("This role doesn't exist");
        }
    }

    @Override
    public Integer getUserId(User user) {
        String sqlQ = "SELECT id from mysiteusers where email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQ)) {
            preparedStatement.setString(1, user.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                return resultSet.getInt("id");
//                if ((user1 != null)) {
//                    return true;
//                } else {
//                    return false;
//                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Cannot connect to db");
        }
    }

    @Override
    public boolean update(UserDto user, int id) {
        String sqlUpdate = "update mysiteusers set email = ?, login = ?, country = ?, infoaboutuser = ? where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
            UserDto dto = readUser(id);
            boolean ch = dto.equals(user);
            if (ch) {
                return false;
            } else {
                preparedStatement.setString(1, user.getEmail());
                preparedStatement.setString(2, user.getLogin());
                preparedStatement.setString(3, user.getCountry());
                preparedStatement.setString(4, user.getInfoAboutUser());
                preparedStatement.setInt(5, id);
                int check = preparedStatement.executeUpdate();
                System.out.println(check);
                if (check == 1) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public User authoriseUser(User user) {
        String sql = "select id,email,login,password,country,infoaboutuser from mysiteusers where email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User authUser = rowMapperWithAllInfo.mapRow(resultSet);
                return authUser;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Cannot Authorise User");
        }
    }

    @Override
    public List<String> getUserRoles(Integer id) {
        String sql = "select rolename from roles join usrs_with_roles uwr on roles.id = uwr.roleID where userID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> roles = new ArrayList<>();
            while (resultSet.next()) {
                roles.add(resultSet.getString("rolename"));
            }
            return roles;
        } catch (SQLException e) {
            throw new IllegalArgumentException("Table exc");
        }
    }

    @Override
    public boolean authentificateUser(User user) {
        String sql = "select email,password from mysiteusers where email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getEmail());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                User checkableUser = userAuthCheck.mapRow(rs);
                if (checkableUser.getEmail().equals(user.getEmail()) && checkableUser.getPassword().equals(user.getPassword())) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("this user does'n exist");
        }
    }

    @Override
    public List<User> getUsersOrderByLogin() {
        list = new ArrayList<>();
        String sql = "select email,login,password from mysiteusers order by login";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                User user = userRowMapper.mapRow(resultSet);
//                list.add(user);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("NotFound");
        }
        return list;
    }

    @Override
    public List<User> getUsersOrderByCountry() {
        list = new ArrayList<>();
        String sqlQ = "select email,login,password from mysiteusers order by country";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQ)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                User user = userRowMapper.mapRow(resultSet);
//                list.add(user);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("NotFound");
        }
        return list;
    }

    @Override
    public List<User> getAllUsers() {
        list = new ArrayList<>();
        String sql = "select login,country,infoaboutuser from mysiteusers";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = minInfoAboutUserRowMapper.mapRow(resultSet);
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            try {
                throw new IllegalAccessException("Cannot connect to DB");
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
