package by.estore.service.impl;

import by.estore.bean.Order;
import by.estore.dao.DAOFactory;
import by.estore.dao.exception.DAOException;
import by.estore.service.OrderService;
import by.estore.service.exception.ServiceException;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Override
    public List<Order> getAllOrders() throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderDAO().getAllOrders();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
