package by.estore.dao.impl;

import by.estore.entity.*;
import by.estore.dao.OrderDAO;
import by.estore.dao.exception.ConnectionPoolException;
import by.estore.dao.impl.pool.ConnectionPool;
import by.estore.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private static final Logger logger = LogManager.getLogger(OrderDAOImpl.class);

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String GET_ALL_ORDERS_BY_USER_ID_QUERY =
            "SELECT o.`id`, o.`create_date`, o.`amount`, o.`order_status_id`, o.`currency_id`, c.`code` AS currency, os.`state` AS status FROM `orders` AS o " +
            "JOIN `order_status` AS os ON os.`id` = o.`order_status_id` " +
            "JOIN `currencies` AS c ON c.`id` = o.`currency_id` " +
            "WHERE `user_id` = ?;";

    private static final String GET_ALL_ORDERS_LAZY_QUERY =
            "SELECT o.`id`, o.`create_date`, o.`amount`, o.`order_status_id`, os.`state`, o.`currency_id`, cur.`code` FROM `orders` AS o " +
            "JOIN `order_status` AS os ON o.`order_status_id` = os.`id` " +
            "JOIN `currencies` AS cur ON o.`currency_id` = cur.`id`;";

    private static final String SAVE_ORDER_QUERY =
            "INSERT INTO `orders` (`amount`, `user_id`, `order_status_id`, `currency_id`) VALUES (?, ?, ?, ?);";

    private static final String SAVE_ORDER_DETAILS_QUERY =
            "INSERT INTO `order_details` (`order_id`, `product_id`, `quantity`) VALUES (?, ?, ?);";

    private static final String UPDATE_ORDER_STATE_BY_ID_QUERY =
            "UPDATE `orders` SET `order_status_id` = (SELECT `id` FROM `order_status` WHERE `state` = ?) " +
            "WHERE `id` = ?;";

    private static final String ORDER_ID = "id";
    private static final String ORDER_CREATE_DATE = "create_date";
    private static final String ORDER_AMOUNT = "amount";
    private static final String ORDER_USER_ID = "user_id";

    private static final String ORDER_STATUS_ID = "order_status_id";
    private static final String ORDER_STATUS_STATE = "state";

    private static final String CURRENCY_ID = "currency_id";
    private static final String CURRENCY_CODE = "code";

    private static final String ORDER_STATUS = "status";
    private static final String ORDER_CURRENCY = "currency";

    @Override
    public List<Order> findAllOrders() throws DAOException {
        List<Order> orders = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement =  null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(GET_ALL_ORDERS_LAZY_QUERY);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                OrderStatus orderStatus = new OrderStatus();
                orderStatus.setId(resultSet.getByte(ORDER_STATUS_ID));
                orderStatus.setState(resultSet.getString(ORDER_STATUS_STATE));

                Currency currency = new Currency();
                currency.setId(resultSet.getShort(CURRENCY_ID));
                currency.setCode(resultSet.getString(CURRENCY_CODE));

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

    @Override
    public List<Order> findAllOrdersByUser(User user) throws DAOException {
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

    @Override
    public boolean saveOrder(Order order) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement =  null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(SAVE_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setBigDecimal(1, order.getAmount());
            preparedStatement.setLong(2, order.getUser().getId());
            preparedStatement.setByte(3, order.getOrderStatus().getId());
            preparedStatement.setShort(4, order.getCurrency().getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            for (Product product : order.getProducts()) {
                preparedStatement = connection.prepareStatement(SAVE_ORDER_DETAILS_QUERY);
                preparedStatement.setLong(1, order.getId());
                preparedStatement.setLong(2, product.getId());
                preparedStatement.setInt(3, product.getQuantity());

                preparedStatement.executeUpdate();
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
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public boolean updateOrderStateById(Long orderId, OrderStatus orderStatus) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATE_BY_ID_QUERY);
            preparedStatement.setString(1, orderStatus.getState());
            preparedStatement.setLong(2, orderId);

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
