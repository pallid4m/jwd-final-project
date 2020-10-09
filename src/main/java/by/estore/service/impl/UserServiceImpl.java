package by.estore.service.impl;

import by.estore.bean.User;
import by.estore.dao.DAOFactory;
import by.estore.dao.exception.DAOException;
import by.estore.service.UserService;
import by.estore.service.exception.AuthorizationException;
import by.estore.service.exception.ServiceException;
import by.estore.service.exception.UserAlreadyExistException;
import by.estore.service.exception.UserNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public boolean saveUser(User user) throws ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().saveUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteUserById(Long id) throws ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().deleteUserById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUserById(Long id) throws ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().getUserById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUserByEmail(String email) throws ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().getUserByEmail(email);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().getAllUsers();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean authorize(User user) throws ServiceException, AuthorizationException, UserNotFoundException {
        User expectedUser = null;

        try {
            expectedUser = DAOFactory.getInstance().getUserDAO().getUserByEmail(user.getEmail());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        if (expectedUser == null) {
            throw new UserNotFoundException("The user is not found.");
        }

        // TODO: 08-Oct-20  to compare the passwords
        if (!user.getPassword().equals(expectedUser.getPassword())) {
            throw new AuthorizationException("Wrong password.");
        }

        return true;
    }

    @Override
    public boolean register(User user) throws ServiceException, UserAlreadyExistException {
        User expectedUser = null;

        try {
            expectedUser = DAOFactory.getInstance().getUserDAO().getUserByEmail(user.getEmail());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        if (expectedUser != null) {
            throw new UserAlreadyExistException("The user already exists.");
        }

        try {
            // TODO: 08-Oct-20  to hash a password
            DAOFactory.getInstance().getUserDAO().saveUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return true;
    }
}
