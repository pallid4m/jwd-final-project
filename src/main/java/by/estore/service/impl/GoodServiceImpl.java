package by.estore.service.impl;

import by.estore.bean.Good;
import by.estore.dao.DAOFactory;
import by.estore.dao.exception.DAOException;
import by.estore.service.GoodService;
import by.estore.service.exception.ServiceException;

import java.util.List;

public class GoodServiceImpl implements GoodService {

    @Override
    public List<Good> getAllGoods() throws ServiceException {
        try {
            return DAOFactory.getInstance().getGoodDAO().getAllGoods();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Good getGoodById(Long id) throws ServiceException {
        try {
            return DAOFactory.getInstance().getGoodDAO().getGoodById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
