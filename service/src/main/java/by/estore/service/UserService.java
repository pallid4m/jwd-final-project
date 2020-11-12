package by.estore.service;

import by.estore.entity.Order;
import by.estore.entity.User;
import by.estore.dto.UserAuth;
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

    List<Order> getAllOrders(User user) throws ServiceException;

    User authorize(UserAuth userAuth) throws ServiceException, AuthorizationException, UserNotFoundException;
    User register(UserAuth userAuth) throws ServiceException, UserAlreadyExistException;

//    boolean updateEmailByUser(User user, String email) throws ServiceException;
//    boolean updatePassword(String password) throws ServiceException;
//    boolean updatePersonalData(String password) throws ServiceException;
}
