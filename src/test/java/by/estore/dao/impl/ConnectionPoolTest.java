package by.estore.dao.impl;

import by.estore.bean.User;
import by.estore.service.exception.ServiceException;
import by.estore.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ConnectionPoolTest {
    private static final Logger logger = LogManager.getLogger(ConnectionPoolTest.class);

    private ConnectionPool pool = new ConnectionPool();
    UserServiceImpl userService = new UserServiceImpl();

    @Test
    public void getConnectionTest() throws SQLException, ServiceException {
        Connection connection = pool.getConnection();
        List<User> userList = userService.getAllUsers();
        userList.forEach(logger::debug);
        assertNotNull(connection);
    }
}