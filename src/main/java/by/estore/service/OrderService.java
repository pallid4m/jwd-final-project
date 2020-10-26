package by.estore.service;

import by.estore.bean.Order;
import by.estore.service.exception.ServiceException;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders() throws ServiceException;
}
