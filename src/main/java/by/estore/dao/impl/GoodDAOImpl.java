package by.estore.dao.impl;

import by.estore.bean.Category;
import by.estore.bean.Good;
import by.estore.dao.GoodDAO;
import by.estore.dao.exception.ConnectionPoolException;
import by.estore.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodDAOImpl implements GoodDAO {
    private static final Logger logger = LogManager.getLogger(GoodDAOImpl.class);

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final String GET_ALL_GOODS_QUERY = "SELECT g.`id`, g.`name` AS `good_name`, g.`description`, g.`price`, g.`category_id`, c.`name` AS `category_name` FROM `goods` AS g JOIN `categories` AS c ON g.`category_id` = c.`id`";
    private final String GET_GOOD_BY_ID = "SELECT g.`name` AS `good_name`, g.`description`, g.`price`, g.`category_id`, c.`name` AS `category_name` FROM `goods` AS g JOIN `categories` AS c ON g.`category_id` = c.`id` WHERE g.`id` = ?";

    private static final String GOOD_ID = "id";
    private static final String GOOD_NAME = "good_name";
    private static final String GOOD_DESCRIPTION = "description";
    private static final String GOOD_PRICE = "price";
    private static final String CATEGORY_ID = "id";
    private static final String CATEGORY_NAME = "category_name";

    @Override
    public List<Good> getAllGoods() throws DAOException {
        List<Good> goods = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement =  null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(GET_ALL_GOODS_QUERY);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Good good = new Good();
                good.setId(resultSet.getLong(GOOD_ID));
                good.setName(resultSet.getString(GOOD_NAME));
                good.setDescription(resultSet.getString(GOOD_DESCRIPTION));
                Category category = new Category();
                category.setId(resultSet.getShort(CATEGORY_ID));
                category.setName(resultSet.getString(CATEGORY_NAME));
                good.setCategory(category);
                goods.add(good);
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

        return goods;
    }

    @Override
    public Good getGoodById(Long id) throws DAOException {
        Good good = null;

        Connection connection = null;
        PreparedStatement preparedStatement =  null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(GET_GOOD_BY_ID);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                good = new Good();
                good.setId(id);
                good.setName(resultSet.getString(GOOD_NAME));
                good.setDescription(resultSet.getString(GOOD_DESCRIPTION));
                Category category = new Category();
                category.setId(resultSet.getShort(CATEGORY_ID));
                category.setName(resultSet.getString(CATEGORY_NAME));
                good.setCategory(category);
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

        return good;
    }
}
