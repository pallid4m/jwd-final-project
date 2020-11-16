package by.estore.dao.impl;

import by.estore.entity.*;
import by.estore.dao.UserDAO;
import by.estore.dao.exception.ConnectionPoolException;
import by.estore.dao.impl.pool.ConnectionPool;
import by.estore.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String SAVE_USER_QUERY =
            "INSERT INTO `users` (`email`, `password`, `phone`, `frist_name`, `last_name`, `image`, `role_id`) VALUES (?, ?, ?, ?, ?, ?, 2);";

    private static final String DELETE_USER_BY_ID_QUERY =
            "DELETE FROM `users` WHERE `id` = ?;";

    private static final String GET_USER_BY_ID_QUERY =
            "SELECT u.`email`, u.`password`, `phone`, `create_date`, `first_name`, `last_name`, `image`, u.`role_id`, r.`name` AS role_name FROM `users` AS u " +
            "JOIN `roles` AS r ON u.`role_id` = r.`id` " +
            "WHERE u.`id` = ?;";

    private static final String GET_USER_BY_EMAIL_QUERY =
            "SELECT u.`id`, u.`password`, u.`phone`, u.`create_date`, u.`first_name`, u.`last_name`, u.`image`, u.`role_id`, r.`name` AS role_name FROM `users` AS u " +
            "JOIN `roles` AS r ON u.`role_id` = r.`id` " +
            "WHERE u.`email` = ?;";

    private static final String GET_ALL_USERS_QUERY =
            "SELECT u.`id`, u.`email`, u.`password`, u.`phone`, u.`create_date`, u.`first_name`, u.`last_name`, u.`image`, u.`role_id`, r.`name` AS role_name FROM `users` AS u " +
            "JOIN `roles` AS r ON u.`role_id` = r.`id`;";

    private static final String UPDATE_USER_EMAIL_BY_USER_ID_QUERY =
            "UPDATE `users` SET `email` = ? WHERE `id` = ?;";

    private static final String UPDATE_USER_PASSWORD_BY_USER_ID_QUERY =
            "UPDATE `users` SET `password` = ? WHERE `id` = ?;";

    private static final String UPDATE_USER_DATA_BY_USER_ID_QUERY =
            "UPDATE `users` SET `phone` = ?, `first_name` = ?, `last_name` = ? " +
            "WHERE `id` = ?;";

    private static final String GET_USER_BY_ORDER_ID_QUERY =
            "SELECT u.`id`, u.`email`, `phone`, `first_name`, `last_name`, u.`role_id`, r.`name` AS role_name FROM `users` AS u " +
            "JOIN `roles` AS r ON u.`role_id` = r.`id` " +
            "JOIN `orders` AS o ON o.`user_id` = u.`id` AND o.`id` = ?;";

    private static final String USER_ID = "id";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";
    private static final String USER_PHONE = "phone";
    private static final String USER_CREATE_DATE = "create_date";
    private static final String USER_FIRST_NAME = "first_name";
    private static final String USER_LAST_NAME = "last_name";
    private static final String USER_IMAGE = "image";

    private static final String ROLE_ID = "role_id";
    private static final String ROLE_NAME = "role_name";

    @Override
    public boolean saveUser(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(SAVE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setString(6, user.getImage());

            preparedStatement.executeUpdate();

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
    public boolean deleteUserById(Long id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID_QUERY);
            preparedStatement.setLong(1, id);

            int records = preparedStatement.executeUpdate();
            if (logger.isDebugEnabled()) {
                logger.debug("User was deleted, count records: {}", records);
            }

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
    public User findUserById(Long id) throws DAOException {
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
                Role role = new Role();
                role.setId(resultSet.getByte(ROLE_ID));
                role.setName(resultSet.getString(ROLE_NAME));

                user = User.builder()
                        .setId(id)
                        .setEmail(resultSet.getString(USER_EMAIL))
                        .setPassword(resultSet.getString(USER_PASSWORD))
                        .setPhone(resultSet.getString(USER_PHONE))
                        .setCreateDate(resultSet.getObject(USER_CREATE_DATE, LocalDateTime.class))
                        .setFirstName(resultSet.getString(USER_FIRST_NAME))
                        .setLastName(resultSet.getString(USER_LAST_NAME))
                        .setImage(resultSet.getString(USER_IMAGE))
                        .setRole(role)
                        .build();
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
    public User findUserByEmail(String email) throws DAOException {
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
                Role role = new Role();
                role.setId(resultSet.getByte(ROLE_ID));
                role.setName(resultSet.getString(ROLE_NAME));

                user = User.builder()
                        .setId(resultSet.getLong(USER_ID))
                        .setEmail(email)
                        .setPassword(resultSet.getString(USER_PASSWORD))
                        .setPhone(resultSet.getString(USER_PHONE))
                        .setCreateDate(resultSet.getObject(USER_CREATE_DATE, LocalDateTime.class))
                        .setFirstName(resultSet.getString(USER_FIRST_NAME))
                        .setLastName(resultSet.getString(USER_LAST_NAME))
                        .setImage(resultSet.getString(USER_IMAGE))
                        .setRole(role)
                        .build();
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
    public User findUserByOrderId(Long orderId) throws DAOException {
        User user= null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(GET_USER_BY_ORDER_ID_QUERY);
            preparedStatement.setLong(1, orderId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getByte(ROLE_ID));
                role.setName(resultSet.getString(ROLE_NAME));

                user = User.builder()
                        .setId(resultSet.getLong(USER_ID))
                        .setEmail(resultSet.getString(USER_EMAIL))
                        .setPhone(resultSet.getString(USER_PHONE))
                        .setFirstName(resultSet.getString(USER_FIRST_NAME))
                        .setLastName(resultSet.getString(USER_LAST_NAME))
                        .setRole(role)
                        .build();
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
    public List<User> findAllUsers() throws DAOException {
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
                Role role = new Role();
                role.setId(resultSet.getByte(ROLE_ID));
                role.setName(resultSet.getString(ROLE_NAME));

                User user = User.builder()
                        .setId(resultSet.getLong(USER_ID))
                        .setEmail(resultSet.getString(USER_EMAIL))
                        .setPassword(resultSet.getString(USER_PASSWORD))
                        .setPhone(resultSet.getString(USER_PHONE))
                        .setCreateDate(resultSet.getObject(USER_CREATE_DATE, LocalDateTime.class))
                        .setFirstName(resultSet.getString(USER_FIRST_NAME))
                        .setLastName(resultSet.getString(USER_LAST_NAME))
                        .setImage(resultSet.getString(USER_IMAGE))
                        .setRole(role)
                        .build();

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

    @Override
    public boolean updateUserEmail(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(UPDATE_USER_EMAIL_BY_USER_ID_QUERY);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setLong(2, user.getId());

            preparedStatement.executeUpdate();

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
    public boolean updateUserPassword(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(UPDATE_USER_PASSWORD_BY_USER_ID_QUERY);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setLong(2, user.getId());

            preparedStatement.executeUpdate();

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
    public boolean updateUserData(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(UPDATE_USER_DATA_BY_USER_ID_QUERY);
            preparedStatement.setString(1, user.getPhone());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setLong(4, user.getId());

            preparedStatement.executeUpdate();

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
}
