package by.estore.service;

import by.estore.bean.User;
import by.estore.service.exception.AuthorizationException;
import by.estore.service.exception.ServiceException;
import by.estore.service.exception.UserAlreadyExistException;
import by.estore.service.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    boolean saveUser(User user) throws ServiceException;
    boolean deleteUserById(Long id) throws ServiceException;
    User getUserById(Long id) throws ServiceException;
    User getUserByEmail(String email) throws ServiceException;
    List<User> getAllUsers() throws ServiceException;

    boolean authorize(User user) throws ServiceException, AuthorizationException, UserNotFoundException;
    boolean register(User user) throws ServiceException, UserAlreadyExistException;
}
