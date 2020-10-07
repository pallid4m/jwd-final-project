package by.estore.dao;

import by.estore.bean.User;
import by.estore.dao.exception.DAOException;

import java.util.List;

public interface UserDAO {
    boolean saveUser(User user) throws DAOException;
    boolean deleteUserById(Long id) throws DAOException;
    User getUserById(Long id) throws DAOException;
    User getUserByEmail(String email) throws DAOException;
    List<User> getAllUsers() throws DAOException;
}
