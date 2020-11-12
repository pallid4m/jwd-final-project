package by.estore.service;

import by.estore.entity.Order;
import by.estore.service.exception.ServiceException;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders() throws ServiceException;
    Order generateOrder(Order order) throws ServiceException;
    Order removeProductById(Order order, Long productId) throws ServiceException;
    Order changeProductCountById(Order order, Long productId, int count) throws ServiceException;
    boolean saveOrder(Order order) throws ServiceException;
}
