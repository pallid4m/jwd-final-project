package by.estore.dao.impl;

import by.estore.dao.exception.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);

    private final String DB_PROPERTIES = "db.properties";

    private String driver;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private BlockingQueue<Connection> queue1;
    private BlockingQueue<Connection> queue2;

    public ConnectionPool() {
        try {
            Properties properties = new Properties();
            properties.load(this.getClass().getClassLoader().getResourceAsStream(DB_PROPERTIES));
            driver = properties.getProperty(DBParameter.DB_DRIVER);
            url = properties.getProperty(DBParameter.DB_URL);
            user = properties.getProperty(DBParameter.DB_USER);
            password = properties.getProperty(DBParameter.DB_PASSWORD);
            try {
                poolSize = Integer.parseInt(properties.getProperty(DBParameter.DB_POOL_SIZE));
            } catch (NumberFormatException e) {
                poolSize = 5;
            }
        } catch (IOException e) {
            logger.error("Pool properties loading is failed", e);
        }
    }

    public void init() throws PoolException {
        try {
            Class.forName(driver);
            queue1 = new ArrayBlockingQueue<>(poolSize);
            queue2 = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                // TODO: 06-Oct-20 PooledConnection class? hmm
                queue1.add(connection);
            }
        } catch (ClassNotFoundException e) {
            throw new PoolException("Can't find database driver class", e);
        } catch (SQLException e) {
            throw new PoolException("Failed to initialize the pool", e);
        }
    }

    public Connection takeConnection() throws PoolException {
        Connection connection = null;
        try {
            connection = queue1.take();
            queue2.add(connection);
        } catch (InterruptedException e) {
            throw new PoolException("Error connecting to the data source", e);
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public void closeConnection(Connection connection, Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        closeConnection(connection);
    }

    public void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        closeConnection(connection, statement);
    }

    public void close() {

    }

    public void dispose() {
        try {
            closeConnectionsQueue(queue2);
            closeConnectionsQueue(queue1);
        } catch (SQLException e) {
            logger.error("Error closing the connections");
        }
    }

    private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
        Connection connection = null;
        while ((connection = queue.poll()) != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            closeConnection(connection);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
