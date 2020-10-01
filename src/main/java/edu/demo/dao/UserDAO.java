package edu.demo.dao;

import edu.demo.bean.AuthDetail;
import edu.demo.bean.User;
import edu.demo.dao.exception.DAOException;

public interface UserDAO {
    User authorization(AuthDetail data) throws DAOException;
    boolean registration(AuthDetail data) throws DAOException;
}
