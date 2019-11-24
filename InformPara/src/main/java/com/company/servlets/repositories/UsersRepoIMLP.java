package com.company.servlets.repositories;

import com.company.servlets.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepoIMLP implements UsersRepository {
    private Connection connection;
    private List<User> list = new ArrayList<>();

    public UsersRepoIMLP(Connection connection) {
        this.connection = connection;
    }

    private RowMapper<User> userRowMapper = resultSet -> new User(
            resultSet.getString("email"),
            resultSet.getString("login"),
            resultSet.getString("password")
    );
    private RowMapper<User> userEmailUniqueCheck = resultSet -> new User(
            resultSet.getString("email")
    );
    private RowMapper<User> userAuthCheck = resultSet -> new User(
            resultSet.getString("email"),
            resultSet.getString("password")
    );
    private RowMapper<User> rowMapperWithAllInfo = resultSet -> new User(
            resultSet.getString("email"),
            resultSet.getString("login"),
            resultSet.getString("password"),
            resultSet.getString("country"),
            resultSet.getString("infoaboutuser")
    );
//    private RowMapper<User> userRole = resultSet -> new User(
//            resultSet.getString("email"),
//            resultSet.getString("email"),
//            resultSet.getString("email"),
//            resultSet.getString("email"),
//            resultSet.getString("email")
//    );

    public boolean save(User user) {
        String sqlQueryAddUser = "INSERT INTO mysiteusers(email,login,password,country,infoaboutuser) VALUES(?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryAddUser)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getCountry());
            preparedStatement.setString(5, user.getInfo());
            boolean ret = preparedStatement.execute();
            System.out.println(ret);
            return ret;
        } catch (SQLException e) {
            return true;
        }
    }

    @Override
    public boolean checkUser(User user) {
        String sqlQ = "SELECT mysiteusers.email from mysiteusers where email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQ)) {
            preparedStatement.setString(1, user.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user1 = userEmailUniqueCheck.mapRow(resultSet);
                if ((user1 != null)) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    public void update(User user) {

    }
    @Override
    public User authoriseUser(User user) throws SQLException {
        String sql = "select email,login,password,country,infoaboutuser,userrole from mysiteusers where email = ? and password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2,user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                User authUser = rowMapperWithAllInfo.mapRow(resultSet);
                return authUser;
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException("Unbelivable situation or db crashed");
        }
    }
    @Override
    public boolean authentificateUser(User user) throws SQLException {
        String sql = "select email,password from mysiteusers where email = ? and password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2,user.getPassword());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                User checkableUser = userAuthCheck.mapRow(rs);
                if (checkableUser.getEmail().equals(user.getEmail())&&checkableUser.getPassword().equals(user.getPassword())){
                    return true;
                }
                else {
                    return false;
                }
            }else {
                return false;
            }
        } catch (SQLException e) {
            throw new SQLException("this user does'n exist");
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
