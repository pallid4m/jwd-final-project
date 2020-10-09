package by.estore.dao;

import by.estore.dao.impl.GoodDAOImpl;
import by.estore.dao.impl.UserDAOImpl;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO userDAO = new UserDAOImpl();
    private final GoodDAO goodDAO = new GoodDAOImpl();

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public GoodDAO getGoodDAO() {
        return goodDAO;
    }
}
