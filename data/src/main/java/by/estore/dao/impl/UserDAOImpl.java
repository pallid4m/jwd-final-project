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

    private static final String GET_ALL_ORDERS_BY_USER_ID_QUERY =
            "SELECT o.`id`, o.`create_date`, o.`amount`, o.`order_status_id`, o.`currency_id`, c.`code` AS currency, os.`state` AS status FROM `orders` AS o " +
            "JOIN `order_status` AS os ON os.`id` = o.`order_status_id` " +
            "JOIN `currencies` AS c ON c.`id` = o.`currency_id` " +
            "WHERE `user_id` = ?;";


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

    private static final String ORDER_STATUS_ID = "order_status_id";
    private static final String ORDER_STATUS_STATE = "state";

    private static final String CURRENCY_ID = "currency_id";
    private static final String CURRENCY_CODE = "code";

    private static final String ORDER_ID = "id";
    private static final String ORDER_CREATE_DATE = "create_date";
    private static final String ORDER_AMOUNT = "amount";
    private static final String ORDER_STATUS = "status";
    private static final String ORDER_CURRENCY = "currency";

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
    public List<Order> getAllOrders(User user) throws DAOException {
        List<Order> orders = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(GET_ALL_ORDERS_BY_USER_ID_QUERY);
            preparedStatement.setLong(1, user.getId());

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                OrderStatus orderStatus = new OrderStatus();
                orderStatus.setId(resultSet.getByte(ORDER_STATUS_ID));
                orderStatus.setState(resultSet.getString(ORDER_STATUS));

                Currency currency = new Currency();
                currency.setId(resultSet.getShort(CURRENCY_ID));
                currency.setCode(resultSet.getString(ORDER_CURRENCY));

                Order order = Order.builder()
                        .setId(resultSet.getLong(ORDER_ID))
                        .setCreateDate(resultSet.getObject(ORDER_CREATE_DATE, LocalDateTime.class))
                        .setAmount(resultSet.getBigDecimal(ORDER_AMOUNT))
                        .setOrderStatus(orderStatus)
                        .setCurrency(currency)
                        .build();

                orders.add(order);
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

        return orders;
    }
}
