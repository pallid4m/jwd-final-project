package by.estore.service.impl;

import by.estore.dao.UserDAO;
import by.estore.dto.ProfileDto;
import by.estore.entity.User;
import by.estore.dto.UserAuth;
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

    private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

    @Override
    public boolean saveUser(User user) throws ServiceException {
        try {
            return userDAO.saveUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteUserById(Long id) throws ServiceException {
        try {
            return userDAO.deleteUserById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findUserById(Long id) throws ServiceException {
        try {
            return userDAO.findUserById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findUserByEmail(String email) throws ServiceException {
        try {
            return userDAO.findUserByEmail(email);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        try {
            return userDAO.findAllUsers();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User authorize(UserAuth userAuth) throws ServiceException, AuthorizationException, UserNotFoundException {
        User user = null;

        try {
            user = userDAO.findUserByEmail(userAuth.getEmail());
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
            user = userDAO.findUserByEmail(userAuth.getEmail());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        if (user != null) {
            throw new UserAlreadyExistException("The user already exists.");
        }

        try {
            String hash = BCrypt.hashpw(userAuth.getPassword(), BCrypt.gensalt());
            userAuth.setPassword(hash);

            boolean isSaved = userDAO.saveUser(userAuthToUser(userAuth));

            if (isSaved) {
                user = userDAO.findUserByEmail(userAuth.getEmail());
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public boolean updateUserEmail(User user, ProfileDto profileDto) throws ServiceException {
        user.setEmail(profileDto.getEmail());
        try {
            return userDAO.updateUserEmail(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateUserPassword(User user, ProfileDto profileDto) throws ServiceException {
        user.setPassword(profileDto.getNewPassword());
        try {
            return userDAO.updateUserPassword(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateUserData(User user, ProfileDto profileDto) throws ServiceException {
        user.setPhone(profileDto.getPhone());
        user.setFirstName(profileDto.getFirstName());
        user.setLastName(profileDto.getLastName());
        try {
            return userDAO.updateUserData(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private User userAuthToUser(UserAuth userAuth) {
        return User.builder()
                .setEmail(userAuth.getEmail())
                .setPassword(userAuth.getPassword())
                .build();
    }
}
