package edu.demo.service;

import edu.demo.bean.AuthDetail;
import edu.demo.bean.User;
import edu.demo.service.exception.ServiceException;

public interface UserService {
    User authorization(AuthDetail data) throws ServiceException;
    boolean registration(AuthDetail data) throws ServiceException;
}
