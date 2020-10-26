package by.estore.dao;

import by.estore.dao.impl.OrderDAOImpl;
import by.estore.dao.impl.ProductDAOImpl;
import by.estore.dao.impl.UserDAOImpl;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO userDAO = new UserDAOImpl();
    private final ProductDAO productDAO = new ProductDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }
}
