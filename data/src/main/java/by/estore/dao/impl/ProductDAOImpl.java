package by.estore.dao.impl;

import by.estore.entity.Category;
import by.estore.entity.Currency;
import by.estore.entity.Order;
import by.estore.entity.Product;
import by.estore.dao.ProductDAO;
import by.estore.dao.exception.ConnectionPoolException;
import by.estore.dao.impl.pool.ConnectionPool;
import by.estore.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ProductDAOImpl implements ProductDAO {
    private static final Logger logger = LogManager.getLogger(ProductDAOImpl.class);

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String GET_ALL_PRODUCTS_QUERY =
            "SELECT p.`id`, p.`name` AS `product_name`, p.`description`, p.`price`, p.`image`, p.`category_id`, cat.`name` AS `category_name`, p.`currency_id`, cur.`code` FROM `products` AS p " +
            "JOIN `categories` AS cat ON p.`category_id` = cat.`id` " +
            "JOIN `currencies` AS cur ON p.`currency_id` = cur.`id`;";

    private static final String GET_PRODUCTS_BY_CATEGORY_QUERY =
            "SELECT p.`id`, p.`name` AS `product_name`, p.`description`, p.`price`, p.`image`, p.`category_id`, p.`currency_id`, cur.`code` FROM `products` AS p " +
            "JOIN `categories` AS cat ON p.`category_id` = cat.`id` " +
            "JOIN `currencies` AS cur ON p.`currency_id` = cur.`id` " +
            "WHERE cat.`name` = ?;";

    private static final String GET_PRODUCTS_BY_CATEGORY_LIMIT_OFFSET_QUERY =
            "SELECT p.`id`, p.`name` AS `product_name`, p.`description`, p.`price`, p.`image`, p.`category_id`, p.`currency_id`, cur.`code` FROM `products` AS p " +
            "JOIN `categories` AS cat ON p.`category_id` = cat.`id` " +
            "JOIN `currencies` AS cur ON p.`currency_id` = cur.`id` " +
            "WHERE cat.`name` = ? LIMIT ? OFFSET ?;";

    private static final String GET_PRODUCT_BY_ID_QUERY =
            "SELECT p.`id`, p.`name` AS `product_name`, p.`description`, p.`price`, p.`image`, p.`category_id`, cat.`name` AS `category_name`, p.`currency_id`, cur.`code` FROM `products` AS p " +
            "JOIN `categories` AS cat ON p.`category_id` = cat.`id` " +
            "JOIN `currencies` AS cur ON p.`currency_id` = cur.`id` " +
            "WHERE p.`id` = ?;";

    private static final String GET_PRODUCTS_BY_ORDER_QUERY =
            "SELECT p.`id`, p.`name` AS `product_name`, p.`description`, p.`price`, p.`image`, p.`category_id`, cat.`name` AS `category_name`, p.`currency_id`, cur.`code` FROM `products` AS p " +
            "JOIN `order_details` AS od ON od.`order_id` = ? AND od.`product_id` = p.`id` " +
            "JOIN `categories` AS cat ON p.`category_id` = cat.`id` " +
            "JOIN `currencies` AS cur ON p.`currency_id` = cur.`id`;";
    
    private static final String GET_PRODUCT_COUNT_QUERY =
            "SELECT COUNT(*) AS `product_count` FROM `products`;";

    private static final String PRODUCT_ID = "id";
    private static final String PRODUCT_NAME = "product_name";
    private static final String PRODUCT_DESCRIPTION = "description";
    private static final String PRODUCT_PRICE = "price";
    private static final String PRODUCT_IMAGE = "image";

    private static final String CATEGORY_ID = "category_id";
    private static final String CATEGORY_NAME = "category_name";

    private static final String CURRENCY_ID = "currency_id";
    private static final String CURRENCY_CODE = "code";

    private static final String PRODUCT_COUNT = "product_count";

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

                Currency currency = new Currency();
                currency.setId(resultSet.getShort(CURRENCY_ID));
                currency.setCode(resultSet.getString(CURRENCY_CODE));

                Product product = Product.builder()
                        .setId(resultSet.getLong(PRODUCT_ID))
                        .setName(resultSet.getString(PRODUCT_NAME))
                        .setDescription(resultSet.getString(PRODUCT_DESCRIPTION))
                        .setPrice(resultSet.getBigDecimal(PRODUCT_PRICE))
                        .setImage(resultSet.getString(PRODUCT_IMAGE))
                        .setCategory(category)
                        .setCurrency(currency)
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

                Currency currency = new Currency();
                currency.setId(resultSet.getShort(CURRENCY_ID));
                currency.setCode(resultSet.getString(CURRENCY_CODE));

                Product product = Product.builder()
                        .setId(resultSet.getLong(PRODUCT_ID))
                        .setName(resultSet.getString(PRODUCT_NAME))
                        .setDescription(resultSet.getString(PRODUCT_DESCRIPTION))
                        .setPrice(resultSet.getBigDecimal(PRODUCT_PRICE))
                        .setImage(resultSet.getString(PRODUCT_IMAGE))
                        .setCategory(category)
                        .setCurrency(currency)
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

                Currency currency = new Currency();
                currency.setId(resultSet.getShort(CURRENCY_ID));
                currency.setCode(resultSet.getString(CURRENCY_CODE));

                Product product = Product.builder()
                        .setId(resultSet.getLong(PRODUCT_ID))
                        .setName(resultSet.getString(PRODUCT_NAME))
                        .setDescription(resultSet.getString(PRODUCT_DESCRIPTION))
                        .setPrice(resultSet.getBigDecimal(PRODUCT_PRICE))
                        .setImage(resultSet.getString(PRODUCT_IMAGE))
                        .setCategory(category)
                        .setCurrency(currency)
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
    public Set<Product> getProductsByOrder(Order order) throws DAOException {
        Set<Product> products = new LinkedHashSet<>();

        Connection connection = null;
        PreparedStatement preparedStatement =  null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(GET_PRODUCTS_BY_ORDER_QUERY);
            preparedStatement.setLong(1, order.getId());

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getShort(CATEGORY_ID));
                category.setName(resultSet.getString(CATEGORY_NAME));

                Currency currency = new Currency();
                currency.setId(resultSet.getShort(CURRENCY_ID));
                currency.setCode(resultSet.getString(CURRENCY_CODE));

                Product product = Product.builder()
                        .setId(resultSet.getLong(PRODUCT_ID))
                        .setName(resultSet.getString(PRODUCT_NAME))
                        .setDescription(resultSet.getString(PRODUCT_DESCRIPTION))
                        .setPrice(resultSet.getBigDecimal(PRODUCT_PRICE))
                        .setImage(resultSet.getString(PRODUCT_IMAGE))
                        .setCategory(category)
                        .setCurrency(currency)
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
    public long getProductCount() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement =  null;
        ResultSet resultSet = null;

        long productCount = 0;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(GET_PRODUCT_COUNT_QUERY);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                productCount = resultSet.getLong(PRODUCT_COUNT);
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

        return productCount;
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

            preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_ID_QUERY);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getShort(CATEGORY_ID));
                category.setName(resultSet.getString(CATEGORY_NAME));

                Currency currency = new Currency();
                currency.setId(resultSet.getShort(CURRENCY_ID));
                currency.setCode(resultSet.getString(CURRENCY_CODE));

                product = Product.builder()
                        .setId(id)
                        .setName(resultSet.getString(PRODUCT_NAME))
                        .setDescription(resultSet.getString(PRODUCT_DESCRIPTION))
                        .setPrice(resultSet.getBigDecimal(PRODUCT_PRICE))
                        .setImage(resultSet.getString(PRODUCT_IMAGE))
                        .setCategory(category)
                        .setCurrency(currency)
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
