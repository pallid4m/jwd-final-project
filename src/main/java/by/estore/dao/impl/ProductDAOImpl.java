package by.estore.dao.impl;

import by.estore.bean.Category;
import by.estore.bean.Order;
import by.estore.bean.Product;
import by.estore.dao.ProductDAO;
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

public class ProductDAOImpl implements ProductDAO {
    private static final Logger logger = LogManager.getLogger(ProductDAOImpl.class);

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String GET_ALL_PRODUCTS_QUERY = "SELECT p.`id`, p.`name` AS `product_name`, p.`description`, p.`image`, p.`category_id`, c.`name` AS `category_name` FROM `products` AS p JOIN `categories` AS c ON p.`category_id` = c.`id`;";
    private static final String GET_PRODUCTS_BY_CATEGORY_QUERY = "SELECT p.`id`, p.`name` AS `product_name`, p.`description`, p.`image`, p.`category_id` FROM `products` AS p JOIN `categories` AS c ON p.`category_id` = c.`id` WHERE c.`name` = ?;";
    private static final String GET_PRODUCTS_BY_CATEGORY_LIMIT_OFFSET_QUERY = "SELECT p.`id`, p.`name` AS `product_name`, p.`description`, p.`image`, p.`category_id` FROM `products` AS p JOIN `categories` AS c ON p.`category_id` = c.`id` WHERE c.`name` = ? LIMIT ? OFFSET ?;";
    private static final String GET_PRODUCT_BY_ID = "SELECT p.`name` AS `product_name`, p.`description`, p.`image`, p.`category_id`, c.`name` AS `category_name` FROM `products` AS p JOIN `categories` AS c ON p.`category_id` = c.`id` WHERE p.`id` = ?;";

    private static final String GET_PRODUCTS_BY_ORDER = "SELECT p.`id`, p.`name` AS `product_name`, p.`description`, p.`image` FROM `products` AS p JOIN `order_details` AS od ON od.`order_id` = ? AND od.`product_id` = p.`id`;";

    private static final String PRODUCT_ID = "id";
    private static final String PRODUCT_NAME = "product_name";
    private static final String PRODUCT_DESCRIPTION = "description";
    private static final String PRODUCT_IMAGE = "image";
    private static final String PRODUCT_PRICE = "price";
    private static final String CATEGORY_ID = "id";
    private static final String CATEGORY_NAME = "category_name";

    @Override
    public List<Product> getAllProducts() throws DAOException {
        List<Product> products = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement =  null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(GET_ALL_PRODUCTS_QUERY);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Category category = new Category();
                category.setId(resultSet.getShort(CATEGORY_ID));
                category.setName(resultSet.getString(CATEGORY_NAME));

                Product product = Product.builder()
                        .setId(resultSet.getLong(PRODUCT_ID))
                        .setName(resultSet.getString(PRODUCT_NAME))
                        .setDescription(resultSet.getString(PRODUCT_DESCRIPTION))
                        .setImage(resultSet.getString(PRODUCT_IMAGE))
                        .setCategory(category)
                        .build();

                products.add(product);
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

        return products;
    }

    @Override
    public List<Product> getProductsByCategory(Category category) throws DAOException {
        List<Product> products = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement =  null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(GET_PRODUCTS_BY_CATEGORY_QUERY);
            preparedStatement.setString(1, category.getName());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                category.setId(resultSet.getShort(CATEGORY_ID));

                Product product = Product.builder()
                        .setId(resultSet.getLong(PRODUCT_ID))
                        .setName(resultSet.getString(PRODUCT_NAME))
                        .setDescription(resultSet.getString(PRODUCT_DESCRIPTION))
                        .setImage(resultSet.getString(PRODUCT_IMAGE))
                        .setCategory(category)
                        .build();

                products.add(product);
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

        return products;
    }

    @Override
    public List<Product> getProductsByCategory(Category category, int limit, int offset) throws DAOException {
        List<Product> products = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement =  null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(GET_PRODUCTS_BY_CATEGORY_LIMIT_OFFSET_QUERY);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                category.setId(resultSet.getShort(CATEGORY_ID));

                Product product = Product.builder()
                        .setId(resultSet.getLong(PRODUCT_ID))
                        .setName(resultSet.getString(PRODUCT_NAME))
                        .setDescription(resultSet.getString(PRODUCT_DESCRIPTION))
                        .setImage(resultSet.getString(PRODUCT_IMAGE))
                        .setCategory(category)
                        .build();

                products.add(product);
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

        return products;
    }

    @Override
    public List<Product> getProductsByOrder(Order order) throws DAOException {
        List<Product> products = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement =  null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(GET_PRODUCTS_BY_ORDER);
            preparedStatement.setLong(1, order.getId());

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Product product = Product.builder()
                        .setId(resultSet.getLong(PRODUCT_ID))
                        .setName(resultSet.getString(PRODUCT_NAME))
                        .setDescription(resultSet.getString(PRODUCT_DESCRIPTION))
                        .setImage(resultSet.getString(PRODUCT_IMAGE))
                        .build();

                products.add(product);
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

        return products;
    }

    @Override
    public Product getProductById(Long id) throws DAOException {
        Product product = null;

        Connection connection = null;
        PreparedStatement preparedStatement =  null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_ID);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Category category = new Category();
                category.setId(resultSet.getShort(CATEGORY_ID));
                category.setName(resultSet.getString(CATEGORY_NAME));

                product = Product.builder()
                        .setId(id)
                        .setName(resultSet.getString(PRODUCT_NAME))
                        .setDescription(resultSet.getString(PRODUCT_DESCRIPTION))
                        .setImage(resultSet.getString(PRODUCT_IMAGE))
                        .setCategory(category)
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

        return product;
    }
}
