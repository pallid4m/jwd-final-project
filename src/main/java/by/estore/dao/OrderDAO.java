package by.estore.dao;

import by.estore.bean.Order;
import by.estore.dao.exception.DAOException;

import java.util.List;

public interface OrderDAO {
    List<Order> getAllOrders() throws DAOException;
}
