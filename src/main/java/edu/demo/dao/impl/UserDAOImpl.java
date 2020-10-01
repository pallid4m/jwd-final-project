package edu.demo.dao.impl;

import edu.demo.bean.User;
import edu.demo.dao.UserDAO;
import edu.demo.dao.exception.DAOException;
import edu.demo.dao.impl.pool.ConnectionPool;

import java.sql.Connection;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public User getUser(Long id) throws DAOException {
        Connection connection = ConnectionPool.getConnection();
        return null;
    }

    @Override
    public void saveUser(User user) throws DAOException {

    }

    @Override
    public List<User> getAllUsers() throws DAOException {
        return null;
    }
}
