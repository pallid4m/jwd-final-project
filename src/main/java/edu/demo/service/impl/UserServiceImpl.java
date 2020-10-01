package edu.demo.service.impl;

import edu.demo.bean.User;
import edu.demo.service.UserService;
import edu.demo.service.exception.ServiceException;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public User getUser(Long id) throws ServiceException {
        return null;
    }

    @Override
    public void saveUser(User user) throws ServiceException {

    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        return null;
    }
}
