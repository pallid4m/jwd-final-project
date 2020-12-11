package by.estore.service.impl;

import by.estore.dao.DAOFactory;
import by.estore.dao.OrderDAO;
import by.estore.dao.ProductDAO;
import by.estore.dao.UserDAO;
import by.estore.dao.exception.DAOException;
import by.estore.entity.Order;
import by.estore.entity.OrderStatus;
import by.estore.entity.Product;
import by.estore.entity.User;
import by.estore.service.AdminService;
import by.estore.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminServiceImpl implements AdminService {
    private static final Logger logger = LogManager.getLogger(AdminServiceImpl.class);

    private final ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
    private final OrderDAO orderDAO  = DAOFactory.getInstance().getOrderDAO();
    private final UserDAO userDAO  = DAOFactory.getInstance().getUserDAO();

    @Override
    public boolean addProduct(Product product) throws ServiceException {
        try {
            return productDAO.addProduct(product);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    // TODO: 16-Nov-20 blacklist
    @Override
    public boolean addUserToBlackListById(Long userId) throws ServiceException {
        return false;
    }

    @Override
    public boolean removeUserFromBlackListById(Long userId) throws ServiceException {
        return false;
    }

    @Override
    public boolean updateOrderStateById(Long orderId, OrderStatus orderStatus) throws ServiceException {
        try {
            return orderDAO.updateOrderStateById(orderId, orderStatus);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findUserByOrderId(Long orderId) throws ServiceException {
        try {
            return userDAO.findUserByOrderId(orderId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
