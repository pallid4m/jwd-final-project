package edu.demo.dao;

import edu.demo.dao.impl.UserDAOImpl;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO userDAO = new UserDAOImpl();

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
