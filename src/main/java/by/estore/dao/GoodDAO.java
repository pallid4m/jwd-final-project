package by.estore.dao;

import by.estore.bean.Good;
import by.estore.dao.exception.DAOException;

import java.util.List;

public interface GoodDAO {
    List<Good> getAllGoods() throws DAOException;
    Good getGoodById(Long id) throws DAOException;
}
