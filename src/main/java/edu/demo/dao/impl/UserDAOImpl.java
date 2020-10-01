package edu.demo.dao.impl;

import edu.demo.bean.AuthDetail;
import edu.demo.bean.User;
import edu.demo.dao.UserDAO;
import edu.demo.dao.exception.DAOException;
import edu.demo.dao.impl.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    private static final String SELECT_USER_BY_LOGIN =
            "SELECT `u.user_id`, `u.user_login`, `role.user_role`" +
                    "FROM users u, user_roles role" +
                    "WHERE u.user_login=? and u.user_password=? and u.user_role=rol.user_role_id and u.user_rating=rat.user_rating_id;";

    private static final String INSERT_USER =
            "INSERT INTO users (`user_email`, `user_login`, `user_password`, `user_role`) " +
                    "VALUES (?, ?, 1, 1)";


    @Override
    public User authorization(AuthDetail data) throws DAOException {
        User user = null;

        ConnectionProvider connectionProvider;
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connectionProvider = ConnectionProvider.getInstance();
            connection = connectionProvider.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN);

            preparedStatement.setString(1, data.getLogin());
            preparedStatement.setString(2, data.getPassword());

            System.out.println("authorizationData.getLogin() - " + data.getLogin());
            System.out.println("authorizationData.getPassword() - " + data.getPassword());

            resultSet = preparedStatement.executeQuery();

            String id = resultSet.getString("user_id");
            String login = resultSet.getString("user_login");
            String role = resultSet.getString("user_role");

            user = new User();
            user.setId(id);
            user.setLogin(login);
            user.setRole(role);
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (ClassNotFoundException e) {
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                }
            }
        }
        return user;
    }

    @Override
    public boolean registration(AuthDetail data) throws DAOException {
        boolean registration = false;

        ConnectionProvider connectionProvider;
        Connection connection;
        PreparedStatement preparedStatement = null;

        try {
            connectionProvider = ConnectionProvider.getInstance();
            connection = connectionProvider.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_USER);

            preparedStatement.setString(1, data.getLogin());
            preparedStatement.setString(2, data.getPassword());

            if (preparedStatement.executeUpdate() == 1) {
                registration = true;
            }

        } catch (SQLException e) {
        } catch (ClassNotFoundException e) {
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException throwable) {
                }
            }
        }

        return registration;
    }
}
