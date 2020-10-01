package edu.demo.service;

import edu.demo.bean.User;
import edu.demo.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    User getUser(Long id) throws ServiceException;
    void saveUser(User user) throws ServiceException;
    List<User> getAllUsers() throws ServiceException;
}
