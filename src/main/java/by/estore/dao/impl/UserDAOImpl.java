package by.estore.dao.impl;

import by.estore.bean.Role;
import by.estore.bean.User;
import by.estore.dao.UserDAO;
import by.estore.dao.exception.ConnectionPoolException;
import by.estore.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final String SAVE_USER_QUERY = "INSERT INTO `users` (`email`, `password`, `role_id`) VALUES (?, ?, 2)";
    private final String DELETE_USER_BY_ID_QUERY = "DELETE FROM `users` WHERE `id` = ?";
    private final String GET_USER_BY_ID_QUERY = "SELECT u.`email`, u.`password`, u.`role_id`, r.`name` FROM `users` AS u JOIN `roles` AS r ON u.`role_id` = r.`id` WHERE u.`id` = ?";
    private final String GET_USER_BY_EMAIL_QUERY = "SELECT u.`id`, u.`password`, u.`role_id`, r.`name` FROM `users` AS u JOIN `roles` AS r ON u.`role_id` = r.`id` WHERE u.`email` = ?";
    private final String GET_ALL_USERS_QUERY = "SELECT u.`id`, u.`email`, u.`password`, u.`role_id`, r.`name` FROM `users` AS u JOIN `roles` AS r ON u.`role_id` = r.`id`;";

    private static final String USER_ID = "id";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";
    private static final String USER_ROLE_ID = "role_id";
    private static final String ROLE_NAME = "name";

    @Override
    public boolean saveUser(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(SAVE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());

            int records = preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
//            logger.debug("User was added; count records: {}; returned id: {}", records, resultSet.getInt(1));

            connection.commit();
            return true;
        } catch (ConnectionPoolException | SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    throw new DAOException(e1);
                }
            }
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public boolean deleteUserById(Long id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID_QUERY);
            preparedStatement.setLong(1, id);

            int records = preparedStatement.executeUpdate();
//            logger.debug("User was deleted, count records: {}", records);

            connection.commit();
            return true;
        } catch (ConnectionPoolException | SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    throw new DAOException(e1);
                }
            }
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public User getUserById(Long id) throws DAOException {
        User user= null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(GET_USER_BY_ID_QUERY);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(id);
                user.setEmail(resultSet.getString(USER_EMAIL));
                user.setPassword(resultSet.getString(USER_PASSWORD));
                Role role = new Role();
                role.setId(resultSet.getByte(USER_ROLE_ID));
                role.setName(resultSet.getString(ROLE_NAME));
                user.setRole(role);
            }

            connection.commit();
        } catch (ConnectionPoolException | SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    throw new DAOException(e1);
                }
            }
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) throws DAOException {
        User user = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL_QUERY);
            preparedStatement.setString(1, email);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong(USER_ID));
                user.setEmail(email);
                user.setPassword(resultSet.getString(USER_PASSWORD));
                Role role = new Role();
                role.setId(resultSet.getByte(USER_ROLE_ID));
                role.setName(resultSet.getString(ROLE_NAME));
                user.setRole(role);
            }

            connection.commit();
        } catch (ConnectionPoolException | SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    throw new DAOException(e1);
                }
            }
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() throws DAOException {
        List<User> users = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(GET_ALL_USERS_QUERY);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(USER_ID));
                user.setEmail(resultSet.getString(USER_EMAIL));
                user.setPassword(resultSet.getString(USER_PASSWORD));
                Role role = new Role();
                role.setId(resultSet.getByte(USER_ROLE_ID));
                role.setName(resultSet.getString(ROLE_NAME));
                user.setRole(role);
                users.add(user);
            }

            connection.commit();
        } catch (ConnectionPoolException | SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    throw new DAOException(e1);
                }
            }
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }

        return users;
    }
}
