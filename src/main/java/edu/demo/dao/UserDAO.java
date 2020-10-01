package edu.demo.dao;

import edu.demo.bean.User;
import edu.demo.dao.exception.DAOException;

import java.util.List;

public interface UserDAO {
    User getUser(Long id) throws DAOException;
    void saveUser(User user) throws DAOException;
    List<User> getAllUsers() throws DAOException;
}
