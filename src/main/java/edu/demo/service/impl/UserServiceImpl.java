package edu.demo.service.impl;

import edu.demo.bean.AuthDetail;
import edu.demo.bean.User;
import edu.demo.dao.DAOFactory;
import edu.demo.dao.UserDAO;
import edu.demo.dao.exception.DAOException;
import edu.demo.service.UserService;
import edu.demo.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

    @Override
    public User authorization(AuthDetail data) throws ServiceException {
        User user;
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            user = userDAO.authorization(data);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public boolean registration(AuthDetail data) throws ServiceException {
        boolean registration = false;

        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        try {
            registration = userDAO.registration(data);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return registration;
    }
}
