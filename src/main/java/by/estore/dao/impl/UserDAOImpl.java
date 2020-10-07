package by.estore.dao.impl;

import by.estore.bean.Role;
import by.estore.bean.User;
import by.estore.dao.UserDAO;
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

    private final ConnectionPool connectionPool = new ConnectionPool();

    private final String SAVE_USER_QUERY = "INSERT INTO `users` (`email`, `password`, `role_id`) VALUES (?, ?, ?)";
    private final String DELETE_USER_BY_ID_QUERY = "DELETE FROM `users` WHERE `id` = ?";
    private final String GET_USER_BY_ID_QUERY = "SELECT `email`, `password`, `role_id` FROM `users` WHERE `id` = ?";
    private final String GET_USER_BY_EMAIL_QUERY = "SELECT `id`, `password`, `role_id` FROM `users` WHERE `email` = ?";
    private final String GET_ALL_USERS_QUERY = "SELECT `id`, `email`, `password`, `role_id` FROM `users`";

    @Override
    public boolean saveUser(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(SAVE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            // TODO: 06-Oct-20 role id
            preparedStatement.setLong(3, user.getRole().getId());

            int records = preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
//            logger.debug("User was added; count records: {}; returned id: {}", records, resultSet.getInt(1));
            connection.commit();
            return true;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    @Override
    public boolean deleteUserById(Long id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID_QUERY);
            preparedStatement.setLong(1, id);

            int records = preparedStatement.executeUpdate();
//            logger.debug("User was deleted, count records: {}", records);
            connection.commit();
            return true;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    @Override
    public User getUserById(Long id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(GET_USER_BY_ID_QUERY);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(id);
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                // TODO: 06-Oct-20 role
                Role role = new Role();
                Long role_id = resultSet.getLong("role_id");
                role.setId(role_id);
                user.setRole(role);
            }
            connection.commit();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL_QUERY);
            preparedStatement.setString(1, email);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(email);
                user.setPassword(resultSet.getString("password"));
                // TODO: 06-Oct-20 role
                Role role = new Role();
                Long role_id = resultSet.getLong("role_id");
                role.setId(role_id);
                user.setRole(role);
            }
            connection.commit();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(GET_ALL_USERS_QUERY);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                // TODO: 06-Oct-20 role
                Role role = new Role();
                Long role_id = resultSet.getLong("role_id");
                role.setId(role_id);
                user.setRole(role);
                users.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return users;
    }
}
