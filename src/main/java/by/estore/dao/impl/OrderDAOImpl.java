package by.estore.dao.impl;

import by.estore.bean.Order;
import by.estore.dao.OrderDAO;
import by.estore.dao.exception.ConnectionPoolException;
import by.estore.dao.exception.DAOException;
import by.estore.dao.impl.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private static final Logger logger = LogManager.getLogger(OrderDAOImpl.class);

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String GET_ALL_ORDERS_LAZY_QUERY =
            "SELECT `id`, `create_date`, `status` FROM `orders`;";

    private static final String ORDER_ID = "id";
    private static final String ORDER_CREATE_DATE = "create_date";
    private static final String ORDER_STATUS = "status";
    private static final String ORDER_AMOUNT = "amount";

    @Override
    public List<Order> getAllOrders() throws DAOException {
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
                Order order = Order.builder()
                        .setId(resultSet.getLong(ORDER_ID))
                        .setCreateDate(resultSet.getObject(ORDER_CREATE_DATE, LocalDateTime.class))
                        .setStatus(resultSet.getString(ORDER_STATUS))
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
