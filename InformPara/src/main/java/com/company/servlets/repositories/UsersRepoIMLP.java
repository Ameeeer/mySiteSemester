package com.company.servlets.repositories;

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

    private RowMapper<User> userRowMapper =
            resultSet -> User.builder().email(resultSet.getString("email")).login(resultSet.getString("login")).password(resultSet.getString("password")).build();

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
    public boolean readUser(User user) {
        return false;
    }

    @Override
    public boolean insertUserRoles(Integer userId, Integer roleId) {
        String sql = "insert into usrs_with_roles(userid, roleid) VALUES (?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2,roleId);
            int checkLine = preparedStatement.executeUpdate();
            if (checkLine<2){
                return false;
            }
            else return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException("Insert Error");
        }
    }
    @Override
    public Integer getIdRole(String roleName) {
        String sqlQuery = "select id from roles where rolename = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1,roleName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                int x = resultSet.getInt(1);
                System.out.println(x);
                return x;
            }else return null;
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

    public void update(User user) {

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
    public List<String> getUserRoles(Integer id){
        String sql = "select rolename from roles join usrs_with_roles uwr on roles.id = uwr.roleID where userID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> roles = new ArrayList<>();
            while (resultSet.next()){
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
                User user = userRowMapper.mapRow(resultSet);
                list.add(user);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("NotFound");
        }
        return list;
    }

    @Override
    public List<User> getUsersOrderByCountry() {
        list = new ArrayList<>();
        String sql = "select email,login,password from mysiteusers order by country";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = userRowMapper.mapRow(resultSet);
                list.add(user);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("NotFound");
        }
        return list;
    }
}
