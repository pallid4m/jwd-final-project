package by.estore.dao.impl;

import by.estore.dao.exception.DAOException;
import by.estore.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class OrderDAOImplTest {
    private static final Logger logger = LogManager.getLogger(OrderDAOImplTest.class);

    private OrderDAOImpl orderDAO = new OrderDAOImpl();

    @Test
    public void getAllOrdersTest() throws DAOException {
        List<Order> orders = orderDAO.findAllOrders();
        orders.forEach(logger::debug);
    }
}