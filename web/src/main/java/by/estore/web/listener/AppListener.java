package by.estore.web.listener;

import by.estore.dao.exception.ConnectionPoolException;
import by.estore.dao.impl.pool.ConnectionPool;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;

public class AppListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(AppListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        initDBPool();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        closeDBPool();
    }

    private void initDBPool() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            connectionPool.init();
        } catch (ConnectionPoolException e) {
            logger.error(e);
        }
        logger.info("The database pool is initialized");
    }

    private void closeDBPool() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.dispose();

        try {
            Iterator<Driver> driverIterator = DriverManager.getDrivers().asIterator();
            while (driverIterator.hasNext()) {
                Driver driver = driverIterator.next();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException e) {
            logger.error(e);
        }

        AbandonedConnectionCleanupThread.checkedShutdown();

        logger.info("The database pool has been destroyed");
    }
}
