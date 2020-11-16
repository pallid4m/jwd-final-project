package by.estore.service;

import by.estore.dto.ProfileDto;
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
    User findUserById(Long id) throws ServiceException;
    User findUserByEmail(String email) throws ServiceException;
    List<User> findAllUsers() throws ServiceException;

    User authorize(UserAuth userAuth) throws ServiceException, AuthorizationException, UserNotFoundException;
    User register(UserAuth userAuth) throws ServiceException, UserAlreadyExistException;

    boolean updateUserEmail(User user, ProfileDto profileDto) throws ServiceException;
    boolean updateUserPassword(User user, ProfileDto profileDto) throws ServiceException;
    boolean updateUserData(User user, ProfileDto profileDto) throws ServiceException;
}
