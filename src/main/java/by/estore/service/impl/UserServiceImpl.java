package by.estore.service.impl;

import by.estore.bean.Order;
import by.estore.bean.Product;
import by.estore.bean.User;
import by.estore.controller.dto.UserAuth;
import by.estore.dao.DAOFactory;
import by.estore.dao.exception.DAOException;
import by.estore.service.UserService;
import by.estore.service.exception.AuthorizationException;
import by.estore.service.exception.ServiceException;
import by.estore.service.exception.UserAlreadyExistException;
import by.estore.service.exception.UserNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

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
    public List<Order> getAllOrders(User user) throws ServiceException {
        List<Order> orders = null;

        try {
            orders = DAOFactory.getInstance().getUserDAO().getAllOrders(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        if (orders != null) {
            try {
                for (Order order : orders) {
                    List<Product> products = DAOFactory.getInstance().getProductDAO().getProductsByOrder(order);
                    order.setProducts(products);
                }
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }

        return orders;
    }

    @Override
    public User authorize(UserAuth userAuth) throws ServiceException, AuthorizationException, UserNotFoundException {
        User user = null;

        try {
            user = DAOFactory.getInstance().getUserDAO().getUserByEmail(userAuth.getEmail());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        if (user == null) {
            throw new UserNotFoundException("The user is not found.");
        }

        if (!BCrypt.checkpw(userAuth.getPassword(), user.getPassword())) {
            throw new AuthorizationException("Wrong password.");
        }

        return user;
    }

    @Override
    public User register(UserAuth userAuth) throws ServiceException, UserAlreadyExistException {
        User user = null;

        try {
            user = DAOFactory.getInstance().getUserDAO().getUserByEmail(userAuth.getEmail());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        if (user != null) {
            throw new UserAlreadyExistException("The user already exists.");
        }

        try {
            String hash = BCrypt.hashpw(userAuth.getPassword(), BCrypt.gensalt());
            userAuth.setPassword(hash);

            boolean isSaved = DAOFactory.getInstance().getUserDAO().saveUser(userAuthToUser(userAuth));

            if (isSaved) {
                user = DAOFactory.getInstance().getUserDAO().getUserByEmail(userAuth.getEmail());
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return user;
    }

    private User userAuthToUser(UserAuth userAuth) {
        return User.builder()
                .setEmail(userAuth.getEmail())
                .setPassword(userAuth.getPassword())
                .build();
    }
}
